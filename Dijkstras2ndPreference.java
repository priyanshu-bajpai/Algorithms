import java.util.ArrayList;
import java.util.TreeSet;

import javafx.util.Pair;

public class ArcadeManao {
//https://arena.topcoder.com/index.html#/u/practiceCode/15629/31602/12504/2/317076
    static public class Node  implements Comparable<Node> {
        int row;
        int cols, cole;
        int weight;
        int idx;
        public Node (int row, int cols, int cole, int weight, int idx) {
            this.cole=cole;
            this.cols=cols;
            this.row= row;
            this.weight=weight;
            this.idx=idx;
        }

        @Override
        public int compareTo(Node n) {
            if(this.weight!=n.weight)
                return this.weight - n.weight;

            return this.idx-n.idx;
        }

        @Override
        public boolean equals(Object o) {
            Node n = (Node)o;
            return this.idx==n.idx;
        }
    }

    public static int shortestLadder(String[] level, int coinRow, int coinColumn) {
        Pair<ArrayList<Node>, Node> p = getNodes(level, coinColumn-1, coinRow-1);
        ArrayList<Node> nodes = p.getKey();
        Node dest = p.getValue();
        int n = nodes.size();
        ArrayList<ArrayList<Node>> adjList = new ArrayList<>();

        for(int i=0;i<n;i++) {
            Node node = nodes.get(i);
            adjList.add(new ArrayList<>());
            for(int j=0;j<n;j++) {
                Node adj = nodes.get(j);
                if(overlap(node, adj)) {
                  adjList.get(i).add(adj);
                }
            }
        }

        int ladder = doDijkstra(nodes.get(nodes.size()-1), dest, adjList);
        return ladder;
    }

    static boolean overlap (Node n1, Node n2) {
       return !((n1.cole < n2.cols) || (n1.cols>n2.cole));
    }

    static int doDijkstra(Node src, Node dest, ArrayList<ArrayList<Node>> adjList) {
        src.weight=0;
        TreeSet<Node> heap = new TreeSet<>();
        heap.add(src);
        while(!heap.isEmpty()) {
            Node front = heap.pollFirst();

            for(Node node : adjList.get(front.idx)) {
                if(node.weight > Math.max(front.weight, Math.abs(front.row-node.row))) {
                    heap.remove(node);
                    node.weight=Math.max(front.weight, Math.abs(front.row-node.row));
                    heap.add(node);
                }
            }
        }

        return dest.weight;
    }

    static Pair<ArrayList<Node>, Node> getNodes(String[] level, int destcol, int destrow ) {
        ArrayList<Node> nodes= new ArrayList<>();
        Node dest=null;
        for(int i=0;i<level.length;i++) {
            int j=0;
            while(j<level[i].length()) {
                if(level[i].charAt(j)!='X') {
                    j++;
                    continue;
                }
                int start =j;
                int end = start;
                while(end+1<level[i].length() && level[i].charAt(end+1)=='X') {
                    end++;
                }
                Node n = new Node(i, start, end, Integer.MAX_VALUE, nodes.size());
                if(i==destrow && start<=destcol && destcol<=end) {
                    dest=n;
                }
                nodes.add(n);
                j=end+1;
            }
        }
        return  new Pair(nodes, dest);
    }
}
