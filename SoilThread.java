package Assignment5;

public class SoilThread implements Runnable
{
	//The sprinkler rate
	private double rate;
	
	private double upperLimit;
	private double lowerLimit;
	
	private double target;
	
	private double current = 0;
	
	public SoilThread(double cSoil, double UL, double LL, double r)
	{
		super();
		rate = r;
		upperLimit = UL;
		lowerLimit = LL;
		current = cSoil;
		target = (UL - 3);
	}
	public double current()
	{
		return current;
	}

	public void run() 
	{
		
	}
}
