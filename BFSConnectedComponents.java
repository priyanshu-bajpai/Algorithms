import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class CarolsSinging {

    static public class Node {
        int row;
        int cols, cole;
        int idx;
        boolean visited;
        public Node(int row, int cols, int cole, int idx) {
            this.row=row;
            this.cols=cols;
            this.cole=cole;
            this.idx=idx;
        }
    }

    public static void main(String args[]) {
        String []lyrics = new String[]{"YNNYYY","YYNYYY","YNNYYN","NYYNNN","YYYNNN","YYYNNY","NYYYYY","NYNYYY","NNNNYY",
                "YYYYYY","YNNNNN","YYYYNY","YYNNNN","NNYYYN","NNNNYY","YYYNNN","NYNNYN","YNNYYN",
                "YYNNNY","NYYNNY","NNYYYN","YNYYYN","NNNYNY","YYYYNN","YYNYNN","NYYNYY","YYNYYN"};

        System.out.println(choose(lyrics));
    }

    public static int choose(String[] lyrics) {
        ArrayList<Node> nodes = getNodes(lyrics);
        ArrayList<ArrayList<Node>> adjList = constructgraph(nodes);
        int connectedComponents=0;
        int n = nodes.size();
        for(int i=0;i<n;i++) {
            if(!nodes.get(i).visited) {
                doBFS(i, nodes, adjList);
                connectedComponents++;
            }
        }

        return connectedComponents;
    }

    static void doBFS(int src, ArrayList<Node> nodes, ArrayList<ArrayList<Node>> adjList) {
        nodes.get(src).visited=true;
        Queue<Node> q = new LinkedList<>();
        q.add(nodes.get(src));
        while(!q.isEmpty()) {
            Node f = q.poll();
            int idx = f.idx;
            for(Node node : adjList.get(idx)) {

               if(!node.visited) {
                   node.visited=true;
                   q.add(node);
               }
            }
        }
    }


    static ArrayList<ArrayList<Node>> constructgraph(ArrayList<Node> nodes) {
        ArrayList<ArrayList<Node>> adjList = new ArrayList<>();
        int n=nodes.size();

        for(int i=0;i<n;i++) {
            Node node = nodes.get(i);
            adjList.add(new ArrayList<>());
            for(int j=0;j<n;j++) {
                Node adj = nodes.get(j);
                if(overlap(adj, node)) {
                    adjList.get(i).add(adj);
                }
            }
        }
        return adjList;
    }

    static boolean overlap(Node n1, Node n2) {
        return !(n1.cols > n2.cole || n1.cole < n2.cols) && n1.row!=n2.row;
    }

    static ArrayList<Node> getNodes(String[] lyrics) {
        ArrayList<Node> nodes = new ArrayList<>();
        for(int i=0;i<lyrics.length;i++) {
            int j=0;

            while(j<lyrics[i].length()) {
                if(lyrics[i].charAt(j)=='N') {
                    j++;
                    continue;
                }

                int start = j;
                int end =start;
                j+=1;
                while (j<lyrics[i].length() &&
                        lyrics[i].charAt(j)=='Y') {
                    end++;
                    j++;
                }
                nodes.add(new Node(i, start, end, nodes.size()));
            }
        }
        return nodes;
    }
}
