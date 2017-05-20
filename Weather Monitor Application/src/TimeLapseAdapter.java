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

    public TimeLapseAdapter(boolean[]displayMode, String location, String source) {
        this.displayMode = displayMode;
        this.location = location;

        this.weatherTimeLapseFrame = new WeatherTimeLapseFrame(source + " Time Lapse", this, location);

        temperatureSeries.add(new Day(2, MonthConstants.JANUARY, 2002), 95.565);
        temperatureSeries.add(new Day(3, MonthConstants.JANUARY, 2002), 95.640);
        rainfallSeries.add(new Day(2, MonthConstants.JANUARY, 2002), 410);
        rainfallSeries.add(new Day(3, MonthConstants.JANUARY, 2002), 455);

        applyChart();
    }

    // TODO: also pass in timestamp, and convert them from String to Date objects

    @Override
    public void displayTemperature(String temperature) {
        if (displayMode[0]) {
            // Add new temperature to series
            float t = Float.parseFloat(temperature);
            temperatureSeries.add(new Day(4, MonthConstants.JANUARY, 2002), t);
        }
    }

    @Override
    public void displayRainFall(String rainFall) {
        if (displayMode[1]) {
            // Add new rainfall to series
            float r = Float.parseFloat(rainFall);
            rainfallSeries.add(new Day(4, MonthConstants.JANUARY, 2002), r);
        }
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
    // Removed "static"
    private JFreeChart createChart() {
        String title = "Weather Information for " + this.location;
        String value1;
        XYDataset data1;

        if (displayMode[0]) {
            data1 = new TimeSeriesCollection(temperatureSeries);
            value1 = "Temperature";
        } else {
            data1 = new TimeSeriesCollection(this.rainfallSeries);
            value1 = "Rainfall";
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

        NumberAxis rangeAxis1 = (NumberAxis) plot.getRangeAxis();
        rangeAxis1.setAutoRangeIncludesZero(false);
        rangeAxis1.setLowerMargin(0.40);
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

        if (displayMode[0] && displayMode[1]) {  // second dataset only created when both temperature and rainfall selected
            NumberAxis rangeAxis2 = new NumberAxis("Rainfall");
            rangeAxis2.setUpperMargin(1.00);  // to leave room for temperature plot
            plot.setRangeAxis(1, rangeAxis2);
            XYDataset data2 = new TimeSeriesCollection(this.rainfallSeries);
            plot.setDataset(1, data2);
            plot.setRangeAxis(1, rangeAxis2);
            plot.mapDatasetToRangeAxis(1, 1);
            XYLineAndShapeRenderer renderer2 = new XYLineAndShapeRenderer();
            renderer2.setSeriesShape(0, shape);
            renderer2.setSeriesPaint(0, Color.BLUE);
            renderer2.setBaseToolTipGenerator(
                    new StandardXYToolTipGenerator(
                            StandardXYToolTipGenerator.DEFAULT_TOOL_TIP_FORMAT,
                            new SimpleDateFormat("d-mm-yyyy"),
                            new DecimalFormat("0.00")));
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
