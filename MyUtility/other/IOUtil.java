package other;

import java.io.IOException;
import java.io.InputStream;
import java.net.SocketException;

public class IOUtil {
    public static InputStream readFromBytes(byte[] bytes) throws IOException {
        int byteRead = 0;
        int bytesToRead = bytes.length;
        InputStream in = System.in;
        while (byteRead < bytesToRead) {
            int result = in.read(bytes, byteRead, bytesToRead - byteRead);
            if (result == -1) break;
            byteRead += result;
        }
        return in;
    }

    public static void main(String[] args) throws SocketException {

    }

    public static int trans(byte b) {
        return b < 0 ? b + 256 : b;
    }
}
