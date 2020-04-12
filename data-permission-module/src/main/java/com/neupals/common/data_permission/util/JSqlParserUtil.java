package com.neupals.common.data_permission.util;

import com.neupals.common.dto.RestException;
import com.neupals.common.dto.ResultCode;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.JSQLParserException;
import net.sf.jsqlparser.expression.BinaryExpression;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.StringValue;
import net.sf.jsqlparser.expression.operators.conditional.AndExpression;
import net.sf.jsqlparser.expression.operators.conditional.OrExpression;
import net.sf.jsqlparser.expression.operators.relational.EqualsTo;
import net.sf.jsqlparser.expression.operators.relational.ExpressionList;
import net.sf.jsqlparser.expression.operators.relational.MultiExpressionList;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.schema.Column;
import net.sf.jsqlparser.schema.Table;
import net.sf.jsqlparser.statement.Statement;
import net.sf.jsqlparser.statement.create.table.ColDataType;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import net.sf.jsqlparser.statement.delete.Delete;
import net.sf.jsqlparser.statement.insert.Insert;
import net.sf.jsqlparser.statement.select.PlainSelect;
import net.sf.jsqlparser.statement.select.Select;
import net.sf.jsqlparser.statement.select.SubSelect;
import net.sf.jsqlparser.statement.update.Update;
import net.sf.jsqlparser.util.TablesNamesFinder;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

/**
 * sql解析的工具类，可以在where中添加字段
 *
 */
@Slf4j
public class JSqlParserUtil {

    //数据权限校验的字段
    private String addColumn;

    //数据权限校验的值
    private String addValue;


    /**
     * 添加租户id条件newMs
     *
     * @param sql
     * @return
     * @throws JSQLParserException
     */
    public String addWhere(String sql) throws Exception {
        Statement stmt = CCJSqlParserUtil.parse(sql);
        if (stmt instanceof Insert) {
            //获得Update对象
            Insert insert = (Insert) stmt;
            insert.getColumns().add(new Column(getColumn()));

            if (insert.getItemsList() instanceof MultiExpressionList){
                for (ExpressionList expressionList : ((MultiExpressionList) insert.getItemsList()).getExprList()) {
                    addTenantValue(expressionList);
                }
            }else {
                addTenantValue(((ExpressionList) insert.getItemsList()));
            }
            return insert.toString();
        }

        if (stmt instanceof Delete) {
            //获得Delete对象
            Delete deleteStatement = (Delete) stmt;
            Expression where = deleteStatement.getWhere();
            if (where instanceof BinaryExpression) {
                EqualsTo equalsTo = new EqualsTo();
                equalsTo.setLeftExpression(new Column(getColumn()));
                equalsTo.setRightExpression(new StringValue(getValue()));
                AndExpression andExpression = new AndExpression(equalsTo, where);
                deleteStatement.setWhere(andExpression);
            }
            return deleteStatement.toString();
        }

        if (stmt instanceof Update) {
            //获得Update对象
            Update updateStatement = (Update) stmt;
            //获得where条件表达式
            Expression where = updateStatement.getWhere();
            if (where instanceof BinaryExpression) {
                // 针对是否含where条件做不同处理
                if (updateStatement.getWhere() != null) {
                    updateStatement.setWhere(addAndExpression(stmt, getColumn(), updateStatement.getWhere()));
                } else {
                    EqualsTo equalsTo = new EqualsTo();
                    equalsTo.setLeftExpression(new Column(getColumn()));
                    equalsTo.setRightExpression(new StringValue(getValue()));
                    updateStatement.setWhere(equalsTo);
                }
            }
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List<String> tableList = tablesNamesFinder.getTableList(stmt);
            // 针对数据库连接测试没有表名的情况处理【select 'x';select 1什么的】
            if (tableList.size() == 0) {
                return updateStatement.toString();
            }
            for (String table : tableList) {
                if (updateStatement.getWhere() != null) {
                    updateStatement.setWhere(addAndExpression(stmt, table, updateStatement.getWhere()));
                } else {
                    throw new Exception("update语句不能没有where条件:" + sql + Arrays.toString(Thread.currentThread().getStackTrace()));
                }
            }
            return updateStatement.toString();
        }

        if (stmt instanceof Select) {
            Select select = (Select) stmt;
            PlainSelect ps = (PlainSelect) select.getSelectBody();
            TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
            List<String> tableList = tablesNamesFinder.getTableList(select);
            if (tableList.size() == 0) {
                return select.toString();
            }
            for (String table : tableList) {
                if (ps.getWhere() != null) {
                    AndExpression where = addAndExpression(stmt, table, ps.getWhere());
                    // form 和 join 中加载的表
                    if (where != null) {
                        ps.setWhere(where);
                    } else {
                        //子查询中的表
                        findSubSelect(stmt, ps.getWhere());
                    }
                } else {
                    ps.setWhere(addEqualsTo(stmt, table));
                }
            }
            return select.toString();
        }

        if (stmt instanceof CreateTable) {
            CreateTable createTable = (CreateTable) stmt;

            ColumnDefinition columnDefinition = new ColumnDefinition();
            columnDefinition.setColumnName("tenantId");
            ColDataType colDataType = new ColDataType();
            colDataType.setDataType("varchar(255)");
            columnDefinition.setColDataType(colDataType);
            createTable.getColumnDefinitions().add(columnDefinition);
            return createTable.toString();
        }

        return null;
    }



