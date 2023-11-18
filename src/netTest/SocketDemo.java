package netTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

class SocketDemo {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String host = "localhost";
        try (
                Socket s = new Socket(host, 9999);
                BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
        ) {
            while (true) {
                System.out.print("Input a Command:");
                String cmd = scanner.nextLine();
                if (!Objects.equals(cmd, "EXIT")) {
                    pw.println(cmd);
                    System.out.println(br.readLine());
                } else break;
            }
        } catch (IOException ioe) {

        }
    }
}
