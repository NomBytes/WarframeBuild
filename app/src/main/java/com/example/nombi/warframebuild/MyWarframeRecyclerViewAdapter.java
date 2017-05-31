package com.example.nombi.warframebuild;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nombi.warframebuild.WarframeFragment.OnListFragmentInteractionListener;
import com.example.nombi.warframebuild.character.Warframe;
//import com.example.nombi.warframebuild.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Warframe} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 */
public class MyWarframeRecyclerViewAdapter extends RecyclerView.Adapter<MyWarframeRecyclerViewAdapter.ViewHolder> {
    /**
     * valyes being pass
     */
    private final List<Warframe> mValues;
    /**
     * allows interaction.
     */
    private final OnListFragmentInteractionListener mListener;

    /**
     * creates adaptor for interaction.
     * @param items
     * @param listener
     */
    public MyWarframeRecyclerViewAdapter(List<Warframe> items, OnListFragmentInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_warframe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getMyCharName());
        holder.mContentView.setText(mValues.get(position).getMyType());//this is where the n/a is coming from


        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    Log.d("inside onclick of mView", "mlistenre is not null");
                    mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public Warframe mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.warframe_char_name);
            mContentView = (TextView) view.findViewById(R.id.warframe_armor);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
