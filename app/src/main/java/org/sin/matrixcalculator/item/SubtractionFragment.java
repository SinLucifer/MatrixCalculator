package org.sin.matrixcalculator.item;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.sin.matrixcalculator.R;

/**
 * Created by Sin on 2016/1/30.
 */
public class SubtractionFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.subtraction_fragment,container,false);
    }
}