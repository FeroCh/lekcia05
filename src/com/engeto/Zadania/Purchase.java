package com.engeto.zadania;



import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;

public class Purchase {
    private String description;
    private Category category;
    private BigDecimal price;
    private LocalDate purchaseDate;

    public Purchase(String description, Category category, BigDecimal price, LocalDate purchaseDate) {
        this.description = description;
        this.category = category;
        this.price = price;
        this.purchaseDate = purchaseDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    @Override
    public String toString() {
        return "Purchase{" +
                "description='" + description + '\'' +
                ", category=" + category +
                ", price=" + price +
                ", purchaseDate=" + purchaseDate +
                '}';
    }
}
