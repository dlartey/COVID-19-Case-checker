package comp1721.cwk1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

import java.io.IOException;

public class CovidChart extends Application {

    @Override public void start(Stage stage) {

        try{
            CovidDataset data = new CovidDataset();
            stage.setTitle("COMP1721 Coursework 1");
            //defining the axes
            final NumberAxis xAxis = new NumberAxis(280,349,5);
            final NumberAxis yAxis = new NumberAxis();
            xAxis.setLabel("Day of Year");
            yAxis.setLabel("Active Cases");
            //creating the chart
            final LineChart<Number,Number>lineChart = new LineChart<Number,Number>(xAxis,yAxis);
            lineChart.setCreateSymbols(false);

            lineChart.setTitle("Active Coronavirus Cases, University of Leeds");
            //defining a series
            XYChart.Series series = new XYChart.Series();
            series.setName("../datafiles/2020-daily.csv");
            //populating the series with data
            data.readDailyCases("../datafiles/2020-daily.csv");
            int startDate = 281;
            for (int i = 9; i < data.size(); i++){
                int activeCases = 0;

                for (int a = i-9; a<=i; a++){
                    activeCases = activeCases + data.getRecord(a).getOtherCases()
                            +data.getRecord(a).getStudentCases()+data.getRecord(a).getStaffCases();

                }
                series.getData().add(new XYChart.Data(startDate, activeCases));
                startDate+=1;
            }

            Scene scene  = new Scene(lineChart,800,600);
            lineChart.getData().add(series);

            stage.setScene(scene);
            stage.show();
        }catch(IOException e){
            System.out.println("Unable to find file");
        }

    }


    public static void main(String[] args) {
        launch(args);
    }
}