package encityproject.rightcodeit.com.encityproject.ui.taxiClient;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import encityproject.rightcodeit.com.encityproject.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TaxiFinishOrderFragment extends Fragment implements View.OnClickListener {

    private ImageView ivCarPoint1, ivCarPoint2, ivCarPoint3, ivCarPoint4, ivCarPoint5;
    private Button btnMarkOrder;
    private int colorGrey, colorYellow, point;

    public TaxiFinishOrderFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_taxi_finish_order, container, false);
        point=5; // max point for order as default
        ivCarPoint1=view.findViewById(R.id.ivCarPoint1);
        ivCarPoint2=view.findViewById(R.id.ivCarPoint2);
        ivCarPoint3=view.findViewById(R.id.ivCarPoint3);
        ivCarPoint4=view.findViewById(R.id.ivCarPoint4);
        ivCarPoint5=view.findViewById(R.id.ivCarPoint5);
        btnMarkOrder=view.findViewById(R.id.btnMarkOrder);

        colorGrey=Color.rgb(204,204,204);
        colorYellow=Color.rgb(224,200,0);
        //Paint all marks into default color Grey
        paintCarPoint(ivCarPoint1, ivCarPoint2, ivCarPoint3, ivCarPoint4, ivCarPoint5, colorYellow);

        ivCarPoint1.setOnClickListener(this);
        ivCarPoint2.setOnClickListener(this);
        ivCarPoint3.setOnClickListener(this);
        ivCarPoint4.setOnClickListener(this);
        ivCarPoint5.setOnClickListener(this);


        btnMarkOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //send int point to server
                Toast.makeText(getContext(), String.valueOf(point), Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    private void paintCarPoint(ImageView ivCarPoint1, ImageView ivCarPoint2, ImageView ivCarPoint3, ImageView ivCarPoint4, ImageView ivCarPoint5, int color) {
        ivCarPoint1.setColorFilter(color);
        ivCarPoint2.setColorFilter(color);
        ivCarPoint3.setColorFilter(color);
        ivCarPoint4.setColorFilter(color);
        ivCarPoint5.setColorFilter(color);
    }

    private void paintCarPointResult(ImageView ivCarPoint, int color) {
        ivCarPoint.setColorFilter(color);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ivCarPoint1:
                paintCarPointResult(ivCarPoint1, colorYellow);
                paintCarPointResult(ivCarPoint2, colorGrey);
                paintCarPointResult(ivCarPoint3, colorGrey);
                paintCarPointResult(ivCarPoint4, colorGrey);
                paintCarPointResult(ivCarPoint5, colorGrey);
                point=1;
                break;
            case R.id.ivCarPoint2:
                paintCarPointResult(ivCarPoint1, colorYellow);
                paintCarPointResult(ivCarPoint2, colorYellow);
                paintCarPointResult(ivCarPoint3, colorGrey);
                paintCarPointResult(ivCarPoint4, colorGrey);
                paintCarPointResult(ivCarPoint5, colorGrey);
                point=2;
                break;
            case R.id.ivCarPoint3:
                paintCarPointResult(ivCarPoint1, colorYellow);
                paintCarPointResult(ivCarPoint2, colorYellow);
                paintCarPointResult(ivCarPoint3, colorYellow);
                paintCarPointResult(ivCarPoint4, colorGrey);
                paintCarPointResult(ivCarPoint5, colorGrey);
                point=3;
                break;
            case R.id.ivCarPoint4:
                paintCarPointResult(ivCarPoint1, colorYellow);
                paintCarPointResult(ivCarPoint2, colorYellow);
                paintCarPointResult(ivCarPoint3, colorYellow);
                paintCarPointResult(ivCarPoint4, colorYellow);
                paintCarPointResult(ivCarPoint5, colorGrey);
                point=4;
                break;
            case R.id.ivCarPoint5:
                paintCarPointResult(ivCarPoint1, colorYellow);
                paintCarPointResult(ivCarPoint2, colorYellow);
                paintCarPointResult(ivCarPoint3, colorYellow);
                paintCarPointResult(ivCarPoint4, colorYellow);
                paintCarPointResult(ivCarPoint5, colorYellow);
                point=5;
                break;

        }
    }
}
