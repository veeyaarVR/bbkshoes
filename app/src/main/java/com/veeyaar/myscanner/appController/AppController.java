package com.veeyaar.myscanner.appController;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.veeyaar.myscanner.network.ApiInterface;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppController extends Application {

    Activity activity;
    WeakReference<Context> mContext;
    static  String BaseUrl = "http://115.124.110.204:50001/b1s/v1/";

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = new WeakReference<>(getApplicationContext());
    }

    public static ApiInterface retrofitClient() {
        return new Retrofit.Builder()
                .baseUrl(BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient())
                .build().create(ApiInterface.class);
    }

    static OkHttpClient okHttpClient() {

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor())
                .addNetworkInterceptor(logging)
                .retryOnConnectionFailure(true)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build();
    }

    static Interceptor interceptor() {
        return chain -> {
            Request newRequest = chain.request().newBuilder().build();
            return chain.proceed(newRequest);
        };
    }

}
