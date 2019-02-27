package beans;

public class Order {

    /*  ORDERS_NUMBER  NUMBER                         NOT NULL,
  USER_ID        NUMBER                         NOT NULL,
  TOTAL_AMOUNT   NUMBER                         NOT NULL,
  TIMAE          VARCHAR2(100 BYTE)             NOT NULL,
  STATUS   */

    private int userId, totalAmount,orderNumber;
    String orderTime, status, userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Order() {

    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setUserId(int id) {
        this.userId = id;
    }

    public int getUserId() {
        return userId;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public String getStatus() {
        return status;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
