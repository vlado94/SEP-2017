package isok.isok;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class IsokApplication {

	public static void main(String[] args) {
		SpringApplication.run(IsokApplication.class, args);
		
		try {
	        Enumeration nis = NetworkInterface.getNetworkInterfaces();
	        while(nis.hasMoreElements())
	        {
	            NetworkInterface ni = (NetworkInterface) nis.nextElement();
	            Enumeration ias = ni.getInetAddresses();
	            while (ias.hasMoreElements())
	            {
	                InetAddress ia = (InetAddress) ias.nextElement();
	                System.out.println(ia.getHostAddress());
	            }

	        }
	    } catch (SocketException ex) {
	    }
	}
}
