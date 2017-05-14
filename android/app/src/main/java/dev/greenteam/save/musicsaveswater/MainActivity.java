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
import com.spotify.sdk.android.authentication.AuthenticationResponse;

import dev.greenteam.save.musicsaveswater.controllers.SpotifyController;
import kaaes.spotify.webapi.android.models.Album;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, SpotifyController.SpotifyAPICalls {
    public static final String TAG = MainActivity.class.getSimpleName();
    public static final int SECTION_MAIN_FRAGMENT = 1;
    private Toolbar toolbar;
    private Handler handler = new Handler();
    private SpotifyController.SpotifyAPICalls callbackSpotifyAPICalls;

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

        //Start Spotify Service
        callbackSpotifyAPICalls = this;
        SpotifyController.getInstance(this).getSpotifyService();

        //Get sample album when SpotifyService available
        Runnable sampleRunnable = new Runnable() {
            public void run() {
                if (SpotifyController.getInstance(getBaseContext()).getSpotifyService() != null) {
                    Log.e(TAG, "Servicio disponible");
                    SpotifyController.getInstance(getBaseContext()).getAlbum("2dIGnmEIy1WZIcZCFSj6i8", callbackSpotifyAPICalls);
                } else {
                    Log.e(TAG, "Servicio aun no disponible");
                    handler.postDelayed(this, 500);
                }
            }
        };
        handler.postDelayed(sampleRunnable, 1000);
    }

    @Override
    public void onGetAlbumResponseSuccess(Album album, Response response) {
        Log.e(TAG, "Album success: " + album.name);
    }

    @Override
    public void onGetAlbumResponseFailure(RetrofitError error) {
        Log.e(TAG, "Album failure: " + error.toString());
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
            SpotifyController.getInstance(getBaseContext()).setAccessToken(response.getAccessToken());
            SpotifyController.getInstance(getBaseContext()).onTokenAvailable();
        }
    }
}
