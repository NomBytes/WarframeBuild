package com.example.nombi.warframebuild;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nombi.warframebuild.loadout.Mod;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LoadoutCreateFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoadoutCreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoadoutCreateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String MOD_SELECTED = "selected_mod";

    Button mod1Button;
    Button mod2Button;
    Button mod3Button;
    Button mod4Button;

    Fragment FRAG = new WarframeFragment();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            Fragment modFragment = new ModDetailFragment();
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, modFragment)
                    .commit();

        }
    };

    public LoadoutCreateFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoadoutCreateFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoadoutCreateFragment newInstance(String param1, String param2) {
        LoadoutCreateFragment fragment = new LoadoutCreateFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_loadout_create, container, false);



        Button warframe_b = (Button) view.findViewById(R.id.warframe_button);

        mod1Button = (Button) view.findViewById(R.id.mod1);

        mod1Button.setOnClickListener(onClickListener);

        mod2Button = (Button) view.findViewById(R.id.mod2);

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();

        warframe_b.setOnClickListener( new View.OnClickListener(){//calls warframe fragment list.
                public void onClick(View v){
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, FRAG)
                            .commit();

                }
            }
        );

        return view;
    }
    /*
    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            // Set article based on argument passed in
            updateView((Mod) args.getSerializable(MOD_SELECTED));
        }
    }

    public void updateView(Mod m) {
        if (m != null) {
            Log.d("udate view editfragment","course not null");
            mod1Button.setText(m.getMyName());

            //mCourseIdEditText.setText(course.getCourseId());
           // mCourseShortDescEditText .setText(course.getShortDescription());
            //mCourseLongDescEditText.setText(course.getLongDescription());
           // mCoursePrereqsEditText.setText(course.getPreReqs());

        }

        //bun.putString("id",mCourseIdTextView.toString());

    }
    */


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    /*

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
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
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
