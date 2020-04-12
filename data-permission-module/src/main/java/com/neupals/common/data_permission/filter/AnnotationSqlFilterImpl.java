package com.neupals.common.data_permission.filter;


import com.neupals.common.data_permission.ISqlFilter;
import com.neupals.common.data_permission.annotation.DataPermission;
import com.neupals.common.util.AnnotationUtil;
import org.springframework.stereotype.Component;

/**
 * 通过方法上的注解判断是否需要拦截
 * @see com.neupals.common.data_permission.annotation.DataPermission
 *
 * 有bug可以改，如果是换了另一种判断方式要重写一个实现类
 *
 */
@Component("annotationSqlFilter")
public class AnnotationSqlFilterImpl implements ISqlFilter {

    /**
     * 输入类名称和方法名称，判断该方法是否需要被拦截
     *
     * @param clazzName 要查询的类名称
     * @param method 要查询的方法名称
     * @return
     */
    @Override
    public boolean needFilter(String clazzName,String method) {

        try {
            boolean isAnnotated = AnnotationUtil.isMethodAnnotatedByClass(clazzName, method, DataPermission.class);

            if(isAnnotated){
                return true;
            }

            if(method.contains("_COUNT")){
                String guessMethod = method.substring(0,method.lastIndexOf("_"));
                isAnnotated = AnnotationUtil.isMethodAnnotatedByClass(clazzName,guessMethod, DataPermission.class);
                if(isAnnotated){
                    return true;
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return false;
        }

        return false;
    }

}
