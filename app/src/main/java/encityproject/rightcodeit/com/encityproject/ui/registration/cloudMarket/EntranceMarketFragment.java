package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.OrdersPagerAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.SlidingTabLayout;
import encityproject.rightcodeit.com.encityproject.ui.registration.CatChoiceAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class EntranceMarketFragment extends Fragment {
    private SlidingTabLayout slidingTabLayout;
    private LinearLayout llQuickOrder;
    private ViewPager viewPager;
    EntrancePagerAdapter adapter;
    private SharedPreferences prefer;
    private SharedPreferences.Editor editor;
    private static final String APP_PREFERENCES = "ensettings";

    CharSequence Titles[]={"Магазини","Послуги"};
    int Numboftabs = 2;

    public EntranceMarketFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_entrance_market, container, false);
        prefer=getContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);

        llQuickOrder=view.findViewById(R.id.llQuickOrder);

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
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewPager = view.findViewById(R.id.viewpager);
        adapter =  new EntrancePagerAdapter(getChildFragmentManager(),Titles,Numboftabs);
        viewPager.setAdapter(adapter);

        slidingTabLayout = view.findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setViewPager(viewPager);
    }
}
