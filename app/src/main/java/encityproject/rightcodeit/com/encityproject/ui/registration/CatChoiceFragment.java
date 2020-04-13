package encityproject.rightcodeit.com.encityproject.ui.registration;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
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
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.CatAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CatChoiceFragment extends Fragment {

    private int port = 4656;
    private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    //private String ip = "35.232.178.112";
    private RecyclerView recyclerView, recyclerViewService;
    private ArrayList<String> categoryList, catsListStore, catsListService;
    private ArrayList<ArrayList<String>> categoryFromServerList;
    private Context context;
    private CatChoiceAdapter catChoiceAdapter, catChoiceAdapterService;
    private Button btnCatChoice;
    private String bunRole, bunPhone;
    private ProgressBar pbCats;

    public CatChoiceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getContext();
        View v= inflater.inflate(R.layout.fragment_cat_choice, container, false);
        btnCatChoice=v.findViewById(R.id.btnCatChoice);
        recyclerView =  v.findViewById(R.id.rvChoiceListStore);
        recyclerViewService=v.findViewById(R.id.rvChoiceListService);
        pbCats=v.findViewById(R.id.pbCats);
        pbCats.setVisibility(View.VISIBLE);
        recyclerViewService.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false));

        categoryFromServerList=new ArrayList<>();
        catsListStore=new ArrayList<>();
        catsListService=new ArrayList<>();

        Bundle bundle = getArguments();
        bunRole= bundle.getString("role");
       bunPhone= bundle.getString("phone");

       GetAllCategories getAllCategories = new GetAllCategories();
       getAllCategories.execute("getcats"+"@.#"+"null");
        /*categoryList = new ArrayList<>();
        categoryList.add("Стройматеріали");
        categoryList.add("Продукти");
        categoryList.add("Послуги");
        categoryList.add("Аптека");
        categoryList.add("Техніка");
        categoryList.add("Одежа");*/

        /*catsListStore = new ArrayList<>();
        catsListStore.add("Продовольчі");
        catsListStore.add("Одежи");
        catsListStore.add("Спортивні");
        catsListStore.add("Автомобільні");
        catsListStore.add("Будівельні");
        catsListStore.add("Побутової техніки");
        catsListStore.add("Сантехніки");
        catsListStore.add("Електрики та освітлення");
        catsListStore.add("Зоомагазини");
        catsListStore.add("Сільськогосподарські");
        catsListStore.add("Аптеки");
        catsListStore.add("Побутової хімії");
        catsListStore.add("Канцелярські");
        catsListStore.add("Дитячі");
        catsListStore.add("Інші");
        catChoiceAdapter = new CatChoiceAdapter(context,catsListStore);

        //Здоров'я,Логістині,Краси,Освіти,Комунальні,Побутові,Нерухомість,Туристичні,Страхові,Банківські
        catsListService=new ArrayList<>();
        catsListService.add("Здоров'я");
        catsListService.add("Логістичні");
        catsListService.add("Краси");
        catsListService.add("Освіти");
        catsListService.add("Комунальні");
        catsListService.add("Побутові");
        catsListService.add("Нерухомість");
        catsListService.add("Туристичні");
        catsListService.add("Страхові");
        catsListService.add("Банківські");
        catsListService.add("Інші");
        catChoiceAdapterService=new CatChoiceAdapter(context,catsListService);*/

      //  catChoiceAdapter = new CatChoiceAdapter(context, categoryList);
       /* recyclerView.setAdapter(catChoiceAdapter);
        recyclerViewService.setAdapter(catChoiceAdapterService);

        btnCatChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(catChoiceAdapter.getItemsForReturn().size()>0 || catChoiceAdapterService.getItemsForReturn().size()>0) {
                    //Toast.makeText(getContext(), catChoiceAdapter.getItemsForReturn().toString(), Toast.LENGTH_SHORT).show();
                    Bundle bundle = new Bundle();
                    bundle.putStringArrayList("catstore", catsListStore);
                    bundle.putStringArrayList("catservice", catsListService);
                    bundle.putString("role", bunRole);
                    bundle.putString("phone", bunPhone);
                    bundle.putIntegerArrayList("checkedcatstore", catChoiceAdapter.getItemsForReturn());
                    bundle.putIntegerArrayList("checkedcatservice", catChoiceAdapterService.getItemsForReturn());
                    NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                    navController.navigate(R.id.nav_form_company_fragment, bundle);
                }
            }
        });*/

        return v;
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
                catChoiceAdapter = new CatChoiceAdapter(context,catsListStore);
                catChoiceAdapterService=new CatChoiceAdapter(context,catsListService);
                recyclerView.setAdapter(catChoiceAdapter);
                recyclerViewService.setAdapter(catChoiceAdapterService);

                pbCats.setVisibility(View.INVISIBLE);

                btnCatChoice.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(catChoiceAdapter.getItemsForReturn().size()>0 || catChoiceAdapterService.getItemsForReturn().size()>0) {
                            //Toast.makeText(getContext(), catChoiceAdapter.getItemsForReturn().toString(), Toast.LENGTH_SHORT).show();
                            Bundle bundle = new Bundle();
                            bundle.putStringArrayList("catstore", catsListStore);
                            bundle.putStringArrayList("catservice", catsListService);
                            bundle.putString("role", bunRole);
                            bundle.putString("phone", bunPhone);
                            bundle.putIntegerArrayList("checkedcatstore", catChoiceAdapter.getItemsForReturn());
                            bundle.putIntegerArrayList("checkedcatservice", catChoiceAdapterService.getItemsForReturn());
                            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                            navController.navigate(R.id.nav_form_company_fragment, bundle);
                        }
                        else {
                            Toast.makeText(getContext(), "Оберіть категорію", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
            else Toast.makeText(getContext(), "Спробуйте пізніше", Toast.LENGTH_SHORT).show();
        }
    }

}
