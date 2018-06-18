package bean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Goodinfo {
    private int id;
    private String goodName;
    private int stock;
    private double price;
    private int secondDirectoryId;
    private int saleVolumn;
    private String imgPath;
    private int publisherId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "goodName", nullable = false, length = 255)
    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    @Basic
    @Column(name = "stock", nullable = false)
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Basic
    @Column(name = "price", nullable = false, precision = 0)
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Basic
    @Column(name = "secondDirectoryId", nullable = false)
    public int getSecondDirectoryId() {
        return secondDirectoryId;
    }

    public void setSecondDirectoryId(int secondDirectoryId) {
        this.secondDirectoryId = secondDirectoryId;
    }

    @Basic
    @Column(name = "saleVolumn", nullable = false)
    public int getSaleVolumn() {
        return saleVolumn;
    }

    public void setSaleVolumn(int saleVolumn) {
        this.saleVolumn = saleVolumn;
    }

    @Basic
    @Column(name = "imgPath", nullable = false, length = 255)
    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    @Basic
    @Column(name = "publisherId", nullable = false)
    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Goodinfo goodinfo = (Goodinfo) o;

        if (id != goodinfo.id) return false;
        if (stock != goodinfo.stock) return false;
        if (Double.compare(goodinfo.price, price) != 0) return false;
        if (secondDirectoryId != goodinfo.secondDirectoryId) return false;
        if (saleVolumn != goodinfo.saleVolumn) return false;
        if (publisherId != goodinfo.publisherId) return false;
        if (goodName != null ? !goodName.equals(goodinfo.goodName) : goodinfo.goodName != null) return false;
        if (imgPath != null ? !imgPath.equals(goodinfo.imgPath) : goodinfo.imgPath != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (goodName != null ? goodName.hashCode() : 0);
        result = 31 * result + stock;
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + secondDirectoryId;
        result = 31 * result + saleVolumn;
        result = 31 * result + (imgPath != null ? imgPath.hashCode() : 0);
        result = 31 * result + publisherId;
        return result;
    }
}
