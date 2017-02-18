package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a single point on a diagram
 *
 * @author Grzesio
 * @version 1.0
 */
public class Point {

    private List<Float> dimensionals;

    public Point() {
        dimensionals = new ArrayList<>();
    }

    public List<Float> getDimensionals() {
        return dimensionals;
    }

    public void setDimensionals(List<Float> dimensionals) {
        this.dimensionals = dimensionals;
    }
}