    /**
     * 多条件情况下，使用AndExpression给where条件加上tenantid条件
     *
     * @param table
     * @param where
     * @return
     * @throws Exception
     */
    private AndExpression addAndExpression(Statement stmt, String table, Expression where) throws Exception {
        EqualsTo equalsTo = addEqualsTo(stmt, table);
        if (equalsTo != null) {
            return new AndExpression(equalsTo, where);
        } else {
            return null;
        }
    }

    /**
     * 创建一个 EqualsTo相同判断 条件
     *
     * @param stmt  查询对象
     * @param table 表名
     * @return “A=B” 单个where条件表达式
     * @throws Exception
     */
    private EqualsTo addEqualsTo(Statement stmt, String table) throws Exception {
        EqualsTo equalsTo = new EqualsTo();
        String aliasName;
        aliasName = getTableAlias(stmt, table);
        if (aliasName != null) {
            equalsTo.setLeftExpression(new Column(aliasName + '.' + getColumn()));
            equalsTo.setRightExpression(new StringValue(getValue()));
            return equalsTo;
        } else {
            return null;
        }
    }

    /**
     * 获取sql送指定表的别名你，没有别名则返回原表名 如果表名不存在返回null
     * 【仅查询from和join 不含 IN 子查询中的表 】
     *
     * @param stmt
     * @param tableName
     * @return
     */
    private String getTableAlias(Statement stmt, String tableName) {
        String alias = null;

        // 插入不做处理
        if (stmt instanceof Insert) {
            return tableName;
        }

        if (stmt instanceof Delete) {
            //获得Delete对象
            Delete deleteStatement = (Delete) stmt;

            if ((deleteStatement.getTable()).getName().equalsIgnoreCase(tableName)) {
                alias = deleteStatement.getTable().getAlias() != null ? deleteStatement.getTable().getAlias().getName() : tableName;
            }
        }

        if (stmt instanceof Update) {
            //获得Update对象
            Update updateStatement = (Update) stmt;

            if (updateStatement.getTable().getName().equalsIgnoreCase(tableName)) {
                alias = updateStatement.getTable().getAlias() != null ? updateStatement.getTable().getAlias().getName() : tableName;
            }
        }

        if (stmt instanceof Select) {
            Select select = (Select) stmt;

            PlainSelect ps = (PlainSelect) select.getSelectBody();

            // 判断主表的别名
            if(ps.getFromItem() instanceof SubSelect){
                alias = ps.getFromItem().getAlias() != null ? ps.getFromItem().getAlias().getName():"";
            }else {
                if (((Table) ps.getFromItem()).getName().equalsIgnoreCase(tableName)) {
                    alias = ps.getFromItem().getAlias() != null ? ps.getFromItem().getAlias().getName() : tableName;
                }
            }

        }
        return alias;
    }

