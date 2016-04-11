package Assignment5;

public class SoilThread extends Thread {
    //The sprinkler rate in seconds
    private double rate;

    //The limits
    private double upperLimit;
    private double lowerLimit;

    //Will get this using the limits
    private double target;

    //Ambient rate
    private double soilRate = 10;

    //Common location to store data
    private GreenHouseEnvironment GHE;

    //Tells whether or not sprinkler is on
    private boolean sprink = false;

    /**
     * The constructor for a soil thread
     *
     * @param UL Upper Limit of allowed soil moisture
     * @param LL Lower Limit of allowed soil moisture
     * @param r  Rate at which sprinkler restores soil moisture
     * @param sr rate at which air becomes more arid, will be negative
     * @param g  the collective Greenhouse environment
     */
    public SoilThread(double UL, double LL, double r, double sr, GreenHouseEnvironment g) {
        super();
        rate = r / 60;
        upperLimit = UL;
        lowerLimit = LL;
        target = (UL - 3);
        soilRate = sr;
        GHE = g;
    }

    /**
     * boolean to tell whether or not sprinkler is active. true if it is on
     *
     * @return boolean - sprink
     */
    public boolean isSprinkON() {
        return sprink;
    }

    public void run() {
        while (GHE.runSubThreads == true) {

        }
        return;
    }
}
