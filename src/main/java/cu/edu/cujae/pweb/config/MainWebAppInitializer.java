package cu.edu.cujae.pweb.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

import com.sun.faces.config.FacesInitializer;

/* Este el code que permite registrar a spring en el contexto de la aplicacin */
public class MainWebAppInitializer extends FacesInitializer implements WebApplicationInitializer {
    public void onStartup(ServletContext sc) throws ServletException {
        AnnotationConfigWebApplicationContext root = new AnnotationConfigWebApplicationContext();
        root.register(SpringConfig.class);
        sc.addListener(new ContextLoaderListener(root));
    }
}
