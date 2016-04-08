package Assignment5;

public class GreenHouseEnvironment {
    //The shared values
    private double temperature = 0;
    private double soilMoisture = 0;
    private double humidity = 0;

    /**
     * Constructor for an environment
     *
     * @param t the initial temperature
     * @param s the initial soil moisture
     * @param h the initial humidity
     */
    public GreenHouseEnvironment(double t, double s, double h) {
        temperature = t;
        soilMoisture = s;
        humidity = h;
    }

    /**
     * returns current temperature
     *
     * @return double - temperature
     */
    public double temperature() {
        return temperature;
    }

    /**
     * returns current soilMoisture
     *
     * @return double - soilMoisture
     */
    public double soilMoisture() {
        return soilMoisture;
    }

    /**
     * returns current humidity
     *
     * @return double - humidity
     */
    public double humidity() {
        return humidity;
    }

    /**
     * Updates temperature
     *
     * @param t the new temperature
     */
    public synchronized void changeTemp(double t) {
        temperature = t;
        return;
    }

    /**
     * Updates soil moisture
     *
     * @param s the new soil moisture
     */
    public synchronized void changeSoil(double s) {
        soilMoisture = s;
        return;
    }

    /**
     * Updates humidity
     *
     * @param h the new humidity
     */
    public synchronized void changeHumidiity(double h) {
        humidity = h;
        return;
    }

}
