package parkinglotmanage;

import java.util.Calendar;
import java.util.GregorianCalendar;

class CarRecord {
    private final GregorianCalendar arriveTime;//到达时间
    private GregorianCalendar leftTime;//离去时间
    private final String CarId;


    public CarRecord(String carId, GregorianCalendar leftTime) {
        this.CarId = carId;//在构造时指定该车的车牌号
        this.leftTime = leftTime;//在构造时设定离开时间
        arriveTime = new GregorianCalendar();//直接使用构造时候的时间作为进入停车场的时间
    }

    public GregorianCalendar getArriveTime() {
        return arriveTime;
    }

    public GregorianCalendar getLeftTime() {
        return leftTime;
    }

    public String getCarId() {
        return CarId;
    }

    public void setLeftTime(GregorianCalendar leftTime) {
        this.leftTime = leftTime;
    }

    public void output(){
        StringBuilder builder=new StringBuilder();
        builder.append(arriveTime.get(Calendar.YEAR));
        builder.append("年");
        builder.append(arriveTime.get(Calendar.MONTH));
        builder.append("月");
        builder.append(arriveTime.get(Calendar.DATE));
        builder.append("日");
        builder.append(arriveTime.get(Calendar.HOUR_OF_DAY));
        builder.append(":");
        builder.append(arriveTime.get(Calendar.MINUTE));
        System.out.println("车牌号："+CarId+" 到达时间："+ builder);
    }
}
