/*
 *  Copyright (c) 2014, Facebook, Inc. All rights reserved.
 *
 *  You are hereby granted a non-exclusive, worldwide, royalty-free license to use,
 *  copy, modify, and distribute this software in source code or binary form for use
 *  in connection with the web services and APIs provided by Facebook.
 *
 *  As with any software that integrates with the Facebook platform, your use of
 *  this software is subject to the Facebook Developer Principles and Policies
 *  [http://developers.facebook.com/policy/]. This copyright notice shall be
 *  included in all copies or substantial portions of the software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 *  FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 *  COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 *  IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 *  CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 */

package com.parse.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import java.util.List;

/**
 * Fragment for the user signup screen.
 */
public class ParseSignupFragment extends ParseLoginFragmentBase implements OnClickListener {
  public static final String USERNAME = "com.parse.ui.ParseSignupFragment.USERNAME";
  public static final String PASSWORD = "com.parse.ui.ParseSignupFragment.PASSWORD";

  private EditText usernameField;
  private EditText passwordField;
  private EditText confirmPasswordField;
  private EditText emailField;
  //private EditText nameField;
  private EditText fNameField;
  private EditText lNameField;
  private Spinner classSelector;
  private Spinner clubSelection;
  private Button createAccountButton;
  private ParseOnLoginSuccessListener onLoginSuccessListener;

  private ParseLoginConfig config;
  private int minPasswordLength;

    private ParseObject myClub = null;

  private static final String LOG_TAG = "ParseSignupFragment";
  private static final int DEFAULT_MIN_PASSWORD_LENGTH = 6;

    //SERVER SIDE KEY VALUES
  //private static final String USER_OBJECT_FNAME_FIELD = "first name";
  //private static final String USER_OBJECT_LNAME_FIELD = "last name";
    private static final String firstName = "firstName";
    private static final String lastName = "lastName";
    private static final String email = "email";
    private static final String classStanding = "classStanding";
    private static final String mainClub = "mainClub";


  public static ParseSignupFragment newInstance(Bundle configOptions, String username, String password) {
    ParseSignupFragment signupFragment = new ParseSignupFragment();
    Bundle args = new Bundle(configOptions);
    args.putString(ParseSignupFragment.USERNAME, username);
    args.putString(ParseSignupFragment.PASSWORD, password);
    signupFragment.setArguments(args);
    return signupFragment;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup parent,
                           Bundle savedInstanceState) {

    Bundle args = getArguments();
    config = ParseLoginConfig.fromBundle(args, getActivity());

    minPasswordLength = DEFAULT_MIN_PASSWORD_LENGTH;
    if (config.getParseSignupMinPasswordLength() != null) {
      minPasswordLength = config.getParseSignupMinPasswordLength();
    }

    String username = (String) args.getString(USERNAME);
    String password = (String) args.getString(PASSWORD);

      View v = inflater.inflate(R.layout.com_parse_ui_parse_signup_fragment,
              parent, false);
    ImageView appLogo = (ImageView) v.findViewById(R.id.app_logo);
    usernameField = (EditText) v.findViewById(R.id.signup_username_input);
    passwordField = (EditText) v.findViewById(R.id.signup_password_input);
    confirmPasswordField = (EditText) v
        .findViewById(R.id.signup_confirm_password_input);
    emailField = (EditText) v.findViewById(R.id.signup_email_input);
    //nameField = (EditText) v.findViewById(R.id.signup_name_input);
    fNameField = (EditText) v.findViewById(R.id.signup_fname_input);
    lNameField = (EditText) v.findViewById(R.id.signup_lname_input);
    classSelector = (Spinner) v.findViewById(R.id.class_selector);
    clubSelection = (Spinner) v.findViewById(R.id.club_selector);
    createAccountButton = (Button) v.findViewById(R.id.create_account);

    usernameField.setText(username);
    passwordField.setText(password);

    if (appLogo != null && config.getAppLogo() != null) {
      appLogo.setImageResource(config.getAppLogo());
    }

    if (config.isParseLoginEmailAsUsername()) {
      usernameField.setHint(R.string.com_parse_ui_email_input_hint);
      usernameField.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
      if (emailField != null) {
        emailField.setVisibility(View.GONE);
      }
    }

    if (config.getParseSignupSubmitButtonText() != null) {
      createAccountButton.setText(config.getParseSignupSubmitButtonText());
    }
    createAccountButton.setOnClickListener(this);

    return v;
  }

  @Override
  public void onAttach(Activity activity) {
    super.onAttach(activity);
    if (activity instanceof ParseOnLoginSuccessListener) {
      onLoginSuccessListener = (ParseOnLoginSuccessListener) activity;
    } else {
      throw new IllegalArgumentException(
          "Activity must implemement ParseOnLoginSuccessListener");
    }

    if (activity instanceof ParseOnLoadingListener) {
      onLoadingListener = (ParseOnLoadingListener) activity;
    } else {
      throw new IllegalArgumentException(
          "Activity must implemement ParseOnLoadingListener");
    }
  }

