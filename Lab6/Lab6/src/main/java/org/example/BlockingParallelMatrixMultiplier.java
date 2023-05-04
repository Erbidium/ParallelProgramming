package org.example;

import mpi.MPI;

import static java.lang.System.exit;

public class BlockingParallelMatrixMultiplier {
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
            var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            var c = new int[matrixSize][matrixSize];

            //System.out.printf("mpi_mm has started with %d tasks.\n", numtasks);

            var startTime = MPI.Wtime();

            averow = matrixSize / numworkers;
            extra = matrixSize % numworkers;

            int offset = 0;
            for (dest = 1; dest <= numworkers; dest++) {
                int rows = (dest <= extra) ? averow + 1 : averow;
                //System.out.printf("Sending %d rows to task %d offset= %d\n", rows,dest,offset);
                MPI.COMM_WORLD.Send(new int[] { offset }, 0, 1, MPI.INT, dest, FROM_MASTER);
                MPI.COMM_WORLD.Send(new int[] { rows }, 0, 1, MPI.INT, dest, FROM_MASTER);

                var subMatrixA = MatrixFunctions.GetSubMatrix(offset, rows, matrixA);

                MPI.COMM_WORLD.Send(subMatrixA, 0, rows, MPI.OBJECT, dest, FROM_MASTER);
                MPI.COMM_WORLD.Send(matrixB, 0, matrixSize, MPI.OBJECT, dest, FROM_MASTER);

                offset += rows;
            }

            var offsetBuffer = new int[1];
            var rowsBuffer = new int[1];

            for (source = 1; source <= numworkers; source++) {
                MPI.COMM_WORLD.Recv(offsetBuffer, 0, 1, MPI.INT, source, FROM_WORKER);
                MPI.COMM_WORLD.Recv(rowsBuffer, 0, 1, MPI.INT, source, FROM_WORKER);

                var calculatedSubMatrixC = new int[rowsBuffer[0]][matrixSize];
                MPI.COMM_WORLD.Recv(calculatedSubMatrixC, 0, rowsBuffer[0], MPI.OBJECT, source, FROM_WORKER);

                MatrixFunctions.AddSubMatrix(c, calculatedSubMatrixC, offsetBuffer[0]);

                //System.out.printf("Received results from task %d\n", source);
            }

            var endTime = MPI.Wtime();

            //System.out.println(endTime - startTime);

            //System.out.println("****\n");
            //System.out.println("Result Matrix:\n");
            MatrixPrinter.Print(c);
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

            var workerMatrixAPart = new int[rows[0]][];
            var workerMatrixB = new int[matrixSize][];

            MPI.COMM_WORLD.Recv(workerMatrixAPart, 0, rows[0], MPI.OBJECT, MASTER, FROM_MASTER);
            MPI.COMM_WORLD.Recv(workerMatrixB, 0, matrixSize, MPI.OBJECT, MASTER, FROM_MASTER);

            var calculatedRows = MatrixFunctions.Multiply(workerMatrixAPart, workerMatrixB);

            MPI.COMM_WORLD.Send(offset, 0, 1, MPI.INT, MASTER, FROM_WORKER);
            MPI.COMM_WORLD.Send(rows, 0, 1, MPI.INT, MASTER, FROM_WORKER);
            MPI.COMM_WORLD.Send(calculatedRows, 0, rows[0], MPI.OBJECT, MASTER, FROM_WORKER);
        }

        MPI.Finalize();
    }
}