package encityproject.rightcodeit.com.encityproject.phoneBook;

import java.util.ArrayList;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data // Створюєм Get, Set
//@AllArgsConstructor // Створюєм Constructor
@EqualsAndHashCode // Створюєм Equals, HashCode

public class ArrayOfContacts {

    ArrayList<Contact> arrayOfContacts = new ArrayList<Contact>();

    public ArrayOfContacts() {
        arrayOfContacts.add(new Contact("МНС", "101"));
        arrayOfContacts.add(new Contact("Поліція", "102"));
        arrayOfContacts.add(new Contact("Швидка допомога", "103"));
        arrayOfContacts.add(new Contact("Газова служба", "104"));
        arrayOfContacts.add(new Contact("СБУ", "+380(6139)6-04-37"));
        arrayOfContacts.add(new Contact("Диспетчерська автовокзалу", "+380(6139)6-26-26"));
        arrayOfContacts.add(new Contact("Залізнична станція", "+380(6139)6-11-93"));
        arrayOfContacts.add(new Contact("Аптека СМСЧ-1", "+380(6139)6-11-47"));
        arrayOfContacts.add(new Contact("Приймальний покій СМСЧ-1", "+380(6139)3-13-19"));
        arrayOfContacts.add(new Contact("Черговий військкомату", "+380(6139)6-07-69"));
        arrayOfContacts.add(new Contact("Приймальна 1-го Державного пожежно-рятувального загону", "+380(6139)3-38-00"));
        arrayOfContacts.add(new Contact("Прокуратура", "+380(6139)3-11-08"));
        arrayOfContacts.add(new Contact("Єдиний офіс «мерія»", "+380(6139)6-22-25"));
        arrayOfContacts.add(new Contact("ДК «Сучасник»", "+380(6139)4-10-97"));
        arrayOfContacts.add(new Contact("Ветеринарний дільниця № 1", "+380(6139)6-33-44"));
        arrayOfContacts.add(new Contact("Дитяча музична школа", "+380(6139)6-27-12,\n" + "+380(6139)6-39-44"));
        arrayOfContacts.add(new Contact("Диспетчерська ліфтового господарства", "+380(6139)6-08-91,\n" + "+380(6139)6-07-88"));
        arrayOfContacts.add(new Contact("Енергодарський лабораторний центр", "+380(6139)6-08-28"));
        arrayOfContacts.add(new Contact("Поштове відділення №1 по Молодіжній, 65", "+380(6139)6-58-72"));
        arrayOfContacts.add(new Contact("Поштове відділення № 2 по Лісовій, 6", "+380(6139)6-13-21"));
        arrayOfContacts.add(new Contact("Поштове відділення №3 по Будівельників, 27", "+380(6139)6-16-79"));
        arrayOfContacts.add(new Contact("Басейн", "+380(6139)5-05-07"));

        arrayOfContacts.add(new Contact("Суші-бар «Сакура»\n" + "вул.Українська, 10", "+38(099)001-09-04"));
        arrayOfContacts.add(new Contact("Піцерія «Мілана»\n" + "вул.Украинская 2а", "+380(6139)6-58-81,\n" + "+38(095)410-70-25"));
        arrayOfContacts.add(new Contact("Кафе «Духмяна піч»\n" + "вул.Козацкая, 16-в", "+380(6139)6-80-14,\n" + "+38(099)161-70-46"));
        arrayOfContacts.add(new Contact("Центр розваг Spring City\n" + "вул.Центральна, 18", "+38(050)499-58-59"));
    }
}