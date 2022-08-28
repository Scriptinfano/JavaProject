package javaTest.exception;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class TestException {
    public static void fun() throws IOException {
        fun2();
    }

    public static void fun2() throws IOException {
        if (true) throw new IOException("异常抛出");
    }

    public static void checkRead(String str) throws SecurityException {
        //isAbsolute()方法是File类的一部分。该函数返回抽象路径名是否为绝对路径
        //indexOf()方法返回指定字符串在调用字符串中第一次出现的索引，若未找到则返回-1
        if (!new File(str).isAbsolute() || (str.contains("..")))
            throw new SecurityException("Access to file:" + str + "denied");
    }

    //测试异常链技术
    public static void testExceptionChain() throws IOException {
        try {
            System.out.println("触发异常并在catch中重新抛出请按1，返回上一个函数调用请按2");
            Scanner scanner = new Scanner(System.in);
            int judge = scanner.nextInt();
            if (judge == 1)
                throw new IOException("引发异常");
            else return;
        } catch (IOException exception) {
            Exception e = new IOException("what we have here is a failure to communicate");
            e.initCause(exception);
            throw exception;
        }
    }

    public static void testExceptionChain2() throws IOException {
        Socket client = null;
        //try圆括号中的语句中的变量会自动被关闭，这被称为带有资源的try子句
        try (Socket client2 = new Socket("192.168.3.21", 25);
             FileWriter file=new FileWriter("foo");
             /*当控制离开了 try 语句块，要么是在成功完成之后，或者是在得到一个异常之后，这两种资源
             都通过调用它们的close()方法而自动关闭,当使用带有资源的 try 的时候，我们不必添加任何
             专门用来关闭文件或网络连接的代码，它会自动为我们做这些*/
        ) {
            testExceptionChain();
            client = new Socket("192.168.3.21", 25);

        } catch (IOException exception) {
            exception.printStackTrace();
        } finally {
            client.close();
            System.out.println("该语句无论如何都会执行，无论是否抛出异常，而且会在catch处理完相关异常之后执行，如果catch未能处理异常那么该语句会在异常传递到下一级之前执行");
            //finally子句的常用用法是确保在try子句中使用的资源得到清除，而不管代码如何退出该语句块
        }
    }

    public static void main(String[] args) {
        try {
            fun();

        } catch (IOException exception) {
            exception.printStackTrace();
            StackTraceElement[] functionCalling = exception.getStackTrace();
            System.out.println("异常所在类：");
            System.out.println("栈中每个方法调用的名称如下：");
            for (int i = 0; i < functionCalling.length; i++) {
                String methodName = functionCalling[i].getMethodName();
                String className = functionCalling[i].getClassName();
                String fileName = functionCalling[i].getFileName();
                System.out.println("方法名：" + methodName + " 所在类：" + className + " 所在文件：" + fileName);
            }

        } catch (SecurityException exception) {

        }
    }
}
