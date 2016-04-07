package Assignment5;

public class TempThread implements Runnable
{
	//AC and furnace rates
	private double heatingRate;
	private double coolingRate;
	
	//The limits 
	private double upperLimit;
	private double lowerLimit;
	
	//Will get this using the limits
	private double target;
	
	//Common location to store data
	private GreenHouseEnvironment GHE;
	
	//Boolean to indicate if furnace is on, if not, then AC has to be on
	private boolean furn = false;
	
	/**
	 * The constructor for a temperature thread
	 * @param UL Upper Limit for temperature
	 * @param LL Lower Limit for temperature
	 * @param hr Heating rate of furnace
	 * @param cr Cooling Rate of AC (negative value)
	 * @param g The collective greenhouse environment
	 */
	public TempThread(double UL, double LL, double hr, double cr, GreenHouseEnvironment g)
	{
		super();
		upperLimit = UL;
		lowerLimit = LL;
		target = (UL - 3);
		GHE = g;
	}
	
	/**
	 * returns whether or not furnace is on. True if it is.
	 * @return boolean - furn
	 */
	public boolean isFurnON()
	{
		return furn;
	}

	public void run() 
	{	
		
	}

}
