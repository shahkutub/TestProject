package com.msrahman.testproject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.msrahman.testproject.utils.Appconstant;


/**
 * Created by User on 7/20/2016.
 */
public class InfoListActivity extends AppCompatActivity implements OnFragmentInteractionListener{
    Context con;
    private ListView listInfo;
    InfoAdapter infoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        con = this;

        initUi();
    }

    private void initUi() {
        listInfo = (ListView)findViewById(R.id.listInfo);
        infoAdapter = new InfoAdapter(con);
        listInfo.setAdapter(infoAdapter);
        infoAdapter.notifyDataSetChanged();
    }

    private class InfoAdapter extends ArrayAdapter<Contact> {
        Context context;

        public InfoAdapter(Context context) {
            super(context, R.layout.raw_info, Appconstant.contacts);

            this.context = context;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = convertView;

            if (v == null) {
                final LayoutInflater vi = (LayoutInflater) context
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.raw_info, null);
            }

            if( position<Appconstant.contacts.size()){

                final Contact query = Appconstant.contacts.get(position);

                final TextView tvName = (TextView)v.findViewById(R.id.tvName);
                final TextView tvAge = (TextView)v.findViewById(R.id.tvAge);

                tvName.setText(query.getName());
                tvAge.setText(query.getAge());

                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        listInfo.setVisibility(View.GONE);
                        Appconstant.contact = query;
                        setContentFragment(new DetailsFragment(),false,"");
                    }
                });
            }


            return v;
        }
    }


    @Override
    public void setContentFragment(Fragment fragment, boolean addToBackStack, String title) {
        if (fragment == null) {
            return;
        }
        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);


        if (currentFragment != null && fragment.getClass().isAssignableFrom(currentFragment.getClass())) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.content_frame, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();


    }


    @Override
    public void addContentFragment(Fragment fragment, boolean addToBackStack,String title) {
        if (fragment == null) {
            return;
        }

        final FragmentManager fragmentManager = getSupportFragmentManager();
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.content_frame);

        if (currentFragment != null && fragment.getClass().isAssignableFrom(currentFragment.getClass())) {
            return;
        }

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.content_frame, fragment, fragment.getClass().getName());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
        fragmentManager.executePendingTransactions();

    }

    @SuppressLint("NewApi")
    @Override
    public void onBackPressed() {

        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        }
        else {
            super.onBackPressed();
        }


    }

}
