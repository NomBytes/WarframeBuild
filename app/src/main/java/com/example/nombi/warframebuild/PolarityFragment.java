package com.example.nombi.warframebuild;


import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.nombi.warframebuild.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PolarityFragment extends DialogFragment {


    public PolarityFragment() {
        // Required empty public constructor
    }

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.pick_polarity)
                .setItems(R.array.polarity_array, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // The 'which' argument contains the index position
                        // of the selected item
                        Resources res = getActivity().getResources();
                        String[] polarities = res.getStringArray(R.array.polarity_array);
                        Toast.makeText(getActivity(), "You chose " + polarities[which],
                                Toast.LENGTH_SHORT).show();

                    }
                });
        return builder.create();
    }

}
