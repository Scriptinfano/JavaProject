package FamilyAccount;
public class FinanceDetails {
    private final int cash;
    private final String info;

    private final boolean incomeOrOutcome;

    public FinanceDetails(int cash, String info, boolean type) {
        this.cash = cash;
        this.info = info;
        this.incomeOrOutcome = type;
    }

    public int getCash() {
        return cash;
    }

    public String getInfo() {
        return info;
    }

    public String getType() {
        if (incomeOrOutcome) return "收入";
        else return "支出";
    }

}
