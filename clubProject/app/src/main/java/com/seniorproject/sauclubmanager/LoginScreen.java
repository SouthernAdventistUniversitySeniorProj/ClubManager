package com.seniorproject.sauclubmanager;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


/**
 * A login screen that offers login via email/password.

 */
@SuppressLint("NewApi")
public class LoginScreen extends Activity implements LoaderCallbacks<Cursor>{

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
   /* private static final String[] DUMMY_CREDENTIALS = new String[]{
            "test@test.com:hello"//"qmarcelle@gmail.com:hello",
    };*/
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    //Shared Prefs/utilities
    public static boolean saveLogin;
    public static SharedPreferences loginPreferences;
    public static SharedPreferences.Editor loginPrefsEditor;
    public static String useremail,password;
    public static String loginSuccess = "no";


    // UI references.
    private EditText mEmailView; // used to be AutoCompleteTextView type
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    public static CheckBox saveLoginCheckbox;
    private Button signIn;

    // regusers
    //EditText reg_id, reg_fname, reg_lname, reg_email, reg_pass;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        // regusers
      /*  reg_id = (EditText) findViewById(R.id.reg_id);
        reg_fname = (EditText) findViewById(R.id.reg_fname);
        reg_lname = (EditText) findViewById(R.id.reg_lname);
        reg_email = (EditText) findViewById(R.id.reg_email);
        reg_pass = (EditText) findViewById(R.id.reg_pass);*/

        //CONNECT TO DB
        Connection conn = null;


        //register link
        TextView btn = (TextView) findViewById(R.id.action_reg);
        btn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                //setContentView(R.layout.login_reg_frag);
                Intent myIntent = new Intent(LoginScreen.this, reg_login.class);
                startActivity(myIntent);
                finish();
            }
        });

        //forget password link
        TextView t = (TextView) findViewById(R.id.ForgotPassText);
        t.setMovementMethod(LinkMovementMethod.getInstance());

        // Set up the login form.
        mEmailView = (EditText) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);
        //signIn = (Button)findViewById(R.id.email_sign_in_button);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }

        });

        //old hardcoded logic
        /*for (String credential : DUMMY_CREDENTIALS) {
            String[] pieces = credential.split(":");
            mEmailView.setText(pieces[0]);
            mPasswordView.setText(pieces[1]);
        }*/


        // Set up utility references
        saveLoginCheckbox = (CheckBox) findViewById(R.id.remember);
        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();


        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                autoLogin();
            }
        });

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin == true) {

            mEmailView.setText(loginPreferences.getString("username", ""));
            mPasswordView.setText(loginPreferences.getString("password", ""));
            saveLoginCheckbox.setChecked(true);
        }

        //hide keyboard if login was saved
        if (saveLoginCheckbox.isChecked()) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(mEmailView.getWindowToken(), 0);
        }

        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);


        if (loginPreferences.getString("username", "") != "" || loginPreferences.getString("password", "") != "") {
            mEmailSignInButton.performClick();
            loginSuccess = "yes";

        } else {
            loginSuccess = "no";
        }
    }
    // saving email for ClubActivity

    private void autoLogin() {
        //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);

        useremail = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
        postParameters.add(new BasicNameValuePair("username", useremail));
        postParameters.add(new BasicNameValuePair("password", password));

        Connection response = null;
        try {

            //response = CustomHttpClient.executeHttpPost("<CONNECTION STRING?>", postParameters);
            String res = response.toString();
            res = res.replaceAll("\\s+", "");


        } catch (Exception e) {

        }

        if (saveLoginCheckbox.isChecked()) {
            loginPrefsEditor.putBoolean("saveLogin", true);
            loginPrefsEditor.putString("username", useremail);
            loginPrefsEditor.putString("password", password);
            loginPrefsEditor.commit();
        } else {
            loginPrefsEditor.clear();
            loginPrefsEditor.commit();
        }
        attemptLogin();


    }
    /*private void populateAutoComplete() {
        getLoaderManager().initLoader(0, null, this);
    }*/

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    @SuppressLint("NewApi")
    public void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt. SHARED PREF STORAGE HERE
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        // Check for a valid password, if the user entered one.
        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }
    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    public void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    //_TO DO_
   /* public void onClick(View view)
    {
        if (view == ok) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(editTextUsername.getWindowToken(), 0);

            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();

            if (saveLoginCheckBox.isChecked()) {
                loginPrefsEditor.putBoolean("saveLogin", true);
                loginPrefsEditor.putString("username", username);
                loginPrefsEditor.putString("password", password);
                loginPrefsEditor.commit();
            } else {
                loginPrefsEditor.clear();
                loginPrefsEditor.commit();
            }
    }*/

    @SuppressLint("NewApi")
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }

    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        List<String> emails = new ArrayList<String>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }

        // addEmailsToAutoComplete(emails);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader) {

    }

    private interface ProfileQuery {
        String[] PROJECTION = {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
        };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }


   /* private void addEmailsToAutoComplete(List<String> emailAddressCollection) {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(LoginScreen.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }*/

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    @SuppressLint("NewApi")
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }


        @Override
        protected Boolean doInBackground(Void... params) {
            // TODO: attempt authentication against a network service.

            // Suggestion:  Load these from a properties object.
            String DRIVER = "net.sourceforge.jtds.jdbc.Driver";

            // Register the native JDBC driver. If the driver cannot be
            // registered, the test cannot continue.
            try {
                Class.forName(DRIVER);
            } catch (Exception e) {
                System.out.println("Driver failed to register.");
                System.out.println(e.getMessage());
                System.exit(1);
            }

            Connection response;
            Statement statement = null;
            ResultSet resultSet = null;
            String dbUsername;

            try {
                // Simulate network access.
                Thread.sleep(2000);

                Log.d("salfjg;sajfjsagjsajg", "Before attempting to open db connection");

                String dbURL = "jdbc:jtds:sqlserver://216.249.119.136;instance=ClubProject;DatabaseName=ClubDatabase";
                //login to server
                response = DriverManager.getConnection(dbURL, "sa", "d1559563!");

                Log.d("salfjg;sajfjsagjsajg", "tried to open db connection");

                statement = response.createStatement();

                resultSet = statement.executeQuery("Select * From Users");

                while (resultSet.next()) {
                    //check if the email entered is in the database
                    Log.d("JHGJGJKGHJGIHGHH", "checking email..........");
                    String myResultEmail = resultSet.getString(5);
                    String myResultPassword = resultSet.getString(4);
                    if (myResultEmail.equals(mEmail)) {
                        Log.d("JHGJGJKGHJGIHGHH", "email found...checking password");
                        return myResultPassword.equals(mPassword);
                    }
                    //logic for the last row in the table
                    if(resultSet.isLast())
                    {
                        if (resultSet.getString(5).equals(mEmail)) {
                            Log.d("JHGJGJKGHJGIHGHH", "email found...checking password");
                            return resultSet.getString(4).equals(mPassword);

                        }
                        return false;
                    }
                }

            } catch (InterruptedException e) {
                return false;
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // TODO: register the new account here.

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                Intent myIntent = new Intent(LoginScreen.this, HomeActivity.class);
                startActivity(myIntent);
                loginSuccess = "yes";
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
                loginSuccess = "no";
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}



