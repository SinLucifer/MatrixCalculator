package org.sin.matrixcalculator.Calculating_Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.sin.matrixcalculator.AllClearDialog;
import org.sin.matrixcalculator.MainActivity;
import org.sin.matrixcalculator.MatrixMode.Matrix;
import org.sin.matrixcalculator.R;


public class ComplexCalculatingActivity extends Activity {
    private TextView show;
    private TextView result;
    private EditText input;

    private int x = 0;
    private int y = 0;
    private String METHOD;
    private Matrix matrix1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.complex_calculating_activity);
        TextView title = (TextView)findViewById(R.id.complex_title);
        show = (TextView)findViewById(R.id.complex_show);
        result = (TextView)findViewById(R.id.complex_result);

        input = (EditText)findViewById(R.id.complex_input);
        Button submit = (Button)findViewById(R.id.complex_submit);
        Button all_clear = (Button)findViewById(R.id.complex_all_clear);
        Button back_space = (Button)findViewById(R.id.complex_back_space);
        Button equal = (Button)findViewById(R.id.complex_equal);

        Intent intent = getIntent();
        matrix1 = (Matrix) intent.getSerializableExtra("Matrix_INFO");
        METHOD = matrix1.getMethod();
        title.setText(METHOD);

        submit.setOnClickListener(new Matrix_OnClickListener());
        all_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllClearDialog allClearDialog = AllClearDialog.newInstance(
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                matrix1.matrix = new double[matrix1.getX()][matrix1.getY()];
                                x = 0;
                                y = 0;
                                show.setText(matrix1.show());
                            }
                        });
                allClearDialog.show(getFragmentManager(), "all_clearDialog");
            }
        });
        back_space.setOnClickListener(new Backspace_OnClickListener());
        equal.setOnClickListener(new Equal_OnclickListener());
    }

    private class Matrix_OnClickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(input.getText())){
                Toast.makeText(ComplexCalculatingActivity.this,
                        "输入有误，请检查输入！", Toast.LENGTH_SHORT).show();
                return;
            }
            if (x == matrix1.getX()){
                Toast.makeText(ComplexCalculatingActivity.this,
                        "矩阵已满，请检查输入！",Toast.LENGTH_SHORT).show();
                //System.out.print(matrix1.show());
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

    private class Backspace_OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            y--;
            if (y < 0) {
                y = matrix1.getY()-1;
                if (x > 0) {
                    x -= 1;
                }else {
                    return;
                }
            }
            matrix1.matrix[x][y] = 0;
            input.setText("");
            show.setText(matrix1.show());
        }
    }

    private class Equal_OnclickListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if (METHOD.equals(MainActivity.DETERMINANT)){
                result.setText(Matrix.getHL(matrix1)+"");
            }else if (METHOD.equals(MainActivity.ADJUGATE)){
                result.setText((matrix1.getA_T()).show());
            }else if (METHOD.equals(MainActivity.INVERSE)){
                result.setText((matrix1.getNiMatrix()).show());
            }
        }
    }
}
