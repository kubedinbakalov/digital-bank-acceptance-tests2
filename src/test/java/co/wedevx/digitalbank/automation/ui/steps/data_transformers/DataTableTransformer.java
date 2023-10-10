package co.wedevx.digitalbank.automation.ui.steps.data_transformers;

import co.wedevx.digitalbank.automation.ui.models.NewCheckingAccountData;
import io.cucumber.java.DataTableType;

import java.util.Map;
import co.wedevx.digitalbank.automation.ui.models.AccountCard;
import co.wedevx.digitalbank.automation.ui.models.Transactions;

public class DataTableTransformer {
    @DataTableType
    public AccountCard accountCartEntry(Map<String, String> entry) {
        String accountName = entry.get("accountName");
        String accountType = entry.get("accountType");
        String ownerShip = entry.get("ownership");
        long accountNumber = Long.valueOf(entry.get("accountNumber"));
        String interestRate = entry.get("interestRate");
        double balance = Double.parseDouble(entry.get("balance"));
        return new AccountCard(accountName, accountType, ownerShip, accountNumber, interestRate, balance);
    }

    @DataTableType
    public Transactions transactionsEntry(Map<String, String> entry) {
        String data = entry.get("data");
        String category = entry.get("category");
        String description = entry.get("description");
        double amount = Double.parseDouble(entry.get("amount"));
        double balance = Double.parseDouble(entry.get("balance"));
        return new Transactions(data, category, description, amount, balance);
    }
    @DataTableType
    public NewCheckingAccountData newCheckingAccountDataEntry(Map<String, String> entry) {
        //    |checkingAccountType|accountOwnerShip|accountName              |initialDepositAmount|
        //    |Standard Checking  |Individual      |Elon Musk Second Checking|10000.00            |
        String checkingAccountType= entry.get("checkingAccountType");
        String accountOwnerShip = entry.get("accountOwnerShip");
        String accountName = entry.get("accountName");
        double initialDepositAmount = Double.parseDouble(entry.get("initialDepositAmount"));
        return new NewCheckingAccountData(checkingAccountType, accountOwnerShip,accountName,initialDepositAmount);
    }
}
