package com.example.nombi.warframebuild;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nombi.warframebuild.character.Warframe;
import com.example.nombi.warframebuild.loadout.Mod;
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
    public final static String WARFRAME_SELECTED = "warframe_selected";
    public final static String SELECTED_BUTTON = "button_selected";
    public static final String LOADOUT_SELECTED = "selected_load";
    private String author;

    WarframeLoadout mLoad = new WarframeLoadout("insert author here");
    Warframe mWarframe;


    Button create_button;
    EditText loadoutText;
    TextView capacityText;
    CheckBox reactorCheckBox;

    private Button mod1Button;
    private Button mod2Button;


    private Button selectedButton;
    private Integer buttonId;

    private Mod mMod;

    Fragment FRAG = new WarframeFragment();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private createLoadoutInteractionListener mListener;

    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            switch(v.getId())
            {
                case R.id.mod1:


// handle button A click;
                    break;
                case R.id.mod2:
// handle button B click;
                    break;
                default:
                    throw new RuntimeException("Unknow button ID");
            }

            //selectedButton = (Button)v.findViewById(v.getId());
            getArguments().putSerializable(SELECTED_BUTTON, v.getId());
            Fragment modFragment = new ModDetailFragment();
            modFragment.setArguments(getArguments());
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
            mWarframe = (Warframe)getArguments().getSerializable(WarframeDetailFragment.WARFRAME_SELECTED);
            mMod = (Mod)getArguments().getSerializable(ModDetailFragment.MOD_SELECTED);
            buttonId = (Integer)getArguments().getSerializable(SELECTED_BUTTON);
            author = (String)getArguments().getString("email");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_loadout_create, container, false);



        Button warframe_b = (Button) view.findViewById(R.id.warframe_button);
        TextView Author = (TextView)view.findViewById(R.id.author);
        /*
        if(author != null){
            Author.setText(author);

        }*/


        loadoutText = (EditText) view.findViewById(R.id.loadout_name);
        reactorCheckBox = (CheckBox) view.findViewById(R.id.reactor_checkbox);
        reactorCheckBox.setChecked(false);
        capacityText = (TextView) view.findViewById(R.id.capacity_text);
        capacityText.setText("Capacity: " + mLoad.calculateRemainingCapacity() + "/" + mLoad.getMyCapacity());

        create_button = (Button) view.findViewById(R.id.create);

        mod1Button = (Button) view.findViewById(R.id.mod1);
        mod2Button = (Button) view.findViewById(R.id.mod2);

        mod1Button.setOnClickListener(onClickListener);
        mod2Button.setOnClickListener(onClickListener);

       if(mWarframe != null){
           warframe_b.setText(mWarframe.getMyCharName());
       }
        if(mMod != null && selectedButton != null){
            selectedButton.setText(mMod.getMyName());
        }
        if(buttonId != null){
            selectedButton = (Button)view.findViewById(buttonId);
        }

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();
        create_button.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){


            }
        });

        reactorCheckBox.setOnClickListener( new View.OnClickListener() {
            public void onClick(View v) {
                boolean checked = ((CheckBox) v).isChecked();
                if (checked) {
                    mLoad.toggleReactor(true);
                    ((CheckBox) v).setChecked(true);
                    capacityText.setText("Capacity: " + mLoad.calculateRemainingCapacity() +
                            "/" + mLoad.getMyCapacity());
                } else {
                    if (mLoad.validateReactor(true)) {
                        mLoad.toggleReactor(false);
                        ((CheckBox) v).setChecked(false);
                        capacityText.setText("Capacity: " + mLoad.calculateRemainingCapacity() +
                                "/" + mLoad.getMyCapacity());
                    } else {
                        ((CheckBox) v).setChecked(false);
                    }
                }
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
            updateView((Mod) args.getSerializable(ModDetailFragment.MOD_SELECTED));
        }
    }

    public void updateView(Mod m) {

        if (m != null) {
            mMod = m;
            //Log.d("udate view editfragment","course not null");
            if(selectedButton != null) {
                selectedButton.setText(m.getMyName());
            }

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
