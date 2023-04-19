package org.example;

public class MatrixComparer {
    public boolean MatricesAreEqual(int [][] matrixA, int [][] matrixB)
    {
        if (matrixA.length != matrixB.length || matrixA[0].length != matrixB[0].length) {
            return false;
        }

        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA[0].length; j++) {
                if (matrixA[i][j] != matrixB[i][j]) {
                    return false;
                }
            }
        }

        return true;
    }
}
