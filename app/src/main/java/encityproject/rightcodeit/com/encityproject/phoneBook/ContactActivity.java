package encityproject.rightcodeit.com.encityproject.phoneBook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

public class ContactActivity extends AppCompatActivity {
    private ArrayOfContacts arrayOfContacts = new ArrayOfContacts();
    private ContactAdapter contactAdapter;
    private AlertDialogPhoneNumbersAdapter adapterAlertDialog;
    private int index;
    private String nameContact;
    private String phoneNumber;
    final Context context = this;
    private Button btnPhone1;
    private Button btnPhone2;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        initData();
    }

    private void initData() {
        contactAdapter = new ContactAdapter(this, arrayOfContacts.getArrayOfContacts());
        ListView listView = findViewById(R.id.lv_contact);
        listView.setAdapter(contactAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final String[] split;
                String toSplit = arrayOfContacts.getArrayOfContacts().get(position).getPhoneNumber();
                split = toSplit.split(",");

/*                String toToast;
                if (split.length < 2) {
                    toToast = split[0];
                } else {
                    toToast = split[0] + split[1];
                }
                Toast toast = Toast.makeText(getApplicationContext(),
                        (toToast),
                        Toast.LENGTH_SHORT);
                toast.show();*/

                if (split.length < 2) {
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                            ("tel: " + arrayOfContacts.getArrayOfContacts().get(position).getPhoneNumber()));
                    if (intent != null) {
                        startActivity(intent);
                    }
                } else {
//                    final Dialog dialog = new Dialog(context);
//                    dialog.setContentView(R.layout.custom_dialog);
//                    btnPhone1 = (Button) dialog.findViewById(R.id.phone1);
//                    btnPhone2 = (Button) dialog.findViewById(R.id.phone2);
//                    btnPhone1.setText(split[0]);
//                    btnPhone2.setText(split[1]);
////////////////////////////////
                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Select a phone number:");
                    builder.setItems(split, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel: " + "1111"));
                            startActivity(intent);
                        }
                    });
                    AlertDialog dialog = builder.create();
////////////////////////////////
                    dialog.show();
//                    btnPhone1.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel: " + split[0]));
//                            if (intent != null) {
//                                startActivity(intent);
//                            }
//                        }
//                    });
//                    btnPhone2.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel: " + split[1]));
//                            if (intent != null) {
//                                startActivity(intent);
//                            }
//                        }
//                    });
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) {
            return;
        }
        ArrayList<String> oneContact;
        oneContact = data.getStringArrayListExtra("name");
        nameContact = oneContact.get(0);
        phoneNumber = oneContact.get(1);
        arrayOfContacts.getArrayOfContacts().set(index, new Contact(nameContact, phoneNumber));
        contactAdapter.notifyDataSetChanged();
    }

}

