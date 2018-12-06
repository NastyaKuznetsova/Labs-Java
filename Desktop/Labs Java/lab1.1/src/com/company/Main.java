package com.company;
import java.util.Random;

public class Main {
    public static void main(String[] args) {


        System.out.print("\n----Kuznetsova.Anastasia----\n\n");
        int m = 6;

        float[][] matrix = new float[m][m];
        // fill matrix
        init(matrix);
        // print
        System.out.print("\nInitial matrix:\n\n");
        print(matrix);
        rotate(matrix);

        System.out.print("\nRotated matrix:\n\n");
        print(matrix);
        // rotate

    }
    public static void print(float [][] matrix){
        for (int i=0; i< matrix.length; i++)
        {
            for (int j=0; j<matrix.length; j++)
            {
                //.out.printf("%5d ", matrix[i][j]);
                System.out.format("%10.5f",matrix[i][j]);
            }
            System.out.print("\n");
        }
    }
    public static void init(float [][] matrix){
        float minX = -10.0f;
        float maxX = 10.0f;
        Random rnd = new Random();

        for (int i=0;i < matrix.length;i++) {
            for (int j=0;j < matrix[i].length;j++) {
                matrix[i][j]= rnd.nextFloat() * (maxX - minX) + minX;
            }
        }
    }
    public static void rotate(float [][] matrix){
        for (int k=0; k<matrix.length/2; k++) // border -> center
        {
            for (int j=k; j<matrix.length-1-k; j++) // left -> right
            {
                // меняем местами 4 угла
                float tmp         = matrix[k][j];
                matrix[k][j]         = matrix[j][matrix.length -1-k];
                matrix[j][matrix.length-1-k]     = matrix[matrix.length-1-k][matrix.length-1-j];
                matrix[matrix.length-1-k][matrix.length-1-j] = matrix[matrix.length-1-j][k];
                matrix[matrix.length-1-j][k]     = tmp;
            }
        }
    }
}
