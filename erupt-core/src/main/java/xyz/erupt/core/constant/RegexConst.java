package xyz.erupt.core.constant;

/**
 * @author YuePeng
 * date 2021/6/22 18:37
 */
public class RegexConst {

    public static final String EMAIL_REGEX = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";

    public static final String PHONE_REGEX = "^\\s*(?:\\+?(\\d{1,3})[-. (]*)?\\s*((01[016789]{3})[-. )]*)?((\\d{3,4})[-. ]*)+(\\d{4})+\\s*$";

}
