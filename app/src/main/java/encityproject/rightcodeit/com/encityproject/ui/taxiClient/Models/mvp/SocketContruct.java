package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.mvp;

import android.graphics.Point;
import android.os.Handler;
import android.os.SystemClock;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import org.osmdroid.api.IGeoPoint;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Marker;

import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.AddressOrder;
import io.reactivex.CompletableTransformer;
import io.reactivex.disposables.CompositeDisposable;
import ua.naiksoftware.stomp.StompClient;

public interface SocketContruct {
    interface View {
        void showData();
    }

    interface Presenter {
        void stompTopic(final MapView map, final Marker marker);

        void connectStomp();

        void sendRequestClient(AddressOrder addressOrder);

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
