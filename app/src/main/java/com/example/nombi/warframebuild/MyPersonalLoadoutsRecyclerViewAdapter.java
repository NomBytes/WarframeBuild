package com.example.nombi.warframebuild;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nombi.warframebuild.PersonalLoadoutsFragment.personalLoadouts;


import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link String} and makes a call to the
 * specified {@link personalLoadouts}.
 */
public class MyPersonalLoadoutsRecyclerViewAdapter extends RecyclerView.Adapter<MyPersonalLoadoutsRecyclerViewAdapter.ViewHolder> {

    private final List<String> mValues;
    private final PersonalLoadoutsFragment.personalLoadouts mListener;

    public MyPersonalLoadoutsRecyclerViewAdapter(List<String> items, PersonalLoadoutsFragment.personalLoadouts listener) {
        mValues = items;
        mListener = listener;
    }

    /**
     * creates view
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_personalloadouts, parent, false);
        return new ViewHolder(view);
    }

    /**
     * displays interaciton and allows content to show.
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        //holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position));

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    mListener.personalLoadouts(holder.mItem);
                }
            }
        });
    }

    /**
     * gives size
     * @return
     */
    @Override
    public int getItemCount() {
        return mValues.size();
    }

    /**
     * holder for objexts
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mIdView;
        public final TextView mContentView;
        public String mItem;

        /**
         * creates view
         * @param view
         */
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = (TextView) view.findViewById(R.id.id);
            mContentView = (TextView) view.findViewById(R.id.content);
        }

        /**
         * to string.
         * @return
         */
        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
