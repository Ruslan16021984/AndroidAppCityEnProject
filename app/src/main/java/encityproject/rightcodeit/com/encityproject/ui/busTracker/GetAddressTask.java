package encityproject.rightcodeit.com.encityproject.ui.busTracker;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.util.Log;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import encityproject.rightcodeit.com.encityproject.ui.taxiClient.TaxiOrderFragment;

public class GetAddressTask extends AsyncTask<String, Void, String> {

    private static final String TAG = "TAG";
    private TaxiOrderFragment fragment;

    public GetAddressTask(TaxiOrderFragment activity) {
        super();
        this.fragment = activity;
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d(TAG, "doInBackground: GETADRESSTaK");
        Geocoder geocoder;
        List<Address> addresses;
        geocoder = new Geocoder(fragment.getActivity(), Locale.getDefault());

        try {
            addresses = geocoder.getFromLocation(Double.parseDouble(params[0]), Double.parseDouble(params[1]), 1);

            //get current Street name
            String address = addresses.get(0).getAddressLine(0);

            return address;

        } catch (IOException ex) {
            ex.printStackTrace();
            return "IOE EXCEPTION";

        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return "IllegalArgument Exception";
        }

    }

    @Override
    protected void onPostExecute(String address) {
        Log.d(TAG, "onPostExecute: GETADRESSTaK");
        // Call back Data and Display the current address in the UI
        fragment.callBackDataFromAsyncTask(address);
    }
}
