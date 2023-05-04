package org.example;

import mpi.MPI;

import static java.lang.System.exit;

public class Main {
    public static void main(String[] args) {
        int MASTER = 0;
        int FROM_MASTER = 1;
        int FROM_WORKER = 2;

        int numworkers, source, dest, averow, extra;

        int matrixSize = 2000;

        MPI.Init(args);
        int numtasks = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();

        if (numtasks < 2 ) {
            System.out.println("Need at least two MPI tasks. Quitting...\n");
            MPI.COMM_WORLD.Abort(1);
            exit(1);
        }
        numworkers = numtasks - 1;
        if (rank == MASTER) {
            var a = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            var b = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            var c = new int[matrixSize][matrixSize];

            //System.out.printf("mpi_mm has started with %d tasks.\n", numtasks);

            var startTime = MPI.Wtime();

            averow = matrixSize / numworkers;
            extra = matrixSize % numworkers;

            var matrixBBuffer = MatrixConverter.ConvertToSingleDimensional(b);

            int offset = 0;
            for (dest = 1; dest <= numworkers; dest++) {
                int rows = (dest <= extra) ? averow + 1 : averow;
                //System.out.printf("Sending %d rows to task %d offset= %d\n", rows,dest,offset);
                MPI.COMM_WORLD.Send(new int[] { offset }, 0, 1, MPI.INT, dest, FROM_MASTER);
                MPI.COMM_WORLD.Send(new int[] { rows }, 0, 1, MPI.INT, dest, FROM_MASTER);

                var subMatrixA = MatrixFunctions.GetSubMatrix(offset, rows, a);
                var subMatrixABuffer = MatrixConverter.ConvertToSingleDimensional(subMatrixA);

                MPI.COMM_WORLD.Send(subMatrixABuffer, 0, rows * matrixSize, MPI.INT, dest, FROM_MASTER);
                MPI.COMM_WORLD.Send(matrixBBuffer, 0, matrixSize * matrixSize, MPI.INT, dest, FROM_MASTER);
                offset += rows;
            }

            var offsetBuffer = new int[1];
            var rowsBuffer = new int[1];

            for (source = 1; source <= numworkers; source++) {
                MPI.COMM_WORLD.Recv(offsetBuffer, 0, 1, MPI.INT, source, FROM_WORKER);
                MPI.COMM_WORLD.Recv(rowsBuffer, 0, 1, MPI.INT, source, FROM_WORKER);

                var calculatedSubMatrixCBuffer = new int[rowsBuffer[0] * matrixSize];
                MPI.COMM_WORLD.Recv(calculatedSubMatrixCBuffer, 0, rowsBuffer[0] * matrixSize, MPI.INT, source, FROM_WORKER);

                var calculatedSubMatrixC = MatrixConverter.ConvertToMatrix(calculatedSubMatrixCBuffer, rowsBuffer[0], matrixSize);
                MatrixFunctions.AddSubMatrix(c, calculatedSubMatrixC, offsetBuffer[0]);

                //System.out.printf("Received results from task %d\n", source);
            }

            var endTime = MPI.Wtime();

            System.out.println(endTime - startTime);

            //System.out.println("****\n");
            //System.out.println("Result Matrix:\n");
            //MatrixPrinter.Print(c);
            //System.out.println("\n********\n");
            //System.out.println("Done.\n");
        }
        else {
            var offset = new int[1];
            var rows = new int[1];

            MPI.COMM_WORLD.Recv(offset, 0, 1, MPI.INT, MASTER, FROM_MASTER);
            MPI.COMM_WORLD.Recv(rows, 0, 1, MPI.INT, MASTER, FROM_MASTER);

            //System.out.println("Received rows: " + rows[0]);
            //System.out.println("Received offset: " + offset[0]);

            var matrixABuffer = new int[rows[0] * matrixSize];
            var matrixBBuffer = new int[matrixSize * matrixSize];

            MPI.COMM_WORLD.Recv(matrixABuffer, 0, rows[0] * matrixSize, MPI.INT, MASTER, FROM_MASTER);
            MPI.COMM_WORLD.Recv(matrixBBuffer, 0, matrixSize * matrixSize, MPI.INT, MASTER, FROM_MASTER);

            var matrixA = MatrixConverter.ConvertToMatrix(matrixABuffer, rows[0], matrixSize);
            var matrixB = MatrixConverter.ConvertToMatrix(matrixBBuffer, matrixSize, matrixSize);

            var result = MatrixFunctions.Multiply(matrixA, matrixB);

            MPI.COMM_WORLD.Send(offset, 0, 1, MPI.INT, MASTER, FROM_WORKER);
            MPI.COMM_WORLD.Send(rows, 0, 1, MPI.INT, MASTER, FROM_WORKER);
            MPI.COMM_WORLD.Send(MatrixConverter.ConvertToSingleDimensional(result), 0, rows[0] * matrixSize, MPI.INT, MASTER, FROM_WORKER);
        }

        MPI.Finalize();
    }
}