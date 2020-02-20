package encityproject.rightcodeit.com.encityproject.ui.benchMap;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import encityproject.rightcodeit.com.encityproject.R;
import yuku.ambilwarna.AmbilWarnaDialog;

public class BenchFragment extends Fragment {
    private Button btnUsb, btnColor, btnBridge, btnLed;
    private int mDefaultColor;
    private ImageView ivLight;
    private ImageView ivLightPower1;
    private ImageView ivLightPower2;
    private ImageView ivLightPower3;

    private ImageView ivSunInDialogPowerLight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beanch_new, container, false);

        btnUsb = v.findViewById(R.id.btnUsb);
        btnColor = v.findViewById(R.id.btnColor);
        btnBridge = v.findViewById(R.id.btnBridge);
        btnLed = v.findViewById(R.id.btnLed);

        ivLightPower1 = v.findViewById(R.id.iv_light_power1);
        ivLightPower2 = v.findViewById(R.id.iv_light_power2);
        ivLightPower3 = v.findViewById(R.id.iv_light_power3);



        ivLight = v.findViewById(R.id.iv_light);

        btnUsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUsb();
            }
        });

        btnBridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SeekBar sbLightPower;
                final TextView tvCount;
                Animation[] anim = {null};

//                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.custom_dialog_light_power, null);

//                sbLightPower = view.findViewById(R.id.sb_light_power);
//                tvCount = view.findViewById(R.id.tv_count_light_power);
//
//                builder.setView(view)
//                        .setTitle("Яскравість")
//                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                ivLight.setAlpha(0.5f);
//                            }
//
//                        })
//                        .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int which) {
//                                int countLight = sbLightPower.getProgress();
//
//                                tvCount.setText(sbLightPower.getProgress() + " %");
//                                if (countLight < 34) {
//                                    ivLightPower1.setVisibility(View.VISIBLE);
//                                    ivLightPower2.setVisibility(View.INVISIBLE);
//                                    ivLightPower3.setVisibility(View.INVISIBLE);
//                                }
//                                if (countLight < 67 && countLight > 33) {
//                                    ivLightPower1.setVisibility(View.VISIBLE);
//                                    ivLightPower2.setVisibility(View.VISIBLE);
//                                    ivLightPower3.setVisibility(View.INVISIBLE);
//                                }
//                                if ((countLight > 67)) {
//                                    ivLightPower1.setVisibility(View.VISIBLE);
//                                    ivLightPower2.setVisibility(View.VISIBLE);
//                                    ivLightPower3.setVisibility(View.VISIBLE);
//                                }
//
////                                if (prog <= 10) {
////                                    ivLight.setAlpha((float) 0.10);
////                                } else if (prog <= 20 && prog >= 11) {
////                                    ivLight.setAlpha((float) 0.20);
////                                } else if (prog <= 30 && prog >= 21) {
////                                    ivLight.setAlpha((float) 0.30);
////                                } else if (prog <= 40 && prog >= 31) {
////                                    ivLight.setAlpha((float) 0.40);
////                                } else if (prog <= 50 && prog >= 41) {
////                                    ivLight.setAlpha((float) 0.50);
////                                } else if (prog <= 60 && prog >= 51) {
////                                    ivLight.setAlpha((float) 0.60);
////                                } else if (prog <= 70 && prog >= 61) {
////                                    ivLight.setAlpha((float) 0.70);
////                                } else if (prog <= 80 && prog >= 71) {
////                                    ivLight.setAlpha((float) 0.80);
////                                } else if (prog <= 90 && prog >= 81) {
////                                    ivLight.setAlpha((float) 0.90);
////                                } else if (prog <= 100 && prog >= 91) {
////                                    ivLight.setAlpha((float) 1);
////                                }
//                            }
//                        });
//                builder.show();


//                sbMain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//                    @Override
//                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
////                        tvCount.setText(String.valueOf(seekBar.getProgress() + " %"));
////                        ivLight.setAlpha((float) (seekBar.getProgress() / 100));
////                        Toast.makeText(getActivity(), String.valueOf(seekBar.getProgress()), Toast.LENGTH_LONG).show();
//                    }
//
//                    @Override
//                    public void onStartTrackingTouch(SeekBar seekBar) {
//                        tvCount.setText(String.valueOf(seekBar.getProgress()));
//                    }
//
//                    @Override
//                    public void onStopTrackingTouch(SeekBar seekBar) {
//                        tvCount.setText(String.valueOf(seekBar.getProgress()));
//                    }
//                });

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                CustomDialogFragmenBenchLightPower dialogLightPower = new CustomDialogFragmenBenchLightPower();
                dialogLightPower.show(getChildFragmentManager(), "custom dialog light power");

//                WindowManager.LayoutParams params = dialogLightPower.getWindow().getAttributes();
//                params.gravity = Gravity.BOTTOM;
                            }
        });

        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogColor();
            }
        });

        return v;
    }


    private void openDialogColor() {
        AmbilWarnaDialog colorPicker = new AmbilWarnaDialog(getActivity(), mDefaultColor, new AmbilWarnaDialog.OnAmbilWarnaListener() {

            @Override
            public void onCancel(AmbilWarnaDialog dialog) {

            }

            @Override
            public void onOk(AmbilWarnaDialog dialog, int color) {
                mDefaultColor = color;
                btnColor.setBackgroundColor(mDefaultColor);
            }
        });
        colorPicker.show();
    }

    private void openDialogUsb() {
        DialogUsb dialogUsb = new DialogUsb();
        dialogUsb.setTargetFragment(BenchFragment.this, 1);
        dialogUsb.show(getFragmentManager(), "DialogUsb");
    }

    private void openDialogBrige() {
        DialogBridge dialogBridge = new DialogBridge();
        dialogBridge.setTargetFragment(BenchFragment.this, 1);
        dialogBridge.show(getFragmentManager(), "DialogBrige");
    }
}
