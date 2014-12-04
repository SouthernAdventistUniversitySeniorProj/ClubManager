package com.seniorproject.sauclubmanager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seniorproject.sauclubmanager.com.seniorproject.utilities.GMailSender;

/**
 * Created by User on 11/19/2014.
 */
public class report extends DashboardActivity {
    private EditText namereport;
    private EditText reportemail;
    private EditText textreport;

    private String subject;
    private String body;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report);

        namereport = (EditText) findViewById(R.id.name_report);
        reportemail = (EditText) findViewById(R.id.report_email);
        textreport = (EditText) findViewById(R.id.text_report);
        subject = "";
        body = "";


        Button reportbutton = (Button) findViewById(R.id.button_report);
        reportbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmailtoUser();
                sendEmailtoAdmin();
               // namereport.setText("");
               // reportemail.setText("");
               // textreport.setText("");
                Toast.makeText(getApplicationContext(), "Submitted.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    protected void sendEmailtoUser() {
        subject = (namereport + ", Thank You for your submission!");
        body = ("Thank you for submitting your report. We will work hard to fix " +
                " your issues your experiencing. If you submitted a suggestion or improvement" +
                " we will work hard to make sure requests come true!");
        try {
            GMailSender sender = new GMailSender("duvonneb@gmail.com", "Berry1559563");
            sender.sendMail(subject.toString(),
                    body.toString(),
                    "duvonneb@gmail.com",
                    reportemail.getText().toString());
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }

    protected void sendEmailtoAdmin() {
        subject = ("Report from: " + namereport);
        try {
            GMailSender sender = new GMailSender("duvonneb@gmail.com", "Berry1559563");
            sender.sendMail(subject.toString(),
                    textreport.getText().toString(),
                    "duvonneb@gmail.com",
                    "duvonneb@southern.edu");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }
}
