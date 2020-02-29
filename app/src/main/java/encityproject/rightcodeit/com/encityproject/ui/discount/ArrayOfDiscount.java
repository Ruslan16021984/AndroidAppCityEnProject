package encityproject.rightcodeit.com.encityproject.ui.discount;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // Створюєм Get, Set
//@AllArgsConstructor // Створюєм Constructor
@EqualsAndHashCode // Створюєм Equals, HashCode

public class ArrayOfDiscount {

    ArrayList<Discount> arrayOfDiscount = new ArrayList<Discount>();

    public ArrayOfDiscount() {
        arrayOfDiscount.add(new Discount("Молоко", "22.50 грн., знижка 50%", "", "https://st3.depositphotos.com/12674628/15422/i/450/depositphotos_154226258-stock-photo-fresh-milk-in-glass.jpg", "Найкраші товари по найкращчм цінам" + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам//////////////////////////////////////////"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"
                + ", Найкраші товари по найкращчм цінам"));
        arrayOfDiscount.add(new Discount("Молоко", "22.50 грн., знижка 50%", "", "https://personal.psu.edu/hyw5138/mini.jpg", "Найкраші товари по найкращчм цінам"));
        arrayOfDiscount.add(new Discount("Молоко", "22.50 грн., знижка 50%", "", "https://220v.guru/images/693740/edinica_izmereniya_osveschennosti.jpg", "Найкраші товари по найкращчм цінам"));
        arrayOfDiscount.add(new Discount("Молоко", "22.50 грн., знижка 50%", "", "https://st3.depositphotos.com/12674628/15422/i/450/depositphotos_154226258-stock-photo-fresh-milk-in-glass.jpg", "Найкраші товари по найкращчм цінам"));
        arrayOfDiscount.add(new Discount("Молоко", "22.50 грн., знижка 50%", "", "https://st3.depositphotos.com/12674628/15422/i/450/depositphotos_154226258-stock-photo-fresh-milk-in-glass.jpg", "Найкраші товари по найкращчм цінам"));
        arrayOfDiscount.add(new Discount("Молоко", "22.50 грн., знижка 50%", "", "https://st3.depositphotos.com/12674628/15422/i/450/depositphotos_154226258-stock-photo-fresh-milk-in-glass.jpg", "Найкраші товари по найкращчм цінам"));
        arrayOfDiscount.add(new Discount("Молоко", "22.50 грн., знижка 50%", "", "https://st3.depositphotos.com/12674628/15422/i/450/depositphotos_154226258-stock-photo-fresh-milk-in-glass.jpg", "Найкраші товари по найкращчм цінам"));
        arrayOfDiscount.add(new Discount("Молоко", "22.50 грн., знижка 50%", "", "https://st3.depositphotos.com/12674628/15422/i/450/depositphotos_154226258-stock-photo-fresh-milk-in-glass.jpg", "Найкраші товари по найкращчм цінам"));
    }
}