package encityproject.rightcodeit.com.encityproject.ui.benchMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


import encityproject.rightcodeit.com.encityproject.R;
/*

public class DialogBridge extends AppCompatDialogFragment {
    private SeekBar sbMain;
    private TextView tvCount;
    private ImageView ivLight;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.custom_dialog_light_power, null);

        builder.setView(view).setTitle("Яркость").setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
//        btnClose = view.findViewById(R.id.btnClose);
//        btnOpen = view.findViewById(R.id.btnOpen);
        sbMain = view.findViewById(R.id.sb_light_power);
        tvCount = view.findViewById(R.id.tv_count_light_power);
        ivLight = view.findViewById(R.id.iv_light);

        sbMain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                tvCount.setText(String.valueOf(seekBar.getProgress()));
//                float fSeekBar = seekBar.getProgress()/100;
//                ivLight.setAlpha(fSeekBar);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                tvCount.setText(String.valueOf(seekBar.getProgress()));
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                tvCount.setText(String.valueOf(seekBar.getProgress()));
            }
        });

        return builder.create();
    }


}
*/
