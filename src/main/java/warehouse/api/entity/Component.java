package warehouse.api.entity;

public class Component {

    private Long id;
    private String name;
    private String lastName;
    private String countryOrigin;


    public Component(){}

    public Component(Long id, String name, String lastName, String countryOrigin) {
        super();
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.countryOrigin = countryOrigin;
    }


    // Overriding the toString method to find all the values
    @Override
    public String toString() {
        return "Component [id="
                + id + ", name="
                + name + ", lastName="
                + lastName + ", email="
                + countryOrigin + "]";
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCountryOrigin() {
        return countryOrigin;
    }

    public void setCountryOrigin(String countryOrigin) {
            this.countryOrigin = countryOrigin;
        }
}


