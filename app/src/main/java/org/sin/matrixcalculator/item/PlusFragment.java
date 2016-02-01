package org.sin.matrixcalculator.item;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.sin.matrixcalculator.CaculatingActivity;
import org.sin.matrixcalculator.MatrixMode.Matrix;
import org.sin.matrixcalculator.R;

/**
 * Created by Sin on 2016/1/30.
 */
public class PlusFragment extends Fragment implements View.OnClickListener{
    private Intent intent;
    private Button bn;
    private View view;
    private Matrix matrix;
    private EditText x;
    private EditText y;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.plus_fragment,container,false);
        x = (EditText)view.findViewById(R.id.editText);
        y = (EditText)view.findViewById(R.id.editText2);
        bn = (Button)view.findViewById(R.id.button);

        bn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
       matrix = new Matrix(Integer.parseInt(x.getText().toString().trim()),
               Integer.parseInt(y.getText().toString().trim()),1);

        intent = new Intent(getActivity(), CaculatingActivity.class);
        intent.putExtra("Matrix_INFO",matrix);
        startActivity(intent);
        Log.d("hello-git","dd");
    }
}
