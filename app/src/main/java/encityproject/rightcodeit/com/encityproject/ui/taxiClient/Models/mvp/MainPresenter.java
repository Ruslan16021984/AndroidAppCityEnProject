package encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.mvp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.util.TimeUtils;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.reactivestreams.Publisher;

import java.io.Serializable;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import encityproject.rightcodeit.com.encityproject.R;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.TaxiClient;
import encityproject.rightcodeit.com.encityproject.ui.taxiClient.Models.TaxiWorker;
import io.reactivex.CompletableTransformer;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainPresenter implements SocketContruct.Presenter {
    private Gson mGson = new GsonBuilder().create();
    private static final String TAG = "MainPresenter";
    private SocketContruct.View mView;
    private SocketContruct.Repository mRepository;
    private TaxiWorker taxiWorker = new TaxiWorker();
    private Disposable dispTopic;

    private String message;

    public MainPresenter(SocketContruct.View mView) {
        this.mView = mView;
        this.mRepository = new MainRepository();
        Log.d(TAG, "Constructor");
        this.mRepository.overtompClient();


    }


    @Override
    public void stompTopic(final MapView map, final Marker marker) {
        Disposable dispTopic = mRepository.getStompClient().topic("/user/taxiOnline/greetings")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(topicMessage -> {
                    Log.d(TAG, "Received " + topicMessage.getPayload());
                    TaxiWorker taxiWorker;
                    String taxi = topicMessage.getPayload();
                    taxiWorker = mGson.fromJson(taxi, TaxiWorker.class);
                    if (map != null && marker != null)
                        SocketContruct.Presenter.animateMarker(map, marker, new GeoPoint(taxiWorker.getLatit(), taxiWorker.getLongit()));
                }, throwable -> {
                    Log.e(TAG, "Error on subscribe topic", throwable);
                });

        mRepository.getCompositeDisposable().add(dispTopic);
    }

    @Override
    public void stompStartTopic(Activity activity, Handler h) {
        Log.e(TAG, "=======>>>>>>>>>>зашли в метод ---->>> stompStartTopic(Activity activity) ");
        dispTopic = mRepository.getStompClient().topic("/user/taxiOnline/greetings")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).take(1)
                .subscribe(topicMessage -> {
                    Log.e(TAG, "успешный ответ от таксиста" );
                    TaxiWorker taxiWorker;
                    String taxi = topicMessage.getPayload();
                    taxiWorker = mGson.fromJson(taxi, TaxiWorker.class);
                    Bundle bundle = new Bundle();
                    Log.e(TAG, "stompStartTopic: "+  taxiWorker.toString());
                    bundle.putSerializable("worker", taxiWorker);
                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
                    navController.navigate(R.id.nav_taxi_taken_order_fragment, bundle);
                    h.removeCallbacksAndMessages(null);
                }, throwable -> {
//                    todo:здесь по истечении 30 сек. если не приходит сообщение тогда нужно выполнить нужные действия
                    Log.e(TAG, "stompStartTopic ", throwable);
                    Bundle bundle = new Bundle();
//                    NavController navController = Navigation.findNavController(activity, R.id.nav_host_fragment);
//                    navController.navigate(R.id.nav_taxi_confirm_order_fragment);
                });

    }

    @Override
    public Disposable getDispTopic() {
        return dispTopic;
    }

    @Override
    public void connectStomp() {
        mRepository.resetSubscriptions();
        Disposable dispLifecycle = mRepository.getStompClient().lifecycle()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(lifecycleEvent -> {
                    switch (lifecycleEvent.getType()) {
                        case OPENED:
                            toast("Stomp connection opened");
                            break;
                        case ERROR:
                            Log.e(TAG, "Stomp connection error", lifecycleEvent.getException());
                            toast("Stomp connection error");
                            break;
                        case CLOSED:
                            toast("Stomp connection closed");
                            mRepository.resetSubscriptions();
                            break;
                        case FAILED_SERVER_HEARTBEAT:
                            toast("Stomp failed server heartbeat");
                            break;
                    }
                });

        mRepository.getCompositeDisposable().add(dispLifecycle);
        mRepository.getStompClient().connect();
    }

    @Override
    public void sendRequestClient(TaxiClient taxiClient) {
        mRepository.getCompositeDisposable().add(mRepository.getStompClient().send("/app/requestTaxi", mGson.toJson(taxiClient))
                .compose(applySchedulers())
                .subscribe(() -> {
                    Log.d(TAG, "sendRequestClient()---->>>>>send successfully");

                }, throwable -> {
                    Log.e(TAG, "sendRequestClient() ------>>>>>>Error send STOMP echo", throwable);
                    toast(throwable.getMessage());
                }));
    }

    @Override
    public void toast(String text) {
        Log.i(TAG, text);

    }

    @Override
    public CompletableTransformer applySchedulers() {
        return upstream -> upstream
                .unsubscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public void addItem(String echoModel) {
        Log.d(TAG, "addItem: " + echoModel);

    }

    @Override
    public void onDestroy() {
        mRepository.getStompClient().disconnect();
        if (mRepository.getCompositeDisposable() != null)
            mRepository.getCompositeDisposable().dispose();

    }

}
