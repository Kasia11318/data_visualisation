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
    private List<float> data;

    /**
     * Names of consecutive dimensions
     */
    private List<String> dimensionNames;

    /**
     * Names of consecutive data series
     */
    private List<String> dataSeriesNames;

    public Data() {
        data = new ArrayList<>();
        dimensionNames = new ArrayList<>();
        dataSeriesNames = new ArrayList<>();
    }

    public List<float> getData() {
        return data;
    }

    public List<String> getDataSeriesNames() {
        return dataSeriesNames;
    }

    public List<String> getDimensionNames() {
        return dimensionNames;
    }

    public void setData(List<float> data) {
        this.data = data;
    }

    public void setDataSeriesNames(List<String> dataSeriesNames) {
        this.dataSeriesNames = dataSeriesNames;
    }

    public void setDimensionNames(List<String> dimensionNames) {
        this.dimensionNames = dimensionNames;
    }
}
