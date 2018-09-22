/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsf.managedBean;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Map;
import org.primefaces.model.chart.DonutChartModel;
import java.util.LinkedHashMap;
import javax.annotation.PostConstruct;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartSeries;
import java.util.Random;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.PieChartModel;
/**
 *
 * @author hongxu
 */
@Named(value = "adminPageManagedBean")
@SessionScoped
public class AdminPageManagedBean implements Serializable {
    private DonutChartModel donutModel1;
    private LineChartModel lineModel1;
    private BarChartModel barModel;

    private PieChartModel pieModel1;

    public PieChartModel getPieModel1() {
        return pieModel1;
    }

    public void setPieModel1(PieChartModel pieModel1) {
        this.pieModel1 = pieModel1;
    }
     


    public BarChartModel getBarModel() {
        return barModel;
    }

    public void setBarModel(BarChartModel barModel) {
        this.barModel = barModel;
    }
    public LineChartModel getLineModel1() {
        return lineModel1;
    }

    public void setLineModel1(LineChartModel lineModel1) {
        this.lineModel1 = lineModel1;
    }

    public LineChartModel getLineModel2() {
        return lineModel2;
    }

    public void setLineModel2(LineChartModel lineModel2) {
        this.lineModel2 = lineModel2;
    }
    private LineChartModel lineModel2;
    
    public DonutChartModel getDonutModel1() {
        return donutModel1;
    }

    public void setDonutModel1(DonutChartModel donutModel1) {
        this.donutModel1 = donutModel1;
    }
    /**
     * Creates a new instance of AdminPageManagedBean
     */
    public AdminPageManagedBean() {
    }
    
    @PostConstruct
    public void init() {
        createDonutModels();
        createLineModels();
        createBarModel();
        createPieModels();
    }
    
    private void createDonutModels() {
        donutModel1 = initDonutModel();
        donutModel1.setTitle("Age Distribution");
        donutModel1.setLegendPosition("w");
        

        
    }

    private DonutChartModel initDonutModel() {
        DonutChartModel model = new DonutChartModel();
         
        Map<String, Number> circle1 = new LinkedHashMap<String, Number>();
        circle1.put("21-35", 168);
        circle1.put("36-50", 78);
        circle1.put("51-65", 34);
        circle1.put("65+", 10);
        model.addCircle(circle1);
         
        
//         
//        Map<String, Number> circle3 = new LinkedHashMap<String, Number>();
//        circle3.put("Brand 1", 40);
//        circle3.put("Brand 2", 325);
//        circle3.put("Brand 3", 402);
//        circle3.put("Brand 4", 421);
//        model.addCircle(circle3);
         
        return model;
    }

    
    private void createLineModels() {
        lineModel1 = initLinearModel();
        lineModel1.setTitle("Age-Savings Relationship");
        lineModel1.setLegendPosition("e");
        Axis yAxis = lineModel1.getAxis(AxisType.Y);
        yAxis.setMin(3000);
        yAxis.setMax(19000);
        
        
        Axis xAxis = lineModel1.getAxis(AxisType.X);
        xAxis.setMin(21);
        xAxis.setMax(75);
        
        xAxis.setLabel("Age");
//         
////        lineModel2 = initCategoryModel();
//        lineModel2.setTitle("Category Chart");
//        lineModel2.setLegendPosition("e");
//        lineModel2.setShowPointLabels(true);
//        lineModel2.getAxes().put(AxisType.X, new CategoryAxis("Years"));
//        yAxis = lineModel2.getAxis(AxisType.Y);
          yAxis.setLabel("Salary");
//        yAxis.setMin(0);
//        yAxis.setMax(200);
    }
        
        private LineChartModel initLinearModel() {
        LineChartModel model = new LineChartModel();
 
        LineChartSeries series1 = new LineChartSeries();
        
       
        series1.setLabel("Age-Salary");
        
       
        
        for(int i=21;i<75;i++){
            series1.set(i, i*200+new Random().nextInt(2000));
        }
       /* series1.set(1, 2);
        series1.set(2, 1);
        series1.set(3, 3);
        series1.set(4, 6);
        series1.set(5, 8);
 
        LineChartSeries series2 = new LineChartSeries();
        series2.setLabel("Series 2");
 
        series2.set(1, 6);
        series2.set(2, 3);
        series2.set(3, 2);
        series2.set(4, 7);
        series2.set(5, 9);*/
 
        model.addSeries(series1);
        //model.addSeries(series2);
         
        return model;
    }
        
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Expected Repayment Year-Average Salary");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Expected Repayment Year");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Average Salary");
        yAxis.setMin(4000);
        yAxis.setMax(20000);
    } 
    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
        ChartSeries boys = new ChartSeries();
        boys.setLabel("Boys");
        boys.set("No Loan", 17500);
        boys.set("5 Years", 13834);
        boys.set("10 Years", 9988);
        boys.set("20 Years", 8731);
        boys.set("30 Years", 7982);
 

 
        model.addSeries(boys);

         
        return model;
    }
    private void createPieModels() {
        pieModel1 = new PieChartModel();
         
        pieModel1.set("<0.2ms", 540);
        pieModel1.set("0.2ms-0.3ms", 325);
        pieModel1.set("0.3ms-0.4ms", 100);
        pieModel1.set("0.4ms-0.5ms", 50);
         
        pieModel1.setTitle("Query Time Distribution");
        pieModel1.setLegendPosition("w");
        pieModel1.setShadow(false);
    }
}
