import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class IPUtils {

    public static int ipToInt(String ip) throws UnknownHostException {
        InetAddress inet = InetAddress.getByName(ip);
        byte[] bytes = inet.getAddress();
        ByteBuffer buffer = ByteBuffer.allocate(4);
        buffer.put(bytes);
        buffer.rewind();
        return buffer.getInt();
    }

    public static boolean isIpInSubnet(String ip, String network, String subnetMask) throws UnknownHostException {
        int ipInt = ipToInt(ip);
        int networkInt = ipToInt(network);
        int maskInt = ipToInt(subnetMask);
        return (ipInt & maskInt) == (networkInt & maskInt);
    }
}

