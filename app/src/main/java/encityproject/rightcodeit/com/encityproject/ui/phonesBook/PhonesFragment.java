package encityproject.rightcodeit.com.encityproject.ui.phonesBook;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.rightcodeit.customweatherdata.WeatherData;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.weatherData.WeatherFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhonesFragment extends Fragment {
    private ArrayOfContacts arrayOfContacts;
    private ContactAdapter contactAdapter;
    private AlertDialogPhoneNumbersAdapter adapterAlertDialog;
    private TextView tvFPhones;
    private int index;
    private String nameContact;
    private String phoneNumber;
    private Button btnPhone1;
    private Button btnPhone2;
    private ListView listView;
    private ProgressBar pbListPhones;
    private ArrayList<Contact> alContact;
    private int port = 4656;
    private Handler h;
    //private String ip = "192.168.1.46";
    private String ip = "35.232.178.112";

    public PhonesFragment() {
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void initData(View view) {
        arrayOfContacts = new ArrayOfContacts();
        contactAdapter = new ContactAdapter(getActivity(), arrayOfContacts.getArrayOfContacts());
        listView = view.findViewById(R.id.lv_contactPhones);
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
    public void onDestroyView() {
        super.onDestroyView();
        h.removeCallbacksAndMessages(null);
    }

    @Override
    public void onPause() {
        super.onPause();
        h.removeCallbacksAndMessages(null);
    }

    @Override
    public void onResume() {
        super.onResume();
        alContact = new ArrayList<>();
        listView.setVisibility(View.INVISIBLE);
            h = new Handler();
            h.post(new Runnable() {
                @Override
                public void run() {
                    if(isOnline()){
                        tvFPhones.setVisibility(View.INVISIBLE);
                        GetPhones getPhones = new GetPhones();
                        getPhones.execute("getPhones");
                    }
                    else {
                        tvFPhones.setVisibility(View.VISIBLE);
                        h.postDelayed(this,5000);
                    }
                }
            });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_phones, container, false);

        tvFPhones=v.findViewById(R.id.tvFPhones);
        tvFPhones.setVisibility(View.INVISIBLE);
        listView = v.findViewById(R.id.lv_contactPhones);
        pbListPhones = v.findViewById(R.id.pbListPhones);
        pbListPhones.setVisibility(View.VISIBLE);
        alContact = new ArrayList<>();



      //  arrayOfContacts = new ArrayOfContacts();

        return v;
    }

    class GetPhones extends AsyncTask<String, Void, Socket> {
        private ArrayList<String> listPhones;
        private String linkCheckVApp = "myNull";
        private Socket socket;


        @Override
        protected Socket doInBackground(String... params) {
            listPhones = new ArrayList<>();

            PrintWriter pw;
            try {
                socket = new Socket(ip, port);

                pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                pw.write(params[0] + "@.#" + linkCheckVApp + "\n");
                pw.flush();

                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                Object object = objectInput.readUnshared();
                listPhones = (ArrayList<String>) object;
                Log.d("check size ", String.valueOf(listPhones.size()));
           /*     try {

                } catch (ClassNotFoundException e) {
                    //e.printStackTrace();
                }*/
                objectInput.close();

            } catch (IOException e) {
                //e.printStackTrace();
                Log.d("Ex GetWeather", e.getMessage());
            } catch (ClassNotFoundException e) {
                //e.printStackTrace();
                Log.d("Ex GetWeather", e.getMessage());
            } finally {

            }


            for(int i=0; i<listPhones.size();i++){
                alContact.add(new Contact(listPhones.get(i).split(";")[0],listPhones.get(i).split(";")[1]));
            }
//data111,43,23
            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            pbListPhones.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
            if(listPhones.size()>0) {
                contactAdapter = new ContactAdapter(getActivity(), alContact);
                listView.setAdapter(contactAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        final String[] split;
                        String toSplit = alContact.get(position).getPhoneNumber();
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
                                    ("tel: " + alContact.get(position).getPhoneNumber()));
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
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
