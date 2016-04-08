package Assignment5;

public class HumidThread implements Runnable {
    //The humidifer rate
    private double rate;

    //The limits
    private double upperLimit;
    private double lowerLimit;

    //Will get this using the limits
    private double target;

    //Common location to store data
    private GreenHouseEnvironment GHE;

    //Tells if the humidfier is on
    private boolean humid = false;

    /**
     * The constructor for a humid thread
     *
     * @param UL Upper Limit of allowed soil moisture
     * @param LL Lower Limit of allowed soil moisture
     * @param r  Rate at which humidifier restores humidity
     * @param g  the collective Greenhouse environement
     */
    public HumidThread(double UL, double LL, double r, GreenHouseEnvironment g) {
        super();
        rate = r;
        upperLimit = UL;
        lowerLimit = LL;
        target = (UL - 3);
        GHE = g;
    }

    /**
     * Tells whether or not humidifier is on, true if it is
     *
     * @return boolean - humid
     */
    public boolean isHumidON() {
        return humid;
    }

    public void run() {
        // TODO Auto-generated method stub

    }
}
