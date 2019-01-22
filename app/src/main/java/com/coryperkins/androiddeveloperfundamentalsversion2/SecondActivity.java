package com.coryperkins.androiddeveloperfundamentalsversion2;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void reloadActivityWithExplode(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
            getWindow().setExitTransition(new Explode());
            getWindow().setEnterTransition(new Explode());
            finish();
            startActivity(
                    getIntent(),
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            );
        }

    }

    public void reloadActivityWithFade(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Apply activity transition
            getWindow().setExitTransition(new Fade());
//            getWindow().setEnterTransition(new Fade());
            finish();
            startActivity(
                    getIntent(),
                    ActivityOptions.makeSceneTransitionAnimation(this).toBundle()
            );
        }
    }

    public void rotateSquare(View view) {
        Animation rotation_animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        view.startAnimation(rotation_animation);
    }


    public void loadMainActivity(View view) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View android_icon_view = findViewById(R.id.icon_android);
            Intent intent = new Intent(this, MainActivity.class);

            Pair<View, String> p1 = Pair.create(findViewById(R.id.icon_android), "android_icon");
//            Pair<View, String> p2 = Pair.create(findViewById(R.id.square), "square");
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    this,
                    p1
//                    p2
            );
            startActivity(intent, options.toBundle());
        }
    }
}
