package intuit.assessment;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author: Jiajie Duan
 * @date: 08/23/17
 */

@SpringBootApplication
public class InvoiceDemoApp {
	public static void main(String[] args) {
		// create a servelet container and host the application
		SpringApplication.run(InvoiceDemoApp.class, args);
	}

}
