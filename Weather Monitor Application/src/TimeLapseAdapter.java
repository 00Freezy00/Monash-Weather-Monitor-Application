import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardXYToolTipGenerator;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.StandardXYBarPainter;
import org.jfree.chart.renderer.xy.XYBarRenderer;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.IntervalXYDataset;
import org.jfree.data.xy.XYDataset;
import org.jfree.date.MonthConstants;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

/**
 * Created by Freya on 19/05/2017.
 */
public class TimeLapseAdapter implements MonitorAdapter {
    private WeatherTimeLapseFrame weatherTimeLapseFrame;
    private boolean[] displayMode;

    private LocationObserver locationObserver;

    public TimeLapseAdapter(boolean[]displayMode, String location, String source) {
        this.displayMode = displayMode;

        weatherTimeLapseFrame = new WeatherTimeLapseFrame(source + " Time Lapse", this, location);

        JFreeChart chart = createChart(location);
        ChartPanel chartPanel = new ChartPanel(chart, true, true, true, false, true);

//        JPanel panel = new JPanel();
//        panel.setLayout(new java.awt.BorderLayout());
//        panel.add(chartPanel, BorderLayout.CENTER);
//        panel.validate();
//        weatherTimeLapseFrame.setGraphPanel(panel);

        weatherTimeLapseFrame.setContentPane(chartPanel);

        weatherTimeLapseFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//        displayOption();
        weatherTimeLapseFrame.pack();
        weatherTimeLapseFrame.setVisible(true);
    }

    @Override
    public void displayTemperature(String temperature) {

    }

    @Override
    public void displayRainFall(String rainFall) {

    }

    @Override
    public void disposeMyself() {
        this.locationObserver.removeMonitorAdapter(this);
    }

    @Override
    public void displayLastUpdated(String timeStamp){
        displayRetrievalTime();
//        weatherTimeLapseFrame.setRainTimestampLabel(timeStamp);
//        weatherTimeLapseFrame.setTempTimestampLabel(timeStamp);
    }

    private void displayRetrievalTime() {
        Date date = new Date();
        DateFormat DateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        weatherTimeLapseFrame.setLastUpdated(DateFormat.format(date));
    }

    @Override
    public void setLocationObserver(LocationObserver locationObserver) {
        this.locationObserver = locationObserver;
    }




    // jFreeChart implementation

    /**
     * Creates a chart.
     *
     * @return a chart.
     */
    private static JFreeChart createChart(String location) {
        String title = "Weather Information for " + location;
        XYDataset tempratureData = createTemperatureDataset();

        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                title,
                "Time",
                "Temperature",
                tempratureData,
                true,
                true,
                false
        );

//            JFreeChart chart = ChartFactory.createTimeSeriesChart(
//                title,
//                "Time",
//                "Temperature",
//                tempratureData,
//                true,
//                true,
//                false
//        );

        ChartUtilities.applyCurrentTheme(chart);

        XYPlot plot = (XYPlot) chart.getPlot();

        NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
        rangeAxis1.setAutoRangeIncludesZero(false);
        rangeAxis1.setLowerMargin(0.40);  // to leave room for rainfall plot
        DecimalFormat format = new DecimalFormat("00.00");
        rangeAxis1.setNumberFormatOverride(format);

        Shape shape = new Ellipse2D.Double(-4.0, -4.0, 8.0, 8.0);
        XYLineAndShapeRenderer renderer1 = (XYLineAndShapeRenderer)
                plot.getRenderer();
        renderer1.setBaseShapesVisible(true);
        renderer1.setSeriesShape(0, shape);
                renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                new SimpleDateFormat("d-mm-yyyy"), new DecimalFormat("0.00")));

        NumberAxis rangeAxis2 = new NumberAxis("Rainfall");
        rangeAxis2.setUpperMargin(1.00);  // to leave room for temperature plot
        plot.setRangeAxis(1, rangeAxis2);
        XYDataset dataset2 = createRainfallDataset();
        plot.setDataset(1, dataset2);
        plot.setRangeAxis(1, rangeAxis2);
        plot.mapDatasetToRangeAxis(1, 1);
        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
        plot.setRenderer(1, renderer2);
        renderer2.setSeriesShape(0, shape);
        renderer2.setSeriesPaint(0, Color.BLUE);
        renderer2.setBaseToolTipGenerator(
                new StandardXYToolTipGenerator(
                        StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                        new SimpleDateFormat("d-mm-yyyy"),
                        new DecimalFormat("0.00")));
        plot.setRenderer(1, renderer2);
        ChartUtilities.applyCurrentTheme(chart);




