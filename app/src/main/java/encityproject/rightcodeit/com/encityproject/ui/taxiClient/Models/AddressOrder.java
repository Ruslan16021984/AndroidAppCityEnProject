package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models;

public class AddressOrder {
    private String address;
    private String numDoor;

    public AddressOrder(String address, String numDoor) {
        this.address = address;
        this.numDoor = numDoor;
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

