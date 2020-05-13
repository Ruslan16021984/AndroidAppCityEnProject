package encityproject.rightcodeit.com.encityproject.ui.weatherData;

public class HourlyParametr {
    private String icon;
    private double Temp;
    private double Hum;
    private double Pres;
    private long time;
    private int color;

    public HourlyParametr(String icon, double temp, double hum, double pres, long time, int color) {
        this.icon = icon;
        this.Temp = temp;
        this.Hum = hum;
        this.Pres = pres;
        this.time = time;
        this.color = color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
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
