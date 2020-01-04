package encityproject.rightcodeit.com.encityproject.benchMap;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;



import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import encityproject.rightcodeit.com.encityproject.R;
import yuku.ambilwarna.AmbilWarnaDialog;


public class BeanchFragment extends Fragment {
    private Button btnUsb, btnColor, btnBridge, btnLed;
    private int mDefaultColor;
    private ImageView im;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beanch, container, false);

        btnUsb = v.findViewById(R.id.btnUsb);
        btnColor = v.findViewById(R.id.btnColor);
        btnBridge = v.findViewById(R.id.btnBridge);
        btnLed = v.findViewById(R.id.btnLed);

        im = v.findViewById(R.id.im);

        btnUsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openDialogUsb();
            }
        });

        btnBridge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final SeekBar sbMain;
                final TextView tvCount;
                Animation[] anim = {null};

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

                LayoutInflater inflater = getActivity().getLayoutInflater();
                View view = inflater.inflate(R.layout.layout_dialog_bridge, null);

                sbMain = view.findViewById(R.id.sbMain);
                tvCount = view.findViewById(R.id.tvCount);

                builder.setView(view).setTitle("Яркость").setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int prog = sbMain.getProgress();

                        if (prog <= 10) {
                            im.setAlpha((float) 0.10);
                        }else if(prog <= 20 && prog >= 11) {
                            im.setAlpha((float) 0.20);
                        }else if(prog <= 30 && prog >= 21) {
                            im.setAlpha((float) 0.30);
                        }else if(prog <= 40 && prog >= 31) {
                            im.setAlpha((float) 0.40);
                        }else if(prog <= 50 && prog >= 41) {
                            im.setAlpha((float) 0.50);
                        }else if(prog <= 60 && prog >= 51) {
                            im.setAlpha((float) 0.60);
                        }else if(prog <= 70 && prog >= 61) {
                            im.setAlpha((float) 0.70);
                        }else if(prog <= 80 && prog >= 71) {
                            im.setAlpha((float) 0.80);
                        }else if(prog <= 90 && prog >= 81) {
                            im.setAlpha((float) 0.90);
                        }else if(prog <= 100&& prog >= 91) {
                            im.setAlpha((float) 1);
                        }
                    }
                });
                builder.show();


                sbMain.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        tvCount.setText(String.valueOf(seekBar.getProgress()));
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
        dialogUsb.setTargetFragment(BeanchFragment.this, 1);
        dialogUsb.show(getFragmentManager(), "DialogUsb");
    }

    private void openDialogBrige() {
        DialogBridge dialogBridge = new DialogBridge();
        dialogBridge.setTargetFragment(BeanchFragment.this, 1);
        dialogBridge.show(getFragmentManager(), "DialogBrige");
    }
}
