package dev.greenteam.save.musicsaveswater;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by LaQuay on 14/05/2017.
 */

public class SignUpActivity extends AppCompatActivity {
    public static final String TAG = SignUpActivity.class.getSimpleName();
    private ImageView buttonConfirmar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        setUpElements();
        setUpListeners();
    }

    private void setUpElements() {
        buttonConfirmar = (ImageView) findViewById(R.id.activity_signup_conexion);
    }

    private void setUpListeners() {
        buttonConfirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}


