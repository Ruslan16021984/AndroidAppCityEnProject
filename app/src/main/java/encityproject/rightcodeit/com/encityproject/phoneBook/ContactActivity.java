package encityproject.rightcodeit.com.encityproject.phoneBook;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

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

