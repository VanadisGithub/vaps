import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

public class NetworkScanner {
    public static void main(String[] args) {
        Set<String> set = getIpAddress();
        if (set.size() > 0) {
            set.forEach(ip -> {
                System.out.println("本机 ip: " + ip);
            });
        }
        set.remove("127.0.0.1");
        scannerNetwork(set);
        System.out.println("扫描完毕...");
    }

    private static Set<String> getIpAddress() {
        Set<String> ipList = new HashSet<>();
        try {
            Enumeration<NetworkInterface> allNetInterfaces = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            while (allNetInterfaces.hasMoreElements()) {
                NetworkInterface netInterface = (NetworkInterface) allNetInterfaces.nextElement();
                // 排除虚拟接口和没有启动运行的接口
                if (netInterface.isVirtual() ||!netInterface.isUp()) {
                    continue;
                } else {
                    Enumeration<InetAddress> addresses = netInterface.getInetAddresses();
                    while (addresses.hasMoreElements()) {
                        ip = addresses.nextElement();
                        // 只获取 IPv4 地址
                        if (ip!= null && (ip instanceof Inet4Address)) {
                            ipList.add(ip.getHostAddress());
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ipList;
    }

    private static void scannerNetwork(Set<String> set) {
        try {
            set.forEach(address -> {
                // 设置 IP 地址网段
                String ips = getNetworkSegment(address);
                System.out.println("开始扫描 " + ips + "网段...");
                String ip;
                InetAddress addip = null;
                // 遍历 IP 地址
                for (int i = 1; i < 255; i++) {
                    ip = ips + i;
                    try {
                        addip = InetAddress.getByName(ip);
                    } catch (UnknownHostException e) {
                        System.out.println("找不到主机: " + ip);
                    }
                    // 获取登录过的设备
                    if (!ip.equals(addip.getHostName())) {
                        try {
                            // 检查设备是否在线，其中 1000ms 指定的是超时时间
                            boolean status = InetAddress.getByName(addip.getHostName()).isReachable(1000);
                            // 当返回值是 true 时，说明 host 是可用的，false 则不可。
                            System.out.println("IP 地址为:" + ip + "\t\t设备名称为: " + addip.getHostName() + "\t\t是否可用: " + (status? "可用" : "不可用"));
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        } catch (Exception uhe) {
            System.err.println("Unable to find: " + uhe.getLocalizedMessage());
        }
    }

    private static String getNetworkSegment(String ip) {
        int startIndex = ip.lastIndexOf(".");
        return ip.substring(0, startIndex + 1);
    }
}