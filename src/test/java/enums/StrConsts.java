package enums;

public enum StrConsts {
    BASEURL("https://www.mtsbank.ru/"),
    REGION("Москва"),
    PHONE_NUMBER("9921235623");

    private final String text;
    StrConsts(final String text){
        this.text = text;
    }

    @Override
    public String toString(){
        return this.text;
    }
}
