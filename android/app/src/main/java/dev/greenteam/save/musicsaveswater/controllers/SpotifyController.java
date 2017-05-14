package dev.greenteam.save.musicsaveswater.controllers;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.spotify.sdk.android.authentication.AuthenticationClient;
import com.spotify.sdk.android.authentication.AuthenticationRequest;
import com.spotify.sdk.android.authentication.AuthenticationResponse;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Error;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerEvent;
import com.spotify.sdk.android.player.Spotify;
import com.spotify.sdk.android.player.SpotifyPlayer;

import static com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE;

/**
 * Created by LaQuay on 14/05/2017.
 */

public class SpotifyController implements
        SpotifyPlayer.NotificationCallback, ConnectionStateCallback {
    public static final String TAG = SpotifyController.class.getSimpleName();
    private static final String CLIENT_ID = "5b2bbb012c6d425f83c577444dc01eb6";
    private static final String REDIRECT_URI = "musicsaveswater://callback";
    private static SpotifyController instance;
    private final SpotifyPlayer.NotificationCallback notificationCallback;
    private final ConnectionStateCallback connectionStateCallback;
    private String mAccessToken;
    private Player mPlayer;

    private SpotifyController(Context ctx) {
        notificationCallback = this;
        connectionStateCallback = this;
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

    public void start(Context context) {
        logInSpotify(context);
    }

    public String getAccessToken() {
        return mAccessToken;
    }

    public void setAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    public void onTokenAvailable(Context context, AuthenticationResponse response) {
        setAccessToken(response.getAccessToken());
        Config playerConfig = new Config(context, response.getAccessToken(), CLIENT_ID);

        Spotify.getPlayer(playerConfig, context, new SpotifyPlayer.InitializationObserver() {
            @Override
            public void onInitialized(SpotifyPlayer spotifyPlayer) {
                mPlayer = spotifyPlayer;
                mPlayer.addConnectionStateCallback(connectionStateCallback);
                mPlayer.addNotificationCallback(notificationCallback);

                mPlayer.playUri(null, "spotify:track:2TpxZ7JUBn3uw46aR7qd6V", 0, 0);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("MainActivity", "Could not initialize player: " + throwable.getMessage());
            }
        });
    }

    public void selectTrack(final Context context, String idTrack) {
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

    @Override
    public void onPlaybackEvent(PlayerEvent playerEvent) {
        Log.d("MainActivity", "Playback event received: " + playerEvent.name());
        switch (playerEvent) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(Error error) {
        Log.d("MainActivity", "Playback error received: " + error.name());
        switch (error.name()) {
            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Error error) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }
}
