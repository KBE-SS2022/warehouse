package warehouse.api.entity;

public class Ingredient {

    private Long id;
    private String name;
    private String brand;
    private String countryOrigin;
    private char nutritionScore;
    private Integer calories;
    private Integer amount;
    private Double weight;


    public Ingredient() {}

    public Ingredient(Long id, String name, String brand, String countryOrigin, char nutritionScore,
                      Integer calories, Integer amount, Double weight) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.countryOrigin = countryOrigin;
        this.nutritionScore = nutritionScore;
        this.calories = calories;
        this.amount = amount;
        this.weight = weight;
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
}


