package Lab4;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class HostInspector {

	public static void main(String[] args) {
		System.out.println("-------Các Address--------");
		System.out.println("-------Không truyền Address");
		Bai4dot1(new String[] {});
		System.out.println();
		System.out.println("-------Address là: localhost");
		Bai4dot1(new String[] {"localhost"});
		System.out.println();
		System.out.println("-------Address là: example.com");
		Bai4dot1(new String[] {"example.com"});
		System.out.println();
		System.out.println("-------Address là: host-khong-ton-tai.invalid");
		Bai4dot1(new String[] {"host-khong-ton-tai.invalid"});

		

	}
	private static void Bai4dot1(String[] args) {
		if (args.length != 1) {
			System.out.println("Usage: java network.HostInspector <hostname>");
			return;
		}
		try {
			InetAddress[] addresses = InetAddress.getAllByName(args[0]);
			System.out.println("Host: " + args[0]);
			for (InetAddress address : addresses) {
				System.out.println("- IP: " + address.getHostAddress());
				System.out.println(" Canonical: "
						+ address.getCanonicalHostName());
				System.out.println(" Loopback: "
						+ address.isLoopbackAddress());
				System.out.println(" Site local: "
						+ address.isSiteLocalAddress());
			}
		} catch (UnknownHostException e) {
			System.err.println("Không phân giải được host: " + args[0]);
		}
	}
}
