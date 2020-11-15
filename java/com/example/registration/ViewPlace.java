package com.example.registration;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.registration.Model.Photos;
import com.example.registration.Model.PlaceDetail;
import com.example.registration.Remote.IGoogleAPIService;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

import static com.example.registration.Common.currentResult;

public class ViewPlace extends AppCompatActivity {

    ImageView photo;
    RatingBar ratingBar;
    TextView  opening_hours,place_address,place_name;
    Button btnViewOnMap;

    IGoogleAPIService mService;
    PlaceDetail mPlace;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_place);

        mService = Common.getGoogleAPIService();


        photo = (ImageView) findViewById(R.id.photo);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        place_address = (TextView) findViewById(R.id.place_address);
        place_name = (TextView) findViewById(R.id.place_name);
        opening_hours = (TextView) findViewById(R.id.place_open_hour);


        //Empty all view
        place_name.setText("");
        place_address.setText("");
        opening_hours.setText("");

        if (currentResult.getPhotos() != null && currentResult.getPhotos().length > 0) {
            Picasso.with(this)
                    .load(getPhotoOfPlace(currentResult.getPhotos()[0].getPhoto_reference(), 1000))
                    .placeholder(R.drawable.ic_baseline_image_24)
                    .error(R.drawable.ic_baseline_error_24)
                    .into(photo);
        }

        //Rating
        if (currentResult.getRating() != null && !TextUtils.isEmpty(currentResult.getRating())) {
            ratingBar.setRating(Float.parseFloat(currentResult.getRating()));
        } else {
            ratingBar.setVisibility(View.GONE);
        }
        //Opening hours
        if (currentResult.getOpening_hours() != null) {
            opening_hours.setText("Open now :" + currentResult.getOpening_hours().getOpen_now());
        } else {
            opening_hours.setVisibility(View.GONE);
        }
        if (Common.currentResult.getFormatted_address() != null) {
            place_address.setText(Common.currentResult.getFormatted_address());
        } else {
            place_address.setVisibility(View.GONE);
        }
        if (Common.currentResult.getName() != null) {
            place_name.setText(Common.currentResult.getName());
        } else {
            place_name.setVisibility(View.GONE);
        }

    }

    private String getPlaceDetailUrl(String place_id) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/details/json");
        url.append("?place_id"+place_id);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();
    }

    private String getPhotoOfPlace(String photo_reference,int maxWidth) {
        StringBuilder url = new StringBuilder("https://maps.googleapis.com/maps/api/place/photo");
        url.append("?maxwidth="+maxWidth);
        url.append("&photoreference="+photo_reference);
        url.append("&key="+getResources().getString(R.string.browser_key));
        return url.toString();

    }


}
