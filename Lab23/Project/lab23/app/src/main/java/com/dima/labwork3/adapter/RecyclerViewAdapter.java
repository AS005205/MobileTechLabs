package com.dima.labwork3.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dima.labwork3.R;
import com.dima.labwork3.fragment.DetailsFragment;
import com.dima.labwork3.pojo.MovieDetails;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<MovieDetails> list;
    private Context context;
    private Fragment fragment;


    public RecyclerViewAdapter(Fragment fragment) {
        this.fragment = fragment;
        list = new ArrayList<>();
    }

    public void addData(List<MovieDetails> data) {
        list.addAll(data);
        notifyDataSetChanged();
    }

    public void setData(List<MovieDetails> data) {
        list.clear();
        list.addAll(data);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView titleTextView;
        TextView descriptionTextView;
        ImageView imageView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.item_title);
            imageView = itemView.findViewById(R.id.item_photo);
            descriptionTextView = itemView.findViewById(R.id.item_description);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            FragmentTransaction transaction =  fragment.getActivity().getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.host, DetailsFragment.newInstance((list.get(this.getAdapterPosition()).getId())));
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            transaction.addToBackStack(null);
            transaction.commit();
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ViewHolder viewHolder = new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item, parent, false));
        context = parent.getContext();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        MovieDetails item = list.get(position);
        holder.titleTextView.setText(item.getTitle());
        holder.descriptionTextView.setText(item.getType().concat(", ".concat(item.getYear())));
        Glide.with(context)
                .load(item.getPosterPath())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}