    /**
     * 针对子查询中的表别名查询
     *
     * @param subSelect
     * @param tableName
     * @return
     */
    private String getTableAlias(SubSelect subSelect, String tableName) {
        PlainSelect ps = (PlainSelect) subSelect.getSelectBody();
        // 判断主表的别名
        String alias = null;
        if (((Table) ps.getFromItem()).getName().equalsIgnoreCase(tableName)) {
            if (ps.getFromItem().getAlias() != null) {
                alias = ps.getFromItem().getAlias().getName();
            } else {
                alias = tableName;
            }
        }
        return alias;
    }

    /**
     * 递归处理 子查询中的tenantid-where
     *
     * @param stmt  sql查询对象
     * @param where 当前sql的where条件 where为AndExpression或OrExpression的实例，解析其中的rightExpression，然后检查leftExpression是否为空，
     *              不为空则是AndExpression或OrExpression，再次解析其中的rightExpression
     *              注意tenantid-where是加在子查询上的
     */
    void findSubSelect(Statement stmt, Expression where) throws Exception {

        // and 表达式
        if (where instanceof AndExpression) {
            AndExpression andExpression = (AndExpression) where;
            if (andExpression.getRightExpression() instanceof SubSelect) {
                SubSelect subSelect = (SubSelect) andExpression.getRightExpression();
                doSelect(stmt, subSelect);
            }
            if (andExpression.getLeftExpression() != null) {
                findSubSelect(stmt, andExpression.getLeftExpression());
            }
        } else if (where instanceof OrExpression) {
            //  or表达式
            OrExpression orExpression = (OrExpression) where;
            if (orExpression.getRightExpression() instanceof SubSelect) {
                SubSelect subSelect = (SubSelect) orExpression.getRightExpression();
                doSelect(stmt, subSelect);
            }
            if (orExpression.getLeftExpression() != null) {
                findSubSelect(stmt, orExpression.getLeftExpression());
            }
        }
    }

    /**
     * 处理select 和 subSelect
     *
     * @param stmt   查询对象
     * @param select
     * @return
     * @throws Exception
     */
    Expression doSelect(Statement stmt, Expression select) throws Exception {
        PlainSelect ps = null;
        boolean hasSubSelect = false;

        if (select instanceof SubSelect) {
            ps = (PlainSelect) ((SubSelect) select).getSelectBody();
        }
        if (select instanceof Select) {
            ps = (PlainSelect) ((Select) select).getSelectBody();
        }

        TablesNamesFinder tablesNamesFinder = new TablesNamesFinder();
        List<String> tableList = tablesNamesFinder.getTableList(select);
        if (tableList.size() == 0) {
            return select;
        }
        for (String table : tableList) {
            // sql 包含 where 条件的情况 使用 addAndExpression 连接 已有的条件和新条件
            if (ps.getWhere() == null) {
                AndExpression where = addAndExpression(stmt, table, ps.getWhere());
                // form 和 join 中加载的表
                if (where != null) {
                    ps.setWhere(where);
                } else {
                    // 如果在Statement中不存在这个表名，则存在于子查询中
                    hasSubSelect = true;
                }
            } else {
                // sql 不含 where条件 新建一个EqualsTo设置为where条件
                EqualsTo equalsTo = addEqualsTo(stmt, table);
                ps.setWhere(equalsTo);
            }
        }

        if (hasSubSelect) {
            //子查询中的表
            findSubSelect(stmt, ps.getWhere());
        }
        return select;
    }

    /**
     * 插入数据 添加租户id
     * @param expressionList
     * @throws Exception
     */
    private void addTenantValue(ExpressionList expressionList) throws Exception {
        expressionList.getExpressions().add(new StringValue(getValue()));
    }

    public String getColumn() {

        if(StringUtils.isBlank(addColumn)) throw new RestException(ResultCode.ERROR,"请设置权限检查列的默认值");
        return addColumn;
    }

    public JSqlParserUtil addColumn(String checkColumn) {
        this.addColumn = checkColumn;
        return this;
    }

    public String getValue() {
        if(StringUtils.isBlank(addValue)) throw new RestException(ResultCode.ERROR,"请设置权限检查列的默认值");

        return addValue;
    }

    public JSqlParserUtil addValue(String checkValue) {
        this.addValue = checkValue;
        return this;
    }
}
