package org.sin.matrixcalculator.Calculating_Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.sin.matrixcalculator.MainActivity;
import org.sin.matrixcalculator.MatrixMode.Matrix;
import org.sin.matrixcalculator.R;


public class SimpleCalculatingActivity extends Activity {
    private TextView title;
    private TextView show;
    private TextView show2;
    private TextView result;
    private EditText input;
    private EditText input2;
    private Button submit;
    private Button submit2;
    private Button equal;
    private Intent intent;

    private int x = 0;
    private int y = 0;
    private int x2 = 0;
    private int y2 = 0;
    private String METHOD;

    private Matrix matrix1;
    private Matrix matrix2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_calculating_activity);
        title = (TextView)findViewById(R.id.simple_title);
        show = (TextView)findViewById(R.id.show);
        show2 = (TextView)findViewById(R.id.show2);
        result = (TextView)findViewById(R.id.result);

        input = (EditText)findViewById(R.id.input);
        input2 = (EditText)findViewById(R.id.input2);

        submit = (Button)findViewById(R.id.submit);
        submit2 = (Button)findViewById(R.id.submit2);
        equal = (Button)findViewById(R.id.equal);

        intent = getIntent();
        matrix1 = (Matrix) intent.getSerializableExtra("Matrix_INFO");
        METHOD = matrix1.getMethod();

        title.setText(METHOD);

        selectMethod(METHOD);

        submit.setOnClickListener(new First_Matrix_OnClickListener());
        submit2.setOnClickListener(new Second_Matrix_OnClickListener());
        equal.setOnClickListener(new Equal_OnclickListener());
    }

    private void selectMethod(String method){
        if (method.equals(MainActivity.PLUS)){
            matrix2 = new Matrix(matrix1.getX(),matrix1.getY(),method);
        }else if (method.equals(MainActivity.SUBTRACT)){
            matrix2 = new Matrix(matrix1.getX(),matrix1.getY(),method);
        }else if (method.equals(MainActivity.MULTIPLE)){
            matrix2 = new Matrix(matrix1.getY(),matrix1.getX(),method);
        }else if (method.equals(MainActivity.DIVIDE)){
            matrix2 = new Matrix(matrix1.getY(),matrix1.getX(),method);
        }
    }

    private class First_Matrix_OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (x == matrix1.getX()){
                input.setText("数组越界");
                input.setFocusable(false);
                input.setFocusableInTouchMode(false);
                System.out.print(matrix1.show());
                return;
            }

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

    private class Second_Matrix_OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (x2 == matrix2.getX()){
                input2.setText("数组越界");
                input2.setFocusable(false);
                input2.setFocusableInTouchMode(false);
                System.out.print(matrix2.show());
                return;
            }
            matrix2.matrix[x2][y2] = Double.parseDouble(input2.getText().toString().trim());
            input2.setText("");
            y2++;
            show2.setText(matrix2.show());

            if (y2 >= matrix2.getY()){
                y2 = 0;
                x2++;
            }
        }
    }

    private class Equal_OnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (METHOD.equals(MainActivity.PLUS)){
                result.setText((matrix1.add(matrix2)).show());
            }else if (METHOD.equals(MainActivity.SUBTRACT)){
                result.setText((matrix1.subtraction(matrix2)).show());
            }else if (METHOD.equals(MainActivity.MULTIPLE)){
                result.setText((matrix1.multipe(matrix2)).show());
            }else if (METHOD.equals(MainActivity.DIVIDE)){
                result.setText((matrix1.division(matrix2)).show());
            }
        }
    }
}


