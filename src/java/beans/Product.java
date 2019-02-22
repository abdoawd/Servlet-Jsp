package beans;

import java.awt.Image;

public class Product implements ListModelInterface {

    /* product_id	name	description	price	quantity
    image	category_id	discount*/
    private String id;
    private String name;
    private String description;
    private int price;
    private String quantity;
    private String imgPath;
    private String categoryId;
    private String categoryName;
    private int discount;
    private String stringImage;
    private String totalPrice;

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
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
 

   
    public Product() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getPrice() {
        return price;
    }

    public String getQuantity() {
        return quantity;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public int getDiscount() {
        return discount;
    }

}
