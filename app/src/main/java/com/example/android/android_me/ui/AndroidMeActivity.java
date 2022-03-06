package com.example.android.android_me.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

public class AndroidMeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);
        if (savedInstanceState == null) {
            HeadPartFragment headPartFragment = new HeadPartFragment();
            BodyPartFragment bodyPartFragmentFragment = new BodyPartFragment();
            LegPartFragment legPartFragment = new LegPartFragment();

            headPartFragment.setmImageIds(AndroidImageAssets.getHeads());
            bodyPartFragmentFragment.setmImageIds(AndroidImageAssets.getBodies());
            legPartFragment.setmImageIds(AndroidImageAssets.getLegs());

            int headIndex = getIntent().getIntExtra("headIndex", 0);
            int bodyIndex = getIntent().getIntExtra("bodyIndex", 0);
            int legIndex = getIntent().getIntExtra("legIndex", 0);

            headPartFragment.setmListIndex(headIndex);
            bodyPartFragmentFragment.setmListIndex(bodyIndex);
            legPartFragment.setmListIndex(legIndex);

            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().add(R.id.head_container, headPartFragment)
                    .add(R.id.body_container, bodyPartFragmentFragment)
                    .add(R.id.legs_container, legPartFragment)
                    .commit();
        }
    }
}
