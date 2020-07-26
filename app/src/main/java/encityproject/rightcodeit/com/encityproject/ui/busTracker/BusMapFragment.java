package encityproject.rightcodeit.com.encityproject.ui.busTracker;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.android.gms.common.util.MapUtils;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.osmdroid.api.IGeoPoint;
import org.osmdroid.bonuspack.BuildConfig;
import org.osmdroid.bonuspack.routing.OSRMRoadManager;
import org.osmdroid.bonuspack.routing.RoadManager;
import org.osmdroid.config.Configuration;
import org.osmdroid.events.MapEventsReceiver;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.MapEventsOverlay;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;

import com.github.clans.fab.FloatingActionButton;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.busTracker.model.BusStop;
import encityproject.rightcodeit.com.encityproject.ui.busTracker.model.DataRouteBusAndBusStop;
import encityproject.rightcodeit.com.encityproject.ui.busTracker.model.Mqttbusenter;

import static android.content.Context.LOCATION_SERVICE;

public class BusMapFragment extends Fragment implements View.OnClickListener{
    private int port = 4656;
    //private String ipLocal = "192.168.1.46";
    private String ip = "35.232.178.112";
    // private String ip = "192.168.1.103";
    //private String ip = "192.168.0.103";
    private MqttHelper mqttHelper;
    private static final int REQUEST_CODE_PERMISSION_READ_CONTACTS = 123;
    private ScaleBarOverlay mScaleBarOverlay;
    private RotationGestureOverlay mRotationGestureOverlay;
    private MapView map = null;
    private MapController mapController = null;
    private MyLocationNewOverlay locationOverlay;
    private CompassOverlay compassOverlay;
    private RoadManager roadManager;
    private Marker busMarker_1;
    private Interpolator interpolator;
    private FloatingActionButton fubBusTrack1;
    private FloatingActionButton fubBusTrack2;
    private FloatingActionButton fubBusTrack3;
    private FloatingActionButton fubBusTrack4;
    private FloatingActionButton fubBusTrack5;
    private ArrayList<GeoPoint> geoPoints;
    private long start;
    private long duration;
    private GeoPoint myPosition;
    private LocationManager mLocationManager;
    Marker m1=null;
    Marker m2=null;
    Marker m3=null;
    Marker m4=null;
    Marker m5=null;
    Marker m6=null;
    Marker m7=null;
    Marker m8=null;
    Marker m9=null;
    Marker m10=null;
    Marker m11=null;
    Marker m12=null;
    Marker m13=null;
    Marker m14=null;
    Marker m15=null;
    Marker m16=null;
    Marker m17=null;
    Marker[] mMarkers=new Marker[17];
    private Button btnZoomIn, btnZoomOut;
    private double zoomScale=14.5;
    private LinearLayout llTransInfo;
    private TextView tvTransInfo;
    private Handler hTransInfo;
    private DataRouteBusAndBusStop dataRouteBusAndBusStop;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);
}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_bus_map, container, false);
        dataRouteBusAndBusStop = new DataRouteBusAndBusStop();
        llTransInfo=view.findViewById(R.id.llTransInfo);
        llTransInfo.setVisibility(View.INVISIBLE);
        tvTransInfo=view.findViewById(R.id.tvTransInfo);
        btnZoomIn=view.findViewById(R.id.btnZoomIn);
        btnZoomOut=view.findViewById(R.id.btnZoomOut);

        fubBusTrack1 = view.findViewById(R.id.fab_action1);
        /*fubBusTrack1.setBackgroundTintList(ColorStateList.valueOf(Color
                .parseColor("#33691E")));*/
        mMarkers[0]=m1;
        mMarkers[1]=m2;
        mMarkers[2]=m3;
        mMarkers[3]=m4;
        mMarkers[4]=m5;
        mMarkers[5]=m6;
        mMarkers[6]=m7;
        mMarkers[7]=m8;
        mMarkers[8]=m9;
        mMarkers[9]=m10;
        mMarkers[10]=m11;
        mMarkers[11]=m12;
        mMarkers[12]=m13;
        mMarkers[13]=m14;
        mMarkers[14]=m15;
        mMarkers[15]=m16;
        mMarkers[16]=m17;

        fubBusTrack1.setOnClickListener(this);
        fubBusTrack2 = view.findViewById(R.id.fab_action2);
        fubBusTrack2.setOnClickListener(this);
        fubBusTrack3 = view.findViewById(R.id.fab_action3);
        fubBusTrack3.setOnClickListener(this);
        fubBusTrack4 = view.findViewById(R.id.fab_action4);
        fubBusTrack4.setOnClickListener(this);
        fubBusTrack5 = view.findViewById(R.id.fab_action5);
        fubBusTrack5.setOnClickListener(this);
        geoPoints = new ArrayList<>();
        duration = 2500;
        start = SystemClock.uptimeMillis();
        interpolator = new LinearInterpolator();
        map = view.findViewById(R.id.map);
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setBuiltInZoomControls(false);
        Configuration.getInstance().load(getActivity(), PreferenceManager.getDefaultSharedPreferences(getContext()));
        checkPermition();



        btnZoomIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomScale=zoomScale+0.5;
                if(zoomScale>=20.0){
                    zoomScale=20.0;
                    mapController.setZoom(zoomScale);
                }else
                    mapController.setZoom(zoomScale);
            }
        });

        btnZoomOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomScale=zoomScale-0.5;
                if(zoomScale<=4.0){
                    zoomScale=4.0;
                    mapController.setZoom(zoomScale);
                }else
                    mapController.setZoom(zoomScale);
            }
        });

        return view;
    }



    private boolean checkTimeDrive(int lastBusTime, int ealyBusTime) {
        Calendar c = Calendar.getInstance();
        int h=c.get(Calendar.HOUR_OF_DAY);
        int m=c.get(Calendar.MINUTE);
        if((h*60)+m>=lastBusTime || (h*60)+m<ealyBusTime){
            return false;
        }
        else return true;
    }

    public void findGPS(double lat, double lon) {
        mLocationManager = (LocationManager)
                getActivity().getSystemService(LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "проверку не прошел");
                return;
            }
        }
        Log.d("TAG", "прошел");
        //Location locationGPS = mLocationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
      //  Location locationGPS = getLastKnownLocation();

            //    mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
       //     myPosition = new GeoPoint(locationGPS.getLatitude(), locationGPS.getLongitude());
            Float latit = new BigDecimal(lat).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
            Float longi = new BigDecimal(lon).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();



    }

    private void startMqtt(String login, String pass, String serverUri, String topic) {
        Log.e("STArtMQTT", "login="+login+"  passs="+pass+"  URI="+serverUri+"  topic="+topic);
        mqttHelper = new MqttHelper(getContext(), login, pass, serverUri, topic);
        mqttHelper.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean b, String s) {
                Log.e("Connect MQTT", s);
            }

            @Override
            public void connectionLost(Throwable throwable) {

            }

            @Override
            public void messageArrived(String topic, MqttMessage mqttMessage) throws Exception {
              //  Log.w("Debug",mqttMessage.toString());
                Log.d("bus", mqttMessage.toString());
                JsonObject jsonObject = new JsonParser().parse(mqttMessage.toString()).getAsJsonObject();
             //   hideMarke.setPosition(new GeoPoint(47.496618, 34.649008));
   /*            hideMarke.setPosition(new GeoPoint(
                        Double.parseDouble(jsonObject.get("position.latitude").getAsString()),
                        Double.parseDouble(jsonObject.get("position.longitude").getAsString())));*/
               /* findGPS(Double.parseDouble(jsonObject.get("position.latitude").getAsString()),
                        Double.parseDouble(jsonObject.get("position.longitude").getAsString()));*/
                map.getOverlays().add(busMarker_1);
                map.invalidate();
                //TODO здесь приходят координаты автобуса
                animateMarker(map, busMarker_1, new GeoPoint(Double.parseDouble(jsonObject.get("position.latitude").getAsString()),
                        Double.parseDouble(jsonObject.get("position.longitude").getAsString())));

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        ArrayList<BusStop> aBus=new ArrayList<>();
        switch (v.getId()) {
            case R.id.fab_action1:
                zoomScale=14.5;
                map.getController().animateTo(new GeoPoint(47.49364, 34.65646));
                mapController.setZoom(zoomScale);
                removeOldStations();
                dataRouteBusAndBusStop.routeBusTwo(map);
                createNewStations(DataRouteBusAndBusStop.BusStop_2());
                break;
            case R.id.fab_action2:
                zoomScale=14.5;
                map.getController().animateTo(new GeoPoint(47.49364, 34.65646));
                mapController.setZoom(zoomScale);
                removeOldStations();
                dataRouteBusAndBusStop.routeBusThree(map);
                createNewStations(DataRouteBusAndBusStop.BusStop_3());
                break;
            case R.id.fab_action3:
                zoomScale=12.6;
                map.getController().animateTo(new GeoPoint(47.497476, 34.622944));
                mapController.setZoom(zoomScale);
                removeOldStations();
                dataRouteBusAndBusStop.routeBusFour(map);
                createNewStations(DataRouteBusAndBusStop.BusStop_4());
                break;
            case R.id.fab_action4:
                zoomScale=14.5;
                map.getController().animateTo(new GeoPoint(47.49364, 34.65646));
                mapController.setZoom(zoomScale);
                removeOldStations();
                dataRouteBusAndBusStop.routeBusFive(map);
                createNewStations(DataRouteBusAndBusStop.BusStop_5());
                break;
            case R.id.fab_action5:
                map.getController().animateTo(new GeoPoint(47.49364, 34.65646));
                mapController.setZoom(14.5);
                removeOldStations();
                dataRouteBusAndBusStop.routeBusSeven(map);
                createNewStations(DataRouteBusAndBusStop.BusStop_7());

                break;
        }
    }

    private void createNewStations(ArrayList<BusStop> aBusStop){
        for(int i=0;i<aBusStop.size();i++){
            mMarkers[i]=new Marker(map);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                //busMarker_1.setIcon(getResources().getDrawable(R.drawable.bus_circle));
                mMarkers[i].setIcon(writeOnDrawable(R.drawable.busstop, aBusStop.get(i).getNameBusStop()));
            }
            map.getOverlays().add(mMarkers[i]);
            mMarkers[i].setPosition(new GeoPoint(aBusStop.get(i).getLatitude(), aBusStop.get(i).getLongitude()));
            map.invalidate();
        }
    }

    private void removeOldStations(){
        for(int y=0;y<mMarkers.length;y++){
            map.getOverlays().remove(mMarkers[y]);
            map.invalidate();
            mMarkers[y]=null;
        }
    }

    private void checkPermition() {
        int ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION);
        int ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION);
        int WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);
        Log.d("TAG", +ACCESS_FINE_LOCATION + " checkPermition() " + ACCESS_COARSE_LOCATION + "" + WRITE_EXTERNAL_STORAGE);
        if (ACCESS_FINE_LOCATION == PackageManager.PERMISSION_GRANTED
                && ACCESS_COARSE_LOCATION == PackageManager.PERMISSION_GRANTED
                && WRITE_EXTERNAL_STORAGE == PackageManager.PERMISSION_GRANTED) {
            createMap();
            Float latit = new BigDecimal(47.490459).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
            Float longi = new BigDecimal(34.660989).setScale(4, BigDecimal.ROUND_HALF_UP).floatValue();
        } else {
            Log.d("TAG", "" + "DONT " + Manifest.permission.ACCESS_COARSE_LOCATION);
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION_READ_CONTACTS);
        }
    }

    public BitmapDrawable writeOnDrawable(int drawableId, String text){

        Bitmap bm = BitmapFactory.decodeResource(getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);
        Bitmap b = Bitmap.createScaledBitmap(bm, 150, 120, false);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(30);

        Canvas canvas = new Canvas(b);
        canvas.drawText(text, 0, b.getHeight()/4, paint);

        return new BitmapDrawable(b);
    }

    private void createMap() {
        Log.d("TAG", "createMap()");

        map.setTileSource(TileSourceFactory.MAPNIK);
        map.setMultiTouchControls(true);
        map.setTilesScaledToDpi(true);
   //     map.setBuiltInZoomControls(true);
        map.setFlingEnabled(true);

        locationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getContext()), map);
        //locationOverlay.enableMyLocation();
