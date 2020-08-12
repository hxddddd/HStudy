package cn.kgc.smbms.pojo;

import java.io.Serializable;
import java.util.Date;

public class Bill implements Serializable {
    private Integer id;
    private String billCode;
    private String productName;
    private String productDesc;
    private String productUnit;
    private String productCount;
    private double totalPrice;
    private Integer isPayment;
    private Integer createdBy;
    private Date creationDate;
    private Integer cmodifyBy;
    private Date modifyDate;
    private Integer providerId;

    /*添加对应的关联关系,一对一*/
    private Provider provider;

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    public Bill(Integer id, String billCode, String productName, String productDesc, String productUnit, String productCount, double totalPrice, Integer isPayment, Integer createdBy, Date creationDate, Integer cmodifyBy, Date modifyDate, Integer providerId) {
        this.id = id;
        this.billCode = billCode;
        this.productName = productName;
        this.productDesc = productDesc;
        this.productUnit = productUnit;
        this.productCount = productCount;
        this.totalPrice = totalPrice;
        this.isPayment = isPayment;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.cmodifyBy = cmodifyBy;
        this.modifyDate = modifyDate;
        this.providerId = providerId;
    }

    public Bill() {
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billCode='" + billCode + '\'' +
                ", productName='" + productName + '\'' +
                ", provider=" + provider +
                '}';
    }
}
