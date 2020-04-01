package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;

public class CustomCompany {
    private String name;
    private String about;
    private String phone;
    private String cats;

    public CustomCompany(String name, String about, String phone, String cats) {
        this.name = name;
        this.about = about;
        this.phone = phone;
        this.cats = cats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCats() {
        return cats;
    }

    public void setCats(String cats) {
        this.cats = cats;
    }
}
