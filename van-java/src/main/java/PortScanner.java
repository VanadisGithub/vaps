import java.io.IOException;
import java.net.Socket;

public class PortScanner {

    public static void main(String[] args) {
        String host = "127.0.0.1"; // 要扫描的目标主机
        int startPort = 1;
        int endPort = 65535;

        for (int port = startPort; port <= endPort; port++) {
            try {
                Socket socket = new Socket(host, port);
                System.out.println("端口 " + port + " 开放");
                socket.close();
            } catch (IOException e) {
                // System.out.println("端口 " + port + " 关闭");
            }
        }
    }
}