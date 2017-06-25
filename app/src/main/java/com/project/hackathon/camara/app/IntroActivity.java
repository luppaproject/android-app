package com.project.hackathon.camara.app;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.github.paolorotolo.appintro.AppIntro;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by matheuscatossi on 6/3/17.
 */

public class IntroActivity extends AppIntro {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }

        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_1_title), getString(R.string.intro_1_desc), R.drawable.money, getResources().getColor(R.color.introColor1)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_2_title), getString(R.string.intro_2_desc), R.drawable.people, getResources().getColor(R.color.introColor2)));
        addSlide(AppIntroFragment.newInstance(getString(R.string.intro_3_title), getString(R.string.intro_3_desc), R.drawable.logo, getResources().getColor(R.color.introColor3)));

        showSkipButton(false);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this, MainActivity.class));
    }

}
