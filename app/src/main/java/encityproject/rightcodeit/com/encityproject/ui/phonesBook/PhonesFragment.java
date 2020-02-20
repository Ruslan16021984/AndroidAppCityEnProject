package encityproject.rightcodeit.com.encityproject.ui.phonesBook;


import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhonesFragment extends Fragment {
    private ArrayOfContacts arrayOfContacts;
    private ContactAdapter contactAdapter;
    private AlertDialogPhoneNumbersAdapter adapterAlertDialog;
    private int index;
    private String nameContact;
    private String phoneNumber;
    private Button btnPhone1;
    private Button btnPhone2;

    public PhonesFragment() {
    }

    private void initData(View view) {
        arrayOfContacts = new ArrayOfContacts();
        contactAdapter = new ContactAdapter(getActivity(), arrayOfContacts.getArrayOfContacts());
        ListView listView = view.findViewById(R.id.lv_contactPhones);
       ArrayList<Contact> ac = new ArrayList<>();
       ac.add(new Contact("тест", "911"));
        listView.setAdapter(new ContactAdapter(getActivity(), ac));
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
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setTitle("Select a phone number:");
//                    builder.setItems(split, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel: " + "1111"));
//                            startActivity(intent);
//                        }
//                    });
//                    AlertDialog dialog = builder.create();
////////////////////////////////

                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());

                    View ve = getLayoutInflater().inflate(R.layout.custom_dialog_listview_twomorephones,null);
                    final ListView lvForTwoMorePhones = ve.findViewById(R.id.lvForTwoMorePhones);
                    final ArrayList<String> alTwoPhones = new ArrayList<>();
                    for(int i=0;i<split.length;i++){
                        alTwoPhones.add(split[i]);
                    }
                    lvForTwoMorePhones.setAdapter(new AlertDialogPhoneNumbersAdapter(getContext(),R.layout.item_one_phonecall,alTwoPhones));
                    lvForTwoMorePhones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                                    ("tel: " + alTwoPhones.get(i)));
                            if (intent != null) {
                                startActivity(intent);
                            }
                        }
                    });

                    builderSingle
                            .setView(ve)
                            .setCancelable(true)
                    ;
                    final AlertDialog alert = builderSingle.create();

                    alert.show();
////////////////////////////////
//                    dialog.show();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phones, container, false);
        arrayOfContacts = new ArrayOfContacts();
        contactAdapter = new ContactAdapter(getActivity(), arrayOfContacts.getArrayOfContacts());
        ListView listView = v.findViewById(R.id.lv_contactPhones);
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
//                    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setTitle("Select a phone number:");
//                    builder.setItems(split, new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel: " + "1111"));
//                            startActivity(intent);
//                        }
//                    });
//                    AlertDialog dialog = builder.create();
////////////////////////////////

                    AlertDialog.Builder builderSingle = new AlertDialog.Builder(getContext());

                    View ve = getLayoutInflater().inflate(R.layout.custom_dialog_listview_twomorephones,null);
                    final ListView lvForTwoMorePhones = ve.findViewById(R.id.lvForTwoMorePhones);
                    final ArrayList<String> alTwoPhones = new ArrayList<>();
                    for(int i=0;i<split.length;i++){
                        alTwoPhones.add(split[i]);
                    }
                    lvForTwoMorePhones.setAdapter(new AlertDialogPhoneNumbersAdapter(getContext(),R.layout.item_one_phonecall,alTwoPhones));
                    lvForTwoMorePhones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                                    ("tel: " + alTwoPhones.get(i)));
                            if (intent != null) {
                                startActivity(intent);
                            }
                        }
                    });

                    builderSingle
                            .setView(ve)
                            .setCancelable(true)
                    ;
                    final AlertDialog alert = builderSingle.create();

                    alert.show();
////////////////////////////////
//                    dialog.show();
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

        return v;
    }

}
