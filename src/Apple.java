
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Apple apple = (Apple) o;
        return com.google.common.base.Objects.equal(weight, apple.weight) &&
                com.google.common.base.Objects.equal(color, apple.color);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(weight, color);
    }

    private Integer weight;
    private String color;
}
