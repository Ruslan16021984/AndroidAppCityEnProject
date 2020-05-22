package encityproject.rightcodeit.com.encityproject.ui.taxiClient;


import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import java.util.ArrayList;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Adapters.AnyAddressAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxiTakenOrderFragment extends Fragment {
    private Button btnCancelTaxi;
    private TextView tvAuto, tvAutoColor, tvAutoNum, tvAutoDriver, tvAutoTime;
    private LinearLayout llBtnCallDriver;

    public TaxiTakenOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_taxi_taken_order, container, false);

        tvAuto=view.findViewById(R.id.tvAuto);
        tvAutoColor=view.findViewById(R.id.tvAutoColor);
        tvAutoNum=view.findViewById(R.id.tvAutoNum);
        tvAutoDriver=view.findViewById(R.id.tvAutoDriver);
        tvAutoNum=view.findViewById(R.id.tvAutoNum);

        btnCancelTaxi=view.findViewById(R.id.btnCancelTaxi);
        llBtnCallDriver=view.findViewById(R.id.llBtnCallDriver);
//В этом фрагменте клиент видит машину такси на карте, после взятия заказа таксистом.
        //после подтверждения выполнения заказа таксистом, клиента перенаправляет на оценку поездки в TaxiFinishOrderFragment

        //bottom handler imitation take order. Ждем взятие заказа такси
        //удалить хендлер, это имитация
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Bundle bundle = new Bundle();
                //    bundle.putString("place", etAddress.getText().toString());
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment);
                navController.navigate(R.id.nav_taxi_finish_order_fragment, bundle);
            }
        },5000);

        //отменить поездку пока таксист не взял заказ. После взятия заказа нельзя
        btnCancelTaxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialogCancelTaxi();
            }
        });

        //звонок таксисту
        llBtnCallDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("tel: " + "111"));
                if (intent != null) {
                    startActivity(intent);
                }
            }
        });

        return view;
    }

    private void openDialogCancelTaxi() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService( Context.LAYOUT_INFLATER_SERVICE );
        View v = inflater.inflate( R.layout.dialog_cancel_taxi, null );

        Button btnCancelTaxiDialog= v.findViewById(R.id.btnCancelTaxiDialog);
        Button btnTaxiNo = v.findViewById(R.id.btnCancelTaxiNo);

        builder
                .setView(v)
                .setCancelable(false)
        ;
        final AlertDialog alert2 = builder.create();

        btnCancelTaxiDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btnTaxiNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert2.dismiss();
            }
        });

        alert2.show();

    }

}
