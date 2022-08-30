package com.gkhy.servicebase.constant;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class Constants {

//    private Constants() { throw new AssertionError();}

    public interface ConstantType {
    }

    public enum Auth implements ConstantType{
        SMS_CODE_CACHE_PREFIX("sms : code :"),
        LOGIN_USER("loginUser"),
        ;

        private final String value;
        Auth(String value) {
            this.value = value;
        }
        public String value() {
            return value;
        }
    }

    public enum Cart implements ConstantType {
        TEMP_USER_COOKIE_NAME("user-key"),
        TEMP_USER_COOKIE_TIMEOUT("60 * 60 * 24 * 30"),
        CART_PREFIX("mall:cart:"),
        ;

        private String value;
        Cart(String value) {
            this.value = value;
        }
        public String value() {
            return value;
        }
    }
    
    public enum  ProductsAttrEnum implements ConstantType{
        ATTR_TYPE_BASE(1,"Basic attributes"),
        ATTR_TYPE_SALE(0,"Sales attributes"),
        ;

        private int code;
        private String msg;

        ProductsAttrEnum(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum ProductStatusEnum implements ConstantType{
        NEW_SPU(0,"New"),
        SPU_UP(1,"Product on sale"),
        SPU_DOWN(2,"Product on sale"),
        ;

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        ProductStatusEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public enum  WarePurchaseStatus implements ConstantType{
        CREATED(0,"CREATED"),
        ASSIGNED(1,"ASSIGNED"),
        RECEIVE(2,"RECEIVE"),
        FINISH(3,"FINISH"),
        ABNORMAL(4,"ABNORMAL");

        private int code;
        private String msg;

        WarePurchaseStatus(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    public enum  warePurchaseDetailStatus implements ConstantType{

        CREATED(0,"CREATED"),
        ASSIGNED(1,"ASSIGNED"),
        SELLING(2,"SELLING"),
        FINISH(3,"FINISH"),
        ABNORMAL(4,"ABNORMAL");

        private int code;
        private String msg;

        warePurchaseDetailStatus(int code,String msg){
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

}

