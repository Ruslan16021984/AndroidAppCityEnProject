package encityproject.rightcodeit.com.encityproject.phoneBook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // Створюєм Get, Set
@AllArgsConstructor // Створюєм Constructor
@EqualsAndHashCode // Створюєм Equals, HashCode

public class PhoneNumbersAlertDialog {
    private String phoneNumber;
}