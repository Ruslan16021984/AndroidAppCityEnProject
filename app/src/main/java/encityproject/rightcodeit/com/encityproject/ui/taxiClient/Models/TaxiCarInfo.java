package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models;

public class TaxiCarInfo {
    private String nameDriver;
    private String phone;
    private String nameCar;
    private String colorCar;
    private String numberCar;

    public TaxiCarInfo(String nameDriver, String phone, String nameCar, String colorCar, String numberCar) {
        this.nameDriver = nameDriver;
        this.phone = phone;
        this.nameCar = nameCar;
        this.colorCar = colorCar;
        this.numberCar = numberCar;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public void setNameDriver(String nameDriver) {
        this.nameDriver = nameDriver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNameCar() {
        return nameCar;
    }

    public void setNameCar(String nameCar) {
        this.nameCar = nameCar;
    }

    public String getColorCar() {
        return colorCar;
    }

    public void setColorCar(String colorCar) {
        this.colorCar = colorCar;
    }

    public String getNumberCar() {
        return numberCar;
    }

    public void setNumberCar(String numberCar) {
        this.numberCar = numberCar;
    }
}
