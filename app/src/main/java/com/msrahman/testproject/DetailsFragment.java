package com.msrahman.testproject;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.msrahman.testproject.utils.Appconstant;

/**
 * Created by Shahin on 6/3/2017.
 */

public class DetailsFragment  extends BaseFragment {

    Context con;

    private TextView tvDetails;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.details, container, false);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        con = getActivity();

        tvDetails = (TextView) getView().findViewById(R.id.tvDetails);

        tvDetails.setText(Appconstant.contact.getName()+" \n"+Appconstant.contact.getAge()+" \n"+Appconstant.contact.getLat()+" \n"+Appconstant.contact.getLon());


    }

}
