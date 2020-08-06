package tb_inspection;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Tb_inspectionApplication extends SpringBootServletInitializer {
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(Tb_inspectionApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(Tb_inspectionApplication.class, args);
		System.out.println("SERVER START SUCCESS!!");
	}

}
