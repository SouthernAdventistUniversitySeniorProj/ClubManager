package com.seniorproject.sauclubmanager.com.seniorproject.utilities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.seniorproject.sauclubmanager.R;

/**
 * Created by User on 11/19/2014.
 */
public class report_frag extends Activity {
    private EditText namereport;
    private EditText reportemail;
    private EditText textreport;

    private EditText subject;
    private EditText body;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_frag);

        namereport = (EditText) findViewById(R.id.name_report);
        reportemail = (EditText) findViewById(R.id.report_email);
        textreport = (EditText) findViewById(R.id.text_report);

        Button helpbutton = (Button) findViewById(R.id.button_report);
        helpbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendEmailtoUser();
                sendEmailtoAdmin();
                namereport.setText("");
                reportemail.setText("");
                textreport.setText("");
            }
        });
    }

    protected void sendEmailtoUser() {
        String[] recipients = {reportemail.getText().toString()};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));

        subject.setText(namereport + "Thank You for your submission!");
        body.setText("Thank you for submitting your report. We will work hard to fix " +
                " your issues your experiencing. If you submitted a suggestion or improvement" +
                " we will work hard to make sure requests come true!");

        // prompts email clients only
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, recipients);
        email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());

        try {
            // the user can choose the email client
            startActivity(Intent.createChooser(email, "Choose an email client from..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(this, "No email client installed.",
                    Toast.LENGTH_LONG).show();
        }

    }

    protected void sendEmailtoAdmin() {

        try {
            GMailSender sender = new GMailSender("duvonneb@gmail.com", "Berry1559563");
            sender.sendMail(subject.getText().toString(),
                    textreport.getText().toString(),
                    "duvonneb@gmail.com",
                    "duvonneb@southern.edu");
        } catch (Exception e) {
            Log.e("SendMail", e.getMessage(), e);
        }
    }
}
