package cu.edu.cujae.pweb.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/* Este code le indica a spring donde se encuentran los componentes */
@Configuration
@ComponentScan(basePackages = {"cu.edu.cujae.pweb"})
public class SpringConfig {

}
