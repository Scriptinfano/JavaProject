package parkinglotmanage;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

class ParkingLot {
    private static ParkingLot instance = null;
    private final Stack<CarRecord> parkingStack = new Stack<>();
    private final Queue<CarRecord> parkingQueue = new LinkedList<>();

    public final int stackSize=5;//停车场最多能停的车辆

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

    public void driveOut(int index) {
    }
    public void driveOut(String CarId) {
    }

    public void driveIn() {
    }
}
