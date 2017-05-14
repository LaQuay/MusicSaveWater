package dev.greenteam.save.musicsaveswater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by LaQuay on 14/05/2017.
 */

public class ShowerActivity extends AppCompatActivity {
    public static final String TAG = ShowerActivity.class.getSimpleName();
    private ImageView buttonTerminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shower);

        setUpElements();
        setUpListeners();
    }

    private void setUpElements() {
        buttonTerminar = (ImageView) findViewById(R.id.activity_login_terminado_image);
    }

    private void setUpListeners() {
        buttonTerminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}


