package Assignment5;

public class GreenHouseThread implements Runnable {
    //Ambient rates
    private double tempRate = 0;
    private double soilRate = 0;
    private double humidRate = 0;

    /**
     * The constructor for the greenhouse thread
     *
     * @param tr rate at which temperature changes, can be negative or positive
     * @param sr rate at which soil dries up, will be negative
     * @param hr rate at which air becomes more arid, will be negative
     */
    public GreenHouseThread(double tr, double sr, double hr) {
        super();
        tempRate = tr;
        soilRate = sr;
        humidRate = hr;
    }

    public void run() {
        //make the threads all run here
    }

}
