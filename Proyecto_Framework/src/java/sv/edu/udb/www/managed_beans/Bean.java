/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.managed_beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.charts.ChartData;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.donut.DonutChartDataSet;
import org.primefaces.model.charts.donut.DonutChartModel;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.title.Title;
import sv.edu.udb.www.entities.MusicEntity;
import sv.edu.udb.www.utils.DBConnection;

/**
 *
 * @author carlo
 */
@ManagedBean
@RequestScoped
public class Bean {

    Connection con = null;
    Statement statement = null;
    ResultSet resultSet = null;
    int like;
    String nombre;
    private DonutChartModel donutModel;

    @PostConstruct
    public void init() {
        donutModel = new DonutChartModel();
        ChartData data = new ChartData();
        DonutChartDataSet dataSet = new DonutChartDataSet();
        List<Number> values = new ArrayList<>();
        List<String> labels = new ArrayList<>();
        try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("select * from music");

            while (resultSet.next()) {
                like = resultSet.getInt("likes");
                nombre = resultSet.getString("nombreCancion");
                values.add(like);
                labels.add(nombre);
            }
        } catch (SQLException e) {
            System.out.println("error" + e);
        }

        dataSet.setData(values);

        List<String> bgColors = new ArrayList<>();
        bgColors.add("rgb(255, 99, 132)");
        bgColors.add("rgb(54, 162, 235)");
        bgColors.add("rgb(255, 205, 86)");
        bgColors.add("rgb(201, 203, 207)");
        bgColors.add("rgb(255, 87, 51)");
        bgColors.add("#A1FF59");
        bgColors.add("#FFF959");
        bgColors.add("#9C4AFF");
        bgColors.add("#00C0E6");
        bgColors.add("#F65E5D");
        bgColors.add("#FFBC47");
        bgColors.add("#1D2D60");
        bgColors.add("#F2F2F0");

        dataSet.setBackgroundColor(bgColors);
        Legend legend = new Legend();
        legend.setDisplay(true);
        legend.setPosition("top");
        
        data.addChartDataSet(dataSet);
        data.setLabels(labels);
        
        BarChartOptions options = new BarChartOptions();
        Title title = new Title();
        title.setDisplay(true);
        title.setText("Gráfica de Likes");
        options.setTitle(title);
        donutModel.setData(data);
        
    }

    public DonutChartModel getDonutModel() {
        return donutModel;
    }
}
