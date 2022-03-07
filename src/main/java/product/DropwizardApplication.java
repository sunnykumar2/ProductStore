package product;

import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import product.db.ProductDAO;
import product.models.Product;
import product.resources.ProductResource;



public class DropwizardApplication extends Application<DropwizardConfiguration> {

    public static void main(final String[] args) throws Exception {
        new DropwizardApplication().run(args);
    }

    @Override
    public String getName() {
        return "Dropwizard";
    }

    private HibernateBundle<DropwizardConfiguration> hibernate = new HibernateBundle<DropwizardConfiguration>(Product.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(DropwizardConfiguration dropwizardConfiguration) {
             return dropwizardConfiguration.getDatabase();
        }
    };

    @Override
    public void initialize(final Bootstrap<DropwizardConfiguration> bootstrap) {
        // TODO: application initialization
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(final DropwizardConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application

        final ProductDAO dao = new ProductDAO(hibernate.getSessionFactory());
        environment.jersey().register(new ProductResource(dao));
    }

}
