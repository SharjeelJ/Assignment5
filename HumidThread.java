package Assignment5;

/**
 * Assignment 5
 * Course: CPSC 233
 * Prof: Manzara
 * UCID: 30002229 & 30008424
 * TA session: 03
 *
 * @author Asjad Hassan Malick & Sharjeel Junaid
 */

public class HumidThread extends Thread {
    //The humidifier rate in seconds
    private double rate;

    //The limits
    private double upperLimit;
    private double lowerLimit;

    //Ambient rate
    private double ambientHumidRate = 10;

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
        ambientHumidRate = hr / 60;
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
        // Puts the thread to sleep till the next update interval
        try {
            Thread.sleep((long) (GHE.humidChangeRate * 1000));
        } catch (InterruptedException e) {
        }

        // Booleans to determine whether the humidifier should be on or not
        boolean reachLowerLimit = false;
        boolean reachUpperLimit = false;

        // Checks to see where the initial humidity is to update the booleans
        if (GHE.humidity() > lowerLimit) {
            reachLowerLimit = true;
            reachUpperLimit = false;
        } else if (GHE.humidity() < lowerLimit) {
            reachLowerLimit = false;
            reachUpperLimit = true;
        }

        // Only runs the thread code when the thread is allowed to run
        while (GHE.runSubThreads == true) {
            // Applies the natural change in humidity to the value
            GHE.changeHumidity(GHE.humidity() + ambientHumidRate * GHE.humidChangeRate);

            // Checks to see whether it is better to turn the humidifier on or not & performs the appropriate adjustments
            if (reachUpperLimit == true) {
                humid = true;
                GHE.changeHumidity(GHE.humidity() + rate * GHE.humidChangeRate);

                // Checks to see if the upper limit is about to get hit to change the boolean
                if (GHE.humidity() >= upperLimit - Math.abs(ambientHumidRate) * GHE.humidChangeRate - Math.abs(rate) * GHE.humidChangeRate) {
                    reachLowerLimit = true;
                    reachUpperLimit = false;
                }
            } else if (reachLowerLimit == true) {
                humid = false;

                // Checks to see if the upper limit is about to get hit to change the boolean
                if (GHE.humidity() <= lowerLimit + Math.abs(ambientHumidRate) * GHE.humidChangeRate) {
                    reachLowerLimit = false;
                    reachUpperLimit = true;
                }
            }

            // Puts the thread to sleep till the next update interval
            try {
                Thread.sleep((long) (GHE.humidChangeRate * 1000));
            } catch (InterruptedException e) {
            }
        }
        return;
    }
}
