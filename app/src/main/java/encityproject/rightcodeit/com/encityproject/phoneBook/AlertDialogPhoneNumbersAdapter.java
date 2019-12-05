package encityproject.rightcodeit.com.encityproject.phoneBook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

public class AlertDialogPhoneNumbersAdapter extends ArrayAdapter<String> {

    private Context context;
    private ArrayList<String> list;
    private int resource;

    public AlertDialogPhoneNumbersAdapter(Context context, int resource, ArrayList<String> list) {
        super(context, resource, list);
        this.context=context;
        this.resource=resource;
        this.list=list;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(resource, null);
        }

        TextView tv = v.findViewById(R.id.tvOnePhone);
        ImageView iv = v.findViewById(R.id.ivPhone);

        tv.setText(list.get(position));

        return v;
    }
    /*private ContactActivity contactActivity;

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
    }*/
}