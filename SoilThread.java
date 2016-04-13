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

public class SoilThread extends Thread {
    // The sprinkler rate per second
    private double rate;

    // The upper and lower soil moisture limits
    private double upperLimit;
    private double lowerLimit;

    // Ambient change rate per second
    private double ambientSoilRate;

    // Common location to store data
    private GreenHouseEnvironment GHE;

    // Boolean to indicate if the sprinkler is on
    private boolean sprink = false;

    /**
     * The constructor for a soil moisture thread
     *
     * @param UL Upper Limit of allowed soil moisture
     * @param LL Lower Limit of allowed soil moisture
     * @param r  Rate at which sprinkler restores soil moisture
     * @param sr Rate at which air becomes more arid
     * @param g  The collective Greenhouse environment
     */
    public SoilThread(double UL, double LL, double r, double sr, GreenHouseEnvironment g) {
        rate = r / 60;
        upperLimit = UL;
        lowerLimit = LL;
        ambientSoilRate = sr / 60;
        GHE = g;
    }

    /**
     * Returns whether or not sprinkler is on. True if it is.
     *
     * @return boolean - sprink
     */
    public boolean isSprinkON() {
        return sprink;
    }

    /**
     * Main method to run the threaded program code
     */
    public void run() {
        // Puts the thread to sleep till the next update interval
        try {
            Thread.sleep((long) (GHE.soilChangeRate * 1000));
        } catch (InterruptedException e) {
        }

        // Booleans to determine whether the sprinkler should be on or not
        boolean reachLowerLimit = false;
        boolean reachUpperLimit = false;

        // Checks to see where the initial soil moisture is to update the booleans
        if (GHE.soilMoisture() >= lowerLimit) {
            reachLowerLimit = true;
            reachUpperLimit = false;
        } else if (GHE.soilMoisture() <= lowerLimit) {
            reachLowerLimit = false;
            reachUpperLimit = true;
        }

        // Only runs the thread code when the thread is allowed to run
        while (GHE.runSubThreads == true) {
            // Applies the natural change in soil moisture to the value
            GHE.changeSoil(GHE.soilMoisture() + ambientSoilRate * GHE.soilChangeRate);

            // Checks to see whether it is better to turn the sprinkler on or not & performs the appropriate adjustments
            if (reachUpperLimit == true) {
                sprink = true;
                GHE.changeSoil(GHE.soilMoisture() + rate * GHE.soilChangeRate);

                // Checks to see if the upper limit is about to get hit to change the boolean
                if (GHE.soilMoisture() >= upperLimit - Math.abs(ambientSoilRate) * GHE.soilChangeRate - Math.abs(rate) * GHE.soilChangeRate) {
                    reachLowerLimit = true;
                    reachUpperLimit = false;
                }
            } else if (reachLowerLimit == true) {
                sprink = false;

                // Checks to see if the upper limit is about to get hit to change the boolean
                if (GHE.soilMoisture() <= lowerLimit + Math.abs(ambientSoilRate) * GHE.soilChangeRate) {
                    reachLowerLimit = false;
                    reachUpperLimit = true;
                }
            }

            // Checks to make sure that the soil moisture value is never less than 0 or greater than 100
            if (GHE.soilMoisture() < 0) {
                GHE.changeSoil(0);
            } else if (GHE.soilMoisture() > 100) {
                GHE.changeSoil(100);
            }

            // Adds the data into the ArrayList To log the simulation (if an out of bounds error is experienced reattempts to write to the log file)
            try {
                GHE.logData.add("SOIL-MOISTURE: " + Double.toString(GHE.soilMoisture()));
            } catch (ArrayIndexOutOfBoundsException e) {
                GHE.logData.add("SOIL-MOISTURE: " + Double.toString(GHE.soilMoisture()));
            }

            // Puts the thread to sleep till the next update interval
            try {
                Thread.sleep((long) (GHE.soilChangeRate * 1000));
            } catch (InterruptedException e) {
            }
        }
        return;
    }
}
