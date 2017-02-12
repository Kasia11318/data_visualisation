package Controller;

import Model.Data;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by root on 11.02.17.
 */
public class DataController {
    public Data importData(String nameOfFile) throws IOException{
        Data data = new Data();
        ArrayList<List<Float>> datas = new ArrayList<>();
        ArrayList<String> seriesName = new ArrayList<>();
        String[] row;

        String regex = chooseRegex(nameOfFile);

        URL url = DataController.class.getResource("data/" + nameOfFile);
        File file = new File(url.getPath());

        FileReader in = new FileReader(file);
        BufferedReader bfr = new BufferedReader(in);

        String line = bfr.readLine();

        row = line.split(regex);

        data.setDimensionNames(Arrays.asList(row).subList(1, row.length));
        for (int i = 1; i < row.length; i++) {
            datas.add(new ArrayList<>());
        }

        line = bfr.readLine();
        while (line != null) {
            row = line.split(regex);
            seriesName.add(row[0]);
            for (int i = 1; i < row.length; i++) {
                datas.get(i-1).add(Float.parseFloat(row[i]));
            }
            line = bfr.readLine();
        }

        data.setData(datas);
        data.setDataSeriesNames(seriesName);

        return data;
    }

    private String chooseRegex(String nameOfFile) {
        String regex = null;
        String type = nameOfFile.substring(nameOfFile.lastIndexOf('.')+1);
        if (type.equalsIgnoreCase("txt")){
            regex = "\\s+";
        } else if (type.equalsIgnoreCase("csv")){
            regex = ",";
        }
        return regex;
    }



    public static void main(String args[]){
        DataController dataController = new DataController();
        try {
            dataController.importData("data1.txt");
        } catch (IOException e) {
            System.out.print("error :<");
        }
    }
}
