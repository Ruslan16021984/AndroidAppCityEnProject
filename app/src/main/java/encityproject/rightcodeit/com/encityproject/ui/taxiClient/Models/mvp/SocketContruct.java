package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.mvp;

import android.app.Activity;
import android.graphics.Point;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Marker;

import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.AddressOrder;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.TaxiClient;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.TaxiWorker;
import io.reactivex.CompletableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import ua.naiksoftware.stomp.StompClient;

public interface SocketContruct {
    interface View {
        void showData();
    }

    interface Presenter {
        Disposable getDispTopic();
        void stompTopic(final MapView map, final Marker marker);
        void stompStartTopic(Activity activity, Handler h);

        void connectStomp();
        void sendRequestClient(TaxiClient taxiClient);

        void toast(String text);

        CompletableTransformer applySchedulers();

        void addItem(String echoModel);

        void onDestroy();

        static void animateMarker(final MapView map, final Marker marker, final GeoPoint toPosition) {
            final Handler handler = new Handler();
            final long start = SystemClock.uptimeMillis();
            Projection proj = map.getProjection();
            Point startPoint = proj.toPixels(marker.getPosition(), null);
            final IGeoPoint startLatLng = proj.fromPixels(startPoint.x, startPoint.y);
            final long duration = 500;

            final Interpolator interpolator = new LinearInterpolator();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    long elapsed = SystemClock.uptimeMillis() - start;
                    float t = interpolator.getInterpolation((float) elapsed / duration);
                    double lng = t * toPosition.getLongitude() + (1 - t) * startLatLng.getLongitude();
                    double lat = t * toPosition.getLatitude() + (1 - t) * startLatLng.getLatitude();

                    marker.setPosition(new GeoPoint(lat, lng));
                    Log.e("TAG", "run: " + lat +" " + lng );

                    if (t < 1.0) {
                        // Post again 16ms later.
                        handler.postDelayed(this, 16);
                    }
                    map.postInvalidate();
                }
            });
        }

    }

    interface Repository {
        AddressOrder loadAddressOrder();

        void setAdressOrder(String adressOrder);

        void overtompClient();

        StompClient getStompClient();

        CompositeDisposable getCompositeDisposable();

        void resetSubscriptions();
    }
}
