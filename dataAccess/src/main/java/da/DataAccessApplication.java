package da;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataAccessApplication {

	public static void main(String[] args) throws UnknownHostException, SocketException {
		SpringApplication.run(DataAccessApplication.class, args);
		/*
		
		InetAddress ia1 = InetAddress.getLocalHost();
		NetworkInterface ni = NetworkInterface.getByInetAddress(ia1);
		
		InetAddress ia2 = InetAddress.getByName("192.168.0.11");
		NetworkInterface ni2 = NetworkInterface.getByInetAddress(ia2);
		
		System.out.println("");
		int a = 1;*/
		
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
	        //Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
	    }
	}
}
