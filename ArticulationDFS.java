import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import javafx.util.Pair;

public class SgAndTheRoads {

    public static void main(String args[]) throws IOException {
        Scanner sc = new Scanner(System.in);
        int villages = sc.nextInt();
        int roads = sc.nextInt();
        int numQ = sc.nextInt();

        ArrayList<ArrayList<Pair<Integer, Integer>>> mat = new ArrayList<>();
        for(int i=0;i<villages;i++) {
            mat.add(new ArrayList<>());
        }

        HashMap<Integer, Pair<Integer, Integer>> rtovMap = new HashMap<>();

        createGraph(mat, rtovMap, roads, sc);

        int[] parent = new int[villages];
        boolean [] visited = new boolean[villages];
        int [] low = new int[villages];
        int[] disc = new int[villages];
        int time=0;
        int src=0;
        boolean[][] AP = new boolean[villages][villages];
        doDFS(mat, parent, disc, low, visited, time, src, AP);
        BufferedWriter wr = new BufferedWriter(new OutputStreamWriter(System.out));

        for(int i=0;i<numQ;i++) {
            if(isArticulation(AP, sc.nextInt()-1, rtovMap))
                wr.append("YES");
            else
                wr.append("no");
            wr.newLine();
        }
        wr.flush();
        wr.close();

        return;
    }

    static void createGraph(ArrayList<ArrayList<Pair<Integer, Integer>>> mat, HashMap<Integer, Pair<Integer, Integer>> rtovMap, int roads, Scanner sc) {
        for(int i=0;i<roads;i++) {
            int v1 = sc.nextInt()-1;
            int v2 = sc.nextInt()-1;
            int road = sc.nextInt()-1;

            mat.get(v1).add(new Pair(v2, road));
            mat.get(v2).add(new Pair(v1, road));

            rtovMap.put(road, new Pair(v1, v2));
        }
    }

    static boolean isArticulation(boolean[][] AP, int road, HashMap<Integer, Pair<Integer, Integer>> rtovMap) {
        Pair<Integer, Integer> p = rtovMap.get(road);
        return AP[p.getKey()][p.getValue()];
    }

    static void doDFS(ArrayList<ArrayList<Pair<Integer, Integer>>> mat, int[] parent, int[]disc,
                      int[] low, boolean[] visited, int time, int src, boolean[][] AP) {

        visited[src]=true;
        low[src]=disc[src]=time;

        for(Pair<Integer, Integer> p : mat.get(src)) {
            int i = p.getKey();
            if(!visited[i]) {
                parent[i]=src;
                doDFS(mat, parent, disc, low, visited, time+1, i, AP);
                low[src]=Math.min(low[i], low[src]);
                if(low[i] > disc[src]) {
                    AP[src][i]=true;
                }
            }
            else {
                if(parent[src]!=i) {
                    low[src] = Math.min(disc[i], low[src]);
                }
            }
        }

    }
}
