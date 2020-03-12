package com.iiitdmk.solasta.ui.team;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.iiitdmk.solasta.Adapters.EventsAdapter;
import com.iiitdmk.solasta.Adapters.TeamAdapter;
import com.iiitdmk.solasta.DividerItemDecoration;
import com.iiitdmk.solasta.R;
import com.iiitdmk.solasta.data.EventsData;
import com.iiitdmk.solasta.data.TeamData;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.BindView;

public class TeamFragment extends Fragment {

    private FirebaseFirestore mFirestore = FirebaseFirestore.getInstance();
    CollectionReference collectionRef = mFirestore.collection("core_team_members");

    ArrayList<TeamData> teamDataList = new ArrayList<>();


    @BindView(R.id.rvTeamMembersMain)
    RecyclerView mRecyclerView;
    ProgressBar pbTeamMembers;
    TeamAdapter mTeamAdapter;
    LinearLayoutManager mLayoutManager;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_team, container, false);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.rvTeamMembersMain);
        pbTeamMembers = (ProgressBar) root.findViewById(R.id.pbTeamMembers);
        getFirebaseTeamData();
        return root;
    }

    private void getFirebaseTeamData() {

        collectionRef.get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                            TeamData teamData = documentSnapshot.toObject(TeamData.class);
                            teamDataList.add(new TeamData(teamData.getName(), teamData.getEmail(), teamData.getRole(), teamData.getImageUrl()));
                        }
                        setUp();
                        Log.i("TEAM DATAAAAAA", teamDataList.toString());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "Team Data Not Found.", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setUp() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.divider_drawable);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        mTeamAdapter = new TeamAdapter(new ArrayList<>(), getActivity());
        prepareDemoContent();
    }

    private void prepareDemoContent() {
        new Handler().postDelayed(() -> {
            Collections.sort(teamDataList, TeamData.teamMemberNameComparator);
            mTeamAdapter.addItems(teamDataList);
            mRecyclerView.setAdapter(mTeamAdapter);
            pbTeamMembers.setVisibility(View.GONE);
        }, 500);
    }
}