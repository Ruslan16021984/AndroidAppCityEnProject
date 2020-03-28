package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnterSmsFragment extends Fragment {

    private Button btnEnterSms;
    private EditText etSms;
    private Bundle bundle;
    private String bunRole, bunPhone;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";

    public EnterSmsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_enter_sms, container, false);
        btnEnterSms=v.findViewById(R.id.btnEnterSmsNext);
        etSms=v.findViewById(R.id.etSms);

        bundle = getArguments();
        bunRole = bundle.getString("role");
        bunPhone = bundle.getString("phone");

        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        //bundle.putString("role", bunStr);

        btnEnterSms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etSms.getText().length()>0){
                    SendForRegSms sendForRegSms = new SendForRegSms();
                    sendForRegSms.execute(etSms.getText().toString());
                }
            }
        });
        return v;
    }

    class SendForRegSms extends AsyncTask<String, Void, Socket> {
        private String linkCheckVApp = "myNull";
        private Socket socket;
        private PrintWriter pw = null;
        private InputStream is = null;
        private String fromServer="";

        @Override
        protected Socket doInBackground(String... params) {

            /*PrintWriter pw;
            try {
                socket = new Socket(ip, port);

                pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                pw.write(params[0] + "@.#" + linkCheckVApp + "\n");
                pw.flush();

                is = socket.getInputStream();
                Scanner sc = new Scanner(is);
                fromServer = sc.nextLine();

                is.close();
            } catch (IOException e) {
                //e.printStackTrace();
                Log.d("Ex GetWeather", e.getMessage());
            }
             finally {

            }*/

            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //pbListPhones.setVisibility(View.INVISIBLE);
            fromServer="ok";
            if(fromServer.length()>0){
                editor = prefer.edit();
                editor.putString("auth", fromServer);
                editor.apply();
                if(bunRole.equals("seller")){
                    Bundle bundle = new Bundle();
                    bundle.putString("role", bunRole);
                    bundle.putString("phone", bunPhone);
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.nav_cat_choice, bundle);
                }
                else{
                    final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    View ve = (LinearLayout) getLayoutInflater()
                            .inflate(R.layout.dialog_congratulate, null);

                    Button btnCong = ve.findViewById(R.id.btnCong);

                    builder
                            .setView(ve)
                            .setCancelable(false)
                    ;
                    final AlertDialog alert2 = builder.create();

                    btnCong.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            alert2.dismiss();
                            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                            navController.navigate(R.id.nav_market, bundle);
                        }
                    });

                    alert2.show();
                    alert2.setCancelable(false);
                }
                /*NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_enter_sms);*/

            }
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
