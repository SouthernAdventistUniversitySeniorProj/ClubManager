package com.seniorproject.sauclubmanager;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Toast;

import com.parse.ParseFile;
import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class settings extends DashboardActivity {
    public static String firstName = "firstName";
    public static String lastName = "lastName";
    public static String classStanding = "classStanding";
    public static String userBio = "userBio";
    public static String userphoto = "userPhoto";
    public static String userMainClub = "mainClub";
    public static String[] userFullname;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(android.R.id.content, new settings_fragment());
        transaction.commit();
    }

    @SuppressLint("ValidFragment")
    public class settings_fragment extends PreferenceFragment {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            PreferenceManager manager = getPreferenceManager();
            manager.setSharedPreferencesName("settings_frag");
            addPreferencesFromResource(R.xml.settings_frag);

            final ParseUser curUser = ParseUser.getCurrentUser();
            String firstPart = curUser.get(firstName).toString();
            String lastPart = curUser.get(lastName).toString();

            // editing display name
            final EditTextPreference user_name = (EditTextPreference) findPreference("reg_name");
            user_name.setText(firstPart + " " + lastPart);
            user_name.setOnPreferenceClickListener( new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    return true;
                }
            });
            user_name.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    userFullname = newValue.toString().split(" ");
                    ParseUser.getCurrentUser().put(firstName, userFullname[0]);
                    ParseUser.getCurrentUser().put(lastName, userFullname[1]);
                    ParseUser.getCurrentUser().saveInBackground();
                    Toast.makeText(getActivity(), userFullname[0] + " " + userFullname[1], Toast.LENGTH_LONG).show();
                    return true;
                }
            });

            // editing class standing
            final ListPreference classstanding = (ListPreference) findPreference("reg_classStanding");
            classstanding.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    final String[] classStandingArray = getResources().getStringArray(R.array.class_standing);
                    int standVal = Integer.parseInt(newValue.toString());
                    curUser.put(classStanding, classStandingArray[standVal]);
                    curUser.saveInBackground();
                    Toast.makeText(getActivity(), "You have successfully changed your class standing to "
                            + classStandingArray[standVal], Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            // editing main club
            final ListPreference pickmainClub = (ListPreference) findPreference("reg_mainClub");
            pickmainClub.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    final String[] mainClubArray = getResources().getStringArray(R.array.club_names);
                    int clubVal = Integer.parseInt(newValue.toString());
                    curUser.put(userMainClub, mainClubArray[clubVal]);
                    curUser.saveInBackground();
                    Toast.makeText(getActivity(), "You have successfully changed your main club to "
                            + mainClubArray[clubVal], Toast.LENGTH_SHORT).show();
                    return true;
                }
            });

            //editing profile pic
            final Preference userpic = findPreference("userpic");
            userpic.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
                @Override
                public boolean onPreferenceClick(Preference preference) {
                    selectImage();
                    return true;
                }
            });

            //editing bio
            final EditTextPreference userTextBio = (EditTextPreference) findPreference("user_bio");
            userTextBio.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    curUser.put(userBio, newValue);
                    curUser.saveInBackground();
                    Toast.makeText(getActivity(), "You have successfully updated your bio", Toast.LENGTH_SHORT).show();
                    return true;
                }
            });


        }
    }
    //method to give user option of uploading a photo in various ways
        private void selectImage() {
            final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
            AlertDialog.Builder builder = new AlertDialog.Builder(settings.this);
            builder.setTitle("Add Photo!");
            builder.setItems(options, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int item) {
                    if (options[item].equals("Take Photo"))
                    {
                        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        File f = new File(android.os.Environment.getExternalStorageDirectory(), "temp.jpg");
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
                        startActivityForResult(intent, 1);
                    }
                    else if (options[item].equals("Choose from Gallery"))
                    {
                        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, 2);

                    }
                    else if (options[item].equals("Cancel")) {
                        dialog.dismiss();
                    }
                }
            });
            builder.show();
        }

    //handling profile pic
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                File f = new File(Environment.getExternalStorageDirectory().toString());
                for (File temp : f.listFiles()) {
                    if (temp.getName().equals("temp.jpg")) {
                        f = temp;
                        break;
                    }
                }
                try {
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();

                    bitmap = BitmapFactory.decodeFile(f.getAbsolutePath(),
                            bitmapOptions);

                    //user_profile.viewImage.setImageBitmap(bitmap);

                    String path = android.os.Environment
                            .getExternalStorageDirectory()
                            + File.separator
                            + "Phoenix" + File.separator + "default";
                    f.delete();
                    OutputStream outFile = null;
                    File file = new File(path, String.valueOf(System.currentTimeMillis()) + ".jpg");
                    try {
                        outFile = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 85, outFile);
                        outFile.flush();
                        outFile.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == 2) {

                Uri selectedImage = data.getData();
                String[] filePath = { MediaStore.Images.Media.DATA };
                Cursor c = getContentResolver().query(selectedImage,filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                //Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                int index = picturePath.lastIndexOf("/");
                String fileName = picturePath.substring(index + 1);
                Log.w("path of image from gallery......******************.........", picturePath + " ------ " + fileName);
                    Bitmap picPath = BitmapFactory.decodeFile(picturePath);
                    // Convert it to byte
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    // Compress image to lower quality scale 1 - 100
                    picPath.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    byte[] photo = stream.toByteArray();
                    ParseFile file = new ParseFile(fileName, photo);
                    file.saveInBackground();
                    ParseUser.getCurrentUser().put(userphoto, file);
                    ParseUser.getCurrentUser().saveInBackground();
            }
        }
    }
}