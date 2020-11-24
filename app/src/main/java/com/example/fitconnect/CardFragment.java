package com.example.fitconnect;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CardFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CardFragment extends Fragment {
    private static final String ARG_COUNT = "param1";
    private Integer counter;
    public CardFragment() {
        // Required empty public constructor
    }
    public static CardFragment newInstance(Integer counter) {
        CardFragment fragment = new CardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COUNT, counter);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            counter = getArguments().getInt(ARG_COUNT);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if(counter == 0){
            return inflater.inflate(R.layout.profile_profile_tab, container, false);
        }else if(counter == 1){
            return inflater.inflate(R.layout.profile_upcoming_tab, container, false);
        }else if(counter == 2){
            return inflater.inflate(R.layout.profile_attended_tab, container, false);
        }

        return inflater.inflate(R.layout.error, container, false);
    }
    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //if statements are necessary so the code doesnt break trying to find an element on a different view
        if(counter==0) {
            TextView textView_location = view.findViewById(R.id.textView_p_location);
            TextView textView_aboutMe = view.findViewById(R.id.textView_p_aboutMe);
            TextView textView_interests = view.findViewById(R.id.textView_p_interests);
            textView_aboutMe.setText(CurrentUser.getCurrentUser().getAboutMe());
            textView_interests.setText(CurrentUser.getCurrentUser().getPreferredActivities().toString());
            textView_location.setText(CurrentUser.getCurrentUser().getCityState());
        }

    }
}