package com.veeyaar.myscanner.presenter;

import android.util.Log;

import com.veeyaar.myscanner.model.SessionLogin;
import com.veeyaar.myscanner.model.SessionResponse;
import com.veeyaar.myscanner.network.ApiClient;
import com.veeyaar.myscanner.network.ApiInterface;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class LoginPresenter {

    private LoginInterface mInterface;
    private ApiInterface apiInterface;

    public LoginPresenter(LoginInterface mInterface) {
        this.mInterface = mInterface;
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public void doSessionLogin(String username, String password) {

        Call<SessionResponse> call = apiInterface.sessionLogin(new SessionLogin(username, password, "BBKTEST"));
        call.enqueue(new Callback<SessionResponse>() {
            @Override
            public void onResponse(@NotNull Call<SessionResponse> call, @NotNull Response<SessionResponse> response) {
                Log.d(TAG, "onResponse: success");
                if (response.body() != null && response.isSuccessful()) {
                    mInterface.loginSuccess(response.body());
                } else {
                    mInterface.loginFailed("Invalid credentials");
                }
            }

            @Override
            public void onFailure(@NotNull Call<SessionResponse> call, @NotNull Throwable t) {
                Log.d(TAG, "onResponse: failed");
                mInterface.loginFailed(t.getLocalizedMessage());
            }
        });

    }


    public interface LoginInterface {

        void loginSuccess(SessionResponse response);

        void loginFailed(String error);
    }


}
