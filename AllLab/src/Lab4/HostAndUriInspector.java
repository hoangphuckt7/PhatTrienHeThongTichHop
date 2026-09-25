package Lab4;

import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.UnknownHostException;

public class HostAndUriInspector {

	public static void main(String[] args) {
        String[][] testCases = {
            // --- 3 Ca hợp lệ ---
            {"localhost", "http://localhost:8080/api/v1/users?id=1#ahihi"},
            {"example.com", "https://example.com/index.html?search=java#top"},
            {"ftp.gnu.org", "ftp://ftp.gnu.org/pub/gnu/?sort=name"},
            
            // --- 3 Ca lỗi ---
            {null, null},                                                 // Lỗi 1: Thiếu tham số
            {"host-khong-ton-tai.invalid", "http://example.com"},         // Lỗi 2: Host không tồn tại
            {"example.com", "http://example.com/path with space"}         // Lỗi 3: URI sai cú pháp (có khoảng trắng)
        };

        // Vòng lặp chạy qua toàn bộ các ca test
        for (int i = 0; i < testCases.length; i++) {
            System.out.println("\n========================================");
            System.out.println("TEST CASE " + (i + 1));
            System.out.println("========================================");
            
            runInspection(testCases[i]);
        }
    }

    private static void runInspection(String[] args) {
        if (args.length < 2 || args[0] == null || args[1] == null) {
            System.err.println("Lỗi: Thiếu tham số đầu vào!");
            return;
        }

        String hostname = args[0];
        String uriString = args[1];

        System.out.println("------ Kiểm tra Hostname: " + hostname);
        inspectHost(hostname);

        System.out.println("\n------ Kiểm tra URI: " + uriString);
        inspectUri(uriString);
    }

    private static void inspectHost(String hostname) {
        try {
            InetAddress[] addresses = InetAddress.getAllByName(hostname);
            for (InetAddress address : addresses) {
                System.out.println("  - IP Address : " + address.getHostAddress());
                
                String ipType = (address instanceof Inet4Address) ? "IPv4" : 
                                (address instanceof Inet6Address) ? "IPv6" : "Không xác định";
                System.out.println("    + Loại IP  : " + ipType);
                System.out.println("    + Loopback : " + address.isLoopbackAddress());
                System.out.println("    + Site local: " + address.isSiteLocalAddress());
            }
        } catch (UnknownHostException e) {
            System.err.println("  - Lỗi: Không phân giải được host: " + hostname);
        }
    }

    private static void inspectUri(String uriString) {
        try {
            URI uri = new URI(uriString);
            System.out.println("  - Scheme   : " + uri.getScheme());
            System.out.println("  - Host     : " + uri.getHost());
            System.out.println("  - Port     : " + (uri.getPort() == -1 ? "Mặc định" : uri.getPort()));
            System.out.println("  - Path     : " + uri.getPath());
            System.out.println("  - Query    : " + uri.getQuery());
            System.out.println("  - Fragment : " + uri.getFragment());
        } catch (URISyntaxException e) {
            System.err.println("  - Lỗi: URI không hợp lệ: " + uriString);
            System.err.println("  - Chi tiết : " + e.getMessage());
        }
    }

}
