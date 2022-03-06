package com.example.android.android_me.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

import java.util.ArrayList;
import java.util.List;

public class BodyPartFragment extends Fragment {
    private static final String Tag= "Body fragment";
    private int mListIndex;
    public  static final String IMAGE_ID_LIST = "image_is";
    public  static final String LIST_IDX = "list_index";
    private List<Integer> mImageIds;
    public BodyPartFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mImageIds = savedInstanceState.getIntegerArrayList(IMAGE_ID_LIST);
            mListIndex = savedInstanceState.getInt(LIST_IDX);
        }
        View rootview = inflater.inflate(R.layout.fragment_body_part,container,false);
        ImageView imageView = (ImageView) rootview.findViewById(R.id.body_part_image_view);
        //imageView.setImageResource(AndroidImageAssets.getBodies().get(0));
        if (mImageIds != null) {
            imageView.setImageResource(AndroidImageAssets.getBodies().get(mListIndex));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (mListIndex < AndroidImageAssets.getBodies().size()-1) {
                        mListIndex++;
                    }
                    else {
                        mListIndex = 0;
                    }
                    imageView.setImageResource(AndroidImageAssets.getBodies().get(mListIndex));
                }
            });
        }
        else {
            Log.v(Tag, "This fragment has a null list of Image Id's");
        }
        return rootview;
    }
    public void setmListIndex(int index) {
        this.mListIndex = index;
    }
    public void setmImageIds(List<Integer> mImageIds) {
        this.mImageIds = mImageIds;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putIntegerArrayList(IMAGE_ID_LIST, (ArrayList<Integer>) mImageIds);
        outState.putInt(LIST_IDX, mListIndex);
    }
}
