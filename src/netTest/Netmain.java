package netTest;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Netmain {
    public final static long perSeconds = 1000;//一秒有1000毫秒，便于后面调用sleep的时候更直观的用秒表示睡眠的时间

    public static void main(String[] args) {
        try {
            netThread();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }

    }

    public static void SocketServer() {
        try {
            ServerSocket server = new ServerSocket(25);
            Socket newSocket = server.accept();//服务器监听请求，如果监听到请求则返回一个socket对象以便完成数据传输
            BufferedReader newBufferReader = new BufferedReader(new InputStreamReader(newSocket.getInputStream()));

            while (true) {
                String newLine = newBufferReader.readLine();
                if (newLine.equals("exit")) {
                    break;
                }
                System.out.println("received from client:" + newLine);
            }

            server.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void testInetAddress() {
        try {
            //在java中使用InetAddress类表示IP对象，可从对象中获得主机地址与ip地址等信息

            //通过getByName()解析域名
            InetAddress inet = InetAddress.getByName("192.168.14.100");
            System.out.println(inet);
            InetAddress inet2 = InetAddress.getByName("www.google.com");//将传入函数的域名地址解析为ip地址并返回
            System.out.println(inet2);
            InetAddress inet3 = InetAddress.getByName("localhost");
            System.out.println(inet3);
            InetAddress inet4 = InetAddress.getByName("127.0.0.1");
            System.out.println(inet4);
            InetAddress inet5 = InetAddress.getLocalHost();//获取本地主机名称与本地ip地址
            System.out.println(inet5);
            System.out.println(inet5.getHostName());//InetAddress对象调用getHostName()可返回主机名称
            System.out.println(inet5.getHostAddress());//InetAddress对象调用getHostAddress()可返回主地址
        } catch (UnknownHostException exception) {
            String errorMessage = exception.getMessage();
            System.out.println(errorMessage);
        }
    }

    public static void netThread() {

        Runnable LaunchServer = new Server();
        Runnable LaunchClient = new Client();

        Thread ServerThread = new Thread(LaunchServer);
        Thread ClientThread = new Thread(LaunchClient);
        ClientThread.setPriority(5);
        ServerThread.setPriority(6);

        ClientThread.setName("客户端线程");
        ServerThread.setName("服务端线程");

        try {
            ServerThread.start();
            ClientThread.start();

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    public static void testDNS() throws IOException {
        InetAddress inet2 = InetAddress.getByName("www.google.com");//将传入函数的域名地址解析为ip地址并返回
        String googleIP = inet2.getHostAddress();
        Socket socket = new Socket(googleIP, 80);
        OutputStream out = socket.getOutputStream();
        String theMessage = "fuck you, google";
        out.write(theMessage.getBytes());
    }

}

class Server implements Runnable {
    //带有资源的try语句，在try块执行结束之后会自动关闭在try资源块中建立的连接，例如网络连接等

    @Override
    public void run() {
        //带有资源的try语句不需要再写finally语句关闭相应资源
        try (
                ServerSocket server = new ServerSocket(8899);
                Socket acceptedSocket = server.accept();
                InputStream istream = acceptedSocket.getInputStream();
                OutputStream ostream = acceptedSocket.getOutputStream();
                /*InputStreamReader类可用于将字节数据转换为字符数据。*/
                InputStreamReader streamReader = new InputStreamReader(istream);
                /*BufferReader由Reader扩展而来，提供通用的缓冲方式文本读取，readLine读取一整个文本行*/
                BufferedReader reader = new BufferedReader(streamReader);
        ) {
            //服务器端从字符流得到客户端发来的问候消息
            String message = reader.readLine();
            String currentThreadName = Thread.currentThread().getName();
            System.out.println(message);
            System.out.println("当前线程名称为" + currentThreadName);
            //服务器的回复消息：
            String returnMessage = "客户端" + acceptedSocket.getInetAddress() + ",我是" + server.getInetAddress() + "我已收到你的消息";
            ostream.write(returnMessage.getBytes());
        } catch (IOException e) {
            String message = e.getMessage();
            e.printStackTrace();
        }

        //关闭资源：一般只需要关闭包装流即可，内部的节点流会随着包装流close()方法的执行而自动关闭

    }
}

class Client implements Runnable {
    public void hello(OutputStream ostream, String theMessage) throws IOException, InterruptedException {
            ostream.write(theMessage.getBytes());//将字符串转换为字节数组，write的参数是一个字节数组

            //让当前线程暂且让出执行权，让另一个线程将所有代码执行完毕之后再让当前线程执行
            Thread.currentThread().sleep(3 * Netmain.perSeconds);
    }

    @Override
    public void run() {
        InetAddress inet = null;//和自己连接
        try {
            inet = InetAddress.getByName("127.0.0.1");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try (
                //得到客户端输出流
                Socket client = new Socket(inet, 8899);
                OutputStream ostream = client.getOutputStream();

                //得到客户端输入流
                InputStream istream = client.getInputStream();
                InputStreamReader reader = new InputStreamReader(istream);
                BufferedReader streamReader = new BufferedReader(reader);

        ) {
            String hostName = inet.getHostAddress();
            if (connected(client, "client")) {
                String currentThreadName = Thread.currentThread().getName();
                System.out.println("当前线程名称：" + currentThreadName);
                hello(ostream, "你好，我是客户端" + hostName);//向服务端发送

//Thread.currentThread().isAlive();
/*
                String getMessageFromServer = streamReader.readLine();
                System.out.println(getMessageFromServer);
*/

            } else {
                Socket thirdSocket;
                //反复向服务端口发起连接请求
                do {
                    thirdSocket = new Socket(inet, 8899);
                } while (!connected(thirdSocket, "secondClient"));
                OutputStream ostream2 = thirdSocket.getOutputStream();
                hello(ostream, "你好，我是客户端" + hostName);//向服务端发送
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

    public static boolean connected(Socket socket, String name)//判断连接是否成功
    {
        if (socket.isClosed() == false && socket.isConnected() == true) {
            System.out.println(name + "处于连接状态");
            return true;
        } else {
            System.out.println(name + "处于未连接状态");
            return false;
        }
    }
}

