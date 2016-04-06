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

}
