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

    public final String WARFRAME_SELECTED = "warframe_selected";

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

    private OnFragmentInteractionListener mListener;


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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }

}
