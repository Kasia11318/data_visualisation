package Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Coordinate system with its parameters
 */
public class CoordinateSystem {

    public enum CoordinateSystemType {
        TWO_D, THREE_D, PARALLEL_D
    }

    public enum ReductionType {
        NONLINEAR, LINEAR
    }

    private List<Point> coordinates;

    private CoordinateSystemType coordinateSystemType;

    private ReductionType reductionType;

    private Data selectedData;

    public CoordinateSystem() {
        coordinates = new ArrayList<>();
    }

    public CoordinateSystem(ArrayList<Point> coordinates, CoordinateSystemType coordinateSystemType,
                            ReductionType reductionType, Data selectedData) {
        this.coordinates = coordinates;
        this.coordinateSystemType = coordinateSystemType;
        this.reductionType = reductionType;
        this.selectedData = selectedData;
    }

    public void addPoint(Point point) {
        coordinates.add(point);
    }

    public void reductionAlgorithm() {
        switch (reductionType) {
            case LINEAR:
                // linear reduction
                break;
            case NONLINEAR:
                // nonlinear reduction
                break;
            default:
                // do nothing
                break;
        }
    }

    public CoordinateSystemType getCoordinateSystemType() {
        return coordinateSystemType;
    }

    public Data getSelectedData() {
        return selectedData;
    }

    public List<Point> getCoordinates() {
        return coordinates;
    }

    public ReductionType getReductionType() {
        return reductionType;
    }

    public void setCoordinateSystemType(CoordinateSystemType coordinateSystemType) {
        this.coordinateSystemType = coordinateSystemType;
    }

    public void setSelectedData(Data selectedData) {
        this.selectedData = selectedData;
    }

    public void setCoordinates(List<Point> coordinates) {
        this.coordinates = coordinates;
    }

    public void setReductionType(ReductionType reductionType) {
        this.reductionType = reductionType;
    }
}
