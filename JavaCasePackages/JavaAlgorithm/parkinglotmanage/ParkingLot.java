package JavaAlgorithm.parkinglotmanage;

import myScannerAndPrinter.ScannerPlus;

import java.util.*;

class ParkingLot {
    private static ParkingLot instance = null;
    private final Stack<CarRecord> parkingStack = new Stack<>();
    private final Queue<CarRecord> parkingQueue = new LinkedList<>();

    public final int stackSize = 5;//停车场最多能停的车辆

    private ParkingLot() {
    }

    public static ParkingLot getInstance() {
        if (instance == null)
            instance = new ParkingLot();
        return instance;
    }

    public int getStackSize() {
        return parkingStack.size();
    }

    public int getQueueSize() {
        return parkingQueue.size();
    }

    /**
     * 判断停车场是否为空
     *
     * @return boolean
     */
    public boolean isEmpty() {
        return parkingStack.size() == 0;
    }

    /**
     * 通过车辆在停车场的编号驶出
     *
     * @param index 汽车在停车场中的编号
     */
    public void driveOut(int index) {
        CarRecord outCar = parkingStack.remove(index - 1);
        System.out.println("已驶出车辆，该车辆信息如下：");
        outCar.output();
    }

    /**
     * 通过车辆的车牌号找到车辆，并将其驶出
     *
     * @param CarId 汽车车牌号
     * @return boolean 如果找到了该车牌号对应的汽车并成功驶出后返回true，否则返回false
     */
    public boolean driveOut(String CarId) {

        Iterator<CarRecord> iter = parkingStack.iterator();
        CarRecord theCar;

        while (iter.hasNext()) {
            theCar = iter.next();
            if (theCar.getCarId().equals(CarId)) {
                System.out.println("已驶出车辆，该车辆信息如下：");
                theCar.output();
                return true;
            }
        }
        System.out.println("你输入的车牌号不存在，请重新输入车牌号：");
        return false;
    }

    /**
     * 将车驶入停车场
     *
     * @param theCar 指定的车
     */
    public void driveInStack(CarRecord theCar) {
        parkingStack.push(theCar);
    }

    /**
     * 将指定的车辆放入候车队列
     *
     * @param theCar 指定的车
     */
    public void driveInQueue(CarRecord theCar) {
        parkingQueue.offer(theCar);
    }

    /**
     * 检查候车道上是否有等待的汽车，如果有再检查停车场中是否有空出来的位置，如果有则将候车道中的车移动到停车场
     */
    public void inputWaitingCar() {
        int totalCar = 0;
        boolean hasIn = false;
        while (!parkingQueue.isEmpty() && parkingStack.size() < stackSize) {
            CarRecord theCar = parkingQueue.peek();
            theCar.setArriveTime(new GregorianCalendar());//更新车辆进入停车场的时间
            parkingStack.push(parkingQueue.poll());
            totalCar++;
            hasIn = true;
        }
        if (hasIn)
            System.out.println("通知：目前的停车场又驶入了" + totalCar + "辆车，停车场目前总共有" + parkingStack.size() + "辆车");
    }

    /**
     * 显示停车场中所有车辆的信息
     */
    public void showInformation() {
        for (CarRecord theRecord : parkingStack) {
            System.out.println(theRecord.toString());
        }
        System.out.println("已输出全部信息");
        ScannerPlus.pause();
    }

    /**
     * 验证车辆的车牌号是否和停车场或者候车道上的车牌号重复
     *
     * @param theId 待验证的车牌号
     * @return boolean 验证成功返回true，否则返回false
     */
    public boolean verifyCarId(String theId) {
        for (CarRecord theRecord : parkingStack) {
            if (theRecord.getCarId().equals(theId))
                return false;
        }
        return true;
    }
}
