package com.iiitdmk.solasta.ui.events;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.iiitdmk.solasta.R;

public class EventsDetailsActivity extends AppCompatActivity {

    private ImageView mImageView;
    private TextView mTvTitle;
    private TextView mTvDescription;
    private TextView mTvRules;
    private TextView mTvPrizes;
    private LinearLayout llDetailed1;
    private LinearLayout llDetailed2;
    private LinearLayout llDetailed3;
    private LinearLayout llDetailed4;
    private Button btnRegisterEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_details);

        Intent intent = getIntent();
        String event_title = intent.getStringExtra("event_name");
        String event_description = intent.getStringExtra("event_description");
        String event_imageUrl = intent.getStringExtra("event_imageUrl");
        String event_rules = intent.getStringExtra("event_rules");
        String event_prize = intent.getStringExtra("event_prize");
        String form_url = intent.getStringExtra("event_register");

        mImageView = (ImageView) findViewById(R.id.ivDetailImage);
        mTvTitle = (TextView) findViewById(R.id.tvDetailedTitle);
        mTvDescription = (TextView) findViewById(R.id.tvDetailedDescription);
        mTvRules = (TextView) findViewById(R.id.tvDetailedRules);
        mTvPrizes = (TextView) findViewById(R.id.tvDetailedPrizes);

        llDetailed1 = (LinearLayout) findViewById(R.id.llDetailed1);
        llDetailed2 = (LinearLayout) findViewById(R.id.llDetailed2);
        llDetailed3 = (LinearLayout) findViewById(R.id.llDetailed3);
        llDetailed4 = (LinearLayout) findViewById(R.id.llDetailed4);

        btnRegisterEvent = (Button) findViewById(R.id.btnRegisterEvent);
        btnRegisterEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri webpage = Uri.parse(form_url);
                Log.i("AAAA", form_url);
                Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
                startActivity(myIntent);
            }
        });
        if (event_imageUrl != null) {
            Glide.with(this)
                    .load(event_imageUrl)
                    .into(mImageView);
        }

        if (event_title != null) {
            llDetailed1.setVisibility(View.VISIBLE);
            mTvTitle.setText(event_title);
        } else {
            llDetailed1.setVisibility(View.GONE);
        }

        if (event_description != null) {
            llDetailed2.setVisibility(View.VISIBLE);
            mTvDescription.setText(event_description);
        } else {
            llDetailed2.setVisibility(View.GONE);
        }

        if (event_rules != null) {
            llDetailed3.setVisibility(View.VISIBLE);
            mTvRules.setText(event_rules);
        } else {
            llDetailed3.setVisibility(View.GONE);
        }

        if (event_prize != null) {
            llDetailed4.setVisibility(View.VISIBLE);
            mTvPrizes.setText("₹ " + event_prize + "/-");
        } else {
            llDetailed4.setVisibility(View.GONE);
        }

        Toast.makeText(this, event_title, Toast.LENGTH_SHORT).show();

    }
}
