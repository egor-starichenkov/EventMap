package com.starichenkov.eventmap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.view.View.OnClickListener;

import com.starichenkov.presenter.IPresenter;
import com.starichenkov.presenter.Presenter;

import static com.google.android.gms.wearable.DataMap.TAG;

public class RegistrationActivity extends Activity implements IView, OnClickListener {

    private IPresenter iPresenter;
    private EditText editFIO;
    private EditText editMail;
    private EditText editPassword;
    private EditText editDateBirth;
    private Button buttonCreateAcc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registration);

        initView();
        iPresenter = new Presenter(this);
    }

    private void initView() {

        editFIO = (EditText) findViewById(R.id.editFIO);
        editMail = (EditText) findViewById(R.id.editMail);
        editPassword = (EditText) findViewById(R.id.editPassword);
        editDateBirth = (EditText) findViewById(R.id.editDateBirth);
        buttonCreateAcc = (Button) findViewById(R.id.buttonCreateAcc);
        buttonCreateAcc.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // по id определяем кнопку, вызвавшую этот обработчик
        Log.d(TAG, "по id определяем кнопку, вызвавшую этот обработчик");
        switch (v.getId()) {

            case R.id.buttonCreateAcc:
                Log.d(TAG, "Create account");

                break;
        }
    }
}
