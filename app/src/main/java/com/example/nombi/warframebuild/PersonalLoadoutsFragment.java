package com.example.nombi.warframebuild;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.example.nombi.warframebuild.Data.WarframeLoadoutDB;
import com.example.nombi.warframebuild.loadout.WarframeLoadout;

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
 * Activities containing this fragment MUST implement the {@link personalLoadouts}
 * interface.
 */
public class PersonalLoadoutsFragment extends Fragment {

    private WarframeLoadoutDB mloadoutDB;
    private List<String> mLoadoutList;

    private static final String ARG_COLUMN_COUNT = "column-count";

    private int mColumnCount = 1;
    public static final String TAG = "PERSONAL_LOADOUTS";
    private personalLoadouts mListener;
    private String mEmail;
    private String URL =
            "http://cssgate.insttech.washington.edu/~_450bteam13/UserLoadout.php?";

    private RecyclerView mRecyclerView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PersonalLoadoutsFragment() {
    }

    @SuppressWarnings("unused")
    public static PersonalLoadoutsFragment newInstance(int columnCount) {
        PersonalLoadoutsFragment fragment = new PersonalLoadoutsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * sets up fragment.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mEmail = getArguments().getString("email");
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    /**
     * sets up view.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_personalloadouts_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            mRecyclerView= (RecyclerView) view;
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            ConnectivityManager connMgr = (ConnectivityManager)
                    getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

            if(networkInfo != null && networkInfo.isConnected()){
                DownloadLoadouts task = new DownloadLoadouts();
                String u = buildUrl(view);
                task.execute(new String[] {u});
            }else{
                Toast.makeText(view.getContext(),
                        "No network connection available. Displaying locally stored data",
                        Toast.LENGTH_SHORT).show();

                if(mloadoutDB == null){
                    mloadoutDB = new WarframeLoadoutDB(getActivity());

                }

                if(mLoadoutList == null){
                    mLoadoutList = mloadoutDB.getLoadouts();
                    mRecyclerView.setAdapter(new MyPersonalLoadoutsRecyclerViewAdapter(mLoadoutList, mListener));
                }
            }
            //recyclerView.setAdapter(new MyPersonalLoadoutsRecyclerViewAdapter(DummyContent.ITEMS, mListener));

            //Log.d("URL",u);

        }
        return view;
    }

    /**
     * builds url for retrieving.
     * @param v
     * @return
     */
    public String buildUrl(View v){
        StringBuilder sb = new StringBuilder(URL);
        try{
            sb.append("email=");
            sb.append(mEmail);
        }catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }

    /**
     * attaches to attivity.
     * @param context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof personalLoadouts) {
            mListener = (personalLoadouts) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement personalLoadouts");
        }
    }

    /**
     * once its done.
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
    public interface personalLoadouts {
        void personalLoadouts(String names);
    }

    /**
     * leoads webservice
     */
    private class DownloadLoadouts extends AsyncTask<String,Void,String>{
        /**
         * connects to url
         * @param urls
         * @return
         */
        @Override
        protected String doInBackground(String... urls) {
            String response = "";
            HttpURLConnection urlConnection = null;
            for (String url : urls) {
                Log.d("Doanloadloadoutsback",urls.toString());
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
                    response = "Unable to download the list of ladouts Reason: "
                            + e.getMessage();
                } finally {
                    if (urlConnection != null)
                        urlConnection.disconnect();
                }
            }
            return response;
        }

        /**
         * downloads while its excuted.
         * @param result
         */
        @Override
        protected void onPostExecute(String result) {
            Log.d("result", result);
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            mLoadoutList = new ArrayList<String>();
            result = WarframeLoadout.parseCourseJSON(result, mLoadoutList);
            // Something wrong with the JSON returned.
            if (result != null) {
                Toast.makeText(getActivity().getApplicationContext(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            }

            // Everything is good, show the list of courses.
            if (!mLoadoutList.isEmpty()) {
                Log.d("POSTEXECUTE", "list is not empty");
                if(mloadoutDB == null){
                    mloadoutDB = new WarframeLoadoutDB(getActivity());
                }
                for(int i = 0; i < mLoadoutList.size();i++){
                    mloadoutDB.insertLoadout(mLoadoutList.get(i));
                }


                mRecyclerView.setAdapter(new MyPersonalLoadoutsRecyclerViewAdapter(mLoadoutList, mListener));
            }

        }
    }
}
