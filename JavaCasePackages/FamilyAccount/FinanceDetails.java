public class FinanceDetails {
    private int cash;
    private String info;

    private boolean incomeOrOutcome;

    FinanceDetails(int cash, String info, boolean type) {
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

    public String getType()
    {
        if(incomeOrOutcome)return "收入";
        else return "支出";
    }

}
