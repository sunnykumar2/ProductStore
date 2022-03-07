package product.db;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import product.models.Product;

import java.util.List;

public class ProductDAO extends AbstractDAO<Product> {

    public ProductDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public  List<Product> findAll(){
        return list(namedTypedQuery("product.models.Product.findAll"));
    }

    public  Product getProductbyId(Integer id){
        return get(id);
    }

    public void removeEmployee(Integer id){
       currentSession().delete(getProductbyId(id));
    }

    public void create(Product product){
        currentSession().clear();
        currentSession().save(product);
    }
    public void update(Product product){
        currentSession().clear();
        currentSession().update(product);
    }
}