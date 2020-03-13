package com.iiitdmk.solasta.ui.schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import com.iiitdmk.solasta.R;

public class ScheduleFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_schedule, container, false);

        PhotoView pv = root.findViewById(R.id.ivImageViewer);
        pv.setImageResource(R.drawable.ic_computer);
        Glide.with(getActivity())
                .load(R.drawable.schedule_main)
                .into(pv);

        return root;
    }


}