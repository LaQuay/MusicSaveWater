package dev.greenteam.save.musicsaveswater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by LaQuay on 14/05/2017.
 */

public class LoginActivity extends AppCompatActivity {
    public static final String TAG = LoginActivity.class.getSimpleName();
    private ImageView buttonRegistro;
    private ImageView buttonEntrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setUpElements();
        setUpListeners();
    }

    private void setUpElements() {
        buttonRegistro = (ImageView) findViewById(R.id.activity_login_registro_image);
        buttonEntrar = (ImageView) findViewById(R.id.activity_login_entrar_image);
    }

    private void setUpListeners() {
        buttonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, SignUpActivity.class);
                myIntent.putExtra("key", ""); //Optional parameters
                startActivity(myIntent);
            }
        });

        buttonEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                myIntent.putExtra("key", ""); //Optional parameters
                startActivity(myIntent);
            }
        });
    }
}


