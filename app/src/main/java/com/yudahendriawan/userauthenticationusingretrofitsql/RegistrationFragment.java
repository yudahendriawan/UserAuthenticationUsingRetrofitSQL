package com.yudahendriawan.userauthenticationusingretrofitsql;


import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * A simple {@link Fragment} subclass.
 */
public class RegistrationFragment extends Fragment {

    private EditText name,user_name,user_password;
    private Button btn_register;

    public RegistrationFragment() {
        // Required empty public constructor
    }


    @SuppressLint("CutPasteId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        name = view.findViewById(R.id.name);
        user_name = view.findViewById(R.id.user_name);
        user_password = view.findViewById(R.id.user_password);
        btn_register = view.findViewById(R.id.btn_register);

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                performRegistration();
            }
        });
        return view;
    }

    public void performRegistration()
    {
        String nama = name.getText().toString();
        String username = user_name.getText().toString();
        String userpassword = user_password.getText().toString();

        Call<User> call = MainActivity.apiInterface.performRegristration(nama, username, userpassword);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.body().getResponse().equals("ok"))
                {
                    MainActivity.prefConfig.displayToast("Regristation Success");
                }
                else if (response.body().getResponse().equals("exist"))
                {
                    MainActivity.prefConfig.displayToast("User Alreday Exist");
                }
                else if (response.body().getResponse().equals("error"))
                {
                    MainActivity.prefConfig.displayToast("Something went wrong");
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        name.setText("");
        user_name.setText("");
        user_password.setText("");
    }

}
