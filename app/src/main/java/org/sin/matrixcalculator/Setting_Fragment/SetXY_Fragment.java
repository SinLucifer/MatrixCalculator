package org.sin.matrixcalculator.Setting_Fragment;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.sin.matrixcalculator.Calculating_Activity.ComplexCalculatingActivity;
import org.sin.matrixcalculator.Calculating_Activity.SimpleCalculatingActivity;
import org.sin.matrixcalculator.MainActivity;
import org.sin.matrixcalculator.MatrixMode.Matrix;
import org.sin.matrixcalculator.R;


public class SetXY_Fragment extends Fragment implements View.OnClickListener{
    private String mArgument;

    private Intent intent;
    private View view;
    private TextView title;
    private EditText x;
    private EditText y;
    private Button bn;
    private Matrix matrix;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.set_fragment,container,false);

        title = (TextView)view.findViewById(R.id.setxy_title);
        x = (EditText)view.findViewById(R.id.set_x);
        y = (EditText)view.findViewById(R.id.set_y);
        bn = (Button)view.findViewById(R.id.button);

        Bundle bundle = getArguments();
        if (bundle != null){
            mArgument = bundle.getString(MainActivity.ARGUMENT);
            title.setText(mArgument);
        }

        bn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        if ((x.getText().toString().trim()).equals("") ||
                (y.getText().toString().trim()).equals("")){
            Toast.makeText(getActivity(),"请检查您的输入",Toast.LENGTH_SHORT).show();
            return;
        }
       matrix = new Matrix(Integer.parseInt(x.getText().toString().trim()),
               Integer.parseInt(y.getText().toString().trim()),mArgument);

        if (mArgument.equals(MainActivity.PLUS) || mArgument.equals(MainActivity.SUBTRACT) ||
                mArgument.equals(MainActivity.MULTIPLE) || mArgument.equals(MainActivity.DIVIDE)){
            intent = new Intent(getActivity(), SimpleCalculatingActivity.class);
        }else {
            intent = new Intent(getActivity(), ComplexCalculatingActivity.class);
        }


        intent.putExtra("Matrix_INFO",matrix);
        startActivity(intent);
    }
}
