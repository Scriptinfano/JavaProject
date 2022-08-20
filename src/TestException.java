import java.io.IOException;

public class TestException {
    public static void fun()throws IOException
    {
        fun2();
    }

    public static void fun2()throws IOException
    {
        if(true)throw new IOException("异常抛出");
    }

    public static void main(String[]args)
    {
        try{
            fun();

        }catch (IOException exception)
        {
            exception.printStackTrace();
           StackTraceElement[] functionCalling= exception.getStackTrace();
           System.out.println("异常所在类：");
           System.out.println("栈中每个方法调用的名称如下：");
           for(int i=0;i<functionCalling.length;i++)
           {
              String methodName= functionCalling[i].getMethodName();
              String className=functionCalling[i].getClassName();
              String fileName=functionCalling[i].getFileName();
              System.out.println("方法名："+methodName+" 所在类："+className+" 所在文件："+fileName);
           }
        }
    }
}
