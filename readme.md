# Weather Monitor Application
### FIT3077 Assignment 1

###### Team members:
- YunHao (Jack) Zhang
- Yifei (Freya) Gao

This Weather Monitor Application allows the user to view the temperature and/or rainfall at locations selected from those supported by the MelbourneWeather2 or MelbourneWeatherTimeLapse web service.

### How to Run
On the GUI window, first select to use the service to use (MelbourneWeather2 or MelbourneWeatherTimeLapse), and click the "Load Locations" button to retrieve a list of all locations.

Once the list has been generated, select one or more locations, tick at least one of "Show Temperature" and "Show Rainfall", and click either "Display Live Information" or "Display Change Over Time" button to display the corresponding information.
"Display Live Information" will display specific values of rainfall or temperature received from the server, and "Display Change Over Time" will display continuous values retrieved from the server over time and display them in a graph view.

Every 5 minutes for source MelbourneWeather2 and every 20 seconds for MelbourneWeatherTimeLapse, the system will automatically update the data of opened locations every 5 minutes.