package FamilyAccount;
import java.util.ArrayList;
import java.util.Iterator;

public class FamilyAccount {
    private static final ArrayList<FinanceDetails> detailList;

    static {
        detailList = new ArrayList<>();
    }

    public static void main(String[] args) {
        String userSelection;
        while (true) {
            showMenu();
            userSelection = Utility.readMenuSelection();
            switch (userSelection) {
                case "1" -> showDetails();
                case "2" -> registeredIncome();
                case "3" -> registeredOutcome();
                case "4" -> exitProgram();
            }
        }
    }

    /**
     * 显示输出的菜单
     */
    public static void showMenu() {
        System.out.println("============家庭收支记账软件============");
        System.out.println("            1、收支明细                ");
        System.out.println("            2、登记收入                ");
        System.out.println("            3、登记支出                ");
        System.out.println("            4、退出                   ");
        System.out.println("======================================");
        System.out.println("请选择<1-4>:");
    }

    public static void showDetails() {
        Iterator<FinanceDetails> iter = detailList.iterator();
        System.out.println("---------收支明细如下--------");
        if(detailList.isEmpty()){
            System.out.println("当前没有任何收支记录，请录入收支情况后再来查看");

        }
        while (iter.hasNext()) {
            FinanceDetails currentDetails = iter.next();
            System.out.println(currentDetails.getType() + ":" + currentDetails.getCash() + "\t说明：" + currentDetails.getInfo());
        }
    }

    public static void registeredIncome() {
        System.out.println("本次收入金额：");
        int money = Utility.readNumber();
        System.out.println("本次收入说明：");
        String info = Utility.stringRead(20);
        FinanceDetails detail = new FinanceDetails(money, info, true);
        detailList.add(detail);
    }

    public static void registeredOutcome() {
        System.out.println("本次支出金额：");
        int money = Utility.readNumber();
        System.out.println("本次支出说明：");
        String info = Utility.stringRead(20);
        FinanceDetails detail = new FinanceDetails(money, info, false);
        detailList.add(detail);
    }

    /**
     * 确认是否退出程序
     */
    public static void exitProgram() {
        System.out.println("确认是否退出程序？（Y/N）");
        if (Utility.readConfirmSelection())
            System.exit(0);
    }
}

