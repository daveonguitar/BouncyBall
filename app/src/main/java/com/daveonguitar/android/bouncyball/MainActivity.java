package com.daveonguitar.android.bouncyball;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "index";

    private int color = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);

        if (fragment == null) {
            fragment = new BallFragment();
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }

        if (savedInstanceState != null) {
            color = savedInstanceState.getInt(KEY_INDEX, Color.BLACK);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.ball_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.reset_button):
                updateBalls();
                return true;
            case (R.id.white_background):
                updateColor(Color.WHITE);
                color = Color.WHITE;
                return true;
            case (R.id.black_background):
                updateColor(Color.BLACK);
                color = Color.BLACK;
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void updateColor(int newColor) {
        View bouncyBallView = findViewById(R.id.ball_view);
        bouncyBallView.setBackgroundColor(newColor);
    }

    public void updateBalls() {
        View bouncyBallView = findViewById(R.id.ball_view);
        setContentView(R.layout.fragment_ball);
        bouncyBallView.setBackgroundColor(color);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt(KEY_INDEX, color);
    }
}
