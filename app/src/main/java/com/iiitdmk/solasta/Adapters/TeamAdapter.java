package com.iiitdmk.solasta.Adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.iiitdmk.solasta.R;
import com.iiitdmk.solasta.ViewHolders.BaseViewHolder;
import com.iiitdmk.solasta.data.EventsData;
import com.iiitdmk.solasta.data.TeamData;
import com.iiitdmk.solasta.ui.events.EventsDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeamAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private static final String TAG = "TeamAdapter";
    private List<TeamData> mTeamList;
    private Context mContext;
//    private ArrayList<String> imagesList = new ArrayList<>();

    public TeamAdapter(List<TeamData> teamList, Context mcontext) {
        mTeamList = teamList;
        mContext = mcontext;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        String baseUrl = "http://www.solasta.org.in";
//
//        imagesList.add(baseUrl+"/static/media/anshProf.f3a69362.jpg");
//        imagesList.add(baseUrl+"/static/media/gayathri.c618161e.jpg");
//        imagesList.add(baseUrl+"/static/media/gauss.7d4365a0.JPG");
//        imagesList.add(baseUrl+"/static/media/Karthik.aa6f930d.jpg");
//        imagesList.add(baseUrl+"-");
//        imagesList.add(baseUrl+"/static/media/mrunal.07d76f04.JPG");
//        imagesList.add(baseUrl+"/static/media/mukesh.a14a72ca.jpeg");
//        imagesList.add(baseUrl+"/static/media/naveen.0d4c698f.jpeg");
//        imagesList.add(baseUrl+"/static/media/Niharika.1c614648.jpeg");
//        imagesList.add(baseUrl+"/static/media/Rohitha.e9a68bca.jpg");
//        imagesList.add(baseUrl+"/static/media/venki.f0146290.jpeg");




//        imagesList.add(R.drawable.u_karthik);
//        imagesList.add(R.drawable.u_lasya);
//        imagesList.add(R.drawable.u_mrunal);
//        imagesList.add(R.drawable.u_naveen);
//        imagesList.add(R.drawable.u_niharika);
//        imagesList.add(R.drawable.u_rohitha);
//        imagesList.add(R.drawable.u_venkatesh);

        return new TeamAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.team_single_member_layout, parent, false));

    }

    @Override
    public int getItemCount() {
        if (mTeamList != null && mTeamList.size() > 0) {
            return mTeamList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<TeamData> teamDataList) {
        mTeamList.addAll(teamDataList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.ivTeamMemberImage)
        ImageView ivTeamMemberImage;
        @BindView(R.id.ivTeamMemberName)
        TextView ivTeamMemberName;
        @BindView(R.id.ivTeamMemberEmail)
        TextView ivTeamMemberEmail;
        @BindView(R.id.ivTeamMemberRole)
        TextView ivTeamMemberRole;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            ivTeamMemberImage.setImageDrawable(null);
            ivTeamMemberName.setText("");
            ivTeamMemberEmail.setText("");
            ivTeamMemberRole.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);
            final TeamData mTeamMember = mTeamList.get(position);

//            Glide.with(itemView.getContext())
//                    .load(imagesList.get(position))
//                    .placeholder(R.drawable.ic_people_black)
//                    .into(ivTeamMemberImage);
//


            Glide.with(itemView.getContext())
                    .load(mTeamMember.getImageUrl())
                    .placeholder(R.drawable.ic_people_black)
                    .into(ivTeamMemberImage);


            if (mTeamMember.getName() != null) {
                ivTeamMemberName.setText(mTeamMember.getName());
            }
            if (mTeamMember.getEmail() != null) {
                ivTeamMemberEmail.setText(mTeamMember.getEmail());
            }
            if (mTeamMember.getRole() != null) {
                ivTeamMemberRole.setText(mTeamMember.getRole());
            }
            itemView.setOnClickListener(v -> {
                if (mTeamMember.getName() != null) {
                    try {
                        Toast.makeText(mContext, "Team Member Detail (coming soon)", Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Log.e(TAG, "onClick: Image url is not correct");
                        Toast.makeText(mContext, "Team Member Detail failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
