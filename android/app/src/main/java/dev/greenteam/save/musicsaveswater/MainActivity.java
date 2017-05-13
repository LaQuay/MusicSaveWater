package dev.greenteam.save.musicsaveswater;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int SECTION_MAIN_FRAGMENT = 1;
    private static final String CLIENT_ID = "5b2bbb012c6d425f83c577444dc01eb6";
    private static final String REDIRECT_URI = "musicsaveswater://callback";
    private Toolbar toolbar;
    private SpotifyService spotifyService;
    private String mAccessToken;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Check first item
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
        onSectionAttached(SECTION_MAIN_FRAGMENT);

        logInSpotify();

        //Get sample album when SpotifyService available
        Runnable sampleRunnable = new Runnable() {
            public void run() {
                if (spotifyService != null) {
                    Log.e(TAG, "Servicio disponible");
                    getAlbum("2dIGnmEIy1WZIcZCFSj6i8");
                } else {
                    Log.e(TAG, "Servicio aun no disponible");
                    handler.postDelayed(this, 500);
                }
            }
        };
        handler.postDelayed(sampleRunnable, 1000);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;
        String fragmentTAG = null;
        if (id == R.id.nav_camera) { // TODO Change me
            fragment = MainFragmentActivity.newInstance(SECTION_MAIN_FRAGMENT);
            fragmentTAG = MainFragmentActivity.TAG;
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.main_container, fragment, fragmentTAG);
            if (id != R.id.nav_camera) {
                ft.addToBackStack(null);
            }
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onSectionAttached(int number) {
        String mTitle;
        switch (number) {
            case SECTION_MAIN_FRAGMENT:
                mTitle = "Main";
                break;

            default:
                mTitle = getString(R.string.app_name);
        }

        if (toolbar != null) {
            toolbar.setTitle(mTitle);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        AuthenticationResponse response = AuthenticationClient.getResponse(resultCode, data);

        Log.e(TAG, "requestCode: " + requestCode + ", resultCode: " + resultCode);

        if (REQUEST_CODE == requestCode) {
            mAccessToken = response.getAccessToken();
            Log.e(TAG, "TOKEN: " + mAccessToken);
            onTokenAvailable();
        }
    }

    private void onTokenAvailable() {
        SpotifyApi spotifyApi = new SpotifyApi();
        spotifyApi.setAccessToken(mAccessToken);

        spotifyService = spotifyApi.getService();
    }

    private void getAlbum(String albumID) {//"2dIGnmEIy1WZIcZCFSj6i8"
        spotifyService.getAlbum(albumID, new Callback<Album>() {
            @Override
            public void success(Album album, Response response) {
                Log.e(TAG, "Album success: " + album.name);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Album failure: " + error.toString());
            }
        });
    }

    private void logInSpotify() {
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

        AuthenticationClient.openLoginActivity(this, REQUEST_CODE, request);
    }
}
