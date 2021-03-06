package beans;

/**
 * Class that describes faculty;
 * @author Vladislav Prokopenko;
 */
public class Faculty {
    /**
     * ID faculty
     */
    private int id;

    /**
     * Faculty name
     */
    private String name;

    /**
     * Faculty description
     */
    private String description;

    /**
     * Amount of budget places
     */
    private int budgetAmount;

    /**
     * Amount of total places
     */
    private int totalAmount;

    /**
     * Logo url
     */
    private String logoUrl;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getBudgetAmount() {
        return budgetAmount;
    }

    public void setBudgetAmount(int budgetAmount) {
        this.budgetAmount = budgetAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }


    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", budgetAmount=" + budgetAmount +
                ", totalAmount=" + totalAmount +
                ", logoUrl='" + logoUrl + '\'' +
                '}';
    }
}
