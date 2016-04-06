package Assignment5;

public class GreenHouseThread implements Runnable
{
	
	private double tempRate = 0;
	private double soilRate = 0;
	private double humidRate = 0;
	
	private double temperature = 0;
	private double soilMoisture = 0;
	private double humidity = 0;
	
	public GreenHouseThread()
	{
		super();
	}

	public void run() 
	{
		//make the threads all run here
	}
	
	public double currentTemp()
	{
		return temperature;
	}
	
	public double currentSoil()
	{
		return soilMoisture;
	}
	
	public double currentHumidity()
	{
		return humidity;
	}

}
