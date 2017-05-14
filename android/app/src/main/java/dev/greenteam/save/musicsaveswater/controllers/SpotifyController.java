package dev.greenteam.save.musicsaveswater.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

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
 * Created by LaQuay on 14/05/2017.
 */

public class SpotifyController {
    public static final String TAG = SpotifyController.class.getSimpleName();
    private static final String CLIENT_ID = "5b2bbb012c6d425f83c577444dc01eb6";
    private static final String REDIRECT_URI = "musicsaveswater://callback";
    private static SpotifyController instance;
    private SpotifyService spotifyService;
    private String mAccessToken;

    private SpotifyController(Context ctx) {
        logInSpotify(ctx);
    }

    public static SpotifyController getInstance(Context ctx) {
        if (instance == null) {
            createInstance(ctx);
        }
        return instance;
    }

    private synchronized static void createInstance(Context ctx) {
        if (instance == null) {
            instance = new SpotifyController(ctx);
        }
    }

    public SpotifyService getSpotifyService() {
        return spotifyService;
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String mAccessToken) {
        Log.e(TAG, "TOKEN: " + mAccessToken);
        this.mAccessToken = mAccessToken;
    }

    public void onTokenAvailable() {
        SpotifyApi spotifyApi = new SpotifyApi();
        spotifyApi.setAccessToken(mAccessToken);

        spotifyService = spotifyApi.getService();
    }

    public void getAlbum(String albumID, final SpotifyAPICalls callbackSpotifyAPICalls) {//"2dIGnmEIy1WZIcZCFSj6i8"
        spotifyService.getAlbum(albumID, new Callback<Album>() {
            @Override
            public void success(Album album, Response response) {
                if (callbackSpotifyAPICalls != null) {
                    callbackSpotifyAPICalls.onGetAlbumResponseSuccess(album, response);
                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (callbackSpotifyAPICalls != null) {
                    callbackSpotifyAPICalls.onGetAlbumResponseFailure(error);
                }
            }
        });
    }

    private void logInSpotify(Context context) {
        final AuthenticationRequest request = new AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
                .setScopes(new String[]{"playlist-read-private",
                        "playlist-read-collaborative",
                        "playlist-modify-public",
                        "playlist-modify-private",
                        "streaming",
                        "user-follow-modify",
                        "user-follow-read",
                        "user-library-read",
                        "user-library-modify",
                        "user-read-private",
                        "user-read-birthdate",
                        "user-read-email",
                        "user-top-read"})
                .build();

        AuthenticationClient.openLoginActivity((Activity) context, REQUEST_CODE, request);
    }

    public interface SpotifyAPICalls {
        void onGetAlbumResponseSuccess(Album album, Response response);

        void onGetAlbumResponseFailure(RetrofitError retrofitError);
    }
}
