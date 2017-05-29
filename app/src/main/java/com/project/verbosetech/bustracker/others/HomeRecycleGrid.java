package com.project.verbosetech.bustracker.others;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.project.verbosetech.bustracker.R;
import com.project.verbosetech.bustracker.models.Student;

import java.util.List;

/**
 * Created by this pc on 12-05-17.
 */

public class HomeRecycleGrid extends RecyclerView.Adapter<HomeRecycleGrid.MyHolder>{

    public RecyclerView re;
    private List<Student> dataSet ;
    public Context context=null;
    VenueAdapterClickCallbacks venueAdapterClickCallbacks;

    public class MyHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView class_section;
        TextView status;
        ImageView image;

        public MyHolder(View itemView)
        {
            super(itemView);
            this.name = (TextView) itemView.findViewById(R.id.student_name);
            this.class_section = (TextView) itemView.findViewById(R.id.class_section);
            this.status=(TextView)itemView.findViewById(R.id.status);
            this.image=(ImageView)itemView.findViewById(R.id.student_image);
        }
    }

    public HomeRecycleGrid(Context c, List<Student> data, VenueAdapterClickCallbacks venueAdapterClickCallback)
    {

        this.dataSet = data;
        this.venueAdapterClickCallbacks=venueAdapterClickCallback;
        context=c;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_home_card, parent, false);
        MyHolder myNewsHolder=new MyHolder(view);
        re = (RecyclerView) parent.findViewById(R.id.card_grid);
        return myNewsHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, final int position) {

        TextView name = holder.name;
        TextView class_sec = holder.class_section;
        TextView status=holder.status;
        ImageView image=holder.image;
        name.setText(dataSet.get(position).getName());
//        String p=dataSet.get(position).urlToImage;
//
//        if(p!=null) {
//            setImageOnCard(context,image,p);
//        }
        class_sec.setText(dataSet.get(position).getClass_section());
        status.setText(dataSet.get(position).getStatus());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                venueAdapterClickCallbacks.onCardClick(dataSet.get(position).getName());

            }
        });

        Glide.with(context)
                .load("http://media.gettyimages.com/photos/male-high-school-student-portrait-picture-id98680202?s=170667a")
                .dontAnimate()
                .bitmapTransform(new RoundedCornersTransformation(context,10, 0))
                .into(image);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }

    public interface VenueAdapterClickCallbacks {
        void onCardClick( String p);

    }


}
