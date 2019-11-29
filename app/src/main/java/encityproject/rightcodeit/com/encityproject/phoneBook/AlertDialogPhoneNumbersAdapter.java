package encityproject.rightcodeit.com.encityproject.phoneBook;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import java.util.ArrayList;
import encityproject.rightcodeit.com.encityproject.R;

public class AlertDialogPhoneNumbersAdapter extends ArrayAdapter<String> {

    private ContactActivity contactActivity;

    public AlertDialogPhoneNumbersAdapter(ContactActivity contactActivity, ArrayList<String> phoneNumbers) {
        super(contactActivity, 0, phoneNumbers);
        this.contactActivity = contactActivity;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final String phoneNumbersAlertDialog = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_alertdialog_phone_numbers, parent, false);
        }

        Button btnPhone = (Button) convertView.findViewById(R.id.btn_phone);

        final String[] split;
        String toSplit = phoneNumbersAlertDialog;
        split = toSplit.split(",");
        for (String s : split) {
            btnPhone.setText(s);
        }
        return convertView;
    }
}