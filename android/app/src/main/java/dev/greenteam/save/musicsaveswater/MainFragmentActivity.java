package dev.greenteam.save.musicsaveswater;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import kaaes.spotify.webapi.android.SpotifyApi;
import kaaes.spotify.webapi.android.SpotifyService;
import kaaes.spotify.webapi.android.models.Album;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE;

/**
 * Created by LaQuay on 13/05/2017.
 */

public class MainFragmentActivity extends Fragment {
    public static final String TAG = MainFragmentActivity.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";

    private View rootView;
    private View baseSnackBarView;
    private Snackbar snackBar;
    private LayoutInflater inflater;

    public static MainFragmentActivity newInstance(int position) {
        MainFragmentActivity fragment = new MainFragmentActivity();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        this.inflater = inflater;

        baseSnackBarView = getActivity().findViewById(R.id.drawer_layout);

        Toast.makeText(getActivity(), "HOLA!", Toast.LENGTH_SHORT).show();

        setUpElements();
        setUpListeners();

        return rootView;
    }

    private void setUpElements() {

    }

    private void setUpListeners() {

    }
}