//        locationOverlay.enableFollowLocation();
        locationOverlay.setDrawAccuracyEnabled(true);
        locationOverlay.setOptionsMenuEnabled(true);
//47.4886911,34.6576792 centre city
        roadManager = new OSRMRoadManager(getContext());
        final DisplayMetrics dm = this.getResources().getDisplayMetrics();
        mScaleBarOverlay = new ScaleBarOverlay(map);
        mScaleBarOverlay.setCentred(true);
        mScaleBarOverlay.setScaleBarOffset(dm.widthPixels / 2, 10);
        mRotationGestureOverlay = new RotationGestureOverlay(getContext(), map);
        mRotationGestureOverlay.setEnabled(true);

        busMarker_1 = new Marker(map);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            busMarker_1.setIcon(getResources().getDrawable(R.drawable.numeric_5_circle));
            //busMarker_1.setIcon(writeOnDrawable(R.drawable.numeric_3_circle, ""));
        }
        map.getOverlays().add(busMarker_1);
      //  map.invalidate();


        compassOverlay = new CompassOverlay(getContext(),
                new InternalCompassOrientationProvider(getContext()), map);
        compassOverlay.enableCompass();
        map.getOverlays().add(mScaleBarOverlay);
        map.getOverlays().add(compassOverlay);
        map.getOverlays().add(locationOverlay);

        mapController = (MapController) map.getController();
        mapController.setZoom(14.5);
        final ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
        map.getController().animateTo(new GeoPoint(47.49364, 34.65646));
        items.add(new OverlayItem("Title", "Description", new GeoPoint(0.0d, 0.0d))); // Lat/Lon decimal degrees
        map.getOverlays().add(new MapEventsOverlay(new MapEventsReceiver() {
            @Override
            public boolean singleTapConfirmedHelper(GeoPoint p) {
                return true;
            }

            @Override
            public boolean longPressHelper(GeoPoint p) {
                return false;
            }
        }));

    }

    public static void animateMarker(final MapView map, final Marker marker, final GeoPoint toPosition) {
        Log.d("TAG", "animateMarker " + toPosition.getLatitude() + " =---" + toPosition.getLongitude());
        final Handler handler = new Handler();
        final long start = SystemClock.uptimeMillis();
        Projection proj = map.getProjection();
        Point startPoint = proj.toPixels(marker.getPosition(), null);
        final IGeoPoint startLatLng = proj.fromPixels(startPoint.x, startPoint.y);
        final long duration = 2000;

        final Interpolator interpolator = new LinearInterpolator();

        handler.post(new Runnable() {
            @Override
            public void run() {
                long elapsed = SystemClock.uptimeMillis() - start;
                float t = interpolator.getInterpolation((float) elapsed / duration);
                double lng = t * toPosition.getLongitude() + (1 - t) * startLatLng.getLongitude();
                double lat = t * toPosition.getLatitude() + (1 - t) * startLatLng.getLatitude();

                marker.setPosition(new GeoPoint(lat, lng));

                if (t < 1.0) {
                    // Post again 16ms later.
                    handler.postDelayed(this, 16);
                }
                map.postInvalidate();
            }
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        mqttHelper.disconnect();
        hTransInfo.removeCallbacksAndMessages(null);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                Log.d("TAG", "" + "OK " + Manifest.permission.ACCESS_COARSE_LOCATION);
                createMap();
            }
        }
    }
    public void onResume() {
        super.onResume();
        Configuration.getInstance().load(getContext(), PreferenceManager.getDefaultSharedPreferences(getActivity())); //needed for compass, my location overlays, v6.0.0 and up
     //   startMqtt();
        GetMoniBus getMoniBus = new GetMoniBus();
        getMoniBus.execute("getbus");
        Animation animAppear= AnimationUtils.loadAnimation(getContext(), R.anim.appear5000);
        Animation animDisapp= AnimationUtils.loadAnimation(getContext(), R.anim.disappear5000);

        llTransInfo.startAnimation(animAppear);

        if(checkTimeDrive(((20*60)+30),((8*60)+30) )==false){
            tvTransInfo.setText("Нажаль міський транспорт\nзараз не працює");
            tvTransInfo.setBackgroundResource(R.drawable.button_rounded_grey);
        }

        hTransInfo=new Handler();
        hTransInfo.postDelayed(new Runnable() {
            @Override
            public void run() {
                llTransInfo.startAnimation(animDisapp);
                //llTransInfo.setVisibility(View.INVISIBLE);
            }
        },2000);
    }

    class GetMoniBus extends AsyncTask<String, Void, Socket> {
        private String linkCheckVApp = "myNull";
        private Socket socket;
        private PrintWriter pw = null;
        private InputStream is = null;
        //private String fromServer="";
        private ArrayList<String> fromServer;
        private ArrayList<Mqttbusenter> mqttbus;

        @Override
        protected Socket doInBackground(String... params) {

            PrintWriter pw;
            try {
                socket = new Socket(ip, port);

                pw = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                pw.write(params[0] + "@.#" + linkCheckVApp + "\n");
                pw.flush();

                /*is = socket.getInputStream();
                Scanner sc = new Scanner(is);
                fromServer = sc.nextLine();

                is.close();*/
                ObjectInputStream objectInput = new ObjectInputStream(socket.getInputStream());
                try {
                    Object object = objectInput.readUnshared();
                    fromServer =  (ArrayList<String>) object;
                    Log.d("fromServerMoni","ok yes");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                objectInput.close();

                mqttbus=new ArrayList<>();
                for(int r=0;r<fromServer.size();r++){
                    mqttbus.add(new Mqttbusenter(
                            fromServer.get(r).split(";")[0],
                            fromServer.get(r).split(";")[1],
                            fromServer.get(r).split(";")[2],
                            fromServer.get(r).split(";")[3]
                    ));
                }
            } catch (IOException e) {
                //e.printStackTrace();
                Log.d("Ex GetMoniBus", e.getMessage());
            }
            finally {

            }

            return null;
        }

        @Override
        protected void onPostExecute(Socket socket) {
            super.onPostExecute(socket);
            //pbListPhones.setVisibility(View.INVISIBLE);
            //   fromServer="ok";
            if(fromServer.size()>0){
                startMqtt(mqttbus.get(0).getLoginbus(),
                        mqttbus.get(0).getPassbus(),
                        mqttbus.get(0).getServerbus(),
                        mqttbus.get(0).getTopicbus());
            }
            else{
                Toast.makeText(getContext(), "Виникли технічні помилки. Вже вирішуемо", Toast.LENGTH_SHORT).show();
            }
        }
    }



}
