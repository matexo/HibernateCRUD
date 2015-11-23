import javax.persistence.*;

/**
 * Created by Matexo on 2015-11-19.
 */
@Entity
@Table(name="Product")
public class Product {
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    Long id;
    private String name;
    private String category;
    private int price;


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {

        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
