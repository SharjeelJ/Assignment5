package Assignment5;

public class HumidThread implements Runnable 
{
	//The humidifer rate
	private double rate;
	
	private double upperLimit;
	private double lowerLimit;
	
	private double target;
	
	private double current = 0;
	
	public HumidThread(double cHumid, double UL, double LL, double r)
	{
		super();
		rate = r;
		upperLimit = UL;
		lowerLimit = LL;
		current = cHumid;
		target = (UL - 3);
	}
	
	public double current()
	{
		return current;
	}

	public void run() {
		// TODO Auto-generated method stub
		
	}
}
