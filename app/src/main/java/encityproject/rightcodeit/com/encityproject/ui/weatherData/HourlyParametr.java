package encityproject.rightcodeit.com.encityproject.ui.weatherData;

public class HourlyParametr {
    private String icon;
    private double Temp;
    private double Hum;
    private double Pres;
    private long time;

    public HourlyParametr(String icon, double temp, double hum, double pres, long time) {
        this.icon = icon;
        Temp = temp;
        Hum = hum;
        Pres = pres;
        this.time = time;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public double getTemp() {
        return Temp;
    }

    public void setTemp(double temp) {
        Temp = temp;
    }

    public double getHum() {
        return Hum;
    }

    public void setHum(double hum) {
        Hum = hum;
    }

    public double getPres() {
        return Pres;
    }

    public void setPres(double pres) {
        Pres = pres;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }


}
