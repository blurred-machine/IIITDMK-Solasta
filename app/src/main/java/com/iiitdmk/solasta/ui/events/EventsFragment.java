package com.iiitdmk.solasta.ui.events;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;

import com.iiitdmk.solasta.DividerItemDecoration;


import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.iiitdmk.solasta.Adapters.EventsAdapter;
import com.iiitdmk.solasta.R;
import com.iiitdmk.solasta.data.EventsData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsFragment extends Fragment {


    @BindView(R.id.rvEventsMain)
    RecyclerView mRecyclerView;
    EventsAdapter mEventsAdapter;
    LinearLayoutManager mLayoutManager;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_events, container, false);

        mRecyclerView = (RecyclerView) root.findViewById(R.id.rvEventsMain);

        ButterKnife.bind(getActivity());
        setUp();
        return root;
    }

    private void setUp() {
        mLayoutManager = new LinearLayoutManager(getActivity());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        Drawable dividerDrawable = ContextCompat.getDrawable(getActivity(), R.drawable.divider_drawable);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(dividerDrawable));
        mEventsAdapter = new EventsAdapter(new ArrayList<>(), getActivity());
        prepareDemoContent();
    }

    private void prepareDemoContent() {

        new Handler().postDelayed(() -> {
            ArrayList<EventsData> mEventsList = new ArrayList<>();
            String[] events_titles = getResources().getStringArray(R.array.events_titles);
            String[] events_description = getResources().getStringArray(R.array.events_description);
            String[] events_rules = getResources().getStringArray(R.array.events_rules);
            String[] events_prize = getResources().getStringArray(R.array.events_prize);

            String[] events_images = getResources().getStringArray(R.array.events_images);

            for (int i = 0; i < events_titles.length; i++) {
                mEventsList.add(new EventsData(events_images[i], events_description[i], events_titles[i], events_rules[i], events_prize[i]));
            }
            mEventsAdapter.addItems(mEventsList);
            mRecyclerView.setAdapter(mEventsAdapter);
        }, 500);
    }


//    @Override
//    public void onEmptyViewRetryClick() {
//        prepareDemoContent();
//    }

}