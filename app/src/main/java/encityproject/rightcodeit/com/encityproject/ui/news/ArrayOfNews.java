package encityproject.rightcodeit.com.encityproject.ui.news;

import java.util.ArrayList;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // Створюєм Get, Set
//@AllArgsConstructor // Створюєм Constructor
@EqualsAndHashCode // Створюєм Equals, HashCode

public class ArrayOfNews {
    ArrayList<News> arrayOfNews = new ArrayList<>();

    public ArrayOfNews() {
        arrayOfNews.add(new News ("FirstNews", "2/25/2020", "Very important news", "https://personal.psu.edu/hyw5138/mini.jpg", "http://helsi.me"));
        arrayOfNews.add(new News ("FirstNews", "2/25/2020", "Very important news", "https://personal.psu.edu/hyw5138/mini.jpg", "http://helsi.me"));
        arrayOfNews.add(new News ("FirstNews", "2/25/2020", "Very important news", "https://personal.psu.edu/hyw5138/mini.jpg", "http://helsi.me"));
        arrayOfNews.add(new News ("FirstNews", "2/25/2020", "Very important news", "https://personal.psu.edu/hyw5138/mini.jpg", "http://helsi.me"));
        arrayOfNews.add(new News ("FirstNews", "2/25/2020", "Very important news", "https://personal.psu.edu/hyw5138/mini.jpg", "http://helsi.me"));
        arrayOfNews.add(new News ("FirstNews", "2/25/2020", "Very important news", "https://personal.psu.edu/hyw5138/mini.jpg", "http://helsi.me"));
    }
}