//        XYPlot plot = (XYPlot) chart.getPlot();
//        NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
//        rangeAxis1.setLowerMargin(0.40);  // to leave room for rainfall plot
//        DecimalFormat format = new DecimalFormat("00.00");
//        rangeAxis1.setNumberFormatOverride(format);
//
//        XYLineAndShapeRenderer renderer1 = new XYLineAndShapeRenderer();
//        renderer1.setSeriesPaint(0, Color.RED);
//        renderer1.setBaseToolTipGenerator(new StandardXYToolTipGenerator(
//                StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
//                new SimpleDateFormat("d-MM-yyyy"), new DecimalFormat("0.00")));
//
//        NumberAxis rangeAxis2 = new NumberAxis("Rainfall");
//        rangeAxis2.setUpperMargin(1.00);  // to leave room for temperature plot
//        plot.setRangeAxis(1, rangeAxis2);
//        plot.setDataset(1, createRainfallDataset());
//        plot.setRangeAxis(1, rangeAxis2);
//        plot.mapDatasetToRangeAxis(1, 1);
//        XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
//        renderer2.setSeriesPaint(0, Color.BLUE);
//        renderer2.setBaseToolTipGenerator(
//                new StandardXYToolTipGenerator(
//                        StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
//                        new SimpleDateFormat("d-MM-yyyy"),
//                        new DecimalFormat("0.00")));
//        plot.setRenderer(1, renderer2);
//        ChartUtilities.applyCurrentTheme(chart);

        return chart;
    }

        /**
         * Creates a sample dataset.
         *
         * @return A sample dataset.
         */
        private static XYDataset createTemperatureDataset() {

            // create dataset 1...
            TimeSeries series1 = new TimeSeries("Temperature");

            series1.add(new Day(2, MonthConstants.JANUARY, 2002), 95.565);
            series1.add(new Day(3, MonthConstants.JANUARY, 2002), 95.640);
            series1.add(new Day(4, MonthConstants.JANUARY, 2002), 95.710);

            series1.add(new Day(7, MonthConstants.JANUARY, 2002), 95.930);
            series1.add(new Day(8, MonthConstants.JANUARY, 2002), 95.930);
            series1.add(new Day(9, MonthConstants.JANUARY, 2002), 95.960);
            series1.add(new Day(10, MonthConstants.JANUARY, 2002), 96.055);
            series1.add(new Day(11, MonthConstants.JANUARY, 2002), 96.335);

            return new TimeSeriesCollection(series1);
        }

        /**
         * Creates a sample dataset.
         *
         * @return A sample dataset.
         */

        private static XYDataset createRainfallDataset() {

            // create dataset 2...
            TimeSeries series2 = new TimeSeries("Rainfall");

            series2.add(new Day(2, MonthConstants.JANUARY, 2002), 100.000);
            series2.add(new Day(3, MonthConstants.JANUARY, 2002), 90.000);
            series2.add(new Day(4, MonthConstants.JANUARY, 2002), 100.000);

            series2.add(new Day(7, MonthConstants.JANUARY, 2002), 90.000);
            series2.add(new Day(8, MonthConstants.JANUARY, 2002), 100.000);
            series2.add(new Day(9, MonthConstants.JANUARY, 2002), 90.000);
            series2.add(new Day(10, MonthConstants.JANUARY, 2002), 100.000);
            series2.add(new Day(11, MonthConstants.JANUARY, 2002), 90.000);

            return new TimeSeriesCollection(series2);
        }






















}
