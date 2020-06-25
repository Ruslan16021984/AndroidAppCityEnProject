package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models;

public class TaxiWorker {
    private String clientPhone = "";
    private String login = "123";
    private String uuId;
    private Double latit;
    private Double longit;
    private int readyToBoard; //ready to board in Taxi. Car is at place order;
    private String nameDriver;
    private String phone;
    private String nameCar;
    private String colorCar;
    private String numberCar;


    public TaxiWorker() {
    }

    public TaxiWorker(String clientPhone, String login, String uuId, Double latit, Double longit, int readyToBoard, String nameDriver, String phone, String nameCar, String colorCar, String numberCar) {
        this.clientPhone = clientPhone;
        this.login = login;
        this.uuId = uuId;
        this.latit = latit;
        this.longit = longit;
        this.readyToBoard = readyToBoard;
        this.nameDriver = nameDriver;
        this.phone = phone;
        this.nameCar = nameCar;
        this.colorCar = colorCar;
        this.numberCar = numberCar;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }

    public Double getLatit() {
        return latit;
    }

    public void setLatit(Double latit) {
        this.latit = latit;
    }

    public Double getLongit() {
        return longit;
    }

    public void setLongit(Double longit) {
        this.longit = longit;
    }

    public int getReadyToBoard() {
        return readyToBoard;
    }

    public void setReadyToBoard(int readyToBoard) {
        this.readyToBoard = readyToBoard;
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
