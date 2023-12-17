package co.wedevx.digitalbank.automation.api.steps.data_transformers;

import co.wedevx.digitalbank.automation.api.models.AccountModel;
import co.wedevx.digitalbank.automation.api.models.UserDomainForDataTable;
import io.cucumber.java.DataTableType;
import java.util.Map;

public class DataTableTransformer {

    @DataTableType
    public UserDomainForDataTable userDomainForDataTable(Map<String, String> entry) {
        String title= entry.get("title");
        String firstName = entry.get("firstName");
        String lastName = entry.get("lastName");
        String gender =  entry.get("gender");
        String dob = entry.get("dob");
        String ssn = entry.get("ssn");
        String emailAddress = entry.get("emailAddress");
        String password = entry.get("password");
        String address = entry.get("address");
        String locality = entry.get("locality");
        String region = entry.get("region");
        String postalCode = entry.get("postalCode");
        String country = entry.get("country");
        String homePhone = entry.get("homePhone");
        String mobilPhone = entry.get("MobilPhone");
        String workPhone = entry.get("workPhone");

        return new UserDomainForDataTable(title,firstName,lastName,gender,dob,ssn,emailAddress,password,address,locality,region,postalCode,country,homePhone,mobilPhone,workPhone);
    }
    @DataTableType
    public AccountModel accountModel(Map<String,String> entry){

        String accountName= entry.get("accountName");
        String accountTypeCode = entry.get("accountTypeCode");
        double openingDeposit = Double.parseDouble(entry.get("openingDeposit"));
        String ownerTypeCode =  entry.get("ownerTypeCode");
        String accountStandingName = entry.get("accountStandingName");

        return new AccountModel(accountName,accountTypeCode,openingDeposit,ownerTypeCode,accountStandingName);

    }
}
