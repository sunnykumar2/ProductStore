package product;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import lombok.Data;
import lombok.Getter;
import org.hibernate.validator.constraints.*;

import javax.validation.Valid;


@Data
@Getter
public class DropwizardConfiguration extends Configuration {
    // TODO: implement service configuration
    @Valid
    private Integer id;
    @Valid
    private String productName;
    @Valid
    private Integer price;
    @Valid
    private DataSourceFactory database = new DataSourceFactory();
//    @JsonProperty("database")
//    public DataSourceFactory getDataSourceFactory() {
//        return database;
//    }
//
//    @JsonProperty("database")
//    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
//        this.database = dataSourceFactory;
//    }
}
