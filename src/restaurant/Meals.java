
package restaurant;


public class Meals {
 private int id;
 private double cost;
 private String type;
 private String name;

    public Meals(int id, double cost, String type, String name) {
        this.id = id;
        this.cost = cost;
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
 
 
}
