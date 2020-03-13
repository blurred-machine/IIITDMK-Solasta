package com.iiitdmk.solasta.ui.aboutUs;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.iiitdmk.solasta.R;

import java.util.List;

public class AboutUsFragment extends Fragment implements View.OnClickListener {

    public static String FACEBOOK_URL = "https://www.facebook.com/solastaiiitdm";
    public static String FACEBOOK_PAGE_ID = "solastaiiitdm";


    ImageView ivWebsite;
    ImageView ivFacebook;
    ImageView ivInstagram;
    ImageView ivAboutUsMap;
    ImageView ivTrailerPlayer;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_about_us, container, false);


        ivWebsite = (ImageView) root.findViewById(R.id.ivAboutUsWebsite);
        ivAboutUsMap = (ImageView) root.findViewById(R.id.ivAboutUsMap);
        ivFacebook = (ImageView) root.findViewById(R.id.ivAboutUsFacebook);
        ivInstagram = (ImageView) root.findViewById(R.id.ivAboutUsInstagram);
        ivTrailerPlayer = (ImageView) root.findViewById(R.id.ivTrailerPlayer);
        ivWebsite.setOnClickListener(this);
        ivAboutUsMap.setOnClickListener(this);
        ivFacebook.setOnClickListener(this);
        ivInstagram.setOnClickListener(this);
        ivTrailerPlayer.setOnClickListener(this);

        /*Intent intent = new Intent(getContext(), YoutubePlayerActivity.class);
        intent.putExtra("videoKey", "rPcui8gFlm4");
        startActivity(intent);*/

        return root;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivWebsite) {
            Uri webpage = Uri.parse("http://iiitk.ac.in/home");
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        } else if (v.getId() == R.id.ivAboutUsMap) {


            Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                    Uri.parse("http://maps.google.com/maps?daddr=15.7618,78.0364"));
            startActivity(intent);

        } else if (v.getId() == R.id.ivFacebook) {
            Uri webpage = Uri.parse(getFacebookPageURL(getContext()));
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        } else if (v.getId() == R.id.ivInstagram) {
            getInstagramUrlOpen("solasta_iiitdmk");
        } else if (v.getId() == R.id.ivTrailerPlayer) {
            playYoutubeVideo("c1WFwx-Xxv8");

        }
    }

    private void playYoutubeVideo(String videoKey) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoKey));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + videoKey));
        try {
            getContext().startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            getContext().startActivity(webIntent);
        }
    }

    public String getFacebookPageURL(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;
            if (versionCode >= 3002850) { //newer versions of fb app
                return "fb://facewebmodal/f?href=" + FACEBOOK_URL;
            } else { //older versions of fb app
                return "fb://page/" + FACEBOOK_PAGE_ID;
            }
        } catch (PackageManager.NameNotFoundException e) {
            return FACEBOOK_URL; //normal web url
        }
    }

    private boolean isIntentAvailable(Context ctx, Intent intent) {
        final PackageManager packageManager = ctx.getPackageManager();
        List<ResolveInfo> list = packageManager.queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    private void getInstagramUrlOpen(String username) {
        String url = "http://instagram.com/_u/" + username;
        String web_url = "http://instagram.com/" + username;
        Uri uri = Uri.parse(url);
        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
        insta.setPackage("com.instagram.android");

        if (isIntentAvailable(getContext(), insta)) {
            startActivity(insta);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(web_url)));
        }
    }
}