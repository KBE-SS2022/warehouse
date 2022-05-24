package warehouse.api.entity;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "Pizza")
public class Pizza {
    @Id
    private Long id;
    @Column(name="name")
    private String name;

    // Requirements: Ingredient "Brot" is mandatory,
    // each ingredient may only occure once
    @Column
    @ManyToMany
    private List<Ingredient> ingredients;


    public Pizza() {}

    public Pizza(Long id, String name, List<Ingredient> ingredients) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Pizza {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ingredients=" + ingredients +
                '}';
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<Ingredient> getIngredients() { return ingredients; }

    public void setIngredients(List<Ingredient> ingredients) { this.ingredients = ingredients; }
}
