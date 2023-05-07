package org.example;

import mpi.MPI;
import mpi.Request;

import static java.lang.System.exit;

public class NonBlockingParallelMatrixMultiplier {
    public static void main(String[] args) {
        int MASTER = 0;
        int FROM_MASTER = 1;
        int FROM_WORKER = 2;

        int workersNumber, source, destination, averow, extra;

        int matrixSize = 1000;

        MPI.Init(args);
        int tasksNumber = MPI.COMM_WORLD.Size();
        int rank = MPI.COMM_WORLD.Rank();

        if (tasksNumber < 2 ) {
            System.out.println("Need at least two MPI tasks. Quitting...\n");
            MPI.COMM_WORLD.Abort(1);
            exit(1);
        }
        workersNumber = tasksNumber - 1;
        if (rank == MASTER) {
            var matrixA = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            var matrixB = MatrixGenerator.GenerateMatrixFilledWithValue(matrixSize, 1);
            var c = new int[matrixSize][matrixSize];

            var startTime = System.currentTimeMillis();

            averow = matrixSize / workersNumber;
            extra = matrixSize % workersNumber;

            int offset = 0;

            for (destination = 1; destination <= workersNumber; destination++) {
                int rows = (destination <= extra) ? averow + 1 : averow;

                MPI.COMM_WORLD.Isend(new int[] { offset }, 0, 1, MPI.INT, destination, FROM_MASTER);
                MPI.COMM_WORLD.Isend(new int[] { rows }, 0, 1, MPI.INT, destination, FROM_MASTER);

                var subMatrixA = MatrixFunctions.GetSubMatrix(offset, rows, matrixA);

                MPI.COMM_WORLD.Isend(subMatrixA, 0, rows, MPI.OBJECT, destination, FROM_MASTER);
                MPI.COMM_WORLD.Isend(matrixB, 0, matrixSize, MPI.OBJECT, destination, FROM_MASTER);

                offset += rows;
            }

            var offsetsBuffer = new int[workersNumber];
            var rowsBuffer = new int[1];

            var offsetReceives = new Request[workersNumber];
            var calculatedSubMatrixReceives = new Request[workersNumber];
            var calculatedSubMatricesC = new int[workersNumber][][];

            for (source = 1; source <= workersNumber; source++) {
                offsetReceives[source - 1] = MPI.COMM_WORLD.Irecv(offsetsBuffer, source - 1, 1, MPI.INT, source, FROM_WORKER);
                var rowsReceive = MPI.COMM_WORLD.Irecv(rowsBuffer, 0, 1, MPI.INT, source, FROM_WORKER);

                rowsReceive.Wait();

                calculatedSubMatricesC[source - 1] = new int[rowsBuffer[0]][matrixSize];
                calculatedSubMatrixReceives[source - 1] = MPI.COMM_WORLD.Irecv(calculatedSubMatricesC[source - 1], 0, rowsBuffer[0], MPI.OBJECT, source, FROM_WORKER);
            }

            Request.Waitall(offsetReceives);
            Request.Waitall(calculatedSubMatrixReceives);

            for (var calculatedSubMatrixC: calculatedSubMatricesC) {
                MatrixFunctions.AddSubMatrix(c, calculatedSubMatrixC, offsetsBuffer[0]);
            }

            var endTime = System.currentTimeMillis();

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

            var offsetReceive = MPI.COMM_WORLD.Irecv(offset, 0, 1, MPI.INT, MASTER, FROM_MASTER);
            var rowsReceive = MPI.COMM_WORLD.Irecv(rows, 0, 1, MPI.INT, MASTER, FROM_MASTER);

            offsetReceive.Wait();
            var offsetSend = MPI.COMM_WORLD.Isend(offset, 0, 1, MPI.INT, MASTER, FROM_WORKER);

            rowsReceive.Wait();

            var workerMatrixAPart = new int[rows[0]][];
            var workerMatrixB = new int[matrixSize][];

            var matrixAPartReceive = MPI.COMM_WORLD.Irecv(workerMatrixAPart, 0, rows[0], MPI.OBJECT, MASTER, FROM_MASTER);
            var matrixBReceive = MPI.COMM_WORLD.Irecv(workerMatrixB, 0, matrixSize, MPI.OBJECT, MASTER, FROM_MASTER);

            Request.Waitall(new Request[] { matrixAPartReceive, matrixBReceive });
            var calculatedRows = MatrixFunctions.Multiply(workerMatrixAPart, workerMatrixB);

            var rowsSend = MPI.COMM_WORLD.Isend(rows, 0, 1, MPI.INT, MASTER, FROM_WORKER);
            var calculatedRowsSend = MPI.COMM_WORLD.Isend(calculatedRows, 0, rows[0], MPI.OBJECT, MASTER, FROM_WORKER);

            Request.Waitall(new Request[] { offsetSend, rowsSend, calculatedRowsSend });
        }

        MPI.Finalize();
    }
}
