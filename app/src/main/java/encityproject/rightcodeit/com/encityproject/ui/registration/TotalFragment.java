package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TotalFragment extends Fragment {

    private int port = 4656;
    //private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    private String ip = "35.232.178.112";
    private TextView tvTotalName;
    private TextView tvTotalPhone, tvTotalCats, tvAbout;
    private CheckBox cbTotal;
    private Button btnTotalNext;
    private ImageView ivTotal;
    private TextView tvCats;
    private String bunPhone, bunRole, bunName, bunSecondPhone, bunAbout;
    private ArrayList<String> catListStore;
    private ArrayList<String> catListService;
    private String totalCatsStoreIndex, totalCatsServiceIndex;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";

    public TotalFragment() {
        // Required empty public constructor
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_total, container, false);

        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        editor = prefer.edit();
        Bundle bundle = getArguments();
        bunPhone=bundle.getString("phone");
        if(bundle.containsKey("secondphone")){
            bunSecondPhone=bundle.getString("secondphone");
        }
        bunRole=bundle.getString("role");
        bunName=bundle.getString("nameCompany");
        bunAbout=bundle.getString("about");
        catListStore=bundle.getStringArrayList("catstore");
        catListService=bundle.getStringArrayList("catservice");
        ArrayList<Integer> CheckedCatsStore= bundle.getIntegerArrayList("checkedcatstore");
        ArrayList<Integer> CheckedCatsService= bundle.getIntegerArrayList("checkedcatservice");

        String totalCatsStore="";
        String totalCatsService="";
        totalCatsStoreIndex="";
        totalCatsServiceIndex="";

        btnTotalNext=v.findViewById(R.id.btnTotalNext);
        tvTotalName=v.findViewById(R.id.tvTotalName);
        tvTotalPhone=v.findViewById(R.id.tvTotalPhone);
        tvTotalCats=v.findViewById(R.id.tvTotalCats);
        tvAbout=v.findViewById(R.id.tvAbout);
        cbTotal=v.findViewById(R.id.cbTotal);

        tvAbout.setText(bunAbout);

        if(CheckedCatsStore.size()>0){
        for(int i=0; i<CheckedCatsStore.size();i++){
            if(i==0){
                totalCatsStore="Магазини - "+catListStore.get(CheckedCatsStore.get(i)).split("@.#")[1];
                totalCatsStoreIndex=catListStore.get(CheckedCatsStore.get(i)).split("@.#")[0];
                if(CheckedCatsStore.size()==1){
                    totalCatsStore=totalCatsStore+"\n";
                }
            }
            else {
                totalCatsStore=totalCatsStore+", "+catListStore.get(CheckedCatsStore.get(i)).split("@.#")[1];
                totalCatsStoreIndex=totalCatsStoreIndex+","+catListStore.get(CheckedCatsStore.get(i)).split("@.#")[0];
            }
            }
            tvTotalCats.setText(totalCatsStore);
        }

        if(CheckedCatsService.size()>0){
            for(int i=0; i<CheckedCatsService.size();i++){
                if(i==0){
                    totalCatsService="Послуги - "+catListService.get(CheckedCatsService.get(i)).split("@.#")[1];
                    totalCatsServiceIndex=catListService.get(CheckedCatsService.get(i)).split("@.#")[0];
                    if(CheckedCatsStore.size()==1){
                        totalCatsService=totalCatsService+"\n";
                    }
                }
                else {
                    totalCatsService=totalCatsService+", "+catListService.get(CheckedCatsService.get(i)).split("@.#")[1];
                    totalCatsServiceIndex=totalCatsServiceIndex+","+catListService.get(CheckedCatsService.get(i)).split("@.#")[0];
                }
            }
            if(tvTotalCats.getText().length()>0){
                tvTotalCats.setText(tvTotalCats.getText().toString()+"\n"+totalCatsService);
            }
            else{
                tvTotalCats.setText(totalCatsService);
            }
        }

        if(totalCatsServiceIndex.length()>1 && totalCatsStoreIndex.length()>1){
            totalCatsStoreIndex=totalCatsStoreIndex+","+totalCatsServiceIndex;
        }
        else if(totalCatsStoreIndex.length()<1){
            totalCatsStoreIndex=totalCatsServiceIndex;
        }


        tvTotalName.setText(bunName);
        tvTotalPhone.setText(bunPhone);

        if(bundle.containsKey("secondphone")){
            tvTotalPhone.setText(tvTotalPhone.getText().toString()+", +380"+bundle.getString("secondphone")+"(для клієнтів)");
        }

        ivTotal=v.findViewById(R.id.ivTotal);
        ivTotal.setRotation(270);


        btnTotalNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isOnline()){
                if(cbTotal.isChecked()) {
                    btnTotalNext.setClickable(false);
                    ObjectAnimator buttonAnimator = ObjectAnimator.ofFloat(ivTotal, "translationY", 0f, -2000f);
                    buttonAnimator.setDuration(3000);
                    buttonAnimator.start();

                    buttonAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {

                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            //AsyncTask for confirm reg

                            editor.putString("name", bunName);
                            editor.putString("phone", bunPhone);
                            if (bunSecondPhone != null) {
                                editor.putString("secondphone", bunSecondPhone);
                                bunPhone=bunPhone+","+bunSecondPhone;
                            }
                            editor.putString("about", bunAbout);
                            editor.putString("who", bunRole);
                            editor.putString("regstatus","ok");
                            editor.putString("addcats", tvTotalCats.getText().toString());
                            editor.putString("addcatsindex", totalCatsStoreIndex);
                            editor.apply();
//authuser, companyname, cats, about, phone
                            SendForRegTotal sendForRegTotal = new SendForRegTotal();
                            sendForRegTotal.execute("addnew"+"@.#"+
                                    prefer.getString("auth","")+"@.#"+
                                    bunName+"@.#"+
                                    totalCatsStoreIndex+"@.#"+
                                    bunAbout+"@.#"+
                                    bunPhone);
                            // getActivity().supportInvalidateOptionsMenu();
                            /*setHasOptionsMenu(true);
                            NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                            Menu menuNav = navigationView.getMenu();
                            MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
                            nav_reg.setVisible(false);
                            MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
                            nav_auth.setVisible(true);
                            MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
                            nav_cloud_market.setVisible(true);
                            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                            navController.navigate(R.id.nav_auth_company_fragment);*/
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });
                }
                else{
                    Toast.makeText(getContext(), "Підтвердіть Вашу згоду", Toast.LENGTH_SHORT).show();
                }
                }
                else{
                    Toast.makeText(getContext(), "Перевірте інтернет", Toast.LENGTH_SHORT).show();
                }
            }
        });



        return v;
    }

    class SendForRegTotal extends AsyncTask<String, Void, Socket> {
        private String linkCheckVApp = "myNull";
        private Socket socket;
        private PrintWriter pw = null;
        private InputStream is = null;
        private String fromServer="";

        @Override
        protected Socket doInBackground(String... params) {

            PrintWriter pw;
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
                Log.d("send total for reg", e.getMessage());
            }
            finally {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //pbListPhones.setVisibility(View.INVISIBLE);
            //fromServer="ok";
            if(fromServer.length()>4){
                editor.putString("auth2", fromServer);
                setHasOptionsMenu(true);
                NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                Menu menuNav = navigationView.getMenu();
                MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
                nav_reg.setVisible(false);
                MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
                nav_auth.setVisible(true);
                MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
                nav_cloud_market.setVisible(true);
                MenuItem nav_basket_work = menuNav.findItem(R.id.nav_work_basket_fragment);
                nav_basket_work.setVisible(true);
                MenuItem nav_basket = menuNav.findItem(R.id.nav_basket_fragment);
                nav_basket.setVisible(true);
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_auth_company_fragment);
            }
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
