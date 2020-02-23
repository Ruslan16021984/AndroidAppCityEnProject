package encityproject.rightcodeit.com.encityproject.ui.benchMap;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import encityproject.rightcodeit.com.encityproject.R;

public class BenchFragmentNew extends Fragment {

    private MyAsyncTask myAsyncTask;

    private Handler handlerButtonUsb;

    private TextView tvUsbTimer;

    private TextView tvLightPower;
    private TextView tvLightColor;
    private SeekBar sbLightPower;
    private SeekBar sbLightColor;
    private Button btnUsb;

    private ImageView ivLightPower1;
    private ImageView ivLightPower2;
    private ImageView ivLightPower3;

    String[] color;
    private String[] red = {MyColor.red100, MyColor.red90, MyColor.red80, MyColor.red70, MyColor.red60};
    private String[] orange = {MyColor.orange100, MyColor.orange90, MyColor.orange80, MyColor.orange70, MyColor.orange60};
    private String[] yellow = {MyColor.yellow100, MyColor.yellow90, MyColor.yellow80, MyColor.yellow70, MyColor.yellow60};
    private String[] green = {MyColor.green100, MyColor.green90, MyColor.green80, MyColor.green70, MyColor.green60};
    private String[] blue = {MyColor.blue100, MyColor.blue90, MyColor.blue80, MyColor.blue70, MyColor.blue60};
    private String[] darkBlue = {MyColor.darkBlue100, MyColor.darkBlue90, MyColor.darkBlue80, MyColor.darkBlue70, MyColor.darkBlue60};
    private String[] purple = {MyColor.purple100, MyColor.purple90, MyColor.purple80, MyColor.purple70, MyColor.purple60};
    private String[] white = {MyColor.white100, MyColor.white90, MyColor.white80, MyColor.white70, MyColor.white60};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable final ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_beanch_new, container, false);

        tvLightColor = v.findViewById(R.id.tv_count_lightColor);
        tvLightPower = v.findViewById(R.id.tv_count_lightPower);

        sbLightPower = v.findViewById(R.id.sb_light);
        sbLightColor = v.findViewById(R.id.sb_color);

        ivLightPower1 = v.findViewById(R.id.iv_light_power1);
        ivLightPower2 = v.findViewById(R.id.iv_light_power2);
        ivLightPower3 = v.findViewById(R.id.iv_light_power3);

        btnUsb = v.findViewById(R.id.btn_usb);
        tvUsbTimer = v.findViewById(R.id.tv_usb_timer);

/////////////////////////////////////////////////////////////////////////////////////
//        handlerButtonUsb = new Handler() {
//            @Override
//            public void handleMessage(Message msg) {
//                btnUsb.setEnabled(false);
//                btnUsb.setVisibility(View.INVISIBLE);
//                tvUsbTimer.setVisibility(View.VISIBLE);
//                tvUsbTimer.setText("Залишилось часу на підключення: " + msg.what + " секунд");
//                if (msg.what == 0) {
//                    btnUsb.setEnabled(true);
//                    btnUsb.setVisibility(View.VISIBLE);
//                    tvUsbTimer.setVisibility(View.INVISIBLE);
//                    btnUsb.setText(R.string.btn_usb);
//                }
//            }
//        };
/////////////////////////////////////////////////////////////////////////////////////

        sbLightPower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvLightPower.setText(seekBar.getProgress() + " %");
                float toSetAlpha = sbLightPower.getProgress();
                ivLightPower1.setAlpha(toSetAlpha / 100);
                ivLightPower2.setAlpha(toSetAlpha / 100);
                ivLightPower2.setAlpha(toSetAlpha / 100);
                if (toSetAlpha < 34) {
                    ivLightPower1.setVisibility(View.VISIBLE);
                    ivLightPower2.setVisibility(View.INVISIBLE);
                    ivLightPower3.setVisibility(View.INVISIBLE);
                }
                if (toSetAlpha < 67 && toSetAlpha > 33) {
                    ivLightPower1.setVisibility(View.VISIBLE);
                    ivLightPower2.setVisibility(View.VISIBLE);
                    ivLightPower3.setVisibility(View.INVISIBLE);
                }
                if ((toSetAlpha > 67)) {
                    ivLightPower1.setVisibility(View.VISIBLE);
                    ivLightPower2.setVisibility(View.VISIBLE);
                    ivLightPower3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        sbLightColor.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                if (progress > 0 && progress <= 10) {
                    color = red;
                    chengeColorSeekBar(progress);
                }
                if (progress > 10 && progress <= 20) {
                    color = orange;
                    chengeColorSeekBar(progress - 10);
                }
                if (progress > 20 && progress <= 30) {
                    color = yellow;
                    chengeColorSeekBar(progress - 20);
                }
                if (progress > 30 && progress <= 40) {
                    color = green;
                    chengeColorSeekBar(progress - 30);
                }
                if (progress > 40 && progress <= 50) {
                    color = blue;
                    chengeColorSeekBar(progress - 40);
                }
                if (progress > 50 && progress <= 60) {
                    color = darkBlue;
                    chengeColorSeekBar(progress - 50);
                }
                if (progress > 60 && progress <= 70) {
                    color = purple;
                    chengeColorSeekBar(progress - 60);
                }
                if (progress > 70 && progress <= 80) {
                    color = white;
                    chengeColorSeekBar(progress - 70);
                }
            }

            private void setColor(String color) {
                tvLightColor.setBackgroundColor(Color.parseColor(color));
                ivLightPower1.setColorFilter(Color.parseColor(color));
                ivLightPower2.setColorFilter(Color.parseColor(color));
                ivLightPower3.setColorFilter(Color.parseColor(color));
            }

            private void chengeColorSeekBar(int progress) {
                if (progress >= 1 && progress <= 2) {
                    setColor(color[0]);
                }
                if (progress > 2 && progress <= 4) {
                    setColor(color[1]);
                }
                if (progress > 4 && progress <= 6) {
                    setColor(color[2]);
                }
                if (progress > 6 && progress <= 8) {
                    setColor(color[3]);
                }
                if (progress > 8 && progress <= 10) {
                    setColor(color[4]);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        btnUsb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                usbOpen();
            }

            private void usbOpen() {
                /////////////////////////////////////////////////////////////////////////////////////
//                Thread threadUsbOpen = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        for (int i = 30; i >= 0; i--) {
//                            handlerButtonUsb.sendEmptyMessage(i);
//                            try {
//                                TimeUnit.SECONDS.sleep(1);
//                            } catch (InterruptedException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                    }
//                });
//                threadUsbOpen.start();
                /////////////////////////////////////////////////////////////////////////////////////
                myAsyncTask = new MyAsyncTask();
                myAsyncTask.execute();
            }
        });

        return v;
    }

    class MyAsyncTask extends AsyncTask<Void, Integer, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            btnUsb.setEnabled(false);
            btnUsb.setVisibility(View.INVISIBLE);
            tvUsbTimer.setVisibility(View.VISIBLE);
            tvUsbTimer.setText("Залишилось часу на підключення: " + "30" + " секунд");
        }

        @Override
        protected Void doInBackground(Void... params) {
            for (int i = 30; i >= 0; i--) {
                try {
                    publishProgress(i);
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvUsbTimer.setText("Залишилось часу на підключення: " + values[0] + " секунд");
        }

        @Override
        protected void onPostExecute(Void result) {
            btnUsb.setEnabled(true);
            btnUsb.setVisibility(View.VISIBLE);
            tvUsbTimer.setVisibility(View.INVISIBLE);
        }
    }
}

