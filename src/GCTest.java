

import java.util.ArrayList;
import java.util.List;

public class GCTest {

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<OOMObject>();
        while (true) {
            list.add(new OOMObject());
        }

    }

    static class OOMObject {

    }
}
