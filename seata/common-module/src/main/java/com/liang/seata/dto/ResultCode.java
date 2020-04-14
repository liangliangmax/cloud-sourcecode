package com.liang.seata.dto;

public enum ResultCode {

    /* 成功状态码 */
    SUCCESS(200, "成功"),
    ERROR(500,"失败"),


    REQUEST_METHOD_NOT_SUPPORT(405,"不被允许的方法"),

    SERVICE_FALLBACK(901,"服务正忙，请稍后访问"),

    NO_SUCH_RESULT(910,"未找到对应的记录"),

    PARAM_INCORRECT(911,"参数错误"),

    DUPLICATE_NAME(912,"名称重复"),

    BATCH_INSERT_ERROR(913,"批量新增失败"),

    /**
     * 1000x库存相关
     */
    STOCK_IS_NEGATIVE(10001,"库存为负"),
    STOCK_IS_LOCK(10002,"库存正在被占用，请稍后重试"),
    OUT_OF_STOCK(10003,"库存不足"),

    UNKNOWN_OP_TYPE(10004,"未知操作类型"),

    GIFT_STOCK_NOT_ENOUGH(10005,"赠品数量不足"),

    /**
     * 2000x 登录相关
     */
    PASSWORD_INCORRECT(20001,"密码错误"),

    VALIDATE_INCORRECT(20003,"用户名或密码错误"),
    VERIFICATION_CODE_ERROR(20004,"验证码校验失败"),




    ;

    private Integer code;

    private String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer code() {
        return this.code;
    }

    public String message() {
        return this.message;
    }

    public static String getMessage(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.message;
            }
        }
        return name;
    }

    public static Integer getCode(String name) {
        for (ResultCode item : ResultCode.values()) {
            if (item.name().equals(name)) {
                return item.code;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name();
    }

}
