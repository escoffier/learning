import java.awt.*;

public class FinalDemo {
    static class CoordinateSystem {
        private final Point origin = new Point(0, 0);

        public Point getOrigin() {
            return origin;
        }
    }

    public static void main(String[] args) {
        CoordinateSystem coordinateSystem = new CoordinateSystem();
        coordinateSystem.getOrigin().x = 1;

        assert coordinateSystem.getOrigin().getX() == 0;
    }
}
