package co.wedevx.digitalbank.automation.api.models;

public class AccountOwnershipTypeModel {

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

    public void setName(String individual) {
        this.name = name;
    }

    public static AccountOwnershipTypeModel createDefaultINDAccountOwnershipTypeModel(){

        AccountOwnershipTypeModel accountOwnershipTypeModel = new AccountOwnershipTypeModel();

        accountOwnershipTypeModel.setId(17);
        accountOwnershipTypeModel.setCode("IND");
        accountOwnershipTypeModel.setName("Individual");

        return accountOwnershipTypeModel;
    }
}
