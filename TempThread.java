package Assignment5;

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

    //Boolean to indicate if furnace is on, if not, then AC has to be on
    private boolean furn = false;

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
        super();
        upperLimit = UL;
        lowerLimit = LL;
        heatingRate = hr * 3600;
        coolingRate = cr * 3600;
        target = (UL - 3);
        ambientTempRate = ar * 3600;
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

    public void run() {
        // Puts the thread to sleep till the next update interval
        try {
            Thread.sleep(Double.doubleToLongBits(GHE.tempChangeRate * 1000));
        } catch (InterruptedException e) {
        }

        // Only runs the thread code when the thread is allowed to run
        while (GHE.runSubThreads == true) {
            // Applies the natural change in temperature to the value
            GHE.changeTemp(GHE.temperature() + ambientTempRate * GHE.tempChangeRate);

            // Checks to see whether it is better to turn the AC on or the furnace & performs the appropriate adjustments
            if (GHE.temperature() > target) {
                furn = false;
                GHE.changeTemp(GHE.temperature() + coolingRate * GHE.tempChangeRate);
            } else if (GHE.temperature() < target) {
                furn = true;
                GHE.changeTemp(GHE.temperature() + heatingRate * GHE.tempChangeRate);
            }
            System.out.println("TEMP: " + GHE.temperature());

            // Puts the thread to sleep till the next update interval
            try {
                Thread.sleep(Double.doubleToLongBits(GHE.tempChangeRate * 1000));
            } catch (InterruptedException e) {
            }
        }
        return;
    }

}
