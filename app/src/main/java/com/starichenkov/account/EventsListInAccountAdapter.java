package com.starichenkov.account;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.starichenkov.RoomDB.Events;
import com.starichenkov.eventmap.R;

import java.util.ArrayList;
import java.util.List;

public class EventsListInAccountAdapter extends RecyclerView.Adapter<EventsListInAccountAdapter.MyViewHolder> {

    private List<Events> events;
    private List<Events> eventsCopy;
    private Context mContext;
    private int mResourse;
    private static final String TAG = "MyLog";
    int lastPosition = -1;

    private OnEventListener mOnEventListener;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        // each data item is just a string in this case
        TextView textNameEvent;
        TextView textTypeEvent;
        TextView textAddressEvent;
        ImageView imageEvent;
        LinearLayout llBookMark;
        Button buttonEdit;
        Button buttonDelete;

        OnEventListener onEventListener;

        public MyViewHolder(View view, OnEventListener onEventListener) {
            super(view);
            textNameEvent = (TextView) view.findViewById(R.id.textNameEvent);
            textTypeEvent = (TextView) view.findViewById(R.id.textTypeEvent);
            textAddressEvent = (TextView) view.findViewById(R.id.textAddressEvent);
            imageEvent = (ImageView) view.findViewById(R.id.imageEvent);
            llBookMark = (LinearLayout) view.findViewById(R.id.llBookMark);

            this.onEventListener = onEventListener;
            itemView.setOnClickListener(this);
        }

        public void loadImage(Uri uri){
            //Picasso.with(context).load(url).placeholder(R.drawable.placeholder).error(R.drawable.error_ph).into(this.image);
            Picasso.get().load(uri).error(R.drawable.event_map_logo).into(imageEvent);
        }

        @Override
        public void onClick(View v) {
            onEventListener.onEventClick(getAdapterPosition());
        }
    }

    public interface OnEventListener{
        void onEventClick(int position);
    }

    //Constructor
    public EventsListInAccountAdapter(Context mContext, int resource, List<Events> events){
        this.mContext = mContext;
        this.mResourse = resource;
        this.events = events;
        this.eventsCopy = new ArrayList<Events>(events);
        this.mOnEventListener = (OnEventListener)mContext;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public EventsListInAccountAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(mResourse, parent, false);
        return new MyViewHolder(view, mOnEventListener);

    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Events event = events.get(position);
        holder.textNameEvent.setText(event.nameEvent);
        holder.textTypeEvent.setText(event.typeEvent);
        holder.textAddressEvent.setText(event.addressEvent);
        //holder.imageEvent.setImageURI(Uri.parse(event.photoEvent));
        //Picasso.with(mContext).load(event.photoEvent).into(imageView);
        Log.d(TAG, "event.nameEvent: " + event.nameEvent);
        holder.loadImage(Uri.parse(event.photoEvent));
        setAnimation(holder.llBookMark, position);

    }

    private void setAnimation(View itemView, int position) {
        //Log.d(TAG, "position: " + position);
        //Log.d(TAG, "lastPosition: " + lastPosition);
        if (position > lastPosition){
            //Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.load_down_anim);
            Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            //Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.layout_animation_fall_down);
            itemView.startAnimation(animation);
            lastPosition = position;
        }/*else{
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.load_up_anim);
            itemView.startAnimation(animation);
            lastPosition = position;
        }*/

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return events.size();
    }

    public void filter(String text) {
        events.clear();
        if(text.isEmpty()){
            events.addAll(eventsCopy);
        } else{
            text = text.toLowerCase();
            for(Events event: eventsCopy){
                if(event.nameEvent.toLowerCase().contains(text)){
                    events.add(event);
                }
            }
        }
        notifyDataSetChanged();
    }

}
