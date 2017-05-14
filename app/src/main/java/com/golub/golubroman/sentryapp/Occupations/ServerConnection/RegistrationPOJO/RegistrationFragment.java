package com.golub.golubroman.sentryapp.Occupations.ServerConnection.RegistrationPOJO;

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
import com.golub.golubroman.sentry.ServerConnection.AuthorizationPOJO.ResponsePOJO;
import com.golub.golubroman.sentry.ServerConnection.AuthorizationPOJO.RetrofitModule;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by roman on 14.05.17.
 */

public class RegistrationFragment extends Fragment{
    @BindView(R.id.username_fragment_sign_up) EditText username;
    @BindView(R.id.password_fragment_sign_up) EditText password;
    @BindView(R.id.name_fragment_sign_up) EditText name;
    @BindView(R.id.sign_fragment_sign_up) ImageView sign;
    @BindView(R.id.sign_in_account_fragment_sign_up) TextView signAccount;

    OnRunMainActivityListener onRunMainActivityListener;

    public interface OnRunMainActivityListener{
        void onRunMainActivityR(String a);
        void onSignAccount();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        onRunMainActivityListener = (OnRunMainActivityListener)activity;
    }

    public static RegistrationFragment newInstance() {
        Bundle args = new Bundle();
        RegistrationFragment fragment = new RegistrationFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);
        ButterKnife.bind(this, view);
        RetrofitModule.buildRegApi();
        final RequestPOJO request = new RequestPOJO();
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().trim().equals("") ||
                        password.getText().toString().trim().equals("") ||
                        name.getText().toString().trim().equals("")){
                    Toast.makeText(getContext(), "Input all fields", Toast.LENGTH_SHORT).show();
                }else{
                    final String username = getAuthorizationFragment().username.getText().toString().trim();
                    String password = getAuthorizationFragment().password.getText().toString().trim();
                    String name = getAuthorizationFragment().name.getText().toString().trim();
                    request.setUsername(username);
                    request.setPassword(password);
                    request.setName(name);
                    Call<ResponsePOJO> call = RetrofitModule.getRegApi().registerUser(request);
                    call.enqueue(new Callback<ResponsePOJO>() {
                        @Override
                        public void onResponse(Call<ResponsePOJO> call, Response<ResponsePOJO> response) {
                            if(response.body().getAccessToken() != null){
                                onRunMainActivityListener.onRunMainActivityR(response.body().getAccessToken());
                            }else{
                                Toast.makeText(getContext(), "The user with username" +
                                        username + " existed", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponsePOJO> call, Throwable t) {
                            Toast.makeText(getContext(),"On Failure", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        signAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onRunMainActivityListener.onSignAccount();
            }
        });

        return view;
    }

    RegistrationFragment getAuthorizationFragment(){
        return this;
    }
}