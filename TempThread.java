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
    //AC and furnace rates in seconds
    private double heatingRate;
    private double coolingRate;

    //The limits
    private double upperLimit;
    private double lowerLimit;

    //Will get this using the limits
    private double target;

    // Ambient rate
    private double ambientTempRate;

    //Common location to store data
    private GreenHouseEnvironment GHE;

    //Boolean to indicate if furnace & ac are on
    private boolean furn = false;
    private boolean ac = false;

    /**
     * The constructor for a temperature thread
     *
     * @param UL Upper Limit for temperature
     * @param LL Lower Limit for temperature
     * @param hr Heating rate of furnace
     * @param cr Cooling Rate of AC (negative value)
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
     * returns whether or not furnace is on. True if it is.
     *
     * @return boolean - furn
     */
    public boolean isFurnON() {
        return furn;
    }

    /**
     * returns whether or not ac is on. True if it is.
     *
     * @return boolean - ac
     */
    public boolean isACON() {
        return ac;
    }

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
                furn = true;
                ac = false;
                GHE.changeTemp(GHE.temperature() + heatingRate * GHE.tempChangeRate);

                // Checks to see if the upper limit is about to get hit to change the boolean
                if (GHE.temperature() >= upperLimit - ambientTempRate * GHE.tempChangeRate - heatingRate * GHE.tempChangeRate) {
                    reachLowerLimit = true;
                    reachUpperLimit = false;
                }
            } else if (reachLowerLimit == true) {
                furn = false;
                ac = true;
                GHE.changeTemp(GHE.temperature() + coolingRate * GHE.tempChangeRate);

                // Checks to see if the upper limit is about to get hit to change the boolean
                if (GHE.temperature() <= lowerLimit + ambientTempRate * GHE.tempChangeRate + coolingRate * GHE.tempChangeRate) {
                    reachLowerLimit = false;
                    reachUpperLimit = true;
                }
            }

            // Puts the thread to sleep till the next update interval
            try {
                Thread.sleep((long) (GHE.tempChangeRate * 1000));
            } catch (InterruptedException e) {
            }
        }
        return;
    }

}
