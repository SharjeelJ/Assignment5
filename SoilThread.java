package Assignment5;

public class SoilThread implements Runnable
{
	//The sprinkler rate
	private double rate;
	
	//The limits 
	private double upperLimit;
	private double lowerLimit;
		
	//Will get this using the limits
	private double target;
		
	//Common location to store data
	private GreenHouseEnvironment GHE;
	
	private boolean sprink = false;
	
	public SoilThread(double UL, double LL, double r,GreenHouseEnvironment g)
	{
		super();
		rate = r;
		upperLimit = UL;
		lowerLimit = LL;
		target = (UL - 3);
		GHE = g;
	}
	
	public boolean isSprinkON()
	{
		return sprink;
	}

	public void run() 
	{
		
	}
}
