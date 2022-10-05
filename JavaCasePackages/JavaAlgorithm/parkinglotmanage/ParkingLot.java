package parkinglotmanage;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class ParkingLot {
    private final Stack<CarRecord>parkingStack=new Stack<>();
    private final Queue<CarRecord>parkingQueue=new LinkedList<>();
}
