package product.models;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "products")
@NamedQuery(
        name = "product.models.Product.findAll",
        query = "SELECT p FROM Product p"
)

public class Product {

    @Id
    private Integer id;
    @Column(name = "productName", nullable = false)
    private String productName;



    @Column(name = "price", nullable = false)
    private Integer price;
    public Product(){

    }
    public Product(Integer id, String productName, Integer price){
        this.id=id;
        this.productName=productName;
        this.price=price;
    }


}