  @Override
  public void onClick(View v) {
    String username = usernameField.getText().toString();
    String password = passwordField.getText().toString();
    String passwordAgain = confirmPasswordField.getText().toString();

    String email = null;
    if (config.isParseLoginEmailAsUsername()) {
      email = usernameField.getText().toString();
    } else if (emailField != null) {
      email = emailField.getText().toString();
    }

      ///GET AND CHECK FIRST AND LAST NAMES
    String fname = null;
    if (fNameField  != null) {
      fname = fNameField.getText().toString();
    }

      String lname = null;
      if (lNameField != null) {
          lname = lNameField.getText().toString();
      }
      ///GET CLASS STANDING AND MAIN CLUB SELECTION

      String CLASSSTAND = classSelector.getSelectedItem().toString();

      String MAINCLUB = clubSelection.getSelectedItem().toString();





    if (username.length() == 0) {
      showToast(R.string.com_parse_ui_no_username_toast);
    } else if (password.length() == 0) {
      showToast(R.string.com_parse_ui_no_password_toast);
    } else if (password.length() < minPasswordLength) {
      showToast(getResources().getQuantityString(
          R.plurals.com_parse_ui_password_too_short_toast,
          minPasswordLength, minPasswordLength));
    } else if (passwordAgain.length() == 0) {
      showToast(R.string.com_parse_ui_reenter_password_toast);
    } else if (!password.equals(passwordAgain)) {
      showToast(R.string.com_parse_ui_mismatch_confirm_password_toast);
      confirmPasswordField.selectAll();
      confirmPasswordField.requestFocus();
    } else if (email != null && email.length() == 0) {
      showToast(R.string.com_parse_ui_no_email_toast);
    } else if (fname != null && fname.length() == 0) {
        showToast(R.string.com_parse_ui_no_name_toast);
    } else if (lname != null && lname.length() == 0) {
        showToast(R.string.com_parse_ui_no_name_toast);
    } else {
      ParseUser user = new ParseUser();

      // Set standard fields
      user.setUsername(username);
      user.setPassword(password);
      user.setEmail(email);



        /*private static final String firstName = "first name";
        private static final String lastName = "last name";
        private static final String email = "email";
        private static final String classStanding = "class standing";
        private static final String mainClub = "main club";*/
        String usrBio = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam rhoncus ullamcorper quam vitae egestas. Sed diam nulla, pharetra non erat id, placerat gravida ante. " +
                "Nullam cursus ligula vel mauris placerat, quis accumsan tortor vestibulum. Quisque sit amet leo est. Aliquam sollicitudin vel ex vitae imperdiet. Vestibulum cursus, dolor nec " +
                "auctor varius, nibh velit luctus arcu, et suscipit arcu dui eget libero. Morbi tempor ligula turpis, in convallis neque pharetra et. Cras non elementum lectus. Proin venenatis est " +
                "varius, pretium augue ac, fringilla enim. Quisque congue quam ac blandit dapibus. Vestibulum et mauris a lacus pulvinar tempor sit amet vel est. Vestibulum at neque quis risus dictum faucibus.";




        // Set additional custom fields only if the user filled it out
      if (fname.length() != 0) {
        user.put(firstName, fname);
      }

        if (lname.length() != 0) {
            user.put(lastName, lname);
        }
        user.put(classStanding,CLASSSTAND);
        user.put(mainClub,MAINCLUB);
        //user.put("clubs",myClub);
        user.put("userBio",usrBio);


        loadingStart();
      user.signUpInBackground(new SignUpCallback() {

        @Override
        public void done(ParseException e) {
          if (isActivityDestroyed()) {
            return;
          }

          if (e == null) {
            loadingFinish();
            signupSuccess();
          } else {
            loadingFinish();
            if (e != null) {
              debugLog(getString(R.string.com_parse_ui_login_warning_parse_signup_failed) +
                  e.toString());
              switch (e.getCode()) {
                case ParseException.INVALID_EMAIL_ADDRESS:
                  showToast(R.string.com_parse_ui_invalid_email_toast);
                  break;
                case ParseException.USERNAME_TAKEN:
                  showToast(R.string.com_parse_ui_username_taken_toast);
                  break;
                case ParseException.EMAIL_TAKEN:
                  showToast(R.string.com_parse_ui_email_taken_toast);
                  break;
                default:
                  showToast(R.string.com_parse_ui_signup_failed_unknown_toast);
              }
            }
          }
        }
      });
    }
  }

  @Override
  protected String getLogTag() {
    return LOG_TAG;
  }

  private void signupSuccess() {
    onLoginSuccessListener.onLoginSuccess();
  }
}
