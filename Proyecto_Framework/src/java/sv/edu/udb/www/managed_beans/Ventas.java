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
import org.primefaces.model.charts.axes.cartesian.CartesianScales;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearAxes;
import org.primefaces.model.charts.axes.cartesian.linear.CartesianLinearTicks;
import org.primefaces.model.charts.bar.BarChartDataSet;
import org.primefaces.model.charts.bar.BarChartModel;
import org.primefaces.model.charts.bar.BarChartOptions;
import org.primefaces.model.charts.optionconfig.legend.Legend;
import org.primefaces.model.charts.optionconfig.legend.LegendLabel;
import org.primefaces.model.charts.optionconfig.title.Title;
import sv.edu.udb.www.utils.DBConnection;

/**
 *
 * @author carlo
 */
@ManagedBean
@RequestScoped
public class Ventas {

    Connection con = null;
    Statement statement = null;
    ResultSet resultSet = null;
    int like;
    String nombre;
    private BarChartModel barModel;
    //Options
    BarChartOptions options = new BarChartOptions();
    CartesianScales cScales = new CartesianScales();
    CartesianLinearAxes linearAxes = new CartesianLinearAxes();
    CartesianLinearTicks ticks = new CartesianLinearTicks();
    Legend legend = new Legend();
    Title title = new Title();
    List<String> borderColor = new ArrayList<>();
    List<String> bgColor = new ArrayList<>();
    List<String> labels = new ArrayList<>();
    LegendLabel legendLabels = new LegendLabel();
    List<Number> values = new ArrayList<>();
        
    @PostConstruct
    public void init() {
        barModel = new BarChartModel();
        ChartData data = new ChartData();

        BarChartDataSet barDataSet = new BarChartDataSet();
        barDataSet.setLabel("Canción mas vendida");
            
         try {
            con = DBConnection.createConnection();
            statement = con.createStatement();
            resultSet = statement.executeQuery("SELECT m.nombreCancion, COUNT(v.idMusic) AS TotalVentas FROM ventas v "
                    + "INNER JOIN music m on m.idMusic = v.idMusic GROUP BY v.idMusic ORDER BY TotalVentas DESC");

            while (resultSet.next()) {
                like = resultSet.getInt("TotalVentas");
                nombre = resultSet.getString("nombreCancion");
                values.add(like);
                labels.add(nombre);
            }
        } catch (SQLException e) {
            System.out.println("error" + e);
        }
        barDataSet.setData(values);

        bgColor.add("rgba(255, 99, 132, 0.2)");
        bgColor.add("rgba(255, 159, 64, 0.2)");
        bgColor.add("rgba(255, 205, 86, 0.2)");
        bgColor.add("rgba(75, 192, 192, 0.2)");
        bgColor.add("rgba(54, 162, 235, 0.2)");
        bgColor.add("rgba(153, 102, 255, 0.2)");
        bgColor.add("rgba(201, 203, 207, 0.2)");
        barDataSet.setBackgroundColor(bgColor);

        borderColor.add("rgb(255, 99, 132)");
        borderColor.add("rgb(255, 159, 64)");
        borderColor.add("rgb(255, 205, 86)");
        borderColor.add("rgb(75, 192, 192)");
        borderColor.add("rgb(54, 162, 235)");
        borderColor.add("rgb(153, 102, 255)");
        borderColor.add("rgb(201, 203, 207)");
        barDataSet.setBorderColor(borderColor);
        barDataSet.setBorderWidth(1);

        data.addChartDataSet(barDataSet);

        
        data.setLabels(labels);

        //Data
        barModel.setData(data);

        ticks.setBeginAtZero(true);
        linearAxes.setTicks(ticks);
        cScales.addYAxesData(linearAxes);
        options.setScales(cScales);
        title.setDisplay(true);
        title.setText("Canción mas vendida");
        options.setTitle(title);

        legend.setDisplay(false);
        legend.setPosition("top");
        legendLabels.setFontStyle("bold");
        legendLabels.setFontColor("#2980B9");
        legendLabels.setFontSize(24);
        legend.setLabels(legendLabels);
        options.setLegend(legend);

        barModel.setOptions(options);
    }

    public BarChartModel getBarModel() {
        return barModel;
    }

}
