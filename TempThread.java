package Assignment5;

public class TempThread implements Runnable
{
	//AC and furnace rates
	private double heatingRate;
	private double coolingRate;
	
	private double upperLimit;
	private double lowerLimit;
	
	private double target;
	
	private double current = 0;
	
	public TempThread(double cTemp, double UL, double LL, double hr, double cr)
	{
		super();
		upperLimit = UL;
		lowerLimit = LL;
		target = (UL - 3);
		current = cTemp;
	}
	public double current()
	{
		return current;
	}

	public void run() 
	{	
		
	}

}
