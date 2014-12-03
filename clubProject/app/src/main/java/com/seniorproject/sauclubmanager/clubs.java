package com.seniorproject.sauclubmanager;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class clubs extends Activity {

    private String[] values;
    private String[] clubPics;
    private ListView clubListView;
    private Context ctx;

    public clubs() {
        // Required empty public constructor
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clubs);
//Initialize private variables
        ctx=this;
        values = this.getResources().getStringArray(R.array.club_names);
        clubPics = this.getResources().getStringArray(R.array.club_pics);
        clubListView = ( ListView ) findViewById( R.id.club_list);
//List to hold club objects
        List clubList = new ArrayList();
//forloop to initialize club objects
        for (int i = 0; i < values.length; i++) {
            clubList.add(new Club(values[i],clubPics[i]));
        }
        clubListView.setAdapter( new ClubListAdapterWithCache(ctx, R.layout.picture_list, clubList ));
    }


    //// CLUB OBJECT
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

    ///// CACHE for clubs used for efficiency
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
    /////////////// Custom Adapter for clubs listview
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
                convertView = ( RelativeLayout ) convertView;
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

}