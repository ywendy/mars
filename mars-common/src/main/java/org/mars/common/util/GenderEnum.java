package org.mars.common.util;

/**
 * @author tony
 * @date 2019/9/24
 */
public enum GenderEnum {

    MAL(1, "男"), FEMALE(2, "女");
    private int code;
    private String value;

    GenderEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }


    public static  String getValue(int code){

        GenderEnum[] values = GenderEnum.values();
        for (int i = 0; i < values.length; i++) {
            GenderEnum genderEnum = values[i];
            if (genderEnum.code == code){
                return genderEnum.value;
            }

        }
        return "";
    }


}
