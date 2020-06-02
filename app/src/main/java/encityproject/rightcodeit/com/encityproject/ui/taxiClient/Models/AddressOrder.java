package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models;

public class AddressOrder {
    private String uuiD;
    private String address;
    private String numDoor;

    public AddressOrder() {
    }

    public AddressOrder(String address, String numDoor) {
        this.address = address;
        this.numDoor = numDoor;
    }

    public AddressOrder(String uuiD, String address, String numDoor) {
        this.uuiD = uuiD;
        this.address = address;
        this.numDoor = numDoor;
    }

    public String getUuiD() {
        return uuiD;
    }

    public void setUuiD(String uuiD) {
        this.uuiD = uuiD;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumDoor() {
        return numDoor;
    }

    public void setNumDoor(String numDoor) {
        this.numDoor = numDoor;
    }
}

