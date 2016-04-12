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
    //The sprinkler rate in seconds
    private double rate;

    //The limits
    private double upperLimit;
    private double lowerLimit;

    //Ambient rate
    private double ambientSoilRate = 10;

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
        ambientSoilRate = sr / 60;
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
        // Puts the thread to sleep till the next update interval
        try {
            Thread.sleep((long) (GHE.soilChangeRate * 1000));
        } catch (InterruptedException e) {
        }

        // Booleans to determine whether the sprinkler should be on or not
        boolean reachLowerLimit = false;
        boolean reachUpperLimit = false;

        // Checks to see where the initial soil moisture is to update the booleans
        if (GHE.soilMoisture() > lowerLimit) {
            reachLowerLimit = true;
            reachUpperLimit = false;
        } else if (GHE.soilMoisture() < lowerLimit) {
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

            // Puts the thread to sleep till the next update interval
            try {
                Thread.sleep((long) (GHE.soilChangeRate * 1000));
            } catch (InterruptedException e) {
            }
        }
        return;
    }
}
