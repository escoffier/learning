package StreamDemo;


public class Dish {

    private Integer calories;
    private String name;
    private DishType dishType;

    public Dish(Integer calories, String name, DishType dishType) {
        this.calories = calories;
        this.name = name;
        this.dishType = dishType;
    }

    public Dish(Integer calories, String name) {
        this.calories = calories;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public DishType getDishType() {
        return dishType;
    }

    public void setDishType(DishType dishType) {
        this.dishType = dishType;
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
        return "Dish {calories : "+calories + ", name : "+ name + ", type : "+ dishType +"}";
    }
}
