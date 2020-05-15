package encityproject.rightcodeit.com.encityproject.ui.discount;

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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.BuildConfig;
import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.news.NewsFragment;
import encityproject.rightcodeit.com.encityproject.ui.phonesBook.AlertDialogPhoneNumbersAdapter;
import encityproject.rightcodeit.com.encityproject.ui.phonesBook.Contact;
import encityproject.rightcodeit.com.encityproject.ui.phonesBook.ContactAdapter;

public class DiscountFragment extends Fragment {
    private ArrayOfDiscount arrayOfDiscount;
    private DiscountAdapter discountAdapter;
    private int index;
    private MapView mMapView;
    private MapController mMapController;
    private int port = 4656;
    //private String ip = "192.168.1.46";
    private String ip = "35.232.178.112";
    private ArrayList<Discount> alDisc;
    private ListView listView;
    private ProgressBar pbDisc;
    private Handler h;
    private TextView tvFDisc;

    public DiscountFragment() {
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
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
        alDisc=new ArrayList<>();
        listView.setVisibility(View.INVISIBLE);
        h = new Handler();
        h.post(new Runnable() {
            @Override
            public void run() {
                if(isOnline()){
                    tvFDisc.setVisibility(View.INVISIBLE);
                    GetDisc getDisc = new GetDisc();
                    getDisc.execute("getDisc");
                }
                else {
                    tvFDisc.setVisibility(View.VISIBLE);
                    h.postDelayed(this,5000);
                }
            }
        });


    }


/*    private void initData(View view) {
        arrayOfDiscount = new ArrayOfDiscount();
        discountAdapter = new DiscountAdapter(getActivity(), arrayOfDiscount.getArrayOfDiscount());
        ListView listView = view.findViewById(R.id.lv_discounts);
        ArrayList<Discount> ac = new ArrayList<>();
        listView.setAdapter(new DiscountAdapter(getActivity(), ac));
    }*/
@Override
public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    // Inflate the menu items for use in the action bar
    inflater.inflate(R.menu.menus, menu);
    super.onCreateOptionsMenu(menu, inflater);
}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.itemMenu){
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View vOpenStreetMap = inflater.inflate(R.layout.custom_dialog_add_sales, null);


            builder
                    .setView(vOpenStreetMap)
                    .setCancelable(true);
            final AlertDialog alert = builder.create();
            alert.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_discount, container, false);
        tvFDisc=v.findViewById(R.id.tvFDisc);
        tvFDisc.setVisibility(View.INVISIBLE);
        pbDisc=v.findViewById(R.id.pbDisc);
        pbDisc.setVisibility(View.VISIBLE);
        alDisc=new ArrayList<>();
        /*arrayOfDiscount = new ArrayOfDiscount();
        discountAdapter = new DiscountAdapter(getActivity(), arrayOfDiscount.getArrayOfDiscount());
        */
        setHasOptionsMenu(true);

        listView = v.findViewById(R.id.lv_discounts);


      //  mMapView = (MapView) v.findViewById(R.id.mapview);

      //  MapView mMapView = v.findViewById(R.id.mv);
        //mMapView.setTileSource(TileSourceFactory.DEFAULT_TILE_SOURCE);
      //  mMapView.setTileSource(TileSourceFactory.MAPNIK);
        //mMapView.setBuiltInZoomControls(true);
       /* mMapView.setBuiltInZoomControls(true);
        mMapView.setMultiTouchControls(true);
        final IMapController mapController = mMapView.getController();
        //   mapController.setZoom(9.5);
        GeoPoint startPoint = new GeoPoint(47.490461, 34.659276);
        mapController.setZoom(16);
        mapController.setCenter(startPoint);
*/
        /*listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //создать диалог
//                Toast toast = Toast.makeText(getActivity(), "Click on item position: " + (position + 1), Toast.LENGTH_LONG);
//                toast.show();
                String description = arrayOfDiscount.getArrayOfDiscount().get(position).getDescription();

                Bundle bundle = new Bundle();
//                bundle.putInt("numberDescription", position);
                bundle.putString("singleDescription",
                        arrayOfDiscount.getArrayOfDiscount().get(position).getNameGoods() + "@" +
                                arrayOfDiscount.getArrayOfDiscount().get(position).getPriceAndDiscount() + "@" +
                                arrayOfDiscount.getArrayOfDiscount().get(position).getLan() + "@" +
                                arrayOfDiscount.getArrayOfDiscount().get(position).getLon() + "@" +
                                arrayOfDiscount.getArrayOfDiscount().get(position).getImgPath() + "@" +
                                arrayOfDiscount.getArrayOfDiscount().get(position).getDescription());

                Toast.makeText(getActivity(), arrayOfDiscount.getArrayOfDiscount().get(position).getDescription() + " " + (position + 1), Toast.LENGTH_LONG).show();


                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_description, bundle);

//  Написати інтент на DescriptionFragment и сховати цей фрагмент.
//  В інтенті передати String description
            }
        });
*/
        return v;
    }

    class GetDisc extends AsyncTask<String, Void, Socket> {
        private ArrayList<String> listDisc;
        private String linkCheckVApp = "myNull";
        private Socket socket;

        @Override
        protected Socket doInBackground(String... params) {
            listDisc = new ArrayList<>();

            PrintWriter pw;
            try {
                socket = new Socket(ip, port);

                pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                pw.write(params[0] + "@.#" + linkCheckVApp + "\n");
                pw.flush();

                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                Object object = objectInput.readUnshared();
                listDisc = (ArrayList<String>) object;
                Log.d("check size ", String.valueOf(listDisc.size()));
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


            for(int i=0; i<listDisc.size();i++){
                Log.d("DISC ", listDisc.get(i));
                alDisc.add(new Discount(listDisc.get(i).split(";")[0],listDisc.get(i).split(";")[1],listDisc.get(i).split(";")[2],
                        listDisc.get(i).split(";")[3], listDisc.get(i).split(";")[4],listDisc.get(i).split(";")[5],
                        listDisc.get(i).split(";")[6],listDisc.get(i).split(";")[7],listDisc.get(i).split(";")[8],
                        listDisc.get(i).split(";")[9]));
            }
//data111,43,23
            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //pbListPhones.setVisibility(View.INVISIBLE);
            listView.setVisibility(View.VISIBLE);
            pbDisc.setVisibility(View.INVISIBLE);
            if(listDisc.size()>0) {
                discountAdapter = new DiscountAdapter(getActivity(), alDisc);
                listView.setAdapter(discountAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //создать диалог
//                Toast toast = Toast.makeText(getActivity(), "Click on item position: " + (position + 1), Toast.LENGTH_LONG);
//                toast.show();
                        String description = alDisc.get(position).getDescription();

                        Bundle bundle = new Bundle();
//                bundle.putInt("numberDescription", position);
                        bundle.putString("singleDescription",
                                alDisc.get(position).getNameGoods() + "@" +
                                        alDisc.get(position).getPriceAndDiscount() + "@" +
                                        alDisc.get(position).getLan() + "@" +
                                        alDisc.get(position).getLon() + "@" +
                                        alDisc.get(position).getImgPath() + "@" +
                                        alDisc.get(position).getDescription()+"@" +
                                        alDisc.get(position).getStartTime()+"@" +
                                        alDisc.get(position).getEndTime()+"@"+
                                        alDisc.get(position).getPhone()+"@"+
                                        alDisc.get(position).getInstalink());

            //            Toast.makeText(getActivity(), arrayOfDiscount.getArrayOfDiscount().get(position).getDescription() + " " + (position + 1), Toast.LENGTH_LONG).show();


                        NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                        navController.navigate(R.id.nav_description, bundle);


//  Написати інтент на DescriptionFragment и сховати цей фрагмент.
//  В інтенті передати String description
                    }
                });

            }
            else{
                tvFDisc.setText("Ще немає знижок");
                tvFDisc.setVisibility(View.VISIBLE);
               // Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }

}
