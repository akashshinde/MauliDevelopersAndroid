package com.example.maulidevelopers.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maulidevelopers.app.R;

/**
 * Created by Akash on 14/06/14.
 */
public class ContactUsFragment extends Fragment{

    public ContactUsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private static final String ARG_SECTION_NUMBER = "section_number";
    public static ContactUsFragment newInstance(int sectionNumber) {
        ContactUsFragment fragment = new ContactUsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.contact_us, container, false);
        final TextView phn1 = (TextView) rootView.findViewById(R.id.textView4);
        final TextView phn2 = (TextView) rootView.findViewById(R.id.textView5);
        phn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+" +phn1.getText().toString().trim()));
                startActivity(callIntent );
            }
        });
        phn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+" +phn2.getText().toString().trim()));
                startActivity(callIntent );
            }
        });
        return rootView;

    }
}
