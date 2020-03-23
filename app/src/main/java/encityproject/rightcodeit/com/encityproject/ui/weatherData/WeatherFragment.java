package encityproject.rightcodeit.com.encityproject.ui.weatherData;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


import com.luckycatlabs.sunrisesunset.SunriseSunsetCalculator;
import com.luckycatlabs.sunrisesunset.dto.Location;
import com.rightcodeit.customweatherdata.WeatherData;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import encityproject.rightcodeit.com.encityproject.MainActivity;
import encityproject.rightcodeit.com.encityproject.MainActivityWithNaviDrawer;
import encityproject.rightcodeit.com.encityproject.R;

import static android.content.Context.AUDIO_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeatherFragment extends Fragment {

    private Uri uri;
    private Handler handlerTime, handlerInet;
    private SimpleDateFormat sdf;
    private float moveDay;
    private RotateAnimation anim;
    private Timer screenTimer;
    private Handler dataHandler;
    //private ImageView ivLogoDar1;

    private int t7=0;

    private float fromDegrees;
    private int toDegrees = 360;
    private int pivotX = 0;
    private int pivotY = -430;

    private FrameLayout.LayoutParams params;

    private AudioManager am;
    private MediaController mediaController;
    private VideoView vvBackground;
    private TextView tvTemp, tvTempSun, tvDateToday, tvPressure, tvCity, tvSunSet, tvSunRise, tvHumidity, tvDayLenght;
    private ImageView ivPressure, ivHumidity, ivSunRise, ivSunSet, ivDayLenght, ivDayRotate, ivSunSetRotate, ivSunRiseRotate, ivRound, ivNightRotate, ivRot;
    private String day, month, dayOfWeek;
    private String timeHHMM;
    String formattedDouble = "0";
    String formattedDoubleDarkTemp = "0";
    String[] arrServerData;
    private FrameLayout flBackgroundGreen, flBackgroundWhite;
    private int dayStart, dayEnd;
    private ProgressBar progessBar;
    private int port = 4656;
    //private String ip = "192.168.1.46";
    private String ip = "35.232.178.112";
    private WeatherData wdTemp;
    private WeatherData wdHP;

    public WeatherFragment() {
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    private void currentDateAndTime(Calendar calendar, SimpleDateFormat sdf)
    {
        timeHHMM = sdf.format(calendar.getTime());
        day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        dayOfWeek = String.valueOf(calendar.get(Calendar.DAY_OF_WEEK));
        tvDateToday.setText(textDayOfWeek(dayOfWeek) +", " + day + " " + textMouthofYear(month) + "\n"+ timeHHMM);
    }

    private String textMouthofYear(String monthOfYear)
    {
        int monthYear = Integer.parseInt(monthOfYear);
        String monthString = "";
        switch (monthYear) {
            case 1:  monthString = "Січня";
                break;
            case 2:  monthString = "Лютого";
                break;
            case 3:  monthString = "Березня";
                break;
            case 4:  monthString = "Квітня";
                break;
            case 5:  monthString = "Травня";
                break;
            case 6:  monthString = "Червня";
                break;
            case 7:  monthString = "Липня";
                break;
            case 8:  monthString = "Серпня";
                break;
            case 9:  monthString = "Вересня";
                break;
            case 10: monthString = "Жовтня";
                break;
            case 11: monthString = "Листопада";
                break;
            case 12: monthString = "Грудня";
                break;

        }
        month = monthString;
        return month;
    }

    private String textDayOfWeek(String dayOfWeek)
    {

        int dayWeek = Integer.parseInt(dayOfWeek);
        String dayName = "";
        switch (dayWeek) {
            case 1:  dayName = "Неділя";
                break;
            case 2:  dayName = "Понеділок";
                break;
            case 3:  dayName = "Вівторок";
                break;
            case 4:  dayName = "Середа";
                break;
            case 5:  dayName = "Четвер";
                break;
            case 6:  dayName = "П'ятниця";
                break;
            case 7:  dayName = "Субота";
                break;

        }
        dayOfWeek = dayName;
        return dayOfWeek;
    }

    public void startvideo(Uri uri) {
        vvBackground.setVideoURI(uri);
        //      vvBackground.requestFocus();
        vvBackground.setMinimumHeight(params.height);
        vvBackground.setMinimumWidth(params.width);
        vvBackground.start();

        /*vvBackground.setVideoURI(uri);
        vvBackground.start();*/

    }


    private void doCalcSome() {
        Calendar calendar = Calendar.getInstance();
        sdf = new SimpleDateFormat("HH:mm");
        currentDateAndTime(calendar, sdf);

        Location location = new Location("47.494593", "34.655766");
        SunriseSunsetCalculator sSuncalculator = new SunriseSunsetCalculator(location, "Energodar/Ukraine");
        String strSunrise = sSuncalculator.getOfficialSunriseForDate(calendar);
        String strSunSet = sSuncalculator.getOfficialSunsetForDate(calendar);

        String[] strSunriseSplit = strSunrise.split(":");
        int hourSunrise = Integer.parseInt(strSunriseSplit[0]);
        hourSunrise = hourSunrise + 3;
        String hSun = Integer.toString(hourSunrise);
        if(hSun.length()==1) {
            hSun = "0" + hSun;
            strSunrise = hSun + ":" + strSunriseSplit[1];
        }
        else{
            strSunrise = Integer.toString(hourSunrise) + ":" + strSunriseSplit[1];
        }

        String[] strSunSetSplit = strSunSet.split(":"); //17:42
        int hourSunSet = Integer.parseInt(strSunSetSplit[0]); //
        hourSunSet = hourSunSet + 3;
        String hSet = Integer.toString(hourSunSet);
        if(hSet.length()==1) {
            hSet = "0" + hSet;
            strSunSet = hSet + ":" + strSunSetSplit[1];
        }
        else{
            strSunSet = Integer.toString(hourSunSet) + ":" + strSunSetSplit[1];
        }

        dayStart = hourSunrise*60 + Integer.parseInt(strSunriseSplit[1]);
        dayEnd = hourSunSet*60 + Integer.parseInt(strSunSetSplit[1]);
        int dayLenght = dayEnd - dayStart;
        int hDay = dayLenght/60;
        int mDay = dayLenght%60;
        String mDayStr = String.valueOf(mDay);
        if(mDayStr.length()<2)
        {
            mDayStr="0"+mDayStr;
        }

        int h = calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int m = calendar.getInstance().get(Calendar.MINUTE);



        moveDay = (float)Math.round((((h*60)+m)/1440.0)*360);
        //Движение солнца или луны

        //Восход солнца
        final float moveSunrise = (float)Math.round((dayStart/1440.0)*360);
        final RotateAnimation animSunrise = new RotateAnimation(moveSunrise, moveSunrise, pivotX, pivotY);
        animSunrise.setInterpolator(new LinearInterpolator());
        animSunrise.setRepeatCount(Animation.INFINITE);
        animSunrise.setDuration(10000);
        //Заход солнца
        final float moveSunset = (float)Math.round((dayEnd/1440.0)*360);
        final RotateAnimation animSunset = new RotateAnimation(moveSunset, moveSunset, pivotX, pivotY);
        animSunset.setInterpolator(new LinearInterpolator());
        animSunset.setRepeatCount(Animation.INFINITE);
        animSunset.setDuration(10000);

        ivSunRiseRotate.startAnimation(animSunrise);
        ivSunSetRotate.startAnimation(animSunset);
        ivSunRiseRotate.setRotation(-moveSunrise);
        ivSunSetRotate.setRotation(-moveSunset);

        tvDayLenght.setText(Integer.toString(hDay)+":"+ mDayStr);

        tvSunRise.setText(strSunrise);
        tvSunSet.setText(strSunSet);

    }

    class GetWeather extends AsyncTask<String, Void, Socket> {
        private ArrayList<WeatherData> wd;
        private String linkCheckVApp = "myNull";
        private Socket socket;


        @Override
        protected Socket doInBackground(String... params) {
            wd = new ArrayList<>();

            PrintWriter pw;
            try {
                socket = new Socket(ip, port);

                pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                pw.write(params[0] + "@.#" + linkCheckVApp + "\n");
                pw.flush();

                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                Object object = objectInput.readUnshared();
                wd = (ArrayList<WeatherData>) object;
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


            for(int i=0; i<wd.size();i++){
                Log.d(wd.get(i).getName(), String.valueOf(wd.get(i).getVal1())+String.valueOf(wd.get(i).getVal2()));
                if(wd.get(i).getName().equals("data111")){
                    wdTemp = new WeatherData(wd.get(i).getName(), wd.get(i).getVal1(),wd.get(i).getVal2());
                }
                else if(wd.get(i).getName().equals("data112")){
                    wdHP = new WeatherData(wd.get(i).getName(), wd.get(i).getVal1(),wd.get(i).getVal2());
                }
            }
//data111,43,23
            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            if(wd.size()>0) {
                /*Log.d("wda", wd.get(0).getName());
                Log.d("wda", String.valueOf(wd.get(0).getVal1()));
                Log.d("wda", String.valueOf(wd.get(0).getVal2()));*/
                progessBar.setVisibility(View.INVISIBLE);

                tvTemp.setText(String.valueOf(round(wdTemp.getVal2(), 1)) + (char) 0x00B0);

                tvTempSun.setText(String.valueOf(round(wdHP.getVal1(), 1)) + (char) 0x00B0);
                tvHumidity.setText(String.valueOf(round(wdTemp.getVal1(), 1) + " %"));
                tvPressure.setText(String.valueOf(round((wdHP.getVal2() / 133.322)-1, 1) + " мм рт.ст."));

                // tvTemp.setVisibility(View.VISIBLE);
                // tvTempSun.setVisibility(View.VISIBLE);
                //      tvHumidity.setVisibility(View.VISIBLE);
                //        tvPressure.setVisibility(View.VISIBLE);
            }
            else{
                progessBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private class ScreenTimerTask extends TimerTask {

        @Override
        public void run() {

            // Do something....
            dataHandler.post(new Runnable() {
                @Override
                public void run() {
                    if(isOnline()) {
                        GetWeather getWeather = new GetWeather();
                        getWeather.execute("getw");
                    }
                    else{
                        Toast.makeText(getContext(), "Перевірте інтернет", Toast.LENGTH_SHORT).show();
                        dataHandler.postDelayed(this,5000);
                    }
                }
            });
        }
    }



    @SuppressLint("ResourceType")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_weather, container, false);

        /*ivLogoDar1 = v.findViewById(R.id.ivLogoDar1);
        Animation anim = AnimationUtils.loadAnimation(getContext(), R.anim.disapp);
        ivLogoDar1.startAnimation(anim);
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                ivLogoDar1.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/

        ivRot=v.findViewById(R.id.ivDayRotate);

        tvCity = v.findViewById(R.id.tvCity);
        tvDateToday = v.findViewById(R.id.tvDateToday);
        tvTemp = v.findViewById(R.id.tvTemp);
        tvTempSun = v.findViewById(R.id.tvTempSun);
        tvPressure = v.findViewById(R.id.tvPressure);
        tvSunSet = v.findViewById(R.id.tvSunset);
        tvSunRise = v.findViewById(R.id.tvSunrise);
        tvHumidity = v.findViewById(R.id.tvHumidity);
        tvDayLenght = v.findViewById(R.id.tvDayLenght);
        vvBackground = v.findViewById(R.id.vvBackground);

        flBackgroundWhite = v.findViewById(R.id.flBackgroundWhite);
        flBackgroundGreen = v.findViewById(R.id.flBackgroundGreen);

        ivPressure = v.findViewById(R.id.ivPressure);
        ivHumidity = v.findViewById(R.id.ivHumidity);
        ivSunRise = v.findViewById(R.id.ivSunrise);
        ivSunSet = v.findViewById(R.id.ivSunset);
        ivDayLenght = v.findViewById(R.id.ivDayLenght);
        ivDayRotate = v.findViewById(R.id.ivDayRotate);
        ivSunRiseRotate=v.findViewById(R.id.ivSunRiseRotate);
        ivSunSetRotate=v.findViewById(R.id.ivSunSetRotate);
        ivRound=v.findViewById(R.id.ivRound);
        ivNightRotate=v.findViewById(R.id.ivNightRotate);

        ivDayRotate.setVisibility(View.INVISIBLE);
        ivNightRotate.setVisibility(View.INVISIBLE);

        ivRound.setImageResource(R.layout.round_day);

        progessBar = v.findViewById(R.id.progressBar);
        progessBar.setVisibility(View.VISIBLE);

        flBackgroundWhite.setBackgroundColor(Color.rgb(255, 255, 255));
        flBackgroundGreen.setBackgroundColor(Color.rgb(0, 255, 0));
        flBackgroundGreen.setAlpha((float) 0.3);
        flBackgroundWhite.setAlpha((float) 0.5);

        ivPressure.setColorFilter(Color.rgb(64,64,64));
        ivHumidity.setColorFilter(Color.rgb(64,64,64));
        ivSunRise.setColorFilter(Color.rgb(64,64,64));
        ivSunSet.setColorFilter(Color.rgb(64,64,64));
        ivDayLenght.setColorFilter(Color.rgb(64,64,64));
        tvCity.setTextColor(Color.rgb(64,64,64));
        tvTemp.setTextColor(Color.rgb(64,64,64));
        tvTempSun.setTextColor(Color.rgb(64,64,64));
        tvPressure.setTextColor(Color.rgb(64,64,64));
        tvHumidity.setTextColor(Color.rgb(64,64,64));
        tvSunRise.setTextColor(Color.rgb(64,64,64));
        tvSunSet.setTextColor(Color.rgb(64,64,64));
        tvDayLenght.setTextColor(Color.rgb(64,64,64));

        tvDateToday.setTextColor(Color.rgb(64,64,64));

        //   ivDayRotate.setColorFilter(Color.rgb(64,64,64));
        ivSunRiseRotate.setColorFilter(Color.rgb(64,64,64));
        ivSunSetRotate.setColorFilter(Color.rgb(64,64,64));
        //    ivNightRotate.setColorFilter(Color.rgb(64,64,64));

        am = (AudioManager) getActivity().getSystemService(AUDIO_SERVICE); //инициализация медиа сервиса. Настраивает меджиавью
      //  am = (AudioManager) this.getSystemService(AUDIO_SERVICE);
        mediaController = new MediaController(getContext()); //медиа контролер на этой видеовьюшке  . УКправляет медиавью

        vvBackground.setMediaController(null);

        Random r = new Random();
        int randomRes = r.nextInt(4);

        switch (randomRes) {
            case 0:   uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+ R.raw.darview01);
                break;
            case 1:   uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+ R.raw.darview04);
                break;
            case 2:   uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+ R.raw.darview05);
                break;
            case 3:  uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+ R.raw.darview06);
                break;
            case 4:  uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+ R.raw.darview07);
                break;
            default: uri = Uri.parse("android.resource://"+getActivity().getPackageName()+"/"+ R.raw.darview);
        }

        DisplayMetrics metrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics);
        params = (FrameLayout.LayoutParams) vvBackground.getLayoutParams();
        params.width =  metrics.widthPixels;
        params.height = metrics.heightPixels;
        if((Double.valueOf(params.height)/Double.valueOf(params.width))>=1.777){
            params.width=metrics.widthPixels+300;
            params.height = metrics.heightPixels;
        }
        else{
            params.width = metrics.widthPixels;
            params.height = metrics.heightPixels+300;
        }
        vvBackground.setVideoURI(uri);

        vvBackground.setMinimumHeight(params.height);
        vvBackground.setMinimumWidth(params.width);
        vvBackground.start();

        vvBackground.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                vvBackground.setVideoURI(uri);
                vvBackground.setMinimumHeight(params.height);
                vvBackground.setMinimumWidth(params.width);
                vvBackground.start();

            }
        });

        return v;
    }

    @Override
    public void onPause() {
        vvBackground.stopPlayback();
        dataHandler.removeCallbacksAndMessages(null);
        handlerTime.removeCallbacksAndMessages(null);
        handlerInet.removeCallbacksAndMessages(null);
        screenTimer.cancel();
        screenTimer=null;
        super.onPause();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        vvBackground.stopPlayback();
        super.onStop();
    }

    @Override
    public void onResume() {
        int hR = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        int mR = Calendar.getInstance().get(Calendar.MINUTE);
        int currentTimeR = hR*60 + mR;
        if(currentTimeR>=dayEnd || currentTimeR<=dayStart){
            t7=0;
        }
        else{
            t7=1;
        }
        handlerTime = new Handler();
        handlerInet = new Handler();
        dataHandler = new Handler();
        if (screenTimer != null)
            screenTimer = null;
        // Create new Timer
        screenTimer = new Timer();
        screenTimer.scheduleAtFixedRate(new ScreenTimerTask(),0, 30000);
        /*if(isOnline()) {
            GetWeather getWeather = new GetWeather();
            getWeather.execute("getw");
        }
        else{
            Toast.makeText(getApplicationContext(), "Проверьте интернет", Toast.LENGTH_SHORT).show();
            handlerInet.post(new Runnable() {
                @Override
                public void run() {
                    if(isOnline()){
                        GetWeather getWeather = new GetWeather();
                        getWeather.execute("getw");
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Проверьте интернет", Toast.LENGTH_SHORT).show();
                        handlerInet.postDelayed(this,5000);
                    }
                }
            });
        }*/
        handlerTime.post(new Runnable() {
            @Override
            public void run() {

                doCalcSome();
                currentDateAndTime(Calendar.getInstance(), sdf);

                int h = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
                int m = Calendar.getInstance().get(Calendar.MINUTE);
                int currentTime = h*60 + m;
                anim = new RotateAnimation(moveDay, moveDay, pivotX, pivotY);
                anim.setInterpolator(new LinearInterpolator());
                anim.setRepeatCount(Animation.INFINITE);
                anim.setDuration(1000);

                if(currentTime>=dayEnd || currentTime<=dayStart) {
                    if(t7==0){
                        t7=1;
                        anim.cancel();
                        ivRot.setImageResource(0);
                        ivRot.setImageResource(R.drawable.moon);
                        anim = new RotateAnimation(moveDay, moveDay, pivotX, pivotY);
                        anim.setInterpolator(new LinearInterpolator());
                        anim.setRepeatCount(Animation.INFINITE);
                        anim.setDuration(1000);
                    }
                    //ниже замена фона тестово для ночного режима
                    //anim.cancel();
                    //  ivRot.setVisibility(View.INVISIBLE);
                    //  ivRot.setImageResource(0);
                    //ivRot.setImageResource(R.drawable.moon);
                    flBackgroundWhite.setBackgroundColor(Color.rgb(10, 10, 15));
                    flBackgroundGreen.setBackgroundColor(Color.rgb(20, 20, 20));
                    flBackgroundGreen.setAlpha((float) 0.3);
                    flBackgroundWhite.setAlpha((float) 0.6);

                    ivPressure.setColorFilter(Color.WHITE);
                    ivHumidity.setColorFilter(Color.WHITE);
                    ivSunRise.setColorFilter(Color.WHITE);
                    ivSunSet.setColorFilter(Color.WHITE);
                    ivDayLenght.setColorFilter(Color.WHITE);
                    //    ivDayRotate.setColorFilter(Color.WHITE); //23.06
                    ivSunRiseRotate.setColorFilter(Color.WHITE);
                    ivSunSetRotate.setColorFilter(Color.WHITE);
                    //ivNightRotate.setColorFilter(Color.BLUE);
                    ivRot.setColorFilter(Color.BLUE);
                    ivPressure.setAlpha((float) 0.8);
                    ivHumidity.setAlpha((float) 0.8);
                    ivSunRise.setAlpha((float) 0.8);
                    ivSunSet.setAlpha((float) 0.8);
                    ivDayLenght.setAlpha((float) 0.8);
                    //ivDayRotate.setAlpha((float) 0.8); //23.06
                    ivSunRiseRotate.setAlpha((float) 0.8);
                    ivSunSetRotate.setAlpha((float) 0.8);
                    //ivNightRotate.setAlpha((float) 0.8);
                    ivRot.setAlpha((float)0.8);
                    // ivDayRotate.setVisibility(View.GONE);
                    //  ivNightRotate.setVisibility(View.VISIBLE);
                    ivRot.setVisibility(View.VISIBLE);

                    tvCity.setTextColor(Color.WHITE);
                    tvTemp.setTextColor(Color.WHITE);
                    tvTempSun.setTextColor(Color.WHITE);
                    tvPressure.setTextColor(Color.WHITE);
                    tvHumidity.setTextColor(Color.WHITE);
                    tvSunRise.setTextColor(Color.WHITE);
                    tvSunSet.setTextColor(Color.WHITE);
                    tvDayLenght.setTextColor(Color.WHITE);
                    tvDateToday.setTextColor(Color.WHITE);

                    tvCity.setAlpha((float) 0.8);
                    tvTemp.setAlpha((float) 0.8);
                    tvTempSun.setAlpha((float) 0.8);
                    tvPressure.setAlpha((float) 0.8);
                    tvHumidity.setAlpha((float) 0.8);
                    tvSunRise.setAlpha((float) 0.8);
                    tvSunSet.setAlpha((float) 0.8);
                    tvDayLenght.setAlpha((float) 0.8);
                    tvDateToday.setAlpha((float) 0.8);
                    //         ivNightRotate.startAnimation(anim);
                    //       ivNightRotate.setRotation(-moveDay);
                    ivRot.startAnimation(anim);
                    ivRot.setRotation(-moveDay);
                    tvTempSun.setVisibility(View.INVISIBLE);
                    tvTemp.setVisibility(View.VISIBLE);
                }
                else {
                    // ivRot.setImageResource(0);
                    if(t7==1){
                        t7=0;
                        anim.cancel();
                        ivRot.setImageResource(0);
                        ivRot.setImageResource(R.drawable.daylenght);
                        anim = new RotateAnimation(moveDay, moveDay, pivotX, pivotY);
                        anim.setInterpolator(new LinearInterpolator());
                        anim.setRepeatCount(Animation.INFINITE);
                        anim.setDuration(1000);


                    }

                    flBackgroundWhite.setBackgroundColor(Color.rgb(255, 255, 255));
                    flBackgroundGreen.setBackgroundColor(Color.rgb(0, 255, 0));
                    flBackgroundGreen.setAlpha((float) 0.3);
                    flBackgroundWhite.setAlpha((float) 0.5);

                    ivPressure.setColorFilter(Color.rgb(64,64,64));
                    ivHumidity.setColorFilter(Color.rgb(64,64,64));
                    ivSunRise.setColorFilter(Color.rgb(64,64,64));
                    ivSunSet.setColorFilter(Color.rgb(64,64,64));
                    ivDayLenght.setColorFilter(Color.rgb(64,64,64));
                    tvCity.setTextColor(Color.rgb(64,64,64));
                    tvTemp.setTextColor(Color.rgb(64,64,64));
                    tvTempSun.setTextColor(Color.rgb(64,64,64));
                    tvPressure.setTextColor(Color.rgb(64,64,64));
                    tvHumidity.setTextColor(Color.rgb(64,64,64));
                    tvSunRise.setTextColor(Color.rgb(64,64,64));
                    tvSunSet.setTextColor(Color.rgb(64,64,64));
                    tvDayLenght.setTextColor(Color.rgb(64,64,64));
                    tvDateToday.setTextColor(Color.rgb(64,64,64));
                    //    ivDayRotate.setColorFilter(Color.rgb(255,255,0));
                    ivRot.setColorFilter(Color.rgb(255,255,0));
                    ivSunRiseRotate.setColorFilter(Color.rgb(64,64,64));
                    ivSunSetRotate.setColorFilter(Color.rgb(64,64,64));
                    //ivNightRotate.setColorFilter(Color.rgb(64,64,64));//23.06
                    //Toast.makeText(getApplicationContext(),"ok", Toast.LENGTH_SHORT).show();
                    // ivNightRotate.setVisibility(View.GONE);
                    /*ivDayRotate.startAnimation(anim);
                    ivDayRotate.setRotation(-moveDay);
                    ivDayRotate.setVisibility(View.VISIBLE);*/
                    ivRot.startAnimation(anim);
                    ivRot.setRotation(-moveDay);
                    ivRot.setVisibility(View.VISIBLE);
                    tvTempSun.setVisibility(View.VISIBLE);
                    tvTemp.setVisibility(View.VISIBLE);
                    //     Toast.makeText(getApplicationContext(), "else", Toast.LENGTH_LONG).show();
                }

                handlerTime.postDelayed(this, 1000);
            }
        } );

        startvideo(uri);

        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
