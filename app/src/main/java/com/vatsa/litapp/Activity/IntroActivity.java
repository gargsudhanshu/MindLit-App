package com.vatsa.litapp.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.ramotion.paperonboarding.PaperOnboardingEngine;
import com.ramotion.paperonboarding.PaperOnboardingPage;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnChangeListener;
import com.ramotion.paperonboarding.listeners.PaperOnboardingOnRightOutListener;
import com.vatsa.litapp.R;

import java.util.ArrayList;

public class IntroActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.onboarding_main_layout);

//        if (android.os.Build.VERSION.SDK_INT <= Build.VERSION_CODES.M) {
//            startActivity(new Intent(this, LoginActivity.class));
//            finish();
//        }

        if (restorePref()){
            startActivity(new Intent(this, StartActivity.class));
            finish();

        }

        PaperOnboardingEngine engine = new PaperOnboardingEngine(findViewById(R.id.onboardingRootView), getDataForOnboarding(), getApplicationContext());

        engine.setOnChangeListener(new PaperOnboardingOnChangeListener() {
            @Override
            public void onPageChanged(int oldElementIndex, int newElementIndex) {

            }
        });

        engine.setOnRightOutListener(new PaperOnboardingOnRightOutListener() {
            @Override
            public void onRightOut() {
                startActivity(new Intent(IntroActivity.this, StartActivity.class));
                finish();
            }
        });

        savePref();

    }

    private ArrayList<PaperOnboardingPage> getDataForOnboarding() {
        PaperOnboardingPage scr1 = new PaperOnboardingPage("Literature", "Literature is the best way to overcome boredom",
                Color.parseColor("#46A7F9"), R.drawable.shelf, R.drawable.plogo);
        PaperOnboardingPage scr2 = new PaperOnboardingPage("Enlighten", "Knowing yourself is enlightenment",
                Color.parseColor("#ED3F5B"), R.drawable.destination, R.drawable.plogo);
        PaperOnboardingPage scr3 = new PaperOnboardingPage("Welcome", "Welcome to our LiteratureApp",
                Color.parseColor("#FDC730"), R.drawable.wings, R.drawable.plogo);

        ArrayList<PaperOnboardingPage> elements = new ArrayList<>();
        elements.add(scr1);
        elements.add(scr2);
        elements.add(scr3);
        return elements;
    }

    private boolean restorePref() {
        SharedPreferences pref= getApplicationContext().getSharedPreferences("myPref", MODE_PRIVATE);
        Boolean isbeforeIntro = pref.getBoolean("introOPen", false);
        return isbeforeIntro;
    }

    private void savePref(){
        SharedPreferences sharedPref = getApplicationContext().getSharedPreferences("myPref",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean("introOPen", true);
        editor.commit();
    }

}
