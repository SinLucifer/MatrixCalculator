package org.sin.matrixcalculator.Calculating_Activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.sin.matrixcalculator.AllClearDialog;
import org.sin.matrixcalculator.MainActivity;
import org.sin.matrixcalculator.MatrixMode.Matrix;
import org.sin.matrixcalculator.R;


public class SimpleCalculatingActivity extends Activity {
    private TextView show;
    private TextView show2;
    private TextView result;
    private EditText input;
    private EditText input2;

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
        TextView title = (TextView) findViewById(R.id.simple_title);
        show = (TextView) findViewById(R.id.simple_show);
        show2 = (TextView) findViewById(R.id.simple_show2);
        result = (TextView) findViewById(R.id.simple_result);

        input = (EditText) findViewById(R.id.simple_input);
        input2 = (EditText) findViewById(R.id.simple_input2);

        Button submit = (Button) findViewById(R.id.simple_submit1);
        Button all_clear1 = (Button) findViewById(R.id.simple_all_clear1);
        Button back_space1 = (Button) findViewById(R.id.simple_back_space1);
        Button submit2 = (Button) findViewById(R.id.simple_submit2);
        Button all_clear2 = (Button) findViewById(R.id.simple_all_clear2);
        Button back_space2 = (Button) findViewById(R.id.simple_back_space2);
        Button equal = (Button) findViewById(R.id.simple_equal);

        Intent intent = getIntent();
        matrix1 = (Matrix) intent.getSerializableExtra("Matrix_INFO");
        METHOD = matrix1.getMethod();

        title.setText(METHOD);

        selectMethod(METHOD);

        submit.setOnClickListener(new First_Matrix_OnClickListener());
        all_clear1.setOnClickListener(new View.OnClickListener() {
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
        back_space1.setOnClickListener(new First_Backspace_OnClickListener());

        submit2.setOnClickListener(new Second_Matrix_OnClickListener());
        all_clear2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllClearDialog allClearDialog = AllClearDialog.newInstance(
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                matrix2.matrix = new double[matrix2.getX()][matrix2.getY()];
                                x2 = 0;
                                y2 = 0;
                                show2.setText(matrix2.show());
                            }
                        });
                allClearDialog.show(getFragmentManager(), "all_clearDialog");
            }
        });
        back_space2.setOnClickListener(new Second_Backspace_OnClickListener());

        equal.setOnClickListener(new Equal_OnclickListener());
    }

    private void selectMethod(String method) {
        if (method.equals(MainActivity.PLUS)) {
            matrix2 = new Matrix(matrix1.getX(), matrix1.getY(), method);
        } else if (method.equals(MainActivity.SUBTRACT)) {
            matrix2 = new Matrix(matrix1.getX(), matrix1.getY(), method);
        } else if (method.equals(MainActivity.MULTIPLE)) {
            matrix2 = new Matrix(matrix1.getY(), matrix1.getX(), method);
        } else if (method.equals(MainActivity.DIVIDE)) {
            matrix2 = new Matrix(matrix1.getY(), matrix1.getX(), method);
        }
    }

    private class First_Matrix_OnClickListener implements View.OnClickListener {

        public First_Matrix_OnClickListener() {
        }

        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(input.getText())){
                Toast.makeText(SimpleCalculatingActivity.this,
                        "输入有误，请检查输入！",Toast.LENGTH_SHORT).show();
                return;
            }

            if (x == matrix1.getX()) {
                Toast.makeText(SimpleCalculatingActivity.this,
                        "矩阵已满，请检查输入！",Toast.LENGTH_SHORT).show();
               //System.out.print(matrix1.show());
                return;
            }
            matrix1.matrix[x][y] = Double.parseDouble(input.getText().toString().trim());
            input.setText("");
            y++;
            show.setText(matrix1.show());

            if (y >= matrix1.getY()) {
                y = 0;
                x++;
            }
        }
    }

    private class First_Backspace_OnClickListener implements View.OnClickListener {
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

    private class Second_Matrix_OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (TextUtils.isEmpty(input2.getText())){
                Toast.makeText(SimpleCalculatingActivity.this,
                        "输入有误，请检查输入！",Toast.LENGTH_SHORT).show();
                return;
            }
            if (x2 == matrix2.getX()) {
                Toast.makeText(SimpleCalculatingActivity.this,
                        "矩阵已满，请检查输入！",Toast.LENGTH_SHORT).show();
                System.out.print(matrix2.show());
                return;
            }
            matrix2.matrix[x2][y2] = Double.parseDouble(input2.getText().toString().trim());
            input2.setText("");
            y2++;
            show2.setText(matrix2.show());

            if (y2 >= matrix2.getY()) {
                y2 = 0;
                x2++;
            }
        }
    }

    private class Second_Backspace_OnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            y2--;
            if (y2 < 0) {
                y2 = matrix2.getY()-1;
                if (x2 > 0) {
                    x2 -= 1;
                }else {
                    return;
                }
            }
            matrix2.matrix[x2][y2] = 0;
            input2.setText("");
            show2.setText(matrix2.show());
        }
    }
    private class Equal_OnclickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (METHOD.equals(MainActivity.PLUS)) {
                result.setText((matrix1.add(matrix2)).show());
            } else if (METHOD.equals(MainActivity.SUBTRACT)) {
                result.setText((matrix1.subtraction(matrix2)).show());
            } else if (METHOD.equals(MainActivity.MULTIPLE)) {
                result.setText((matrix1.multipe(matrix2)).show());
            } else if (METHOD.equals(MainActivity.DIVIDE)) {
                result.setText((matrix1.division(matrix2)).show());
            }
        }
    }
}


