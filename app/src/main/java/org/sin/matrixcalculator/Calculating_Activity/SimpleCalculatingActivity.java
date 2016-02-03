package org.sin.matrixcalculator.Calculating_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.sin.matrixcalculator.MatrixMode.Matrix;
import org.sin.matrixcalculator.R;


public class SimpleCalculatingActivity extends Activity implements View.OnClickListener{
    private TextView title;
    private TextView show;
    private EditText input;
    private Button submit;
    private Intent intent;

    private int x = 0;
    private int y = 0;
    private Matrix matrix1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculating_activity);
        title = (TextView)findViewById(R.id.simple_title);
        show = (TextView)findViewById(R.id.show);
        input = (EditText)findViewById(R.id.input);
        submit = (Button)findViewById(R.id.submit);

        intent = getIntent();
        matrix1 = (Matrix) intent.getSerializableExtra("Matrix_INFO");
        title.setText(matrix1.getMethod());

        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (x == matrix1.getX()){
            input.setText("数组越界");
            input.setFocusable(false);
            input.setFocusableInTouchMode(false);
            System.out.print(matrix1.show());
            return;
        }
        Log.d("See X Y",x+"  "+y);

        matrix1.matrix[x][y] = Double.parseDouble(input.getText().toString().trim());
        input.setText("");
        y++;
        show.setText(matrix1.show());

        if (y >= matrix1.getY()){
            y = 0;
            x++;
        }


    }
}
