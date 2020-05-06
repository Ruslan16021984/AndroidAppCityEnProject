package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.OrdersPagerAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.SlidingTabLayout;
import encityproject.rightcodeit.com.encityproject.ui.registration.CatChoiceAdapter;
import encityproject.rightcodeit.com.encityproject.ui.registration.CatChoiceFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntranceMarketFragment extends Fragment {

    private int port = 4656;
    //private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    private String ip = "35.232.178.112";
    private ArrayList<ArrayList<String>> categoryFromServerList;
    private ArrayList<String> categoryList, catsListStore, catsListService;
    private SlidingTabLayout slidingTabLayout;
    private LinearLayout llQuickOrder;
    private ViewPager viewPager;
    private ProgressBar pb;
    EntrancePagerAdapter adapter;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";

    CharSequence Titles[]={"Магазини","Послуги"};
    int Numboftabs = 2;

    public EntranceMarketFragment() {
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
        View view= inflater.inflate(R.layout.fragment_entrance_market, container, false);
        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        pb=view.findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);

        catsListStore=new ArrayList<>();
        catsListService=new ArrayList<>();

      //  Toast.makeText(getContext(), "привет", Toast.LENGTH_SHORT).show();

        llQuickOrder=view.findViewById(R.id.llQuickOrder);
        llQuickOrder.setVisibility(View.INVISIBLE);

        if(isOnline()) {
            GetAllCategories getAllCategories = new GetAllCategories();
            getAllCategories.execute("getcats" + "@.#" + "null");
        }
        else {
            Toast.makeText(getContext(), "Перевірте інтернет", Toast.LENGTH_SHORT).show();
        }
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.viewpager);

        slidingTabLayout = view.findViewById(R.id.sliding_tabs);
        /*adapter =  new EntrancePagerAdapter(getChildFragmentManager(),Titles,Numboftabs);
        viewPager.setAdapter(adapter);

        slidingTabLayout = view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);*/
    }

    class GetAllCategories extends AsyncTask<String, Void, Socket> {
        private Socket s = null;
        //private String fromServer;
        private PrintWriter pw = null;
        private InputStream is = null;

        @Override
        protected Socket doInBackground(String... params) {
            try {
                s = new Socket(ip, port);
                pw = new PrintWriter(new BufferedOutputStream(s.getOutputStream()));
                pw.write(params[0] + "\n");
                pw.flush();
                /*is = s.getInputStream();
                Scanner sc = new Scanner(is);
                fromServer = sc.nextLine();
                is.close();*/
                ObjectInputStream objectInput = new ObjectInputStream(s.getInputStream());
                try {
                    Object object = objectInput.readUnshared();
                    categoryFromServerList =  (ArrayList<ArrayList<String>>) object;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objectInput.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            if(categoryFromServerList!=null && categoryFromServerList.size()>0) {
                for(int i=0;i<categoryFromServerList.size();i++){
                    if(i==0) {
                        catsListStore = categoryFromServerList.get(i);
                    }
                    else {
                        catsListService=categoryFromServerList.get(i);
                    }

                }

                adapter =  new EntrancePagerAdapter(getChildFragmentManager(),Titles,Numboftabs, catsListStore, catsListService);
                viewPager.setAdapter(adapter);

                slidingTabLayout.setDistributeEvenly(true);
                slidingTabLayout.setViewPager(viewPager);

                pb.setVisibility(View.INVISIBLE);


                llQuickOrder.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        View ve = (LinearLayout) getLayoutInflater()
                                .inflate(R.layout.dialog_quick_order, null);

                        TextView tvQuickAsk= ve.findViewById(R.id.tvQuickAsk);
                        Button btnQuickConfirm = ve.findViewById(R.id.btnQuickConfirm);
                        Button btnQuickCancel = ve.findViewById(R.id.btnQuickCancel);
                        EditText etQuickOrder= ve.findViewById(R.id.etQuickOrder);

                        ArrayList<String> catsList= new ArrayList<>();
                        catsList.add("Всі Магазини");
                        catsList.add(" - Продовольчі");
                        catsList.add(" - Одежи");
                        catsList.add(" - Спортивні");
                        catsList.add(" - Автомобільні");
                        catsList.add(" - Будівельні");
                        catsList.add(" - Побутової техніки");
                        catsList.add(" - Сантехніки");
                        catsList.add(" - Електрики та освітлення");
                        catsList.add(" - Зоомагазини");
                        catsList.add(" - Сільськогосподарські");
                        catsList.add(" - Аптеки");
                        catsList.add(" - Побутової хімії");
                        catsList.add(" - Канцелярські");
                        catsList.add(" - Дитячі");
                        catsList.add(" - Інші");

                        //Здоров'я,Логістині,Краси,Освіти,Комунальні,Побутові,Нерухомість,Туристичні,Страхові,Банківські
                        catsList.add("Всі Послуги");
                        catsList.add(" - Здоров'я");
                        catsList.add(" - Логістичні");
                        catsList.add(" - Краси");
                        catsList.add(" - Освіти");
                        catsList.add(" - Комунальні");
                        catsList.add(" - Побутові");
                        catsList.add(" - Нерухомість");
                        catsList.add(" - Туристичні");
                        catsList.add(" - Страхові");
                        catsList.add(" - Банківські");
                        catsList.add(" - Інші");

                        String[]items=catsList.toArray(new String[0]);
                        Spinner spinner = (Spinner) ve.findViewById(R.id.spCats);
                        // Создаем адаптер ArrayAdapter с помощью массива строк и стандартной разметки элемета spinner
                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), R.layout.spinner_item, catsList);
                        // Определяем разметку для использования при выборе элемента
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Применяем адаптер к элементу spinner
                        spinner.setAdapter(adapter);

                        builder
                                .setView(ve)
                                .setCancelable(false)
                        ;
                        final AlertDialog alert2 = builder.create();

                        btnQuickConfirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(prefer.contains("auth")){
                                    if(etQuickOrder.getText().length()>5){

                                    }
                                    else{
                                        Toast.makeText(getContext(), "Мінімально 5 символів", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                else {
                                    tvQuickAsk.setText("Ви не авторизовані. Для можливості робити замовлення пройдіть Найшвидку Авторизацію");
                                    tvQuickAsk.setTextColor(Color.RED);
                                }

                            }
                        });

                        btnQuickCancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                alert2.dismiss();
                            }
                        });

               /* btnConfirmDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                        setHasOptionsMenu(true);
                        NavigationView navigationView = getActivity().findViewById(R.id.nav_view);
                        Menu menuNav=navigationView.getMenu();
                        MenuItem nav_reg = menuNav.findItem(R.id.nav_reg);
                        nav_reg.setVisible(true);
                        MenuItem nav_auth = menuNav.findItem(R.id.nav_auth_company_fragment);
                        nav_auth.setVisible(false);
                        MenuItem nav_cloud_market = menuNav.findItem(R.id.nav_entrance_market);
                        nav_cloud_market.setVisible(false);
                        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
                        prefer.edit().clear().commit();
                        Toast.makeText(getContext(), "Вашу Компанію деактивовано", Toast.LENGTH_SHORT).show();
                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_weather);
                    }
                });

                btnConfirmCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alert2.dismiss();
                    }
                });
*/
                        alert2.show();
                        alert2.setCancelable(false);

                    }
                });


            }
            else Toast.makeText(getContext(), "Спробуйте пізніше", Toast.LENGTH_SHORT).show();
        }
    }
}
