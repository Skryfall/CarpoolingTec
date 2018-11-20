package tec.ac.cr.carpoolingtec.logic;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        List list = new List();
        createNodes(list);
        int matrixEnableRoads[][] = new int[5][5];
        int matrixLenghtRoads[][] = new int[5][5];
        createRoads(matrixEnableRoads);
        createLenghts(matrixLenghtRoads, matrixEnableRoads, list);
        printGraph(matrixLenghtRoads);
        int roadMatrix[][] = createRoadsMatrix(5,5);
        setMinRoad(matrixLenghtRoads,roadMatrix);
        printGraph( roadMatrix);
        ArrayList<Integer> route = createRoute(0,3,roadMatrix);
        for(int i:route){
            System.out.print(i + " ");
        }
    }

    public static void createNodes(List list) {
        for (int i = 0; i < 5; i++) {
            list.addElement(randomWithRangeForPosition(100, 1), randomWithRangeForPosition(100, 1), i);
        }
    }

    public static void createRoads(int matrixEnableRoads[][]) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == j) {
                    matrixEnableRoads[i][j] = 0;
                } else {
                    int road = randomWithRangeForRoad();
                    matrixEnableRoads[i][j] = road;
                }
            }
        }
    }

    public static void createLenghts(int matrixLengthRoads[][], int matrixEnableRoads[][], List list) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (matrixEnableRoads[i][j] == 0) {
                    if (i == j){
                        matrixLengthRoads[i][j] = 0;
                    }else {
                        matrixLengthRoads[i][j] = 1000;
                    }
                }
                else{
                    matrixLengthRoads[i][j] = (int) getRoadsLenght(list.searchElement(i), list.searchElement(j));
                }
            }
        }
    }

    public static void printGraph(int[][] matrix) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(" ");
        }
    }

    public static int randomWithRangeForRoad() {
        double road = Math.random();
        if (road < 0.5) {
            return 0;
        } else {
            return 1;
        }
    }

    public static int randomWithRangeForPosition(int max, int min) {
        int range = (max - min) + 1;
        int number = (int) (Math.random() * range) + min;
        if (number == 0) {
            randomWithRangeForPosition(100, 1);
        }
        return number;
    }

    public static double getRoadsLenght(Node node1, Node node2) {
        int posX1 = node1.getPosx();
        int posY1 = node1.getPosy();
        int posX2 = node2.getPosx();
        int posY2 = node2.getPosy();
        double length = Math.sqrt(Math.abs(((posX2 - posX1) * (posX2 - posX1))) + ((posY2 - posY1) * (posY2 - posY1)));
        return (int) length;
    }

    public static int[][] createRoadsMatrix(int a, int b) {
        int roadsMatrix[][] = new int[5][5];
        for (int j = 0; j < b; j++) {

            for (int i = 0; i < a; i++) {
                roadsMatrix[i][j] = j;
            }
        }
        return roadsMatrix;
    }

    public static int[][] setMinRoad(int[][] lengthMatrix, int[][] roadMatrix) {
        for (int i_j = 0; i_j < 5; i_j++) {
            for (int tmp_i = 0; tmp_i < 5; tmp_i++) {
                if (tmp_i != i_j) {
                    for (int tmp_j = 0; tmp_j < 5; tmp_j++) {
                        if (tmp_j != i_j) {
                            if (lengthMatrix[i_j][tmp_j] + lengthMatrix[tmp_i][i_j] < lengthMatrix[tmp_i][tmp_j]) {
                                roadMatrix[tmp_i][tmp_j] = i_j;
                                lengthMatrix[tmp_i][tmp_j] = lengthMatrix[i_j][tmp_j] + lengthMatrix[tmp_i][i_j];
                            }
                        }
                    }
                }
            }
        }
        return roadMatrix;
    }

    public static ArrayList createRoute(int pointA, int pointB, int[][] roadMatrix){
       ArrayList<Integer> route = new ArrayList<Integer>(); //Cambiar a lista enlazada xd
        while(roadMatrix[pointA][pointB] != pointB){
            route.add(roadMatrix[pointA][pointB]);
            pointA = roadMatrix[pointA][pointB];
        }
        route.add(roadMatrix[pointA][pointB]);
        return route;
    }
}