package encityproject.rightcodeit.com.encityproject.ui.phonesBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

public class ContactAdapter extends BaseAdapter {

    private Context ctx;
    LayoutInflater lInflater;
    ArrayList<Contact> contacts;


    public ContactAdapter(Context context, ArrayList<Contact> contacts) {
        this.ctx = context;
        this.contacts=contacts;
        lInflater = (LayoutInflater) ctx
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    Contact getContact(int position) {
        return ((Contact) getItem(position));
    }

    // кол-во элементов
    @Override
    public int getCount() {
        return contacts.size();
    }

    // элемент по позиции
    @Override
    public Contact getItem(int position) {
        return contacts.get(position);
    }

    // id по позиции
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        Contact contact = getContact(position);

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(ctx);
            v = vi.inflate(R.layout.item_contact_with_cardview, null);
        }

        TextView tvNameContact = (TextView) v.findViewById(R.id.tv_name_contact);
        TextView tvPhoneNumber = (TextView) v.findViewById(R.id.tv_phone_number);

        tvNameContact.setText(contact.getNameContact());
        String toSplit = contact.getPhoneNumber();
        final String[] split;
        split = toSplit.split(",");
        if (split.length > 1) {
            String s1 = "";
            ////////////////////////////////////////////////////////////////////
            String s2 = "";
            for (int i = 1; i <= split.length; i++) {
                s1 = s1 + split[i-1] + "\n";
                if (i == split.length) {
                    s2 = s1.trim();
                }
            }
            ////////////////////////////////////////////////////////////////////

//            for (String s : split) {
//                s1 = s1 + s + "\n";
//            }

//            tvPhoneNumber.setText(s1);
            tvPhoneNumber.setText(s2);
//            tvPhoneNumber.setText(split[0] + "\n" + split[1]);
        } else {
            tvPhoneNumber.setText(contact.getPhoneNumber());
        }

        return v;
    }
}