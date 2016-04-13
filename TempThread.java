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

public class TempThread extends Thread {
    // AC and furnace rates per second
    private double heatingRate;
    private double coolingRate;

    // The upper and lower temperature limits
    private double upperLimit;
    private double lowerLimit;

    // Target / ideal temperature
    private double target;

    // Ambient change rate per second
    private double ambientTempRate;

    // Common location to store data
    private GreenHouseEnvironment GHE;

    // Boolean to indicate if the furnace & AC are on
    private boolean furn = false;
    private boolean ac = false;

    /**
     * The constructor for a temperature thread
     *
     * @param UL Upper Limit for temperature
     * @param LL Lower Limit for temperature
     * @param hr Heating rate of furnace
     * @param cr Cooling Rate of AC
     * @param ar Ambient change rate
     * @param g  The collective greenhouse environment
     */
    public TempThread(double UL, double LL, double hr, double cr, double ar, GreenHouseEnvironment g) {
        upperLimit = UL;
        lowerLimit = LL;
        heatingRate = hr / 60;
        coolingRate = cr / 60;
        target = (UL - 3);
        ambientTempRate = ar / 60;
        GHE = g;
    }

    /**
     * Returns whether or not furnace is on. True if it is.
     *
     * @return boolean - furn
     */
    public boolean isFurnON() {
        return furn;
    }

    /**
     * Returns whether or not ac is on. True if it is.
     *
     * @return boolean - ac
     */
    public boolean isACON() {
        return ac;
    }

    /**
     * Main method to run the threaded program code
     */
    public void run() {
        // Puts the thread to sleep till the next update interval
        try {
            Thread.sleep((long) (GHE.tempChangeRate * 1000));
        } catch (InterruptedException e) {
        }

        // Booleans to determine whether the AC or furnace should be on or not
        boolean reachLowerLimit = false;
        boolean reachUpperLimit = false;

        // Checks to see where the initial temperature is to update the booleans
        if (GHE.temperature() >= target) {
            reachLowerLimit = true;
            reachUpperLimit = false;
        } else if (GHE.temperature() <= target) {
            reachLowerLimit = false;
            reachUpperLimit = true;
        }

        // Only runs the thread code when the thread is allowed to run
        while (GHE.runSubThreads == true) {
            // Applies the natural change in temperature to the value
            GHE.changeTemp(GHE.temperature() + ambientTempRate * GHE.tempChangeRate);

            // Checks to see whether it is better to turn the AC on or the furnace & performs the appropriate adjustments
            if (reachUpperLimit == true) {
                // Checks to see if the ambient rate is capable of heating by itself
                if (ambientTempRate > 0 && GHE.temperature() >= lowerLimit) {
                    furn = false;
                    ac = false;

                    // Checks to see if the upper limit is about to get hit to change the boolean
                    if (GHE.temperature() >= upperLimit - Math.abs(ambientTempRate) * GHE.tempChangeRate) {
                        reachLowerLimit = true;
                        reachUpperLimit = false;
                    }
                } else if (GHE.temperature() <= lowerLimit || ambientTempRate < 0) {
                    furn = true;
                    ac = false;
                    GHE.changeTemp(GHE.temperature() + heatingRate * GHE.tempChangeRate);

                    // Checks to see if the upper limit is about to get hit to change the boolean
                    if (GHE.temperature() >= upperLimit - Math.abs(ambientTempRate) * GHE.tempChangeRate - Math.abs(heatingRate) * GHE.tempChangeRate) {
                        reachLowerLimit = true;
                        reachUpperLimit = false;
                    }
                }
            } else if (reachLowerLimit == true) {
                // Checks to see if the ambient rate is capable of cooling by itself
                if (ambientTempRate < 0 && GHE.temperature() <= upperLimit) {
                    furn = false;
                    ac = false;

                    // Checks to see if the lower limit is about to get hit to change the boolean
                    if (GHE.temperature() <= lowerLimit + Math.abs(ambientTempRate) * GHE.tempChangeRate) {
                        reachLowerLimit = false;
                        reachUpperLimit = true;
                    }
                } else if (GHE.temperature() >= upperLimit || ambientTempRate > 0) {
                    furn = false;
                    ac = true;
                    GHE.changeTemp(GHE.temperature() + coolingRate * GHE.tempChangeRate);

                    // Checks to see if the lower limit is about to get hit to change the boolean
                    if (GHE.temperature() <= lowerLimit + Math.abs(ambientTempRate) * GHE.tempChangeRate + Math.abs(coolingRate) * GHE.tempChangeRate) {
                        reachLowerLimit = false;
                        reachUpperLimit = true;
                    }
                }
            }

            // Adds the data into the ArrayList to log the simulation
            GHE.logData.add("TEMPERATURE: " + Double.toString(GHE.temperature()));

            // Puts the thread to sleep till the next update interval
            try {
                Thread.sleep((long) (GHE.tempChangeRate * 1000));
            } catch (InterruptedException e) {
            }
        }
        return;
    }

}
