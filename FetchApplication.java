package RestAPI.Fetch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class FetchApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(FetchApplication.class, args);
		Operation bean = app.getBean(Operation.class);
		Home h = app.getBean(Home.class);
		Information in = app.getBean(Information.class);


		//in.setAmount(11f);
		h.setAmnt(13);
		in.setHome(h);
		bean.save(in);
		System.out.println("in="+in.hashCode());
	}
}
