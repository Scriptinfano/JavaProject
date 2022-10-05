package parkinglotmanage;

import myscan.ScannerPlus;
import viewManagerPack.ViewManager;

public class ParkingLotManager implements ViewManager {
    private static ScannerPlus scanner = new ScannerPlus();
    private final ParkingLot parkinglot = ParkingLot.getInstance();

    public static void main(String[] args) {
        ParkingLotManager manager = new ParkingLotManager();
        manager.viewInteraction();
    }

    public void showMenu() {
        System.out.println("*****************************");
        System.out.println("*         1、驶入车辆         *");
        System.out.println("*         2、驶出车辆         *");
        System.out.println("*         3、退出程序         *");
        System.out.println("******************************");
    }

    private void showSubMenu() {
        System.out.println("*****************************");
        System.out.println("* 1、输入车辆在停车场中的编号   *");
        System.out.println("* 2、输入车辆的车牌号          *");
        System.out.println("* 3、返回                    *");
        System.out.println("*****************************");

    }

    @Override
    public void viewInteraction() {
        while (true) {
            while (true) {
                String choice;
                showMenu();
                System.out.print("请输入你的选择：");
                choice = scanner.nextSelectionByString(1, 3);
                if (choice.equals("1")) {
                    driveOutInteraction();
                } else if (choice.equals("2")) {
                    driveInInteraction();
                } else if (choice.equals("3")) {
                    exitProgram();
                } else {
                    System.out.println("你的输入不合法，请重新输入");
                }
            }
        }
    }

    private void viewInteraction2() {
        while (true) {
            while (true) {
                String choice;
                showSubMenu();
                System.out.print("请输入你的选择：");
                choice = scanner.nextSelectionByString(1, 3);
                if (choice.equals("1")) {
                    driveOutByIndex();
                } else if (choice.equals("2")) {
                    driveOutById();
                } else if (choice.equals("3")) {
                    return;
                } else {
                    System.out.println("你的输入不合法，请重新输入");
                }
            }
        }

    }

    /**
     * 驶出过程中与用户的交互
     */
    private void driveOutInteraction() {
        if (parkinglot.getStackSize() == 0) {
            //停车场中没有车辆，无法驶出
            System.out.println("停车场中目前没有任何车辆，无法驶出");
            ScannerPlus.pause();
            return;
        }
        viewInteraction2();


    }

    /**
     * 驶入的过程中与用户的交互
     */
    private void driveInInteraction() {
        if (parkinglot.getStackSize() == parkinglot.stackSize) {
            //要驶入的车辆暂时不能进入停车场，要在停车场外的候车道等候（也就是不能进入栈，只能进入队列，等待处理）
        }


        parkinglot.driveIn();

    }

    /**
     * 通过输入汽车在停车场的编号将汽车驶出
     */
    private void driveOutByIndex() {
        System.out.println("目前停车场中总共有" + parkinglot.getStackSize() + "辆车，请输入你要驶出的车辆在停车场的编号：");
        int index = scanner.nextSelectionByInt(1, parkinglot.getStackSize());
        parkinglot.driveOut(index);
    }

    /**
     * 通过输入汽车的车牌号将汽车驶出
     */
    private void driveOutById() {
        System.out.println("请输入你要驶出的车辆的车牌号：");
        String CarId = scanner.nextLine();
        parkinglot.driveOut(CarId);
    }
}
