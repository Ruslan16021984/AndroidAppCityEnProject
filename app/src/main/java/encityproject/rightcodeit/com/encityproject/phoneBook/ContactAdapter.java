package encityproject.rightcodeit.com.encityproject.phoneBook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import encityproject.rightcodeit.com.encityproject.R;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private ContactActivity discountActivity;

    public ContactAdapter(ContactActivity contactActivity, ArrayList<Contact> contacts) {
        super(contactActivity, 0, contacts);
        this.discountActivity = contactActivity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Contact contact = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
        }

        TextView tvNameContact = (TextView) convertView.findViewById(R.id.tv_name_contact);
        TextView tvPhoneNumber = (TextView) convertView.findViewById(R.id.tv_phone_number);

        tvNameContact.setText(contact.getNameContact());
        tvPhoneNumber.setText(contact.getPhoneNumber());

        return convertView;
    }
}