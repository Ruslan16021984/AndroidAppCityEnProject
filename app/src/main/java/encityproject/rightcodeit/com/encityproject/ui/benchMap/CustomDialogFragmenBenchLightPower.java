package encityproject.rightcodeit.com.encityproject.ui.benchMap;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import encityproject.rightcodeit.com.encityproject.R;

public class CustomDialogFragmenBenchLightPower extends DialogFragment {
    private LayoutInflater inflater;
    private View v;

    private ImageView ivLightPower1;
    private ImageView ivLightPower2;
    private ImageView ivLightPower3;
    private SeekBar sbLightPower;
    private ImageView ivSunInDialogPowerLight;
    private TextView tvCountSeekBar;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        inflater = getActivity().getLayoutInflater();
        v = inflater.inflate(R.layout.custom_dialog_light_power, null);

        createButton();
        makeSeekBarLight();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Яскравість")
                .setMessage("Для підтвердження нажміть ОК")
                .setView(v)
//                .setView(R.layout.custom_dialog_light_power)
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }

                })
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int countLight = sbLightPower.getProgress();
                        tvCountSeekBar.setText(countLight + " %");
                        if (countLight < 34) {
                            ivLightPower1.setVisibility(View.VISIBLE);
                            ivLightPower2.setVisibility(View.INVISIBLE);
                            ivLightPower3.setVisibility(View.INVISIBLE);
                        }
                        if (countLight < 67 && countLight > 33) {
                            ivLightPower1.setVisibility(View.VISIBLE);
                            ivLightPower2.setVisibility(View.VISIBLE);
                            ivLightPower3.setVisibility(View.INVISIBLE);
                        }
                        if ((countLight > 67)) {
                            ivLightPower1.setVisibility(View.VISIBLE);
                            ivLightPower2.setVisibility(View.VISIBLE);
                            ivLightPower3.setVisibility(View.VISIBLE);
                        }
                    }

                })
                .create();
    }

    private void createButton() {
        ivLightPower1 = v.findViewById(R.id.iv_light_power1);
        ivLightPower2 = v.findViewById(R.id.iv_light_power2);
        ivLightPower3 = v.findViewById(R.id.iv_light_power3);

        Button btnCancel = v.findViewById(R.id.btn_cancel_light_power);
        Button btnOk = v.findViewById(R.id.btn_ok_light_power);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int countLight = sbLightPower.getProgress();
                tvCountSeekBar.setText(countLight + " %");
                if (countLight < 34) {
                    ivLightPower1.setVisibility(View.VISIBLE);
                    ivLightPower2.setVisibility(View.INVISIBLE);
                    ivLightPower3.setVisibility(View.INVISIBLE);
                }
                if (countLight < 67 && countLight > 33) {
                    ivLightPower1.setVisibility(View.VISIBLE);
                    ivLightPower2.setVisibility(View.VISIBLE);
                    ivLightPower3.setVisibility(View.INVISIBLE);
                }
                if ((countLight > 67)) {
                    ivLightPower1.setVisibility(View.VISIBLE);
                    ivLightPower2.setVisibility(View.VISIBLE);
                    ivLightPower3.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void makeSeekBarLight() {
        tvCountSeekBar = v.findViewById(R.id.tv_count_light_power);
        ivSunInDialogPowerLight = v.findViewById(R.id.iv_dialog_light_power);
        sbLightPower = v.findViewById(R.id.sb_light_power);
        sbLightPower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvCountSeekBar.setText(seekBar.getProgress() + " %");
                float toSetAlpha = sbLightPower.getProgress();
                ivSunInDialogPowerLight.setAlpha(toSetAlpha / 100);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
