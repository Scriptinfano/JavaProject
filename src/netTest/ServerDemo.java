package netTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Calendar;

class ServerDemo {
    public static void main(String[] args) throws IOException {
        System.out.println("服务端启动...\n");
        try (
                ServerSocket server = new ServerSocket(9999)) {
            Socket s = server.accept();
            System.out.println("等待连接请求中...\n");
            new ServerThread(s).start();

        } catch (IOException e) {
            System.out.println("发生IO错误:" + e.getMessage());
        }


    }
}

class ServerThread extends Thread {
    private final Socket s;

    ServerThread(Socket s) {
        this.s = s;
    }

    public void run() {
        BufferedReader br = null;
        PrintWriter pw = null;
        try {
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            pw = new PrintWriter(s.getOutputStream(), true);
            Calendar c = Calendar.getInstance();
            do {
                String cmd = br.readLine();
                if (cmd == null)
                    break;
                cmd = cmd.toUpperCase();
                if (cmd.startsWith("BYE"))
                    break;
                if (cmd.startsWith("DATE") || cmd.startsWith("TIME"))
                    pw.println(c.getTime());
                if (cmd.startsWith("DOM"))
                    pw.println(c.get(Calendar.DAY_OF_MONTH));
                if (cmd.startsWith("DOW"))
                    switch (c.get(Calendar.DAY_OF_WEEK)) {
                        case Calendar.SUNDAY -> pw.println("星期日");
                        case Calendar.MONDAY -> pw.println("星期一");
                        case Calendar.TUESDAY -> pw.println("星期二");
                        case Calendar.WEDNESDAY -> pw.println("星期三");
                        case Calendar.THURSDAY -> pw.println("星期四");
                        case Calendar.FRIDAY -> pw.println("星期五");
                        case Calendar.SATURDAY -> pw.println("星期六");
                    }
                if (cmd.startsWith("DOY"))
                    pw.println(c.get(Calendar.DAY_OF_YEAR));
                if (cmd.startsWith("PAUSE"))
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        System.out.println("中断已触发");
                    }
            } while (true);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            System.out.println("正在关闭连接...\n");
            try {
                if (br != null)
                    br.close();
                if (pw != null)
                    pw.close();
                if (s != null)
                    s.close();
            } catch (IOException e) {
            }

        }

    }
}
