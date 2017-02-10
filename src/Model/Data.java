package Model;

import java.util.List;
import java.util.ArrayList;

/**
 * Class representing imported data
 *
 * @author Grzesio
 * @version 1.0
 */
public class Data {

    /**
     * Point values in consecutive dimensions
     */
    private List<Float> data;

    /**
     * Names of consecutive dimensions
     */
    private List<String> dimensionNames;

    /**
     * Names of consecutive data series
     */
    private List<String> dataSeriesNames;

    public Data() {
        data = new ArrayList<Float>();
        dimensionNames = new ArrayList<>();
        dataSeriesNames = new ArrayList<>();
    }

    public List<Float> getData() {
        return data;
    }

    public List<String> getDataSeriesNames() {
        return dataSeriesNames;
    }

    public List<String> getDimensionNames() {
        return dimensionNames;
    }

    public void setData(List<Float> data) {
        this.data = data;
    }

    public void setDataSeriesNames(List<String> dataSeriesNames) {
        this.dataSeriesNames = dataSeriesNames;
    }

    public void setDimensionNames(List<String> dimensionNames) {
        this.dimensionNames = dimensionNames;
    }
}
