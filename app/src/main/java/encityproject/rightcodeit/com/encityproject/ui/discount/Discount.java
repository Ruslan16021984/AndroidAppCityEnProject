package encityproject.rightcodeit.com.encityproject.ui.discount;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // Створюєм Get, Set
@AllArgsConstructor // Створюєм Constructor
@EqualsAndHashCode // Створюєм Equals, HashCode

public class Discount {
    private String nameGoods;
    private String priceAndDiscount;
    private String lan;
    private String lon;
    private String imgPath;
    private String description;
    private String startTime;
    private String endTime;
    private String phone;
    private String instalink;
//titlesale0, pricesale1, percentsale, lat, lon, linkphoto, bodysale, starttime, endtime, telseller

    /*listDisc.get(i).split(";")[0],listDisc.get(i).split(";")[1],listDisc.get(i).split(";")[2],
            listDisc.get(i).split(";")[3], listDisc.get(i).split(";")[4],listDisc.get(i).split(";")[5],
            listDisc.get(i).split(";")[6],listDisc.get(i).split(";")[7],listDisc.get(i).split(";")[8])*/
}