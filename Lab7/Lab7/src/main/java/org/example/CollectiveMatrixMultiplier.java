package org.example;

import mpi.MPI;

import java.util.Arrays;

import static java.lang.System.exit;

public class CollectiveMatrixMultiplier {
    public static void main(String[] args) {
        int MASTER = 0;

        long startTime = 0, endTime;

        int workersNumber, destination, averow, extra;

        int matrixSize = 500;

        MPI.Init(args);
        int tasksNumber = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();

        if (tasksNumber < 2 ) {
            System.out.println("Need at least two MPI tasks. Quitting...\n");
            MPI.COMM_WORLD.Abort(1);
            exit(1);
        }

        workersNumber = tasksNumber - 1;

        int[][] matrixB = new int[matrixSize][matrixSize];
        int[][] matrixA = new int[0][];

        int[] counts = new int[tasksNumber];
        int[] displs = new int[tasksNumber];

        averow = matrixSize / tasksNumber;
        extra = matrixSize % tasksNumber;

        int offset = 0;
        for (destination = 1; destination <= workersNumber + 1; destination++) {
            int rows = (destination <= extra) ? averow + 1 : averow;

            counts[destination - 1] = rows;
            displs[destination - 1] = offset;

            offset += rows;
        }

        if (rank == MASTER) {
            matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            startTime = System.currentTimeMillis();
        }

        var workerMatrixAPart = new int[counts[rank]][matrixSize];

        MPI.COMM_WORLD.Scatterv(matrixA, 0, counts, displs, MPI.OBJECT, workerMatrixAPart, 0, counts[rank], MPI.OBJECT, MASTER);
        MPI.COMM_WORLD.Bcast(matrixB,0, matrixSize, MPI.OBJECT, MASTER);

        int[][] calculatedRows = MatrixFunctions.Multiply(workerMatrixAPart, matrixB);
        int[][] result = new int[matrixSize][];

        MPI.COMM_WORLD.Allgatherv(calculatedRows, 0, calculatedRows.length, MPI.OBJECT, result, 0, counts, displs, MPI.OBJECT);

        if (rank == MASTER) {
            endTime = System.currentTimeMillis();

            System.out.println(endTime - startTime);

            //System.out.println("****\n");
            //System.out.println("Result Matrix:\n");
            MatrixPrinter.Print(result);
            //System.out.println("\n********\n");
            //System.out.println("Done.\n");
        }

        MPI.Finalize();
    }
}