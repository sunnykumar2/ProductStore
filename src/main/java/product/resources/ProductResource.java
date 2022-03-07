package product.resources;

import io.dropwizard.hibernate.UnitOfWork;
import org.jetbrains.annotations.NotNull;
import product.db.ProductDAO;
import product.models.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
public class ProductResource {

    private final ProductDAO productDAO;

    public ProductResource(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }
    @GET
    @Path("/name/{name}")
    @UnitOfWork
    public String helloStranger(@PathParam("name") String name){
        return "Hello "+name+" !";
    }

    @GET
    @UnitOfWork
    public Response getProducts(){
        return Response.ok(productDAO.findAll()).build();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public Response getProductById( @PathParam("id") Integer id){
        Product product= productDAO.getProductbyId(id);
        if(product!=null)
            return Response.ok(product).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }

    @POST
    @UnitOfWork
    public Response createProductById(@NotNull Product product) throws URISyntaxException {
        Product p = productDAO.getProductbyId(product.getId());
        if(p==null){
            productDAO.create(product);
           return Response.created(new URI("/products/"+product.getId())).build();
          //  return Response.status(Response.Status.NOT_FOUND).build();
        }else
            return Response.status(Response.Status.NOT_FOUND).build();

    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public Response updateProductById(@PathParam("id")Integer id, Product product){
        Product p =productDAO.getProductbyId(id);
        if(p!=null){
            product.setId(id);
            productDAO.update(product);
            return Response.ok(product).build();
        }else
            return Response.ok(Response.Status.NOT_FOUND).build();
    }

    @DELETE
    @Path("/{id}")
    @UnitOfWork
    public Response removeProductById(@PathParam("id") Integer id){
        Product product = productDAO.getProductbyId(id);
        if(product!=null){
            productDAO.removeEmployee(id);
            return Response.ok().build();
        }else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
