package com.example.nombi.warframebuild;

import android.content.Context;
import android.os.Bundle;
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
import android.widget.Toast;

import com.example.nombi.warframebuild.character.Warframe;
import com.example.nombi.warframebuild.loadout.Mod;
import com.example.nombi.warframebuild.loadout.WarframeLoadout;

import java.net.URLEncoder;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link addLoadout} interface
 * to handle interaction events.
 * Use the {@link LoadoutCreateFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoadoutCreateFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public final static String SELECTED_BUTTON = "button_selected";
    public static final String LOADOUT_SELECTED = "selected_load";
    public static final String MOD_CONFIRMED = "MOD_CONFIRMED";
    public static final String CREATE_TAG ="CREATE_SELECTED";

    public static final String ADD_URL =
            "http://cssgate.insttech.washington.edu/~_450bteam13/addWarF.php?";

    private String author;

    WarframeLoadout mLoad;
    Warframe mWarframe;


    Button create_button;
    EditText loadoutText;
    TextView capacityText;
    CheckBox reactorCheckBox;

    private Button mod1Button;
    private Button mod2Button;
    private Button warframeB;


    private Integer buttonId;
    TextView Author;



    Fragment FRAG = new WarframeFragment();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private addLoadout mListener;
    /**
     * onlick function for  mod buttons.
     */
    private View.OnClickListener onClickListener = new View.OnClickListener() {

        @Override
        public void onClick(final View v) {
            /*
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
            */

            //selectedButton = (Button)v.findViewById(v.getId());
            getArguments().putSerializable(SELECTED_BUTTON, new Integer(v.getId()));
            //Log.d("v.getId",v.getId());
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
            mLoad = (WarframeLoadout) getArguments().getSerializable(LOADOUT_SELECTED);

            buttonId = (Integer)getArguments().getSerializable(SELECTED_BUTTON);
            author = (String)getArguments().getString("email");
        }
    }

    /**
     * creaitng for user to see.
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_loadout_create, container, false);



        warframeB = (Button) view.findViewById(R.id.warframe_button);
        Author = (TextView)view.findViewById(R.id.author);

        if(author != null){

            Author.setText(author);
            if(mLoad == null){
                mLoad = new WarframeLoadout(author);
                getArguments().putSerializable(LOADOUT_SELECTED,mLoad);
            }


        }




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

        /*
       if(mWarframe != null){
           warframeB.setText(mWarframe.getMyCharName());
       }
       */

       /*
        if(mMod != null && selectedButton != null){
            selectedButton.setText(mMod.getMyName());
        }
        if(buttonId != null){
            selectedButton = (Button)view.findViewById(buttonId);
        }
        */
        getArguments().putSerializable(LOADOUT_SELECTED,mLoad);

        FloatingActionButton floatingActionButton = (FloatingActionButton)
                getActivity().findViewById(R.id.fab);
        floatingActionButton.hide();
        create_button.setOnClickListener( new View.OnClickListener(){
            public void onClick(View v){
                if(mWarframe != null) {
                    String url = buildCreatURL(v);
                    mListener.addLoadout(new WarframeLoadout(mWarframe,
                            loadoutText.getText().toString(), Author.toString()), url);
                }else{
                    Toast.makeText(getActivity().getApplicationContext(), "Warframe is null",
                    Toast.LENGTH_LONG).show();
                }



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

        warframeB.setOnClickListener( new View.OnClickListener(){//calls warframe fragment list.
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

    /**
     * activity comes back.
     */
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

            author = (String)args.getString("email");
            buttonId = (Integer)args.getSerializable(SELECTED_BUTTON);

            updateView((WarframeLoadout) args.getSerializable(LOADOUT_SELECTED),
                    (Mod)args.getSerializable(MOD_CONFIRMED),
            args.getInt(ModDetailFragment.LEVEL));

        }
    }

    /**
     * updatrs
     * @param w
     * @param m
     * @param level
     */
    public void updateView(WarframeLoadout w,Mod m,int level) {

        mWarframe = w.getMyWarframe();

        if (w != null) {
            warframeB.setText(w.getMyWarframe().getMyCharName());
            //mMod = m;
            if(buttonId == null){
                Log.d("buttonId","button is null");

            }
            //Log.d("buttonId value",buttonId.toString());
            if(buttonId != null ) {
                switch (buttonId) {
                    case R.id.mod1:
                        //w.changeMod(m, level, 1);
                        if(m != null){
                            mod1Button.setText(m.getMyName());
                            w.changeMod(m,level,0);
                        }
                        Mod[] arr = w.getMyMods();
                        if(arr[1] != null){
                            //Toast.makeText()
                            mod2Button.setText(arr[1].getMyName());
                       }
                        // handle button A click;
                        break;
                    case R.id.mod2:
                       // w.changeMod(m, level, 1);
                        if(m != null){
                            mod2Button.setText(m.getMyName());
                            w.changeMod(m,level,1);
                        }

                        Mod[] arr2 = w.getMyMods();
                        if(arr2[0] != null){
                            mod1Button.setText(arr2[0].getMyName());

                        }
                        // handle button B click;
                        break;
                    default:
                        throw new RuntimeException("Unknow button ID");
                }
                Mod[] arr = w.getMyMods();
                if(arr[0] != null){

                }
            }


            //mod1Button.setText(m.getMyName());

            Author.setText(author);


            //mCourseIdEditText.setText(course.getCourseId());
           // mCourseShortDescEditText .setText(course.getShortDescription());
            //mCourseLongDescEditText.setText(course.getLongDescription());
           // mCoursePrereqsEditText.setText(course.getPreReqs());

        }

        //bun.putString("id",mCourseIdTextView.toString());

    }



/*
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.addLoadout(uri);
        }
    }
    */




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
    public interface addLoadout {
        // TODO: Update argument type and name
        void addLoadout(WarframeLoadout w, String url);
    }

    private String buildCreatURL(View v) {

        StringBuilder sb = new StringBuilder(ADD_URL);

        try {

            String email = Author.getText().toString();
            sb.append("email=");
            sb.append(email);


            String name = loadoutText.getText().toString();
            sb.append("&name=");
            sb.append(URLEncoder.encode(name, "UTF-8"));


            String warframe = warframeB.getText().toString();
            sb.append("&warframe=");
            sb.append(URLEncoder.encode(warframe, "UTF-8"));

            String mod1 = mod1Button.getText().toString();
            sb.append("&mod1=");
            sb.append(URLEncoder.encode(mod1, "UTF-8"));

            String mod2 = mod2Button.getText().toString();
            sb.append("&mod2=");
            sb.append(URLEncoder.encode(mod2, "UTF-8"));

            Log.i("createloadFragment", sb.toString());

        }
        catch(Exception e) {
            Toast.makeText(v.getContext(), "Something wrong with the url" + e.getMessage(), Toast.LENGTH_LONG)
                    .show();
        }
        return sb.toString();
    }
}
