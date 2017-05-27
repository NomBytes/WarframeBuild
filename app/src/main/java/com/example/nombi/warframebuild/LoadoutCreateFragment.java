package com.example.nombi.warframebuild;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.nombi.warframebuild.loadout.WarframeLoadout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link createLoadoutInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LoadoutCreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoadoutCreateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String LOADOUT_SELECTED = "selected_load";

    WarframeLoadout mLoad;

    Button create_button;

    private Button mod1Button;
    private Button mod2Button;
    private Button mod3Button;
    private Button mod4Button;
    private Button mod5Button;
    private Button mod6Button;
    private Button mod7Button;
    private Button mod8Button;
    private Button mod9Button;
    private Button mod10Button;


    Fragment FRAG = new WarframeFragment();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private createLoadoutInteractionListener mListener;

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

    /**
     * where we retrive loadout
     * @param savedInstanceState
     */
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

        create_button = (Button) view.findViewById(R.id.create);

        mod1Button = (Button) view.findViewById(R.id.mod1);
        mod2Button = (Button) view.findViewById(R.id.mod2);
        mod3Button = (Button) view.findViewById(R.id.mod3);
        mod4Button = (Button) view.findViewById(R.id.mod4);
        mod5Button = (Button) view.findViewById(R.id.mod5);
        mod6Button = (Button) view.findViewById(R.id.mod6);
        mod7Button = (Button) view.findViewById(R.id.mod7);
        mod8Button = (Button) view.findViewById(R.id.mod8);
        mod9Button = (Button) view.findViewById(R.id.mod9);
        mod10Button= (Button) view.findViewById(R.id.mod10);

        mod1Button.setOnClickListener(onClickListener);
        mod2Button.setOnClickListener(onClickListener);
        mod3Button.setOnClickListener(onClickListener);
        mod4Button.setOnClickListener(onClickListener);
        mod5Button.setOnClickListener(onClickListener);
        mod6Button.setOnClickListener(onClickListener);
        mod7Button.setOnClickListener(onClickListener);
        mod8Button.setOnClickListener(onClickListener);
        mod9Button.setOnClickListener(onClickListener);
        mod10Button.setOnClickListener(onClickListener);

        //create_button


        FloatingActionButton floatingActionButton = (FloatingActionButton)
                getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();
        create_button.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){


            }
        });



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

/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onCreateLoadFragInteraction(uri);
        }
    }
    */




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof createLoadoutInteractionListener) {
            mListener = (createLoadoutInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement createLoadoutInteractionListener");
        }
    }


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
    public interface createLoadoutInteractionListener {
        // TODO: Update argument type and name
        void onCreateLoadFragInteraction(WarframeLoadout w);
    }
}
