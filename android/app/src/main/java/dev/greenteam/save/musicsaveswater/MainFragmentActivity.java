package dev.greenteam.save.musicsaveswater;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by LaQuay on 13/05/2017.
 */

public class MainFragmentActivity extends Fragment {
    public static final String TAG = MainFragmentActivity.class.getSimpleName();
    private static final String ARG_SECTION_NUMBER = "section_number";

    private View rootView;
    private LayoutInflater inflater;
    private ImageView gogogoButton;
    private ImageView medioButton;
    private ImageView spaButton;

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

        setUpElements();
        setUpListeners();

        return rootView;
    }

    private void setUpElements() {
        gogogoButton = (ImageView) rootView.findViewById(R.id.fragment_main_gogogo);
        medioButton = (ImageView) rootView.findViewById(R.id.fragment_main_medio);
        spaButton = (ImageView) rootView.findViewById(R.id.fragment_main_spa);
    }

    private void setUpListeners() {
        gogogoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), ShowerActivity.class);
                myIntent.putExtra("key", ""); //Optional parameters
                startActivity(myIntent);
            }
        });
        medioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), ShowerActivity.class);
                myIntent.putExtra("key", ""); //Optional parameters
                startActivity(myIntent);
            }
        });
        spaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getContext(), ShowerActivity.class);
                myIntent.putExtra("key", ""); //Optional parameters
                startActivity(myIntent);
            }
        });
    }
}
