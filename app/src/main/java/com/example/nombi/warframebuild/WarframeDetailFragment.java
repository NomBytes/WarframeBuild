package com.example.nombi.warframebuild;


import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nombi.warframebuild.character.Warframe;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class WarframeDetailFragment extends Fragment {

    public final static String WARFRAME_SELECTED = "warframe_selected";

    private TextView mName;
    private TextView mHealth;
    private TextView mShields;
    private TextView mArmor;
    private TextView mPower;
    private TextView mSprintSpeed;

    private TextView mPwrRange;
    private TextView mPwrStrength;
    private TextView mPwrDuration;
    private TextView mPwrEfficency;

    private Warframe mWarframeItem;
   // private OnFragmentInteractionListener mListener;


    public WarframeDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_warframe_detail, container, false);


        mName = (TextView) view.findViewById((R.id.warframe_char_name));
        mHealth = (TextView) view.findViewById((R.id.warframe_health));
        mShields = (TextView) view.findViewById((R.id.warframe_shields));
        mArmor = (TextView) view.findViewById((R.id.warframe_armor));
        mPower = (TextView) view.findViewById((R.id.warframe_power));
        mSprintSpeed = (TextView) view.findViewById((R.id.warframe_sprint_speed));
        mPwrRange = (TextView) view.findViewById((R.id.warframe_pwr_range));
        mPwrStrength = (TextView) view.findViewById((R.id.warframe_pwr_strength));
        mPwrDuration = (TextView) view.findViewById((R.id.warframe_pwr_duration));
        mPwrEfficency = (TextView) view.findViewById((R.id.warframe_pwr_efficency));

        mName.setText("testing");
        if (mWarframeItem != null) {
            mName.setText(mWarframeItem.getMyCharName());
            mHealth.setText("Health: " + mWarframeItem.getMyHealth());
            mShields.setText("Shields: " + mWarframeItem.getMyShields());
            mArmor.setText("Armor: " + mWarframeItem.getMyArmor());
            mPower.setText("Power: " + mWarframeItem.getMyPower());
            mSprintSpeed.setText("Speed: " + mWarframeItem.getMySprintSpeed());
            mPwrRange.setText("Power Range: " + mWarframeItem.getMyPwrRange());
            mPwrStrength.setText("Power Strength: " + mWarframeItem.getMyPwrStrength());
            mPwrDuration.setText("Power Duration: " + mWarframeItem.getMyPwrDuration());
            mPwrEfficency.setText("Power Efficency: " + mWarframeItem.getMyPwrEfficency());
        }

        return view;
    }

    public void updateView(Warframe warframe) {
        if (warframe != null) {
            mName.setText(warframe.getMyCharName());
            mHealth.setText("Health: " + warframe.getMyHealth());
            mShields.setText("Shields: " + warframe.getMyShields());
            mArmor.setText("Armor: " + warframe.getMyArmor());
            mPower.setText("Power: " + warframe.getMyPower());
            mSprintSpeed.setText("Speed: " + warframe.getMySprintSpeed());
            mPwrRange.setText("Power Range: " + warframe.getMyPwrRange());
            mPwrStrength.setText("Power Strength: " + warframe.getMyPwrStrength());
            mPwrDuration.setText("Power Duration: " + warframe.getMyPwrDuration());
            mPwrEfficency.setText("Power Efficency: " + warframe.getMyPwrEfficency());
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWarframeItem = (Warframe)
                    getArguments().getSerializable(WARFRAME_SELECTED);
        }
    }


    @Override
    public void onStart() {
        super.onStart();

        //During startup, check if there are arguments passed to the fragment.
        //onStart is a good place to do this because the layout has already been
        //applied to the fragment at this point so we can safely call the method
        //below that sets the article text.
        Bundle args = getArguments();
        if (args != null) {
            //Set article based on argument passed in
            updateView((Warframe) args.getSerializable(WARFRAME_SELECTED));
        }
    }
    /*
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    */

   /* public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }*/
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment CourseDetailFragment.
     */
    public static WarframeDetailFragment getWarframeDetailFragment(
            Warframe warframe) {
        WarframeDetailFragment fragment = new WarframeDetailFragment();
        //fragment.updateView(warframe);
        Bundle args = new Bundle();
        args.putSerializable(WARFRAME_SELECTED, warframe);
        fragment.setArguments(args);
        return fragment;
    }

}
