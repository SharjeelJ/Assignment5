package Assignment5;

public class HumidThread extends Thread {
    //The humidifier rate in seconds
    private double rate;

    //The limits
    private double upperLimit;
    private double lowerLimit;

    //Will get this using the limits
    private double target;

    //Ambient rate
    private double humidRate = 10;

    //Common location to store data
    private GreenHouseEnvironment GHE;

    //Tells if the humidifier is on
    private boolean humid = false;

    /**
     * The constructor for a humid thread
     *
     * @param UL Upper Limit of allowed soil moisture
     * @param LL Lower Limit of allowed soil moisture
     * @param r  Rate at which humidifier restores humidity
     * @param hr rate at which soil dries up, will be negative
     * @param g  the collective Greenhouse environment
     */
    public HumidThread(double UL, double LL, double r, double hr, GreenHouseEnvironment g) {
        super();
        rate = r / 60;
        upperLimit = UL;
        lowerLimit = LL;
        target = (UL - 3);
        humidRate = hr;
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
        while (GHE.runSubThreads == true) {

        }
        return;
    }
}
