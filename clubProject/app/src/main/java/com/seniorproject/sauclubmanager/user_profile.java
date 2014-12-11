package com.seniorproject.sauclubmanager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseRelation;
import com.parse.ParseUser;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class user_profile extends DashboardActivity {
    /**
     * onCreate - called when the activity is first created.
     * Called when the activity is first created.
     * This is where you should do all of your normal static set up: create views, bind data to lists, etc.
     * This method also provides you with a Bundle containing the activity's previously frozen state, if there was one.
     */

    private String id, fname, lname, pass;


    final ParseUser user = new ParseUser();

    //Setup Ui reference variables
    private TextView name;//userpro_name
    private TextView classStand;//userpro_classStanding
    private TextView userEmail;//userpro_email
    private ListView userClubs;


    //SERVER SIDE KEY VALUES
    private static final String firstName = "firstName";
    private static final String lastName = "lastName";
    private static final String email = "email";
    private static final String classStanding = "classStanding";
    //private static final String mainClub = "mainClub";


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        ParseUser curUser = ParseUser.getCurrentUser();

        //connect ui widgets to their private variables
        name = (TextView) findViewById(R.id.userpro_name);
        classStand = (TextView) findViewById(R.id.userpro_classStanding);
        userEmail = (TextView) findViewById(R.id.userpro_email);
        userClubs = (ListView) findViewById(R.id.userClubsListView);

        //get and display the info from the web to the appropriate ui field

        //get and store
        String firstPart = curUser.get(firstName).toString();
        String lastPart = curUser.get(lastName).toString();

        name.setText(firstPart + " " +lastPart);
        classStand.setText(curUser.get(classStanding).toString());
        userEmail.setText(curUser.get(email).toString());


        //find the column where clubnames are held in the user table then add them to the adapter list through a forloop


       /* ParseQueryAdapter<ParseObject> adapter = new ParseQueryAdapter<ParseObject>(this, ParseUser);
        adapter.setTextKey("name");
        adapter.setImageKey("photo");*/

       // ParseQuery<ParseObject> findMyClub = ParseQuery.getQuery("Club");

       //
       // findMyClub.whereEqualTo()

        //Array of club objects
        //final ParseObject clubObjs[] = null;


       // ParseRelation <ParseObject> relation = ParseUser.getCurrentUser().getRelation("clubs");



     /*   relation.getQuery().findInBackground(new FindCallback<ParseObject>() {
            public void done(List<ParseObject> results, ParseException e) {
                if (e != null) {
                    // There was an error
                } else {
                    // results have all the Posts the current user liked.
                    //save the objects to an array

                        //clubObjs[0]=results.get(results.size()-1);

                }
            }
        });*/



        //ParseUser curUser = ParseUser.getCurrentUser();

        //1) query current user
        ParseQuery<ParseUser> findUser = ParseQuery.getQuery(String.valueOf(curUser));

        //2) compare the club choice of the user to the club objects in the DB

        /*findClub.whereEqualTo("clubName",myClub);

        //SHOULD RETURN ONE CLUB AND I PLACE THAT CLUB IN MY CLUB VARIABLE
        findClub.findInBackground(new FindCallback<ParseObject>() {
            @Override




       Log.d("PARSE USER CLUB", ParseUser.getCurrentUser().getRelation("clubs").toString());

*/

        //Log.d("PARSE USER CLUB", clubObjs[0].get("clubName").toString());

        ///populate database with 1500 users!!!!!

        //username-------------------------
        //password
        //email-----------
        //class standing
        //main club-string
        //clubs - relation
        // user - bio






      //  }





    }

    /**
     * onDestroy
     * The final call you receive before your activity is destroyed.
     * This can happen either because the activity is finishing (someone called finish() on it,
     * or because the system is temporarily destroying this instance of the activity to save space.
     * You can distinguish between these two scenarios with the isFinishing() method.
     */
    protected void onDestroy () {
        super.onDestroy ();
    }

    /**
     * onPause
     * Called when the system is about to start resuming a previous activity.
     * This is typically used to commit unsaved changes to persistent data, stop animations
     * and other things that may be consuming CPU, etc.
     * Implementations of this method must be very quick because the next activity will not be resumed
     * until this method returns.
     * Followed by either onResume() if the activity returns back to the front,
     * or onStop() if it becomes invisible to the user.
     */
    protected void onPause () {
        super.onPause ();
    }

    /**
     * onRestart
     * Called after your activity has been stopped, prior to it being started again.
     * Always followed by onStart().
     *
     */
    protected void onRestart () {
        super.onRestart ();
        ParseUser curUser = ParseUser.getCurrentUser();

        //connect ui widgets to their private variables
        name = (TextView) findViewById(R.id.userpro_name);
        classStand = (TextView) findViewById(R.id.userpro_classStanding);
        userEmail = (TextView) findViewById(R.id.userpro_email);

        //get and display the info from the web to the appropriate ui field

        //get and store
        String firstPart = curUser.get(firstName).toString();
        String lastPart = curUser.get(lastName).toString();

        name.setText(firstPart + " " +lastPart);
        classStand.setText(curUser.get(classStanding).toString());
        userEmail.setText(curUser.get(email).toString());





    }

    /**
     * onResume
     * Called when the activity will start interacting with the user.
     * At this point your activity is at the top of the activity stack, with user input going to it.
     * Always followed by onPause().
     *
     */
    protected void onResume () {
        super.onResume ();
        ParseUser curUser = ParseUser.getCurrentUser();

        //connect ui widgets to their private variables
        name = (TextView) findViewById(R.id.userpro_name);
        classStand = (TextView) findViewById(R.id.userpro_classStanding);
        userEmail = (TextView) findViewById(R.id.userpro_email);

        //get and display the info from the web to the appropriate ui field

        //get and store
        String firstPart = curUser.get(firstName).toString();
        String lastPart = curUser.get(lastName).toString();

        name.setText(firstPart + " " +lastPart);
        classStand.setText(curUser.get(classStanding).toString());
        userEmail.setText(curUser.get(email).toString());

        ////////////////////DELETE BELOW????????
       /* Random rnum = new Random();
        Random rclub = new Random();
        Random rclass = new Random();
        // int randNum = rn.nextInt(99);
        //int randClub = rn.nextInt(29);
        //int randClass = rn.nextInt(4);
        //

        //array of names
        String names[] = {"Nereida","Vesta", "Tameika","Tambra","Jacqueline","Eunice","Macy","Margert","Wendell","Colton","Carin","Cletus", "Sanjuanita","Arlena","Arie","Shanna", "Bruce","Lindsy","Agustin","Inocencia","Horace","Gabriel","Lynna","Allie","Yuko","Elyse","Norah","Shandra","Jetta","Geoffrey","Jorge","Julietta","Tawny","Lashonda","Raymon",
                "Mari","Marianna","Catherina","Dwight","Vertie","Jerrold","Brenda","Shana","Lara","Michiko","Hattie","Angel","Penni","Belle","Darrell","Tamela","Rodolfo","Janyce","Raelene","Fernando",
                "Glayds","Zackary","Demetrius","Aleshia","Yen","yrstal","Elwanda","Domenic","Cristina","Kimbery","Dianne","Reginia","Elma","Grayce","Renee","Coralee","Caitlin","Jaleesa","Xiomara","Justina","Renetta","Britta","Jospeh","Rima",
                "Annalee","Barbara","Otelia","Vicky","Kathrine","Caroll","Grover","Otto","Marjorie","Gustavo","Benny","Audry","Seth","Quiana","Brandon","Robbie","Xenia","Loyd","Teresita","Freida","Florene", };

        //select first name
        // String fName = names[randNum];
        //select last name
        //  String lName = names[randNum];
        //Select email
        //String email = fName + lName + "@gmeil.com";
        String passWrd = names[40]+"1234";

        String[] clubName = this.getResources().getStringArray(R.array.club_names);
        String[] className = this.getResources().getStringArray(R.array.class_standing);




        //  for(int i = 0; i<100; i++){

        String randEmail = names[rnum.nextInt(100)]+names[rnum.nextInt(100)]+"@gmeil.com";
        user.setUsername(randEmail);
        user.setPassword(names[rnum.nextInt(100)]+"123");
        user.setEmail(randEmail);
        user.put("firstName",names[rnum.nextInt(100)]);
        user.put("lastName",names[rnum.nextInt(100)]);
        //So the random club is the same random club in the relation
        String ClubName = clubName[rclub.nextInt(29)];
        user.put("mainClub",ClubName);
        user.put("userBio",usrBio);
        user.put("classStanding", className[rclass.nextInt(4)+1]);




        //1) query club object
        ParseQuery<ParseObject> findClub = ParseQuery.getQuery("Club");

        //2) compare the club choice of the user to the club objects in the DB

        findClub.whereEqualTo("clubName",ClubName);

        //SHOULD RETURN ONE CLUB AND I PLACE THAT CLUB IN MY CLUB VARIABLE
        findClub.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> club, ParseException e) {
                if(e==null) {
                    Log.d("Brand", "Retrieved " + club.size() + " clubs");
                    ParseRelation<ParseObject> relation = user.getRelation("clubs");
                    relation.add(club.get(club.size()-1));
                    Log.d("Relation", "Relation added sucessfully");
                    // user.saveInBackground();



                    user.signUpInBackground();
                    //Save user in Club object
                    ParseRelation<ParseObject> relation2 = club.get(club.size()-1).getRelation("members");
                    relation2.add(user);
                    club.get(club.size()-1).saveInBackground();
                    Log.d("Relation", "Should have saved the user into the club relation");


                    //myClub = club.get(0);
                }else{
                    Log.d("Brand", "Error: " + e.getMessage());
                }
            }
        });



*/


    }

    /**
     * onStart
     * Called when the activity is becoming visible to the user.
     * Followed by onResume() if the activity comes to the foreground, or onStop() if it becomes hidden.
     *
     */
    protected void onStart () {
        super.onStart ();
    }

    /**
     * onStop
     * Called when the activity is no longer visible to the user
     * because another activity has been resumed and is covering this one.
     * This may happen either because a new activity is being started, an existing one
     * is being brought in front of this one, or this one is being destroyed.
     *
     * Followed by either onRestart() if this activity is coming back to interact with the user,
     * or onDestroy() if this activity is going away.
     */
    @Override
    protected void onStop()
    {
        super.onStop();
        // insert here your instructions
    }




}
