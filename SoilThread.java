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
	
	//Tells whether or not sprinkler is on
	private boolean sprink = false;
	
	/**
	 * The constructor for a soil thread
	 * @param UL Upper Limit of allowed soil moisture
	 * @param LL Lower Limit of allowed soil moisture
	 * @param r Rate at which sprinkler restores soil moisture
	 * @param g the collective Greenhouse environement
	 */
	public SoilThread(double UL, double LL, double r, GreenHouseEnvironment g)
	{
		super();
		rate = r;
		upperLimit = UL;
		lowerLimit = LL;
		target = (UL - 3);
		GHE = g;
	}
	
	/**
	 * boolean to tell whether or not sprinkler is active. true if it is on
	 * @return boolean - sprink
	 */
	public boolean isSprinkON()
	{
		return sprink;
	}

	public void run() 
	{
		
	}
}
