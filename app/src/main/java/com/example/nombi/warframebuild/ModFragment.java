package com.example.nombi.warframebuild;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;


import com.example.nombi.warframebuild.loadout.Mod;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListModInteractionListener}
 * interface.
 */
public class ModFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private static final String URL =
            "http://cssgate.insttech.washington.edu/~_450bteam13/ModList.php?";

    private int mColumnCount = 1;
    private OnListModInteractionListener mListener;
    private RecyclerView mRecycle;

    private ArrayList<Mod> modArrayList = new ArrayList<Mod>();


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ModFragment() {
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListModInteractionListener{

        void OnListModInteractionListener(Mod m);

    }

    @SuppressWarnings("unused")
    public static ModFragment newInstance(int columnCount) {
        ModFragment fragment = new ModFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * creates default.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /**
     * creating vixibility.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mod_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecycle= (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecycle.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecycle.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
            DownloadMods task = new DownloadMods();
            task.execute(new String[]{URL});
        }
        return view;
    }

    /**
     * attaches/
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListModInteractionListener) {
            mListener = (OnListModInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement personalLoadouts");
        }
    }

    /**
     * detatches
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * dowlnoading bods.
     */
    private class DownloadMods extends AsyncTask <String, Void, String> {

        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    java.net.URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to download the list of moD, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        /**
         * executes url
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.


            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }





            Log.d("result", result);
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            List<Mod> modList = new ArrayList<Mod>();
            result = Mod.parseCourseJSON(result, modList);
            // Something wrong with the JSON returned.
            if (result != null) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            // Everything is good, show the list of courses.
            if (!modList.isEmpty()) {
                //Log.d("POSTEXECUTE","list is not empty");
                mRecycle.setAdapter(new MyModRecyclerViewAdapter(modList, mListener));


            }


        }
    }


}
