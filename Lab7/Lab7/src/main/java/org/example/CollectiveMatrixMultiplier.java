package org.example;

import mpi.MPI;

import static java.lang.System.exit;

public class CollectiveMatrixMultiplier {
    public static void main(String[] args) {
        int MASTER = 0;

        long startTime = 0, endTime;

        int matrixSize = 500;

        MPI.Init(args);
        int tasksNumber = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();

        if (tasksNumber < 2 ) {
            System.out.println("Need at least two MPI tasks. Quitting...\n");
            MPI.COMM_WORLD.Abort(1);
            exit(1);
        }

        var counts = new int[tasksNumber];
        var displs = new int[tasksNumber];

        int averow = matrixSize / tasksNumber;
        int extra = matrixSize % tasksNumber;

        int offset = 0;
        for (int destination = 1; destination <= tasksNumber; destination++) {
            int rows = (destination <= extra) ? averow + 1 : averow;

            counts[destination - 1] = rows;
            displs[destination - 1] = offset;

            offset += rows;
        }

        var matrixB = new int[matrixSize][matrixSize];
        var matrixA = new int[0][];

        if (rank == MASTER) {
            matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);

            startTime = System.currentTimeMillis();
        }

        var workerMatrixAPart = new int[counts[rank]][matrixSize];

        MPI.COMM_WORLD.Scatterv(matrixA, 0, counts, displs, MPI.OBJECT, workerMatrixAPart, 0, counts[rank], MPI.OBJECT, MASTER);
        MPI.COMM_WORLD.Bcast(matrixB,0, matrixSize, MPI.OBJECT, MASTER);

        var calculatedRows = MatrixFunctions.Multiply(workerMatrixAPart, matrixB);
        var result = new int[matrixSize][];

        MPI.COMM_WORLD.Gatherv(calculatedRows, 0, calculatedRows.length, MPI.OBJECT, result,0, counts, displs, MPI.OBJECT, MASTER);
        //MPI.COMM_WORLD.Allgatherv(calculatedRows, 0, calculatedRows.length, MPI.OBJECT, result, 0, counts, displs, MPI.OBJECT);

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