package Assignment5;

public class GreenHouseThread implements Runnable
{
	//Initialize a runnable tempthread with these
	private double tempUpperLimit;
	private double tempLowerLimit;
	
	//Initialize a runnable humidthread with these
	private double humidUpperLimit;
	private double humidLowerLimit;
	
	//Initilaize a runnable soilthread with these
	private double soilUpperLimit;
	private double soilLowerLimit;
	
	private boolean furnON = false;
	private boolean sprinkON = false;
	private boolean humidON = false;
	
	private double temperature = 0;
	private double soilMoisture = 0;
	private double humidity = 0;
	
	public GreenHouseThread(double tUL, double tLL, double hUL, double hLL, double sUL, double sLL)
	{
		super();
	}

	public void run() 
	{
		//make the threads all run here
	}
	
	public boolean isFurnON()
	{
		return furnON;
	}
	
	public boolean isSprinkON()
	{
		return sprinkON;
	}
	
	public boolean isHumidON()
	{
		return humidON;
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
