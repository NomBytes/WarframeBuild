package com.example.nombi.warframebuild;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nombi.warframebuild.character.Warframe;


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


    LoadoutCreateFragment CREATE_LOADFRAG = new LoadoutCreateFragment();
    private Warframe mWarframeItem;
    Bundle args = getArguments();
   // private addLoadout mListener;
    private static final String ADD_URL =
            "http://cssgate.insttech.washington.edu/~_450bteam13/setWarframe.php";

    public WarframeDetailFragment() {
        // Required empty public constructor
    }

    /**
     * doing what evern needs to be done in when creating the view.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
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

        Button selectWarframe;
        selectWarframe = (Button) view.findViewById(R.id.select_warframe_button);
        Bundle args = new Bundle();
        selectWarframe.setOnClickListener( new View.OnClickListener(){//calls warframe fragment list.
                public void onClick(View v){

                    if(getArguments() != null) {
                        CREATE_LOADFRAG.setArguments(getArguments());
                    }
                    LoadoutCreateFragment myFragment = (LoadoutCreateFragment)getActivity().getSupportFragmentManager()
                            .findFragmentByTag(LoadoutCreateFragment.CREATE_TAG);
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .addToBackStack(null)
                            .replace(R.id.fragment_container, myFragment)
                            .commit();

                }
            }
        );


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

    /**
     * where we retrieve the warframe that we selected.
     * this allows us to get to view the information
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mWarframeItem = (Warframe)
                    getArguments().getSerializable(WARFRAME_SELECTED);
        }
    }


    /*

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
    */

    /*
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }
    */

   /* public interface addLoadout {
        void addLoadout(Uri uri);
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
        //Bundle args = new Bundle();
        //args.putSerializable(WARFRAME_SELECTED, warframe);
       // fragment.setArguments(args);
        return fragment;
    }

    private String buildCourseURL(View v) {

        StringBuilder sb = new StringBuilder(ADD_URL);

        try {


            String warframe = mWarframeItem.getMyCharName();
            sb.append("warframe=");
            sb.append(warframe);


            //String courseShortDesc = mCourseShortDescEditText.getText().toString();
            sb.append("&name=");
            //sb.append(URLEncoder.encode(courseShortDesc, "UTF-8"));


            Log.i("CourseAddFragment", sb.toString());


        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }


}
