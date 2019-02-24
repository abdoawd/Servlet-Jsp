package beans;

import java.awt.Image;
import javax.servlet.http.Part;

public class Product implements ListModelInterface {

    /* product_id	name	description	price	quantity
    image	category_id	discount*/
    private int id;
    private String name;
    private String description;
    private double price;
    private int quantity;
    private String imgPath;
    private int categoryId;
    private String categoryName;
    private double discount;
    
    private Image image;
    private String stringImage;
    private Part part;

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getStringImage() {
        return stringImage;
    }

    public void setStringImage(String stringImage) {
        this.stringImage = stringImage;
    }
    public void setImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getImgPath() {
        return imgPath;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public double getDiscount() {
        return discount;
    }
}
