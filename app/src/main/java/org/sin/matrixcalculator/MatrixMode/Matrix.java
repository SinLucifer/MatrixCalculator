package org.sin.matrixcalculator.MatrixMode;

import java.io.Serializable;
import java.util.Scanner;

/**
 * Created by Sin on 2016/1/30.
 */
public class Matrix implements Serializable{
    private int x;
    private int y;
    private double matrix[][];

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Matrix(Matrix a, Matrix b) {
        x = a.x;
        y = b.y;
        matrix = new double[x][y];
    }

    public Matrix(int x, int y, int flag) {
        this.x = x;
        this.y = y;
        matrix = new double[x][y];
    }

    public Matrix(int x, int y) {
        this.x = x;
        this.y = y;
        matrix = new double[x][y];
        Scanner input = new Scanner(System.in);
        System.out.println("请输入" + x + "行" + y + "列的矩阵");
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                matrix[i][j] = input.nextDouble();
            }
        }
        input.close();
    }

    public Matrix add(Matrix t) {
        Matrix result = new Matrix(this, t);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                result.matrix[i][j] = matrix[i][j] + t.matrix[i][j];
            }
        }
        return result;
    }

    public Matrix subtraction(Matrix t) {
        Matrix result = new Matrix(this, t);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                result.matrix[i][j] = matrix[i][j] - t.matrix[i][j];
            }
        }
        return result;
    }

    public Matrix multipe(Matrix t) {
        Matrix result = new Matrix(this, t);
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < t.y; j++) {
                int temp = 0;
                for (int k = 0; k < y; k++) {
                    temp += matrix[i][k] * t.matrix[k][j];
                }
                result.matrix[i][j] = temp;
            }
        }
        return result;
    }

    public Matrix division(Matrix t) {
        Matrix ni = t.getNiMatrix();
        Matrix result = this.multipe(ni);
        return result;
    }

    public static Matrix getDY(double[][] data, int h, int v) {
        int H = data.length;
        int V = data[0].length;
        Matrix newData = new Matrix(H - 1, V - 1, 1);

        for (int i = 0; i < newData.x; i++) {

            if (i < h - 1) {
                for (int j = 0; j < newData.y; j++) {
                    if (j < v - 1) {
                        newData.matrix[i][j] = data[i][j];
                    } else {
                        newData.matrix[i][j] = data[i][j + 1];
                    }
                }
            } else {
                for (int j = 0; j < newData.y; j++) {
                    if (j < v - 1) {
                        newData.matrix[i][j] = data[i + 1][j];
                    } else {
                        newData.matrix[i][j] = data[i + 1][j + 1];
                    }
                }

            }
        }
        return newData;
    }

    public static double getHL(Matrix t) {

        if (t.x != t.y) {
            System.out.println("此矩阵无行列式");
            return 0;
        }

        // 终止条件
        if (t.x == 1) {
            return t.matrix[0][0];
        }
        if (t.x == 2) {
            return t.matrix[0][0] * t.matrix[1][1] - t.matrix[0][1]
                    * t.matrix[1][0];
        }

        double total = 0;
        // 根据data 得到行列式的行数和列数
        int num = t.x;
        // 创建一个大小为num 的数组存放对应的展开行中元素求的的值
        double[] nums = new double[num];

        for (int i = 0; i < num; i++) {
            if (i % 2 == 0) {
                nums[i] = t.matrix[0][i] * getHL(getDY(t.matrix, 1, i + 1));
            } else {
                nums[i] = -t.matrix[0][i] * getHL(getDY(t.matrix, 1, i + 1));
            }
        }
        for (int i = 0; i < num; i++) {
            total += nums[i];
        }
        return total;
    }

    public Matrix getA_T() {
        if (this.x != this.y) {
            System.out.println("此矩阵没有伴随矩阵");
            return this;
        }
        Matrix result = new Matrix(x, y, 1);
        if (this.x == 1) {
            result.matrix[0][0] = 1;
            return result;
        }
        double[][] newData = new double[this.x][this.y];
        for (int i = 0; i < this.x; i++) {
            for (int j = 0; j < this.x; j++) {
                double num;
                if ((i + j) % 2 == 0) {
                    num = getHL(getDY(this.matrix, i + 1, j + 1));
                } else {
                    num = -getHL(getDY(this.matrix, i + 1, j + 1));
                }

                newData[i][j] = num;
            }
        }

        for (int j = 0; j < this.y; j++) {
            for (int j2 = 0; j2 < this.x; j2++) {
                result.matrix[j2][j] = newData[j][j2];
            }
        }

        return result;
    }

    public Matrix getNiMatrix() {
        Matrix result = new Matrix(this.x, this.y, 1);
        double HL = getHL(this);
        result = this.getA_T();
        for (int i = 0; i < result.x; i++) {
            for (int j = 0; j < result.y; j++) {
                result.matrix[i][j] /= HL;
            }
        }
        return result;
    }

    public void show() {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
