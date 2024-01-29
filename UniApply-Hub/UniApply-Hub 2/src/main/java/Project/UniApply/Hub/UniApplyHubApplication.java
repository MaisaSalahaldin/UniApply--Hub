package Project.UniApply.Hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("Project.UniApply.Hub")
public class UniApplyHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(UniApplyHubApplication.class, args);
	}

}
