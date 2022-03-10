package com.company;

import java.util.Scanner;

public class CDMA {

    private static int [][] walshTable;
    private static int [] CDMA_sequence;

    public static void printWalshTable(int N){
        for(int i=0; i<N; i++) {
            for (int j=0; j<N; j++){
                System.out.print(walshTable[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static void buildWalshTable(int N, int r, int c, boolean inv){

        // W2N = [WN1, WN2; WN3, WN4], WN1 = WN2 = WN3 = WN, WN4 = inv(WN)
        // W1 = [1]
        if(N == 1){
            if(inv) walshTable[r][c] = -1;
            else walshTable[r][c] = 1;
            return ;
        }

        int L = N/2;
        buildWalshTable(L, r, c, false); // WN1
        buildWalshTable(L, r, c+L, false); // WN2
        buildWalshTable(L, r+L, c, false); // WN3
        buildWalshTable(L, r+L, c+L, true); // WN4

        if(inv){
            for(int i=0; i<N; i++)
                for(int j=0; j<N; j++)
                    walshTable[r+i][c+j] *= -1;
        }
        return;
    }

    public static void computeCDMA(int [] data, int N){

        for(int i=0; i<N; i++){
            CDMA_sequence[i] = 0;
        }
        for(int i=0; i<N; i++)
            for(int j= 0; j< N; j++){
                CDMA_sequence[j] += walshTable[i][j] * data[i];
            }

        System.out.println("CDMA_SEQUENCE: ");
        for(int i=0; i<N; i++)
            System.out.print(CDMA_sequence[i] + " ");
        System.out.println(" ");
    }

    public static void main(String [] args){

        // get input station data
        Scanner scaner = new Scanner(System.in);
        System.out.println("Input Station data: ");
        String input = scaner.nextLine();
        String[] input_item = input.split(" ");

        int N = input_item.length;
        int[] data = new int[N];

        for(int i=0; i<N; i++){
            data[i] = Integer.valueOf(input_item[i]);
        }

        // inialization
        walshTable = new int[N][N];
        CDMA_sequence = new int[N];
        buildWalshTable(N, 0, 0, false);
        printWalshTable(N);
        computeCDMA(data, N);

        do{
            System.out.println("Input station number: ");
            int K = scaner.nextInt();
            if(K <= 0 || K > N){
                System.out.println("out of range");
                break;
            }
            int d = 0;
            for(int i = 0; i< N; i++ ){
                d += walshTable[K-1][i]* CDMA_sequence[i];
            }

            System.out.println("Station " + K + " data is: " + (d/N));

        }while (true);

    }
}
