package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.mvp;

import android.util.Log;

import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.AddressOrder;
import io.reactivex.disposables.CompositeDisposable;
import ua.naiksoftware.stomp.Stomp;
import ua.naiksoftware.stomp.StompClient;

public class MainRepository implements SocketContruct.Repository {
    private static final String TAG = "MainRepository";
    public static final String ANDROID_EMULATOR_LOCALHOST = "192.168.0.105";
    public static final String ANDROID_EMULATOR_LOCALHOST_2 = "192.168.43.115";
    public static final String SERVER_PORT = "8080";
    private StompClient mStompClient;
    private CompositeDisposable compositeDisposable;
    private AddressOrder addressOrder;
    @Override
    public AddressOrder loadAddressOrder() {
        Log.d(TAG, "loadMessage()");
        return null;
    }

    @Override
    public void setAdressOrder(String adressOrder) {

    }

    @Override
    public void overtompClient() {
        mStompClient = Stomp.over(Stomp.ConnectionProvider.OKHTTP, "ws://" + ANDROID_EMULATOR_LOCALHOST
                + ":" + SERVER_PORT + "/ws/websocket");
        Log.e(TAG, "overtompClient: overtompClient()");
        resetSubscriptions();
    }

    @Override
    public StompClient getStompClient() {
        return mStompClient;
    }

    @Override
    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    @Override
    public void resetSubscriptions() {
        if (compositeDisposable != null) {
            compositeDisposable.dispose();
        }
        compositeDisposable = new CompositeDisposable();
    }

}
