package com.iiitdmk.solasta.ViewHolders;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

public abstract class EventsBaseViewHolder extends RecyclerView.ViewHolder {

    private int mCurrentPosition;
    public EventsBaseViewHolder(View itemView) {
        super(itemView);
    }
    protected abstract void clear();
    public void onBind(int position) {
        mCurrentPosition = position;
        clear();
    }
    public int getCurrentPosition() {
        return mCurrentPosition;
    }

}
