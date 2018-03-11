package com.example.amanmehta.rentals;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class MainHome extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private RecyclerView programmingList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Rooms");
        programmingList = (RecyclerView) findViewById(R.id.recyclerView);
        programmingList.setLayoutManager(new LinearLayoutManager(this));
        programmingList.setNestedScrollingEnabled(false);
        programmingList.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(this, R.dimen.item_offset);
        programmingList.addItemDecoration(itemDecoration);

    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Rooms,RoomViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Rooms, RoomViewHolder>(
                Rooms.class,
                R.layout.layout_rooms,
                RoomViewHolder.class,
                mDatabase

        ) {
            @Override
            protected void populateViewHolder(RoomViewHolder viewHolder, Rooms model, int position) {

                viewHolder.post_noOfRooms.setText(model.getNoOfRooms());
                viewHolder.post_Rate.setText(model.getRate());
                Picasso.with(MainHome.this).load(model.getImage1()).into(viewHolder.post_image);
            }
        };
        programmingList.setAdapter(firebaseRecyclerAdapter);

    }


    public static class RoomViewHolder extends RecyclerView.ViewHolder{
        TextView post_address,post_Rate,post_pref,post_noOfRooms;
        ImageView post_image;
        public RoomViewHolder(View itemView) {
            super(itemView);

            post_Rate = (TextView) itemView.findViewById(R.id.txtRate);
            post_noOfRooms = (TextView) itemView.findViewById(R.id.txtNoOfRooms);
            post_image = (ImageView) itemView.findViewById(R.id.MainImage);
        }


    }

    public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

        private int mItemOffset;

        public ItemOffsetDecoration(int itemOffset) {
            mItemOffset = itemOffset;
        }

        public ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
            this(context.getResources().getDimensionPixelSize(itemOffsetId));
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                                   RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
        }
    }


}
