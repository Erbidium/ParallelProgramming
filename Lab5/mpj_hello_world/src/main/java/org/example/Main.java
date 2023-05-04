package org.example;

import mpi.MPI;

import java.util.Arrays;

import static java.lang.System.exit;

public class Main {
    public static void main(String args[]) {
        MPI.Init(args);

        int myrank = MPI.COMM_WORLD.Rank();
        int size = MPI.COMM_WORLD.Size() ;
        System.out.println("Hello world from rank " + myrank + " of " + size);

        MPI.Finalize();
    }
}