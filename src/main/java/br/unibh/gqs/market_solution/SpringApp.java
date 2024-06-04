package br.unibh.gqs.market_solution;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "API de teste GQS", version = "1.0", description = "API exemplo da UC Gestão e Qualidade de Software", 
	license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0"),
	contact = @Contact(name = "Suporte da Empresa XPTO", email = "suporte@empresa.com"), 
	termsOfService = "http://empresa.com/termos_uso_api")	)
public class SpringApp {

	private static final Logger log = LoggerFactory.getLogger(SpringApp.class);
	
	public static void main(String[] args) {
		log.info("Inicializando Spring App...");
	    System.setProperty("server.servlet.context-path", "/market-api/v1");
		//SpringApplication.run(SpringApp.class, args);
		new SpringApplicationBuilder(SpringApp.class).web(WebApplicationType.SERVLET).run(args);

	}
}
