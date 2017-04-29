
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.Exception;

import MelbourneWeather2.MelbourneWeather2Stub;
import MelbourneWeather2.MelbourneWeather2Stub.*;

import javax.swing.*;

/*
 * Basic example of use of MelbourneWeather2 Axis2 web services in Java
 * IMPORTANT: This is not intended as an example of good design. It is
 * simply an illustration of the basics.
 * 
 * Author:  David.Squire@monash.edu
 * Last Modified: 20170405
 */		

public class TestWeatherService {

	// set up some constants to index into the result arrays
	private static final int TimestampIndex = 0;
	private static final int RainfallIndex = 1;
	private static final int TemperatureIndex = 1;
	
	public static void main(String[] args) throws Exception {

		final MelbourneWeather2Stub MelbourneWeatherService = new MelbourneWeather2Stub();
		
		// Get the available locations from the web service
		GetLocationsResponse LocationsResponse = MelbourneWeatherService.getLocations();
		String[] Locations = LocationsResponse.get_return();


//		// Creating and opening the main frame
//		JFrame frame = new JFrame();    // Define JFrame
//		System.out.print("ok");
//		frame.setContentPane(new MainFrame().mainPanel);     // Call panel inside frame
//		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//		frame.addWindowListener(new WindowAdapter() {
//			public void windowClosing(WindowEvent e) {
//				System.exit(0);
//			}
//		});
//		frame.pack();
//		frame.setVisible(true);



//		// Loop over the locations, and display the temperature and rainfall at each
//		for (int i = 0; i < Locations.length; i++) {
//			// Get rainfall
//			GetRainfall RainfallRequest = new GetRainfall();
//			RainfallRequest.setLocation(Locations[i]);
//			GetRainfallResponse RainfallResponse = MelbourneWeatherService.getRainfall(RainfallRequest);
//			String[] Rainfall = RainfallResponse.get_return();
//			// Get temperature
//			GetTemperature TemperatureRequest = new GetTemperature();
//			TemperatureRequest.setLocation(Locations[i]);
//			GetTemperatureResponse TemperatureResponse = MelbourneWeatherService.getTemperature(TemperatureRequest);
//			String[] Temperature = TemperatureResponse.get_return();
//			System.out.print(
//				Locations[i]
//				+ " @ " + Rainfall[TimestampIndex]
//				+ ":\n\tTemperature:\t" + Temperature[TemperatureIndex]
//				+ "\n\tRainfall:\t" + Rainfall[RainfallIndex]
//				+ "\n\n"
//			);
//		}
	}

}
