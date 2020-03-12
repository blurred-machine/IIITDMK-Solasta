package com.iiitdmk.solasta.ui.home;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.snackbar.Snackbar;
import com.iiitdmk.solasta.R;

import java.util.List;

public class HomeFragment extends Fragment implements View.OnClickListener {

    public static String FACEBOOK_URL = "https://www.facebook.com/solastaiiitdm";
    public static String FACEBOOK_PAGE_ID = "solastaiiitdm";

    ImageView ivPaymentQR;
    ImageView ivCallAdmin;
    ImageView ivWebsite;
    ImageView ivFacebook;
    ImageView ivInstagram;
    ImageView ivSolastaMainLogo;
    Button btnCulturalReg;
    Button btnTechnicalReg;
    VideoView vvTrailerVideo;

    MediaController mController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        ivPaymentQR = (ImageView) root.findViewById(R.id.ivPaymentQR);
        ivCallAdmin = (ImageView) root.findViewById(R.id.ivCallAdmin);
        ivWebsite = (ImageView) root.findViewById(R.id.ivWebsite);
        ivFacebook = (ImageView) root.findViewById(R.id.ivFacebook);
        ivInstagram = (ImageView) root.findViewById(R.id.ivInstagram);
        ivSolastaMainLogo = (ImageView) root.findViewById(R.id.ivSolastaMainLogo);
        btnCulturalReg = (Button) root.findViewById(R.id.btnCulturalReg);
        btnTechnicalReg = (Button) root.findViewById(R.id.btnTechnicalReg);
        vvTrailerVideo = (VideoView) root.findViewById(R.id.vvTrailerVideo);

        mController = new MediaController(getContext());
        mController.setAnchorView(vvTrailerVideo);
        vvTrailerVideo.setMediaController(mController);

        vvTrailerVideo.setVideoPath("android.resource://com.iiitdmk.solasta/"+R.raw.trailer_main);
        vvTrailerVideo.start();

        ivPaymentQR.setOnClickListener(this);
        ivCallAdmin.setOnClickListener(this);
        ivWebsite.setOnClickListener(this);
        ivFacebook.setOnClickListener(this);
        ivInstagram.setOnClickListener(this);
        ivSolastaMainLogo.setOnClickListener(this);
        btnCulturalReg.setOnClickListener(this);
        btnTechnicalReg.setOnClickListener(this);


        return root;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.ivPaymentQR) {
            Toast.makeText(getActivity(), "QR Code Payment", Toast.LENGTH_SHORT).show();
        } else if (v.getId() == R.id.ivCallAdmin) {
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "9894322678"));
            startActivity(intent);
        } else if (v.getId() == R.id.ivWebsite) {
            Uri webpage = Uri.parse("http://iiitk.ac.in/home");
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        } else if (v.getId() == R.id.ivFacebook) {
            Uri webpage = Uri.parse(getFacebookPageURL(getContext()));
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        } else if (v.getId() == R.id.ivInstagram) {
            getInstagramUrlOpen("solasta_iiitdmk");
        } else if (v.getId() == R.id.ivSolastaMainLogo) {
            Uri webpage = Uri.parse("http://www.solasta.org.in/");
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        }



        else if (v.getId() == R.id.btnCulturalReg) {
            Uri webpage = Uri.parse("https://forms.gle/1V35agZ3NK8AvVc19");
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
        } else if (v.getId() == R.id.btnTechnicalReg) {
            Uri webpage = Uri.parse("https://forms.gle/hJ2Ct2pZUUQDUx7n9");
            Intent myIntent = new Intent(Intent.ACTION_VIEW, webpage);
            startActivity(myIntent);
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

    private void getFacebookUrlOpen(String username) {
        String url = "http://facebook.com/_u/" + username;
        String web_url = "http://facebook.com/" + username;
        Uri uri = Uri.parse(url);
        Intent insta = new Intent(Intent.ACTION_VIEW, uri);
        insta.setPackage("com.facebook.katana");

        if (isIntentAvailable(getContext(), insta)) {
            startActivity(insta);
        } else {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(web_url)));
        }
    }
}