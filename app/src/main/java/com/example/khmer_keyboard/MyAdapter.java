package com.example.khmer_keyboard;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

//    private List<View> getAllChildren(View v) {
//
//        if (!(v instanceof ViewGroup)) {
//            ArrayList<View> viewArrayList = new ArrayList<>();
//            viewArrayList.add(v);
//            return viewArrayList;
//        }
//
//        ArrayList<View> result = new ArrayList<>();
//
//        ViewGroup viewGroup = (ViewGroup) v;
//        for (int i = 0; i < viewGroup.getChildCount(); i++) {
//
//            View child = viewGroup.getChildAt(i);
//
//            //Do not add any parents, just add child elements
//            result.addAll(getAllChildren(child));
//        }
//        return result;
//    }




    private final String[] mDataset;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textView;
        public MyViewHolder(View v) {
            super(v);

            textView = v.findViewById(R.id.textVieww);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public MyAdapter(String[] myDataset) {

        mDataset = myDataset;
        Log.d("PIUKeyboard", "MyAdapter: "+mDataset.length);
    }

    // Create new views (invoked by the layout manager)
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.emoji_key, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element



        holder.textView.setText(mDataset[position]);

    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        Log.d("PIUKeyboard", "getItemCount:"+mDataset.length);

        return mDataset.length;
    }
}