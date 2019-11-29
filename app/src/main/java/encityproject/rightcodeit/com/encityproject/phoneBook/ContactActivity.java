package encityproject.rightcodeit.com.encityproject.phoneBook;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.nio.file.Files;
import java.util.ArrayList;
import encityproject.rightcodeit.com.encityproject.R;

public class ContactActivity extends AppCompatActivity {

    ArrayOfContacts arrayOfContacts = new ArrayOfContacts();
    ContactAdapter adapter;

    private int index;

    private String nameContact;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        initData();
    }

    private void initData() {
        adapter = new ContactAdapter(this, arrayOfContacts.getArrayOfContacts());
        ListView listView = findViewById(R.id.lv_contact);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel: " + arrayOfContacts.getArrayOfContacts().get(position).getPhoneNumber()));
                if (intent != null) {
                    startActivity(intent);
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

        adapter.notifyDataSetChanged();
    }
}

