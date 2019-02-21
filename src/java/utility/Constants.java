package utility;

public class Constants {

    // database connection attribute 
    public static final String DATABASE_NAME = "storeusers";
    public static final String DATABASE_PASSWORD = "1234";

    // TABLE NAMES 
    public static final String ORDER_ITEM_TABLE_NAME = "";

    // USERS TABLE 
    public static final String USER_TABLE_NAME = "E_USERS";
    // user columns name 
    public static final String COLUMN_USER_ID = "USER_ID";
    public static final String COLUMN_USER_FIRST_NAME = "F_NAME";
    public static final String COLUMN_USER_LAST_NAME = "L_NAME";
    public static final String COLUMN_USER_ROLE = "USER_ROLE";
    public static final String COLUMN_USER_EMAIL = "EMAIL";
    public static final String COLUMN_USER_PASSWORD = "USER_PASSWORD";
    public static final String COLUMN_USER_BIRTHDAY = "BIRTHDAY";
    public static final String COLUMN_USER_JOP = "USER_JOB";

    public static final String COLUMN_USER_CREDIT_LIMIT = "CREDIT_LIMIT";
    public static final String COLUMN_USER_IMAGE = "PROFILE_IMAGE";

    // TABLE ADDRESSES
    public static final String ADDRESSES_TABLE_NAME = "ADDRESSES";
    public static final String COLUMN_ADDRESS_ID = "ADDRESS_ID";
    public static final String COLUMN_ADDRESSES_USER_ID = "USER_ID";
    public static final String COLUMN_ADDRESSES_STREET = "STREET";
    public static final String COLUMN_ADDRESSES_CITY = "CITY";
    public static final String COLUMN_ADDRESSES_COUNTRY = "COUNTRY";

    // CREDIT CARD TABLE 
    public static final String CREDIT_CARD_TABLE_NAME = "CREDIT_CARDS";
    public static final String COLUMN_CARD_NUMBER = "CARD_NUMBER";
    public static final String COLUMN_CARD_AMOUNT = "AMOUNT";

    // PRODUCT TABLE 
    public static final String PRODUCT_TABLE_NAME = "PRODUCT";

    public static final String COLUMN_PRODUCT_ID = "PRODUCT_ID";
    public static final String COLUMN_PRODUCT_NAME = "PRODUCT_NAME";
    public static final String COLUMN_PRODUCT_DESCRIPTION = "DESCRIPTION";
    public static final String COLUMN_PRODUCT_PRICE = "PRICE";
    public static final String COLUMN_PRODUCT_QUANTITY = "QUANTITY";
    public static final String COLUMN_PRODUCT_IMAGE = "IMAGE";
    public static final String COLUMN_PRODUCT_CATEGORY_ID = "CATEGORY_ID";
    public static final String COLUMN_PRODUCT_DISCOUNT = "DISCOUNT";
    public static final String COLUMN_PRODUCT_STATUS = "STATUS";

    public static final String CATEGORY_TABLE_NAME = "PRODUCT_CATEGORY";
    public static final String COLUMN_CATEGORY_ID = "CATEGORY_ID";
    public static final String COLUMN_CATEGORY_NAME = "CATEGORY_NAME";

    public static final String SHOPPING_CART_TABLE_NAME = "SHOPPING_CART";
    public static final String COLUMN_SHOPPING_CART_USER_ID = "USER_ID";

    public static final String COLUMN_SHOPPING_CART_PRODUCT_ID = "PRODUCT_ID";
    public static final String COLUMN_SHOPPING_CART_QUANTITY = "QUANTITY";

    // INTERESTS TABLE 
    public static final String INTERESTS_TABLE_NAME = "USER_INTERSTS";

    public static final String COLUMN_INTERESTS_USER_ID = "USER_ID";
    public static final String COLUMN_INTERESTS_CATEGORY_ID = "CATEGORY_ID";

    // ORDER TABLE 
    public static final String ORDER_TABLE_NAME = " USER_ORDERS";

    public static final String COLUMN_ORDER_NUMBER = "ORDERS_NUMBER";
    public static final String COLUMN_ORDER_USER_ID = "USER_ID";
    public static final String COLUMN_ORDER_TOTAL_AMOUNT = "TOTAL_AMOUNT";
    public static final String COLUMN_ORDER_TIMAE = "TIMAE";
    public static final String COLUMN_ORDER_STATUS = "STATUS";

    // sequences 
    public static final String ADDRESSES_SEQUENCES = "ADDRESSES_SEQ";
    public static final String PRODUCT_SEQUENCES = "PRODUCT_SEQ";
    public static final String CREDIT_SEQUENCES = "CREDIT_CARD_SEQ";
    public static final String USERSES_SEQUENCES = "E_USERS_SEQ";
    public static final String CATEGORY_SEQUENCES = "PRODUCT_CATEGORY_SEQ";
    public static final String ORDERS_SEQUENCES = "USER_ORDERS_SEQ";

    // getAllProducts Method 
    public static final int SELECT_ALL = 0;
    
    // public static final String SELECT_ID = "SELECT_ID";
    public static final int SELECT_ACTIVE = -1;
    
    //Product Status
    public static final int PRODUCT_ACTIVE = 1;
    public static final int PRODUCT_INACTIVE = 0;
    
    //Error code
    public static final int ERROR_FAILED = 0;
    public static final int ERROR_ALREADY_EXIST = -1;
    public static final int ERROR_SUCCESS = 1;
    
    // Undefined Category
    public static final String UNDEFINED_CATEGORY = "Undefined";
    public static final int UNDEFINED_CATEGORY_ID = 0;
}
