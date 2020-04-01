package encityproject.rightcodeit.com.encityproject.ui.registration.marketCats01;

public class SubCat {
    private String name;
    private String[] strCats;

    public SubCat(String name, String[] strCats) {
        this.name=name;
        this.strCats = strCats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getStrCats() {
        return strCats;
    }

    public void setStrCats(String[] strCats) {
        this.strCats = strCats;
    }
}
