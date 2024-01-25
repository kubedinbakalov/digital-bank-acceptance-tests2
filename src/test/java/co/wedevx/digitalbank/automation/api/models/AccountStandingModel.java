package co.wedevx.digitalbank.automation.api.models;

public class AccountStandingModel {

    private int id;
    private String code;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static AccountStandingModel createDefaultA1AccountStandingModel(){

        AccountStandingModel accountStandingModel = new AccountStandingModel();

        accountStandingModel.setId(19);
        accountStandingModel.setCode("A1");
        accountStandingModel.setName("Open");

        return accountStandingModel;
    }
}
