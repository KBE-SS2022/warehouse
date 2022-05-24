package warehouse.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "ingredient")
public class Ingredient {
    @Id
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="brand")
    private String brand;
    @Column(name="countryOrigin")
    private String countryOrigin;
    @Column(name="nutritionScore")
    private char nutritionScore;
    @Column(name="calories")
    private Integer calories;
    @Column(name="amount")
    private Integer amount;
    @Column(name="weight")
    private Double weight;
    @Column(name="price")
    private Double price;


    public Ingredient() {}

    public Ingredient(Long id, String name, String brand, String countryOrigin, char nutritionScore,
                      Integer calories, Integer amount, Double weight, Double price) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.countryOrigin = countryOrigin;
        this.nutritionScore = nutritionScore;
        this.calories = calories;
        this.amount = amount;
        this.weight = weight;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Ingredient {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", countryOrigin='" + countryOrigin + '\'' +
                ", nutritionScore=" + nutritionScore +
                ", calories=" + calories +
                ", amount=" + amount +
                ", weight=" + weight +
                ", price=" + price +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
        this.countryOrigin = countryOrigin;
    }

    public char getNutritionScore() {
        return nutritionScore;
    }

    public void setNutritionScore(char nutritionScore) {
        this.nutritionScore = nutritionScore;
    }

    public Integer getCalories() {
        return calories;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getPrice() { return price; }

    public void setPrice(Double price) { this.price = price; }
}


