package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models;

public class TaxiClient {
    private String name;
    private String uuId;
    private Double latit;
    private Double longit;
    private String textAddress; //text address for order taxi
    private String phone;

    public TaxiClient() {
    }

    public TaxiClient(String name, String uuId, Double latit, Double longit, String textAddress, String phone) {
        this.name = name;
        this.uuId = uuId;
        this.latit = latit;
        this.longit = longit;
        this.textAddress = textAddress;
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getTextAddress() {
        return textAddress;
    }

    public void setTextAddress(String textAddress) {
        this.textAddress = textAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuId() {
        return uuId;
    }

    public void setUuId(String uuId) {
        this.uuId = uuId;
    }
}
