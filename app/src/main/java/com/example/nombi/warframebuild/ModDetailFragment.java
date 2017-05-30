package com.example.nombi.warframebuild;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.nombi.warframebuild.loadout.Mod;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ModDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ModDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ModDetailFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    public static final String MOD_SELECTED = "selected_mod";
    public static final String LEVEL = "level_selected";

    private TextView mModName;
    private TextView mModLevel;
    private TextView mModCost;
    private TextView mAttribute;
    private SeekBar  mLevelScroll;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Button changePolarity;
    Button changeMod;
    Button clearMod;
    Button backToLoadout;
    Button confirmB;
    int mLevel;
    private OnFragmentInteractionListener mListener;
    ModFragment modFrag = new ModFragment();
    LoadoutCreateFragment createFrag = new LoadoutCreateFragment();
    private Mod mMod;

    public ModDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ModDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ModDetailFragment newInstance(String param1, String param2) {
        ModDetailFragment fragment = new ModDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    /**
     * where we are getting our mods.
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getArguments().putSerializable(LEVEL,mLevel);
        if (getArguments() != null) {
            mMod = (Mod)getArguments().getSerializable(MOD_SELECTED);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_mod_detail, container, false);

        changePolarity = (Button) v.findViewById(R.id.change_polarity_button);

        mModName = (TextView) v.findViewById(R.id.mod_name);
        mModLevel = (TextView) v.findViewById(R.id.level_number);
        mModCost = (TextView) v.findViewById(R.id.mod_cost_text);
        mAttribute = (TextView) v.findViewById(R.id.mod_effect_1);
        mLevelScroll = (SeekBar) v.findViewById(R.id.level_seekbar);
        confirmB = (Button) v.findViewById(R.id.confirm_button);
        backToLoadout = (Button) v.findViewById(R.id.back_to_loadout_button);

        backToLoadout.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                LoadoutCreateFragment myFragment = (LoadoutCreateFragment)getActivity()
                        .getSupportFragmentManager()
                        .findFragmentByTag(LoadoutCreateFragment.CREATE_TAG);

                //Don't make any changes and go back.
                //createFrag.setArguments(getArguments());
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, myFragment)
                        .commit();
            }
        });

        if(mMod != null){
            mLevel = mMod.getMyBaseCost();

            mModName.setText(mMod.getMyName());
            mModCost.setText("Cost = " + Integer.toString(mMod.getMyBaseCost()));
            mLevelScroll.setMax(mMod.getMyMaxLevel());
            mLevelScroll.setProgress(0);

            mLevelScroll.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    mModLevel.setText(String.valueOf(progress));
                    mModCost.setText("Cost " + String.valueOf(
                            mMod.calculateCost(mMod.getMyPolarity(),progress)
                    ));

                    mLevel = mMod.calculateCost(mMod.getMyPolarity(),progress);

                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                    mLevel = mMod.calculateCost(mMod.getMyPolarity(),seekBar.getProgress());


                }

            });

            getArguments().putInt(LEVEL,mLevel);

            confirmB.setOnClickListener(new View.OnClickListener(){
                public void onClick(View view){


                    //Set the slot's mod to this one.
                    //getActivity().getSupportFragmentManager().popBackStackImmediate();

                    //getActivity().getSupportFragmentManager().popBackStack();

                    LoadoutCreateFragment myFragment = (LoadoutCreateFragment)getActivity()
                            .getSupportFragmentManager()
                            .findFragmentByTag(LoadoutCreateFragment.CREATE_TAG);

                    getArguments().putSerializable(LoadoutCreateFragment.MOD_CONFIRMED,mMod);
                    //createFrag.setArguments(getArguments());
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, myFragment)
                            .commit();

                }
            });


        }

        changeMod = (Button) v.findViewById(R.id.change_mod_button);
        clearMod = (Button) v.findViewById(R.id.clear_mod_button);
        backToLoadout = (Button) v.findViewById(R.id.back_to_loadout_button);

        changePolarity.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Open up list of polarities...
                //Each option represents a different polarity when checked
                //will change the correct polarity array and the image on the screen. (by swtich?)
            }
        });

        changeMod.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)
                        .replace(R.id.fragment_container, modFrag)
                        .commit();
            }
        });
        return v;
    }

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
        if (context instanceof addLoadout) {
            mListener = (addLoadout) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement addLoadout");
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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CourseDetailFragment.
     */
    public static ModDetailFragment getModDetailFragment (
            Mod m) {
        ModDetailFragment fragment = new ModDetailFragment();
        //fragment.updateView(warframe);
        //Bundle args = new Bundle();
       //args.putSerializable(MOD_SELECTED, m);
        //fragment.setArguments(args);
        return fragment;
    }
    /**
     *
     */
}
