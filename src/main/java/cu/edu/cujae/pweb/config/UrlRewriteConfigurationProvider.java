
    
    package cu.edu.cujae.pweb.config;

    import javax.servlet.ServletContext;

    import org.ocpsoft.rewrite.annotation.RewriteConfiguration;
    import org.ocpsoft.rewrite.config.Configuration;
    import org.ocpsoft.rewrite.config.ConfigurationBuilder;
    import org.ocpsoft.rewrite.servlet.config.HttpConfigurationProvider;
    import org.ocpsoft.rewrite.servlet.config.rule.Join;

    @RewriteConfiguration
    public class UrlRewriteConfigurationProvider extends HttpConfigurationProvider {

        @Override
        public Configuration getConfiguration(ServletContext context) {
            return ConfigurationBuilder.begin()
                    
                    .addRule(Join.path("/security-users").to("/pages/security/users/user-list.jsf"))
                    .addRule(Join.path("/security-tourist").to("/pages/list/tourist-list.jsf"))
                    .addRule(Join.path("/security-driver").to("/pages/list/driver-list.jsf"))
                    .addRule(Join.path("/security-car").to("/pages/list/car-list.jsf"))
                    .addRule(Join.path("/welcome").to("/pages/welcome/welcome.jsf"));

        
            //ej using params
            //.addRule(Join.path("/shop/{category}").to("/faces/viewCategory.jsf"));
        }

        @Override
        public int priority() {
            return 0;
        }

}
