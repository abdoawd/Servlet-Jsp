/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

public class UserShoppingCart implements ListModelInterface {

    private String userId;
    private String productId;
    private String productQuantity;
    private String userCartProductQuantity;
    private String productName;
    private int productPrice;
    private String stringImage;

    public String getUserCartProductQuantity() {
        return userCartProductQuantity;
    }

    public void setUserCartProductQuantity(String userCartProductQuantity) {
        this.userCartProductQuantity = userCartProductQuantity;
    }

    public String getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(String productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getStringImage() {
        return stringImage;
    }

    public void setStringImage(String stringImage) {
        this.stringImage = stringImage;
    }

    public UserShoppingCart() {
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

}
