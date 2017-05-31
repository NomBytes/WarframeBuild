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

import com.example.nombi.warframebuild.character.CharacterDB;
import com.example.nombi.warframebuild.character.Warframe;
//import com.example.nombi.warframebuild.dummy.DummyContent;
//import com.example.nombi.warframebuild.dummy.DummyContent.DummyItem;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class WarframeFragment extends Fragment {

    private static ArrayList<Warframe> warframes = new ArrayList<Warframe>();

    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";

    public static final String WAR_SELECTED = "warframe_selected";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    private RecyclerView mRecyclerView;

    private static final String Warframe_URL
            = "http://cssgate.insttech.washington.edu/~_450bteam13/warframes.php?";

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WarframeFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static WarframeFragment newInstance(int columnCount) {
        WarframeFragment fragment = new WarframeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * default fragments
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        for (Warframe warframe : CharacterDB.Warframes) {
            warframes.add(warframe);
        }

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /**
     * creates view.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_warframe_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView = (RecyclerView) view;
            if (warframes.isEmpty()) {
                for (Warframe warframe : CharacterDB.Warframes) {
                    warframes.add(warframe);
                }
            }
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            DownloadWarframeTask task = new DownloadWarframeTask();
            task.execute(new String[]{Warframe_URL});

            //mRecyclerView.setAdapter(new MyWarframeRecyclerViewAdapter(warframes,mListener));


        }
        return view;
    }

    /**
     * attaches to activity
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement personalLoadouts");
        }
    }

    /**
     * detaches form activity.
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Warframe warframe);
    }

    /**
     * download warfreames
     */
    private class DownloadWarframeTask extends AsyncTask<String, Void, String> {

        /**
         * exutes url
         * @param urls
         * @return
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                try {
                    URL urlObject = new URL(url);
                    urlConnection = (HttpURLConnection) urlObject.openConnection();

                    InputStream content = urlConnection.getInputStream();

                    BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                    String s = "";
                    while ((s = buffer.readLine()) != null) {
                        response += s;
                    }

                } catch (Exception e) {
                    response = "Unable to download the list of courses, Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        /**
         * checks url and network and dowlload.s
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

            List<Warframe> courseList = new ArrayList<Warframe>();
            result = Warframe.parseCourseJSON(result, courseList);
            // Something wrong with the JSON returned.
            if (result != null) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            // Everything is good, show the list of courses.
            if (!courseList.isEmpty()) {
                //Log.d("POSTEXECUTE","list is not empty");

                mRecyclerView.setAdapter(new MyWarframeRecyclerViewAdapter(courseList, mListener));
            }
        }


    }
}

