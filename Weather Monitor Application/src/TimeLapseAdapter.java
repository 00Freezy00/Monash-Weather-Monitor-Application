import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TimeLapseAdapter.java
 * An adapter class which translates data from LocationObserver.java and passes it to WeatherTimeLapseFrame
 * to display a graph view.
 *
 * JFreeChart related functions referenced from official JFreeChart website:
 * https://github.com/ngadde/playground/blob/master/com.iis.sample1/src/main/java/demo/PriceVolumeDemo1.java
 *
 * Author: Yifei (Freya) Gao, Yun Hao (Jack) Zhang
 */

public class TimeLapseAdapter implements MonitorAdapter {
    private WeatherTimeLapseFrame weatherTimeLapseFrame;
    private LocationObserver locationObserver;
    private String location;
    private boolean[] displayMode;
    private TimeSeries temperatureSeries = new TimeSeries("Temperature");
    private TimeSeries rainfallSeries = new TimeSeries("Rainfall");
    private SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");

    /**
     * An init function that constructs a TimeLapseAdapter
     * @param displayMode A boolean array which represents user's selection. [0] represents temperature, [1] represents rainfall
     * @param location A string representing location name
     * @param source A string representing the data source it comes from
     */
    public TimeLapseAdapter(boolean[] displayMode, String location, String source) {
        this.displayMode = displayMode;
        this.location = location;
        this.weatherTimeLapseFrame = new WeatherTimeLapseFrame(source + " Time Lapse", this, location);
        applyChart();
    }

    /**
     * Called to add an entry of temperature into graph
     * @param temperature String representing temperature in degree C
     * @param temperatureTimeStamp String containing the timestamp of temperature update
     */
    @Override
    public void displayTemperature(String temperature, String temperatureTimeStamp) {
        if (displayMode[0]) {
            // Add new temperature to series
            addToSeries(this.temperatureSeries, temperature, temperatureTimeStamp);
        }
    }

    /**
     * Called to add an entry of rainfall into graph
     * @param rainFall String representing rainfall in mm
     * @param rainFallTimeStamp String containing the timestamp of rainfall update
     */
    @Override
    public void displayRainFall(String rainFall, String rainFallTimeStamp) {
        if (displayMode[1]) {
            // Add new rainfall to series
            addToSeries(this.rainfallSeries, rainFall, rainFallTimeStamp);
        }
    }

    // Modifies graph series to update graph

    /**
     * Adds the given value and timestamp to the series of the graph
     * @param series TimeSeries for temperature or rainfall
     * @param value String representing value
     * @param timestamp String representing timestamp of given value
     */
    private void addToSeries(TimeSeries series, String value, String timestamp) {
        try {
            Date date = dateFormat.parse(timestamp);
            float v = Float.parseFloat(value);
            series.addOrUpdate(new Millisecond(date), v);
        } catch (Exception e) {
        }
    }

    /**
     * A method provided to WeatherTimeLapseFrame to close the adapter
     */
    @Override
    public void disposeMyself() {
        this.locationObserver.removeMonitorAdapter(this);
    }

    /**
     * Sets the location observer for this adapter
     */
    @Override
    public void setLocationObserver(LocationObserver locationObserver) {
        this.locationObserver = locationObserver;
    }


    // jFreeChart implementation

    /**
     * A method to create a JFreeChart chart according to user's selection on temperature/rainfall
     *
     * JFreeChart referenced from:
     * https://github.com/ngadde/playground/blob/master/com.iis.sample1/src/main/java/demo/PriceVolumeDemo1.java
     */
    private JFreeChart createChart() {
        String title = "Weather Information for " + this.location;
        String value1;
        XYDataset data1;

        // Change axis series and label depending on user's selection.
        if (displayMode[0]) {
            data1 = new TimeSeriesCollection(temperatureSeries);
            value1 = "Temperature (C)";
        } else {
            data1 = new TimeSeriesCollection(rainfallSeries);
            value1 = "Rainfall (mm)";
        }

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                "Time",
                value1,
                data1,
                true,
                true,
                false
        );
        ChartUtilities.applyCurrentTheme(chart);

        XYPlot plot = (XYPlot) chart.getPlot();
        // Set date axis display format
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("dd/MM/yyyy H:mm"));

        NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
        rangeAxis1.setAutoRangeIncludesZero(false);
        // Leave space for other plot
        rangeAxis1.setUpperMargin(0.20);
        rangeAxis1.setLowerMargin(0.80);
        DecimalFormat format = new DecimalFormat("0.00");
        rangeAxis1.setNumberFormatOverride(format);
        // Apply shape to plots for better visibility
        Shape shape = new Ellipse2D.Double(-1.0, -1.0, 2.0, 2.0);
        XYLineAndShapeRenderer renderer1 = (XYLineAndShapeRenderer)
                plot.getRenderer();
        renderer1.setBaseShapesVisible(true);
        renderer1.setSeriesShape(0, shape);

        // second dataset only created when both temperature and rainfall selected
        if (displayMode[0] && displayMode[1]) {
            NumberAxis rangeAxis2 = new NumberAxis("Rainfall (mm)");
            // Leave room for temperature plot
            rangeAxis2.setUpperMargin(1.00);
            rangeAxis1.setLowerMargin(0.20);
            plot.setRangeAxis(1, rangeAxis2);
            // Assign the axis a series
            XYDataset data2 = new TimeSeriesCollection(this.rainfallSeries);
            plot.setDataset(1, data2);
            plot.setRangeAxis(1, rangeAxis2);
            plot.mapDatasetToRangeAxis(1, 1);
            XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
            renderer2.setSeriesShape(0, shape);
            renderer2.setSeriesPaint(0, Color.BLUE);
            plot.setRenderer(1, renderer2);
        }
        ChartUtilities.applyCurrentTheme(chart);
        return chart;
    }

    public void applyChart() {
        JFreeChart chart = createChart();
        //ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);

        // TODO: figure out if can apply chart to a panel instead of the whole frame

//        JPanel panel = new JPanel();
//        panel.setLayout(new java.awt.BorderLayout());
//        panel.add(chartPanel, BorderLayout.CENTER);
//        panel.validate();
//        weatherTimeLapseFrame.setGraphPanel(panel);

        weatherTimeLapseFrame.setGraphPanel(chart);

        weatherTimeLapseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        weatherTimeLapseFrame.pack();
        weatherTimeLapseFrame.setVisible(true);
    }




}
