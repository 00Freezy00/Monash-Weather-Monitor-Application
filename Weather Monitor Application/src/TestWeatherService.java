
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

	
	public static void main(String[] args) throws Exception {

			LocationSubject locationSubject = new LocationSubject();
				try {
					// MainFrame frame = new MainFrame();
					JFrame mainFrame = new JFrame("Weather Monitor Application");
					mainFrame.setContentPane(new MainFrame(locationSubject).mainPanel);
					mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
					mainFrame.pack();
					mainFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

	}

