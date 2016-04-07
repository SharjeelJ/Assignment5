package Assignment5;

public class GreenHouseEnvironment 
{
	private double temperature = 0;
	private double soilMoisture = 0;
	private double humidity = 0;
	
	public GreenHouseEnvironment(double t, double s, double h)
	{
		temperature = t;
		soilMoisture = s;
		humidity = h;
	}
	
	public double temperature()
	{
		return temperature;
	}
	public double soilMoisture()
	{
		return soilMoisture;
	}
	public double humidity()
	{
		return humidity;
	}
	
	public synchronized void changeTemp(double t)
	{
		temperature = t;
		return;
	}
	public synchronized void changeSoil(double s)
	{
		soilMoisture = s;
		return;
	}
	public synchronized void changeHumidiity(double h)
	{
		humidity = h;
		return;
	}
	
}
