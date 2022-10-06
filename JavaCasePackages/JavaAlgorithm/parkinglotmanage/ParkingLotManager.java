package parkinglotmanage;

import myscan.ScannerPlus;
import viewManagerPack.ViewManager;

import java.util.Calendar;
import java.util.GregorianCalendar;

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
        //TODO 测试此处的双重while循环是否合理
        while (true) {
            String choice;
            showMenu();
            System.out.print("请输入你的选择：");
            choice = scanner.nextSelectionByString(1, 3);
            switch (choice) {
                case "1" -> driveOutInteraction();
                case "2" -> driveInInteraction();
                case "3" -> exitProgram();
                default -> System.out.println("你的输入不合法，请重新输入");
            }
        }
    }

    private void viewInteraction2() {
        while (true) {
            if (parkinglot.isEmpty()) {
                //停车场中没有车辆，无法驶出
                System.out.println("停车场中目前没有任何车辆，无法驶出");
                ScannerPlus.pause();
                return;
            }
            while (true) {
                String choice;
                showSubMenu();
                System.out.print("请输入你的选择：");
                choice = scanner.nextSelectionByString(1, 3);
                if (choice.equals("1")) {
                    driveOutByIndex();
                    break;
                } else if (choice.equals("2")) {
                    driveOutById();
                    break;
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
        viewInteraction2();
        parkinglot.inputWaitingCar();
    }

    /**
     * 驶入的过程中与用户的交互
     */
    private void driveInInteraction() {
        CarRecord theCar = getBasicInformation();
        if (parkinglot.getStackSize() == parkinglot.stackSize) {
            //要驶入的车辆暂时不能进入停车场，要在停车场外的候车道等候（也就是不能进入栈，只能进入队列，等待处理）
            parkinglot.driveInQueue(theCar);
        } else
            parkinglot.driveInStack(theCar);
    }

    /**
     * 要驶入新车辆时，要录入新车辆的基本信息
     *
     * @return {@link CarRecord}
     */
    private CarRecord getBasicInformation() {
        System.out.println("输入该车的车牌号:");
        String carId = scanner.nextLine();
        GregorianCalendar theLeftTime = new GregorianCalendar();//车辆离开的时间
        System.out.println("你想停多长时间（停车场最多停一周）");
        System.out.println("天数（1-7）：");
        int day = scanner.nextSelectionByInt(1, 7);
        System.out.println("小时数（1-24）：");
        int hour = scanner.nextSelectionByInt(1, 24);
        System.out.println("分钟数（1-24）：");
        int minute = scanner.nextSelectionByInt(1, 60);
        theLeftTime.add(Calendar.DAY_OF_MONTH, day);
        theLeftTime.add(Calendar.HOUR, hour);
        theLeftTime.add(Calendar.MINUTE, minute);
        return new CarRecord(carId, theLeftTime);
    }

    /**
     * 通过输入汽车在停车场的编号将汽车驶出
     */
    private void driveOutByIndex() {
        System.out.println("目前停车场中总共有" + parkinglot.getStackSize() + "辆车，请输入你要驶出的车辆在停车场的编号(编号1从里面开始数)：");
        int index = scanner.nextSelectionByInt(1, parkinglot.getStackSize());
        parkinglot.driveOut(index);
    }

    /**
     * 通过输入汽车的车牌号将汽车驶出
     */
    private void driveOutById() {
        System.out.println("请输入你要驶出的车辆的车牌号：");
        while (true) {
            String CarId = scanner.nextLine();
            if (parkinglot.driveOut(CarId)) break;
        }
    }
}
