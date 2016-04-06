package Assignment5;

public class TempThread implements Runnable
{
	private double rate;
	
	private double upperLimit;
	private double lowerLimit;
	
	private double target;
	
	private double current;
	
	public TempThread(double cTemp, double UL, double LL, double hr, double cr, double hr)
	{
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
