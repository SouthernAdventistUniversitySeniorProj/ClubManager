package com.seniorproject.sauclubmanager;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class clubs extends DashboardActivity {

    private String[] values;
    private String[] clubPics;
    private ListView clubListView;
    private Context ctx;
    private Intent intent;

    public clubs() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubs);
        //shows back button only if user is logged in
        if (ParseUser.getCurrentUser() != null) {
            getActionBar().setHomeButtonEnabled(true);
            getActionBar().setDisplayHomeAsUpEnabled(true); }

        //Initialize private variables
        ctx=this;
        values = this.getResources().getStringArray(R.array.club_names);
        clubPics = this.getResources().getStringArray(R.array.club_pics);
        clubListView = (ListView) findViewById(R.id.club_list);

        //List to hold club objects
        List clubList = new ArrayList();

        //forloop to initialize club objects
        for (int i = 0; i < values.length; i++) {
            clubList.add(new Club(values[i],clubPics[i]));
        }

        //when a club is selected from the array of clubs
        clubListView.setAdapter( new ClubListAdapterWithCache(ctx, R.layout.picture_list, clubList));
        clubListView.setOnItemClickListener(new OnItemClickListenerListViewItem(){

            public void onItemClick(AdapterView<?> a, View
                    v, int position, long id) {
                Log.d("salfjg;sajfjsagjsajg", values[position]+" "+ clubPics[position]+" "+"Item Clicked");
                launchClubPage(values[position],clubPics[position]);
            }
        });
    }

    public  void launchClubPage(String clubName, String clubPic){
        intent = new Intent(this,ClubActivity.class);
        intent.putExtra("CLUB_NAME", clubName);
        intent.putExtra("CLUB_PIC", clubPic);
        startActivity(intent);
    }

    // CLUB OBJECT
    public class Club {
        private String Name;
        private String Image;

        public Club(String name, String image) {
            super();
            this.Name = name;
            this.Image = image;
        }
        //get and set variables
        public String getName(){
            return Name;
        }
        public void setName (String nameText) {
            Name = nameText;
        }
        public String getImage() {
            return Image;
        }
        public void setImage (String image) {
            this.Image = image;
        }
    }

    // CACHE for clubs used for efficiency
    class ClubListViewCache {
        private View BaseView;
        private TextView clubNameText;
        private ImageView clubImage;
        public ClubListViewCache(View baseView) {
            this.BaseView = baseView;
        }
        public View getBaseView() {
            return BaseView;
        }
        public TextView getClubNameText(int resource) {
            if (clubNameText == null) {
                clubNameText = (TextView) BaseView.findViewById(R.id.clubName);
            }
            return clubNameText;
        }
        public ImageView getClubImage (int resource){
            if(clubImage == null) {
                clubImage = (ImageView) BaseView.findViewById(R.id.clubImage);
            }
            return clubImage;
        }
    }

    // Custom Adapter for clubs listview
    public class ClubListAdapterWithCache extends ArrayAdapter<Club>{
        private int resource;
        private LayoutInflater inflater;
        private Context context;

        public ClubListAdapterWithCache ( Context ctx, int resourceId, List objects) {
            super( ctx, resourceId, objects );
            resource = resourceId;
            inflater = LayoutInflater.from( ctx );
            context=ctx;
        }

        // setting up how the array is displayed once populated
        @Override
        public View getView ( int position, View convertView, ViewGroup parent ) {
            Club clubObj = getItem(position);
            ClubListViewCache viewCache;
            if ( convertView == null ) {
                convertView = (RelativeLayout) inflater.inflate( resource, null );
                viewCache = new ClubListViewCache( convertView );
                convertView.setTag( viewCache );
            }
            else {
                viewCache = ( ClubListViewCache ) convertView.getTag();
            }
            TextView txtName = viewCache.getClubNameText(resource);
            txtName.setText(clubObj.getName());

            ImageView clubImage = viewCache.getClubImage(resource);
            String uri = clubObj.getImage();
            int imageResource = context.getResources().getIdentifier(uri, null, context.getPackageName());
            Drawable image = context.getResources().getDrawable(imageResource);
            clubImage.setImageDrawable(image);
            return convertView;
        }
    }
    //getting information when a club is clicked
    public class OnItemClickListenerListViewItem implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            Context context = view.getContext();

            TextView textViewItem = ((TextView) view.findViewById(R.id.clubName));

            // get the clicked item name
            String listItemText = textViewItem.getText().toString();

            // get the clicked item ID
            String listItemId = textViewItem.getTag().toString();

            // just toast it
            Toast.makeText(context, "Item: " + listItemText + ", Item ID: " + listItemId, Toast.LENGTH_SHORT).show();
        }
    }
}