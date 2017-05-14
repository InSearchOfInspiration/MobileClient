package com.golub.golubroman.sentryapp.Occupations.ServerConnection.AuthorizationPOJO;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.golub.golubroman.sentry.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roman on 14.05.17.
 */

public class AuthorizationFragment extends Fragment{
    @BindView(R.id.username_button_fragment_sign_in) EditText username;
    @BindView(R.id.password_fragment_sign_in) EditText password;
    @BindView(R.id.sign_fragment_sign_in) ImageView sign;
    @BindView(R.id.create_account_fragment_sign_in) TextView create;

    OnRunMainActivityListener onRunMainActivityListener;

    public interface OnRunMainActivityListener{
        void onRunMainActivityA(String accessToken);
        void onCreateAccount();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onRunMainActivityListener = (OnRunMainActivityListener)activity;
    }

    public static AuthorizationFragment newInstance() {
        Bundle args = new Bundle();
        AuthorizationFragment fragment = new AuthorizationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        RetrofitModule.buildAuthApi();
        final String[] accessToken = new String[1];
        final RequestPOJO request = new RequestPOJO();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().equals("") ||
                        password.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(), "Input all fields", Toast.LENGTH_SHORT).show();
                }else{
                    String username = getAuthorizationFragment().username.getText().toString().trim();
                    String password = getAuthorizationFragment().password.getText().toString().trim();
                    request.setUsername(username);
                    request.setPassword(password);
                    Call<ResponsePOJO> call = RetrofitModule.getAuthApi().registerUser(request);
                    call.enqueue(new Callback<ResponsePOJO>() {
                        @Override
                        public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                            accessToken[0] = response.body().getAccessToken();
                            onRunMainActivityListener.onRunMainActivityA(accessToken[0]);
                        }

                        @Override
                        public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                            Toast.makeText(getContext(),"On Failure", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRunMainActivityListener.onCreateAccount();
            }
        });
        return view;
    }
    AuthorizationFragment getAuthorizationFragment(){
        return this;
    }

}