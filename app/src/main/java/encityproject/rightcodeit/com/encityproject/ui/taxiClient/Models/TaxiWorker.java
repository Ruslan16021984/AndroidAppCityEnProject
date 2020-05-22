package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models;

public class TaxiWorker {
    private String loginClient= "";
    private String login;
    private String uuId;
    private Double latit;
    private Double longit;
    private int readyToBoard; //ready to board in Taxi. Car is at place order;
    private String phone;

    public TaxiWorker() {
    }

    public TaxiWorker(String loginClient, String login, String uuId, Double latit, Double longit, int readyToBoard, String phone) {
        this.loginClient = loginClient;
        this.login = login;
        this.uuId = uuId;
        this.latit = latit;
        this.longit = longit;
        this.readyToBoard = readyToBoard;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getReadyToBoard() {
        return readyToBoard;
    }

    public void setReadyToBoard(int readyToBoard) {
        this.readyToBoard = readyToBoard;
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

    public String getLoginClient() {
        return loginClient;
    }

    public void setLoginClient(String loginClient) {
        this.loginClient = loginClient;
    }

    @Override
    public String toString() {
        return "TaxiWorker{" +
                "loginClient='" + loginClient + '\'' +
                ", login='" + login + '\'' +
                ", uuId='" + uuId + '\'' +
                ", latit=" + latit +
                ", longit=" + longit +
                '}';
    }
}
