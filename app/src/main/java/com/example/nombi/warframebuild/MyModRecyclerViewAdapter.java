package com.example.nombi.warframebuild;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nombi.warframebuild.ModFragment.OnListModInteractionListener;
//import com.example.nombi.warframebuild.dummy.DummyContent.DummyItem;
import com.example.nombi.warframebuild.loadout.Mod;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {link ModItem} and makes a call to the
 * specified {link personalLoadouts}.
 */
public class MyModRecyclerViewAdapter extends RecyclerView.Adapter<MyModRecyclerViewAdapter.ViewHolder> {

    private final List<Mod> mValues;
    private final OnListModInteractionListener mListener;

    /**
     * MModRecyclerViewAdapter
     * @param items
     * @param listener
     */
    public MyModRecyclerViewAdapter(List<Mod> items, OnListModInteractionListener listener) {
        mValues = items;
        mListener = listener;
    }

    /**
     * shows contents fo mod.
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_mod, parent, false);
        return new ViewHolder(view);
    }

    /**
     * allows interaction.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mIdView.setText(mValues.get(position).getMyName());
        holder.mContentView.setText(Integer.toString(mValues.get(position).getMyBaseCost()));//only seems to work if it's a string

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.OnListModInteractionListener(holder.mItem);
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
        public Mod mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        /**
         * gives string.
         * @return
         */
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
