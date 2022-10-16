package parkinglotmanage;

import java.util.Calendar;
import java.util.GregorianCalendar;

class CarRecord {
    private GregorianCalendar arriveTime;//到达时间
    private final GregorianCalendar leftTime;//离去时间
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

    public void setArriveTime(GregorianCalendar arriveTime) {
        this.arriveTime = arriveTime;
    }

    public void output() {
        StringBuilder builder = new StringBuilder();
        builder.append(arriveTime.get(Calendar.YEAR));
        builder.append("年");
        builder.append(arriveTime.get(Calendar.MONTH));
        builder.append("月");
        builder.append(arriveTime.get(Calendar.DATE));
        builder.append("日");
        builder.append(arriveTime.get(Calendar.HOUR_OF_DAY));
        builder.append(":");
        builder.append(arriveTime.get(Calendar.MINUTE));
        System.out.println("车牌号：" + CarId + " 到达时间：" + builder);
    }

    /**
     * 经过重写之后的toString函数，返回该条CarRecord的具体信息字符串
     *
     * @return {@link String}
     */
    @Override
    public String toString() {
        return "车牌号码：" + CarId + "抵达时间："
                + arriveTime.get(Calendar.YEAR) + "年"
                + arriveTime.get(Calendar.MONTH) + "月"
                + arriveTime.get(Calendar.DAY_OF_MONTH) + "日"
                + arriveTime.get(Calendar.HOUR_OF_DAY) + ":"
                + arriveTime.get(Calendar.MINUTE) + "分"
                + " 离开时间："
                + leftTime.get(Calendar.YEAR) + "年"
                + leftTime.get(Calendar.MONTH) + "月"
                + leftTime.get(Calendar.DAY_OF_MONTH) + "日"
                + leftTime.get(Calendar.HOUR_OF_DAY) + ":"
                + leftTime.get(Calendar.MINUTE) + "分";
    }
}
