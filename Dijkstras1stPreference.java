import java.util.TreeSet;

//https://arena.topcoder.com/index.html#/u/practiceCode/1384/2269/2449/1/1384

public class DungeonEscape {

    static int [][] d = new int[][]{{1,0,-1,0}, {0,-1,0,1}};

    static class Node implements Comparable<Node>{
        int x;
        int y;
        int weight;
        public Node(int x, int y, int weight) {
            this.weight=weight;
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x &&
                    y == node.y &&
                    weight == node.weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight-n.weight;
        }
    }

    static public int exitTime(String[] up, String[] down, String[] east, String[] west,
                 int startLevel, int startEasting) {
        int srcx = startLevel;
        int srcy = startEasting;
        int n = up.length;
        int m = up[0].length();

        String [][] lookUp = new String[][]{down, west, up, east};
        Node [][] mat = new Node[n][m];
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                mat[i][j] = new Node(i, j, Integer.MAX_VALUE);
            }
        }

        mat[srcx][srcy].weight = 0;
        TreeSet<Node> heap = new TreeSet<>();
        heap.add(mat[srcx][srcy]);

        int exitTime = Integer.MAX_VALUE;

        while(!heap.isEmpty()) {
            Node front = heap.pollFirst();
            int x = front.x;
            int y = front.y;
            int weight = front.weight;

            for(int i=0; i<4; i++){
                int newx = x + d[0][i];
                int newy = y + d[1][i];
                if(isValid(newx, newy, n, m) && lookUp[i][x].charAt(y)!='x') {
                    int newWeight = weight + (lookUp[i][x].charAt(y)- '0');
                    if(newx<0) {
                        exitTime = Math.min(exitTime, newWeight);
                        continue;
                    }
                    else if(mat[newx][newy].weight > newWeight && newWeight < (n-newx)*m) {
                        heap.remove(mat[newx][newy]);
                        mat[newx][newy].weight = newWeight;
                        heap.add(mat[newx][newy]);
                    }
                }
            }
        }

        return exitTime==Integer.MAX_VALUE ? -1 : exitTime;
    }

    static boolean isValid(int x, int y, int n, int m) {
        return x>=-1 && x<n && y>=0 && y<m;
    }
}
