package com.veeyaar.myscanner.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.veeyaar.myscanner.R;
import com.veeyaar.myscanner.utility.SharedHelper;

import io.github.inflationx.calligraphy3.CalligraphyConfig;
import io.github.inflationx.calligraphy3.CalligraphyInterceptor;
import io.github.inflationx.viewpump.ViewPump;
import io.github.inflationx.viewpump.ViewPumpContextWrapper;
import io.reactivex.disposables.CompositeDisposable;

abstract class BaseActivity extends AppCompatActivity {

    CompositeDisposable disposable;
    Activity activity;
    SharedHelper sharedHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewPump.init(ViewPump.builder()
                .addInterceptor(new CalligraphyInterceptor(
                        new CalligraphyConfig.Builder()
                                .setDefaultFontPath("fonts/Poppins-Regular.ttf")
                                .setFontAttrId(R.attr.fontPath)
                                .build()))
                .build());

        disposable = new CompositeDisposable();
        activity = this;
        sharedHelper = new SharedHelper(activity);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase));    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (disposable.isDisposed()) {
            disposable.clear();
        }
    }
}
