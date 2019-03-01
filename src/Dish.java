public class Dish {

    private Integer calories;
    private String name;

    public Dish(Integer calories, String name) {
        this.calories = calories;
        this.name = name;
    }

    public Integer getCalories() {
        return calories;
    }

    public String getName() {
        return name;
    }

    public void setCalories(Integer calories) {
        this.calories = calories;
    }

    @Override
    public Dish clone() {
        return new Dish(this.calories, this.name);
    }

    @Override
    public String toString() {
        return "Dish {calories : "+calories + ", name : "+ name + "}";
    }
}
