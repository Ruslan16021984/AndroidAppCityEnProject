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

}