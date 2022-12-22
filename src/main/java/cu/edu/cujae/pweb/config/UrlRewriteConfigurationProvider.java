
    
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
                    .addRule(Join.path("/security-roles").to("/pages/list/role-list.jsf"))
                    .addRule(Join.path("/security-tourist").to("/pages/list/tourist-list.jsf"))
                    .addRule(Join.path("/security-driver").to("/pages/list/driver-list.jsf"))
                    .addRule(Join.path("/security-cars").to("/pages/list/car-list.jsf"))
                    .addRule(Join.path("/security-cars-models").to("/pages/list/model-list.jsf"))
                    .addRule(Join.path("/security-cars-brands").to("/pages/list/brand-list.jsf"))
                    .addRule(Join.path("/security-contracts").to("/pages/list/contract-list.jsf"))
                    .addRule(Join.path("/security-contracts-bills").to("/pages/list/bill-list.jsf"))
                    .addRule(Join.path("/security-contracts-payments").to("/pages/list/payment-list.jsf"))
                    .addRule(Join.path("/security-reports-contracts").to("/pages/report/contract-for-brand-and-model-report.jsf"))
                    .addRule(Join.path("/security-reports-contracts").to("/pages/report/contract-for-country-report.jsf"))
                    .addRule(Join.path("/security-reports-tourist").to("/pages/report/tourist-fail-contract-report.jsf"))
                    .addRule(Join.path("/security-reports-cars").to("/pages/report/car-status-reports.jsf"))
                    .addRule(Join.path("/security-reports-annual-income").to("/pages/report/tourist-fail-contract-report.jsf"))
                    .addRule(Join.path("/welcome").to("/pages/welcome/welcome.jsf"));
        }

        @Override
        public int priority() {
            return 0;
        }

}
