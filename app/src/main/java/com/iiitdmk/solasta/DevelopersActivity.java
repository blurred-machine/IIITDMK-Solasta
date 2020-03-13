package com.iiitdmk.solasta;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iiitdmk.solasta.data.TeamData;

import java.util.ArrayList;

public class DevelopersActivity extends AppCompatActivity {

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionRef = mFirestore.collection("core_developer_team");

    ArrayList<TeamData> developersDataList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        getFirebaseTeamData();
    }

    private void getFirebaseTeamData() {

        collectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            TeamData teamData = documentSnapshot.toObject(TeamData.class);
                            developersDataList.add(new TeamData(teamData.getName(), teamData.getEmail(), teamData.getRole(), teamData.getImageUrl(), teamData.getLinkedin(), teamData.getGithub()));
                        }
                        setUp();
                        Log.i("TEAM DATAAAAAA", developersDataList.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(DevelopersActivity.this, "Team Data Not Found.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setUp(){



    }
}
