package com.example.android.android_me;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.android_me.data.AndroidImageAssets;
import com.example.android.android_me.ui.AndroidMeActivity;
import com.example.android.android_me.ui.BodyPartFragment;
import com.example.android.android_me.ui.HeadPartFragment;
import com.example.android.android_me.ui.LegPartFragment;
import com.example.android.android_me.ui.MasterListFragment;

public class MainActivity extends AppCompatActivity implements MasterListFragment.OnImageClickListener {
    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    private boolean mTwoPane;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (findViewById(R.id.android_me_linear_layout) != null) {
            mTwoPane = true;
            if (savedInstanceState == null) {
                Button button = (Button) findViewById(R.id.next_button);
                button.setVisibility(View.GONE);
                GridView gridView = (GridView) findViewById(R.id.images_grid_view);
                gridView.setNumColumns(2);
                HeadPartFragment headPartFragment = new HeadPartFragment();
                BodyPartFragment bodyPartFragmentFragment = new BodyPartFragment();
                LegPartFragment legPartFragment = new LegPartFragment();

                headPartFragment.setmImageIds(AndroidImageAssets.getHeads());
                bodyPartFragmentFragment.setmImageIds(AndroidImageAssets.getBodies());
                legPartFragment.setmImageIds(AndroidImageAssets.getLegs());

                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().add(R.id.head_container, headPartFragment)
                        .add(R.id.body_container, bodyPartFragmentFragment)
                        .add(R.id.legs_container, legPartFragment)
                        .commit();
            }
        }
        else {
            mTwoPane = false;
        }
    }

    @Override
    public void onImageSelected(int position) {
        Toast.makeText(this, "Position clicked = "+position, Toast.LENGTH_SHORT).show();
        int bodyPartNumber = position / 12;
        int listIndex = position - bodyPartNumber*12;
        if(mTwoPane) {
            HeadPartFragment newHeadPartFragment = new HeadPartFragment();
            BodyPartFragment newBodyPartFragment = new BodyPartFragment();
            LegPartFragment newLegPartFragment = new LegPartFragment();
            switch (bodyPartNumber) {
                case 0:
                    newHeadPartFragment.setmImageIds(AndroidImageAssets.getHeads());
                    newHeadPartFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.head_container,newHeadPartFragment)
                            .commit();
                    break;
                case 1:
                    newBodyPartFragment.setmImageIds(AndroidImageAssets.getBodies());
                    newBodyPartFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.body_container,newBodyPartFragment)
                    .commit();
                    break;
                case 2:
                    newLegPartFragment.setmImageIds(AndroidImageAssets.getLegs());
                    newLegPartFragment.setmListIndex(listIndex);
                    getSupportFragmentManager().beginTransaction().
                            replace(R.id.legs_container,newLegPartFragment)
                            .commit();
                    break;
                default:
                    break;
            }
        }else {
            switch (bodyPartNumber) {
                case 0:
                    headIndex = listIndex;
                    break;
                case 1:
                    bodyIndex = listIndex;
                    break;
                case 2:
                    legIndex = listIndex;
                    break;
                default:
                    break;
            }

            Bundle bundle = new Bundle();
            bundle.putInt("headIndex", headIndex);
            bundle.putInt("bodyIndex", bodyIndex);
            bundle.putInt("legIndex", legIndex);
            final Intent intent = new Intent(this, AndroidMeActivity.class);
            intent.putExtras(bundle);
            //startActivity(intent);
            Button button = (Button) findViewById(R.id.next_button);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(intent);
                }
            });
        }
    }
}
