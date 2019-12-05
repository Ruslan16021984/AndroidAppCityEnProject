package encityproject.rightcodeit.com.encityproject.phoneBook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

public class ContactAdapter extends ArrayAdapter<Contact> {

    private ContactActivity contactActivity;

    public ContactAdapter(ContactActivity contactActivity, ArrayList<Contact> contacts) {
        super(contactActivity, 0, contacts);
        this.contactActivity = contactActivity;
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
        String toSplit = contact.getPhoneNumber();
        final String[] split;
        split = toSplit.split(",");
        if (split.length > 1) {
            String s1 = "";
            for (String s : split) {
                s1 = s1 + s + "\n";
            }
            tvPhoneNumber.setText(s1);
//            tvPhoneNumber.setText(split[0] + "\n" + split[1]);
        } else {
            tvPhoneNumber.setText(contact.getPhoneNumber());
        }

        return convertView;
    }
}