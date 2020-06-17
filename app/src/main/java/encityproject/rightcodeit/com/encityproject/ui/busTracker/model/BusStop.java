package encityproject.rightcodeit.com.encityproject.ui.busTracker.model;

public class BusStop {
    private String nameBusStop;
    private double latitude;
    private double longitude;


    public BusStop(String nameBusStop, double latitude, double longitude) {
        this.nameBusStop = nameBusStop;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getNameBusStop() {
        return nameBusStop;
    }

    public void setNameBusStop(String nameBusStop) {
        this.nameBusStop = nameBusStop;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "BusStop{" +
                "nameBusStop='" + nameBusStop + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
