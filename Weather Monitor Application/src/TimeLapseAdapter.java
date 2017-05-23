import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.MonthConstants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Referenced from:
 * https://github.com/ngadde/playground/blob/master/com.iis.sample1/src/main/java/demo/PriceVolumeDemo1.java#L19
 */
public class TimeLapseAdapter implements MonitorAdapter {
    private WeatherTimeLapseFrame weatherTimeLapseFrame;
    private LocationObserver locationObserver;
    private String location;
    private boolean[] displayMode;
    private TimeSeries temperatureSeries = new TimeSeries("Temperature");
    private TimeSeries rainfallSeries = new TimeSeries("Rainfall");
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy H:mm:ss");

    public TimeLapseAdapter(boolean[] displayMode, String location, String source) {
        this.displayMode = displayMode;
        this.location = location;
        this.weatherTimeLapseFrame = new WeatherTimeLapseFrame(source + " Time Lapse", this, location);
        applyChart();
    }

    @Override
    public void displayTemperature(String temperature, String temperatureTimeStamp) {
        if (displayMode[0]) {
            // Add new temperature to series
            addToSeries(this.temperatureSeries, temperature, temperatureTimeStamp);
        }
    }

    @Override
    public void displayRainFall(String rainFall, String rainFallTimeStamp) {
        if (displayMode[1]) {
            // Add new rainfall to series
            addToSeries(this.rainfallSeries, rainFall, rainFallTimeStamp);
        }
    }

    // Modifies graph series to update graph
    private void addToSeries(TimeSeries series, String value, String timestamp) {
        try {
            Date date = dateFormat.parse(timestamp);
            System.out.print(date.toString() + "\n");   // TODO: test
            float v = Float.parseFloat(value);
            series.addOrUpdate(new Millisecond(date), v);
        } catch (Exception e) {
        }
    }

    @Override
    public void disposeMyself() {
        this.locationObserver.removeMonitorAdapter(this);
    }

    @Override
    public void setLocationObserver(LocationObserver locationObserver) {
        this.locationObserver = locationObserver;
    }


    // jFreeChart implementation

    /**
     * JFreeChart referenced from:
     * https://github.com/ngadde/playground/blob/master/com.iis.sample1/src/main/java/demo/PriceVolumeDemo1.java
     */

    // Removed "static"
    private JFreeChart createChart() {
        String title = "Weather Information for " + this.location;
        String value1;
        XYDataset data1;

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

        Shape shape = new Ellipse2D.Double(-1.0, -1.0, 2.0, 2.0);
        XYLineAndShapeRenderer renderer1 = (XYLineAndShapeRenderer)
                plot.getRenderer();
        renderer1.setBaseShapesVisible(true);
        renderer1.setSeriesShape(0, shape);
//        renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
//                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
//                new SimpleDateFormat("d-mm-yyyy"), new DecimalFormat("0.00")));

        if (displayMode[0] && displayMode[1]) {  // second dataset only created when both temperature and rainfall selected
            NumberAxis rangeAxis2 = new NumberAxis("Rainfall (mm)");
            // Leave room for temperature plot
            rangeAxis2.setUpperMargin(1.00);
            rangeAxis1.setLowerMargin(0.20);
            plot.setRangeAxis(1, rangeAxis2);
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
        ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);

        // TODO: figure out if can apply chart to a panel instead of the whole frame

//        JPanel panel = new JPanel();
//        panel.setLayout(new java.awt.BorderLayout());
//        panel.add(chartPanel, BorderLayout.CENTER);
//        panel.validate();
//        weatherTimeLapseFrame.setGraphPanel(panel);

        weatherTimeLapseFrame.setContentPane(chartPanel);

        weatherTimeLapseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        weatherTimeLapseFrame.pack();
        weatherTimeLapseFrame.setVisible(true);
    }




}
