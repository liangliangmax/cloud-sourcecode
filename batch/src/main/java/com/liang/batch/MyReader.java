package com.liang.batch;

import io.micrometer.core.instrument.util.StringUtils;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.Order;
import org.springframework.batch.item.database.support.MySqlPagingQueryProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class MyReader {

    @Autowired
    private DataSource dataSource;


    @Bean("userJdbcPagingItemReader")
    @StepScope
    public JdbcPagingItemReader<User> userJdbcPagingItemReader(){

        JdbcPagingItemReader<User> pagingItemReader = new JdbcPagingItemReader<>();

        pagingItemReader.setDataSource(dataSource);
        pagingItemReader.setPageSize(2);
        pagingItemReader.setRowMapper(new UserRowMapper());

        MySqlPagingQueryProvider mySqlPagingQueryProvider = new MySqlPagingQueryProvider();

        //拼接select 的字段，所有被Column标记的都是被拼接的对象
        StringBuffer columns = new StringBuffer();

        columns.append("id,name,create_date,");

        mySqlPagingQueryProvider.setSelectClause(columns.deleteCharAt(columns.lastIndexOf(",")).toString());

        mySqlPagingQueryProvider.setFromClause("user");


        //只有一个条件时候，如果条件一样，只会取一条出来
        //想要多条件排序，需要用LinkedHashMap来保证排序顺序
        Map<String, Order> sort = new LinkedHashMap<>();
        sort.put("create_date", Order.DESCENDING);
//        sort.put("id", Order.DESCENDING);

        mySqlPagingQueryProvider.setSortKeys(sort);

        pagingItemReader.setQueryProvider(mySqlPagingQueryProvider);

        return pagingItemReader;

    }

}


class UserRowMapper implements RowMapper<User> {


    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();

        user.setId(resultSet.getString("id"));
        user.setName(resultSet.getString("name"));
        user.setCreateDate(resultSet.getDate("create_date"));

        return user;
    }
}
