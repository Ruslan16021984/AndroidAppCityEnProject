package encityproject.rightcodeit.com.encityproject.ui.registration.cloudMarket;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
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

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.MainActivityWithNaviDrawer;
import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.CloudMarketAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.adapter.inner_adapter.CurCatCompaniesAdapter;
import encityproject.rightcodeit.com.encityproject.ui.market.model.CategoryModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurCatCompaniesFragment extends Fragment {
    private int port = 4656;
    private String ip = "192.168.1.46";
    //private String ip = "192.168.1.103";
    //private String ip = "35.232.178.112";
    private CurCatCompaniesAdapter adapter = null;

    private RecyclerView recyclerView;
    private Activity activity;
    private Context context;
    private ArrayList<String> curCatListComp;
    private ProgressBar pb;
    private TextView tvNullComp;
    private String curCategory;

    public CurCatCompaniesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_cur_cat_companies, container, false);
        Bundle bundle = getArguments();
        curCategory=bundle.getString("curcat","");
        Toolbar toolbar = (Toolbar) getActivity().findViewById(R.id.toolbar);

        toolbar.setTitle(curCategory.split("@.#")[1]);
        //getActivity().setTitle(curCategory.split("@.#")[1]);

        pb=view.findViewById(R.id.pb);
        pb.setVisibility(View.VISIBLE);
        tvNullComp=view.findViewById(R.id.tvNullComp);
        tvNullComp.setVisibility(View.INVISIBLE);

        activity = getActivity();
        context = getContext();
        curCatListComp=new ArrayList<>();
        recyclerView =  view.findViewById(R.id.rvCurCatComp);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 1, GridLayoutManager.VERTICAL, false));

        GetCurCatCompanies getCurCatCompanies = new GetCurCatCompanies();
        getCurCatCompanies.execute("getcurcat"+"@.#"+curCategory.split("@.#")[0]);

        return view;
    }


    class GetCurCatCompanies extends AsyncTask<String, Void, Socket> {
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
                    curCatListComp =  (ArrayList<String>) object;

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
            pb.setVisibility(View.INVISIBLE);
            if(curCatListComp!=null && curCatListComp.size()>0) {
                adapter = new CurCatCompaniesAdapter(context,activity ,curCatListComp, curCategory);
                recyclerView.setAdapter(adapter);
            }
            else {
            Toast.makeText(getContext(), "Спробуйте пізніше", Toast.LENGTH_SHORT).show();
            tvNullComp.setVisibility(View.VISIBLE);
            }
        }
    }

}
