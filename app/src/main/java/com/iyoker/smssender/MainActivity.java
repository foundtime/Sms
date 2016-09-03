package com.iyoker.smssender;


import android.app.Activity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnClickListener {
    private EditText et_number1;
    private EditText et_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        et_content= (EditText) findViewById(R.id.et_content);
        et_number1 = (EditText) findViewById(R.id.et_number);
        Button btn_send= (Button) findViewById(R.id.btn_sendSms);
        btn_send.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_sendSms:
                String content = et_content.getText().toString().trim();
                String number = et_number1.getText().toString().trim();
                if(TextUtils.isEmpty(content)||TextUtils.isEmpty(number)){
                    Toast.makeText(this, "请输入号码或内容", Toast.LENGTH_SHORT).show();
                    return;
                }else {
                    SmsManager smsManager=SmsManager.getDefault();
                    ArrayList<String> contents = smsManager.divideMessage(content);
                    for (String str:contents
                         ) {
                        smsManager.sendTextMessage(number,null,str,null,null);
                    }

                }
                break;
        }
    }
}
