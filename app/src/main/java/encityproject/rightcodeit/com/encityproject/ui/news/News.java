package encityproject.rightcodeit.com.encityproject.ui.news;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // Створюєм Get, Set
@AllArgsConstructor // Створюєм Constructor
@EqualsAndHashCode // Створюєм Equals, HashCode

public class News {
    private String titleNews;
    private String dateAndTimeNews;
    private String textNews;
    private String imgLinkNews;
    private String LinkSite;

}
