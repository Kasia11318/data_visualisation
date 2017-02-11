package Model;

import java.io.*;
import java.util.List;
import java.util.StringTokenizer;
import java.util.Vector;

public class Project {

    private String projectName;

    private Data data;

    private Vector<Integer> unaccountedColumn;

    private String pathSavedFile;

    private String defaultOpenFilesType;

    private boolean defaultDataSeriesNames;

    private boolean defaultDimensionNames;

    public Project() {
        unaccountedColumn = new Vector<>();
        data = new Data();
    }

    public void setData(Data data) {
        this.data = data;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public void setUnaccountedColumn(Vector<Integer> unaccountedColumn) {
        this.unaccountedColumn = unaccountedColumn;
    }

    public void setPathSavedFile(String pathSavedFile) {
        this.pathSavedFile = pathSavedFile;
    }

    public void setDefaultOpenFilesType(String defaultOpenFilesType) {
        this.defaultOpenFilesType = defaultOpenFilesType;
    }

    public void setDefaultDataSeriesNames(boolean defaultDataSeriesNames) {
        this.defaultDataSeriesNames = defaultDataSeriesNames;
    }

    public void setDefaultDimensionNames(boolean defaultDimensionNames) {
        this.defaultDimensionNames = defaultDimensionNames;
    }

    public Data getData() {
        return data;
    }

    public String getProjectName() {
        return projectName;
    }

    public Vector<Integer> getUnaccountedColumn() {
        return unaccountedColumn;
    }

    public String getPathSavedFile() {
        return pathSavedFile;
    }

    public String getDefaultOpenFilesType() {
        return defaultOpenFilesType;
    }

    public boolean isDefaultDataSeriesNames() {
        return defaultDataSeriesNames;
    }

    public boolean isDefaultDimensionNames() {
        return defaultDimensionNames;
    }

    public void loadProject(String filePath) throws IOException {
        FileInputStream fileStream = new FileInputStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream));
        String fileLine;

        // project properties
        fileLine = bufferedReader.readLine();
        if (fileLine == null) {
            throw new IOException(); // can change to something like ProjectException if needed
        }
        StringTokenizer st = new StringTokenizer(fileLine, ",");
        if (st.countTokens() != 4) {
            throw new IOException(); // can change to something like ProjectException if needed
        }
        String temp;
        this.projectName = st.nextToken();
        this.defaultOpenFilesType = st.nextToken();
        temp = st.nextToken();
        this.defaultDataSeriesNames = (temp.equals("true"));
        temp = st.nextToken();
        this.defaultDimensionNames = (temp.equals("true"));

        // TODO: loading columns, data series names, dimension names, points

        this.pathSavedFile = filePath;

        bufferedReader.close();
    }

    public void newProject(String projectName, String pathSavedFile, String defaultOpenFilesType,
                           boolean defaultDataSeriesNames, boolean defaultDimensionNames) {
        this.projectName = projectName;
        this.pathSavedFile = pathSavedFile;
        this.defaultOpenFilesType = defaultOpenFilesType;
        this.defaultDataSeriesNames = defaultDataSeriesNames;
        this.defaultDimensionNames = defaultDimensionNames;
    }

    public void saveProject() throws IOException {
        StringBuilder sb = new StringBuilder();

        File file = new File(pathSavedFile);

        if (!file.exists()) {
            file.createNewFile();
        }

        FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

        // project properties
        sb.append(projectName).append(",").append(defaultOpenFilesType).append(",").append(defaultDataSeriesNames)
                .append(",").append(defaultDimensionNames);
        bufferedWriter.write(sb.toString());
        bufferedWriter.newLine();

        // clearing StringBuilder
        sb.setLength(0);

        // unaccounted columns
        for (Integer column : unaccountedColumn) {
            sb.append(column).append(",");
        }
        if (unaccountedColumn.isEmpty()) {
            sb.append("-1"); // no unaccounted columns
        }
        bufferedWriter.write(sb.toString());
        bufferedWriter.newLine();

        // data series names
        if (!defaultDataSeriesNames) {
            sb.setLength(0);
            for (String dataSeriesName : data.getDataSeriesNames()) {
                sb.append(dataSeriesName).append(",");
            }
            bufferedWriter.write(sb.toString());
            bufferedWriter.newLine();
        }

        // dimension names
        if (!defaultDimensionNames) {
            sb.setLength(0);
            for (String dimensionName : data.getDimensionNames()) {
                sb.append(dimensionName).append(",");
            }
            bufferedWriter.write(sb.toString());
            bufferedWriter.newLine();
        }

        // point values
        for (List<Float> pointValues : data.getData()) {
            sb.setLength(0);
            for (Float value : pointValues) {
                sb.append(value).append(",");
            }
            bufferedWriter.write(sb.toString());
            bufferedWriter.newLine();
        }

        bufferedWriter.close();
    }
}