package other;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class NetUtil {
    public static void getAllNetWorkInterfaces() throws SocketException {
        Enumeration<NetworkInterface> networkInterfaceEnumeration = NetworkInterface.getNetworkInterfaces();
        int count = 1;
        while (networkInterfaceEnumeration.hasMoreElements()) {
            NetworkInterface next = networkInterfaceEnumeration.nextElement();
            System.out.println("----------" + count + "号设备----------");
            System.out.println("网络设备名称：" + next.getName());
            System.out.println("网络设备显示名称：" + next.getDisplayName());
            System.out.println("网络接口索引：" + next.getIndex());
            System.out.println("是否开启并运行" + next.isUp());
            Enumeration<InetAddress> ipv4s = next.getInetAddresses();
            System.out.print("接口IP地址:");
            while (ipv4s.hasMoreElements()) {
                InetAddress address = ipv4s.nextElement();
                System.out.print(address.getHostAddress());
                System.out.println(" ");
            }
            System.out.println();
            System.out.println("是否为回调接口" + next.isLoopback());
            count++;
        }
    }

    public static void checkSpam(String[] args) {
        for (String arg : args) {
            if (isSpammer(arg)) {
                System.out.println(arg + "是垃圾邮件发送者");
            } else {
                System.out.println(arg + "不是垃圾邮件发送者");
            }
        }
    }

    private static boolean isSpammer(String arg) {
        final String BLACKHOLE = "sbl.spamhaus.org";
        try {
            InetAddress address = InetAddress.getByName(arg);
            byte[] quad = address.getAddress();
            String query = BLACKHOLE;
            for (byte octet : quad) {
                int unsignedByte = octet < 0 ? octet + 256 : octet;
                query = unsignedByte + "." + query;
            }
            InetAddress.getByName(query);

            return true;
        } catch (UnknownHostException e) {
            return false;
        }
    }
}
