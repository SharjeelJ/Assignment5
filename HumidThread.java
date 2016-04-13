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
    // The humidifier rate per second
    private double rate;

    // The upper and lower limits
    private double upperLimit;
    private double lowerLimit;

    // Ambient change rate per second
    private double ambientHumidRate;

    // Common location to store data
    private GreenHouseEnvironment GHE;

    // Boolean to indicate if the humidifier is on
    private boolean humid = false;

    /**
     * The constructor for a humidity thread
     *
     * @param UL Upper Limit of allowed humidity
     * @param LL Lower Limit of allowed humidity
     * @param r  Rate at which humidifier restores humidity
     * @param hr Rate at which soil dries up
     * @param g  The collective Greenhouse environment
     */
    public HumidThread(double UL, double LL, double r, double hr, GreenHouseEnvironment g) {
        rate = r / 60;
        upperLimit = UL;
        lowerLimit = LL;
        ambientHumidRate = hr / 60;
        GHE = g;
    }

    /**
     * Returns whether or not humidifier is on. True if it is.
     *
     * @return boolean - humid
     */
    public boolean isHumidON() {
        return humid;
    }

    /**
     * Main method to run the threaded program code
     */
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
        if (GHE.humidity() >= lowerLimit) {
            reachLowerLimit = true;
            reachUpperLimit = false;
        } else if (GHE.humidity() <= lowerLimit) {
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

            // Checks to make sure that the humidity value is never less than 0 or greater than 100
            if (GHE.humidity() < 0) {
                GHE.changeHumidity(0);
            } else if (GHE.humidity() > 100) {
                GHE.changeHumidity(100);
            }

            // Adds the data into the ArrayList To log the simulation (if an out of bounds error is experienced reattempts to write to the log file)
            try {
                GHE.logData.add("HUMIDITY: " + Double.toString(GHE.humidity()));
            } catch (ArrayIndexOutOfBoundsException e) {
                GHE.logData.add("HUMIDITY: " + Double.toString(GHE.humidity()));
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
