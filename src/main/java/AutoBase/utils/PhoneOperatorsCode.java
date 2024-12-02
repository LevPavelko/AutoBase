package AutoBase.utils;

public enum PhoneOperatorsCode {

    VODAFON095("095"),
    VODAFON050("050"),
    KIIVSTAR097("097");

    private String operatorsCode;

    PhoneOperatorsCode(String operatorsCode) {
        this.operatorsCode = operatorsCode;
    }

    public String getOperatorsCode() {
        return operatorsCode;
    }

}
