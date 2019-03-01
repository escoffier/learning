public class Apple {
    public Apple() {
        this.weight = 0;
        this.color = "yellow";
    }

    public Apple(Integer weight) {
        this.weight = weight;
        this.color = "yellow";
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Apple { weight : " + weight + ", color : " + color + " }";
    }

    private Integer weight;
    private String color;
}
