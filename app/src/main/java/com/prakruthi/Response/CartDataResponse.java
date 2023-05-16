package com.prakruthi.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CartDataResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
//    @SerializedName("user_id")
//    @Expose
//    private Integer userId;
    @SerializedName("product_id")
    @Expose
    private Integer productId;
    @SerializedName("quantity")
    @Expose
    private Integer quantity;
//    @SerializedName("logged_date")
//    @Expose
//    private String loggedDate;
//    @SerializedName("created_by")
//    @Expose
//    private Integer createdBy;
//    @SerializedName("updated_by")
//    @Expose
//    private Object updatedBy;
//    @SerializedName("updated_date")
//    @Expose
//    private Object updatedDate;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("customer_price")
    @Expose
    private String customerPrice;
//    @SerializedName("color")
//    @Expose
//    private String color;
//    @SerializedName("size")
//    @Expose
//    private String size;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("attachment")
    @Expose
    private String attachment;

//    public CartDataModal(int id, String name, String attachment) {
//        this.id = id;
//        this.name = name;
//        this.attachment = attachment;
//    }

    public CartDataResponse(int id, String name, String attachment, String description) {
        this.id = id;
        this.name = name;
        this.attachment = attachment;
        this.description = description;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getUserId() {
//        return userId;
//    }

//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

//    public String getLoggedDate() {
//        return loggedDate;
//    }

//    public void setLoggedDate(String loggedDate) {
//        this.loggedDate = loggedDate;
//    }

//    public Integer getCreatedBy() {
//        return createdBy;
//    }

//    public void setCreatedBy(Integer createdBy) {
//        this.createdBy = createdBy;
//    }

//    public Object getUpdatedBy() {
//        return updatedBy;
//    }

//    public void setUpdatedBy(Object updatedBy) {
//        this.updatedBy = updatedBy;
//    }

//    public Object getUpdatedDate() {
//        return updatedDate;
//    }

//    public void setUpdatedDate(Object updatedDate) {
//        this.updatedDate = updatedDate;
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCustomerPrice() {
        return customerPrice;
    }

    public void setCustomerPrice(String customerPrice) {
        this.customerPrice = customerPrice;
    }

//    public String getColor() {
//        return color;
//    }

//    public void setColor(String color) {
//        this.color = color;
//    }

//    public String getSize() {
//        return size;
//    }

//    public void setSize(String size) {
//        this.size = size;
//    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAttachment() {
        return attachment;
    }

    public void setAttachment(String attachment) {
        this.attachment = attachment;
    }
}
