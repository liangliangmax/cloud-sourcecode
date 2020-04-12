package com.neupals.common.data_permission.annotation;


import java.lang.annotation.*;

/**
 * 自定义注解
 * 标记方法需要被进行拦截，并加入数据级权限控制
 *
 * 以下两个字段可以在单元测试的时候给默认值，
 * 在真实生产中不用赋值，
 * 会通过com.neupals.common.data_permission.IGetPermissionIdColumnService接口的实现类获取
 */
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Target({ElementType.METHOD})
public @interface DataPermission {

    //数据权限 数据库中校验的字段，比如create_user_id，或者org_id等
    //单元测试可以给默认值，生产环境千万不能赋值
    String checkColumn() default "";

    //数据权限 校验字段的值，比如查询create_user_id = 1 的所有数据
    //单元测试可以给默认值，生产环境千万不能赋值
    String checkValue() default "";
}
