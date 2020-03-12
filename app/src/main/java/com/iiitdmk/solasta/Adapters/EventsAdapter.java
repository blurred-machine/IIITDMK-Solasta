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
import com.iiitdmk.solasta.ui.events.EventsDetailsActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsAdapter extends RecyclerView.Adapter<BaseViewHolder> {
    private static final String TAG = "EventsAdapter";
    private List<EventsData> mEventsList;
    private Context mContext;

    public EventsAdapter(List<EventsData> eventsList, Context mcontext) {
        mEventsList = eventsList;
        mContext = mcontext;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.events_single_event_layout, parent, false));

    }

    @Override
    public int getItemCount() {
        if (mEventsList != null && mEventsList.size() > 0) {
            return mEventsList.size();
        } else {
            return 1;
        }
    }

    public void addItems(List<EventsData> sportList) {
        mEventsList.addAll(sportList);
        notifyDataSetChanged();
    }


    public class ViewHolder extends BaseViewHolder {

        @BindView(R.id.ivEventThumbnailImage)
        ImageView ivEventThumbnailImage;
        @BindView(R.id.tvEventName)
        TextView tvEventName;
        @BindView(R.id.tvEventDescription)
        TextView tvEventDescription;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        protected void clear() {
            ivEventThumbnailImage.setImageDrawable(null);
            tvEventName.setText("");
            tvEventDescription.setText("");
        }

        public void onBind(int position) {
            super.onBind(position);
            final EventsData mEvent = mEventsList.get(position);
            if (mEvent.getImageUrl() != null) {
                Glide.with(itemView.getContext())
                        .load(mEvent.getImageUrl())
                        .into(ivEventThumbnailImage);
            }

            if (mEvent.getSubTitle() != null) {
                tvEventName.setText(mEvent.getSubTitle());
            }
            if (mEvent.getInfo() != null) {
                tvEventDescription.setText(mEvent.getInfo());
            }
            itemView.setOnClickListener(v -> {
                if (mEvent.getSubTitle() != null) {
                    try {

                    /*Intent intent = new Intent();
                    intent.setAction(Intent.ACTION_VIEW);
                    intent.addCategory(Intent.CATEGORY_BROWSABLE);
                    intent.setData(Uri.parse(mEvent.getImageUrl()));
                    itemView.getContext().startActivity(intent);*/

                        Intent intent = new Intent(mContext, EventsDetailsActivity.class);
                        intent.putExtra("event_name", mEvent.getSubTitle());
                        intent.putExtra("event_description", mEvent.getInfo());
                        intent.putExtra("event_imageUrl", mEvent.getImageUrl());
                        intent.putExtra("event_rules", mEvent.getRules());
                        intent.putExtra("event_prize", mEvent.getPrize());
                        mContext.startActivity(intent);

                    } catch (Exception e) {
                        Log.e(TAG, "onClick: Image url is not correct");
                        Toast.makeText(mContext, "Event Detail failed", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}