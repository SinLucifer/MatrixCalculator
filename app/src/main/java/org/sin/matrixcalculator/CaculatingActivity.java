package org.sin.matrixcalculator;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.sin.matrixcalculator.MatrixMode.Matrix;

/**
 * Created by Sin on 2016/1/31.
 */
public class CaculatingActivity extends Activity{
    private TextView t1;
    private TextView t2;
    private Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caculating_activity);
        t1 = (TextView)findViewById(R.id.show);
        t2 = (TextView)findViewById(R.id.show2);
        intent = getIntent();
        Matrix matrix1 = (Matrix) intent.getSerializableExtra("Matrix_INFO");
        Log.d("Sin!!!!",matrix1.getX()+""+matrix1.getY());
        t1.setText(matrix1.getX() + "");
        t2.setText(matrix1.getY() + "");
    }
}
