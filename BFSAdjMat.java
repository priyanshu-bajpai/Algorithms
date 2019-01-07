import java.util.LinkedList;
import java.util.Queue;

public class MazeWanderingEasy {

    static int[][] d = new int[][]{{1,0,-1,0}, {0,-1,0,1}};
    static public class Node {
        int x;
        int y;
        int px, py;
        boolean visited;
        public Node (int x, int y, int px, int py){
            this.x=x;
            this.y=y;
            this.px=px;
            this.py=py;
            visited=false;
        }
    }

    public static void main(String args[]) {
        String[] maze= new String[]		{".X.X..X..X.XX..", "...X.XMX.....X.", ".X.X.X...XX.X..", "X..X..X.X..X..X", ".X.X.X...X...X.", ".X....X.X..XX..", "...X.X..X.XX..X", "X.X...X...X..X.", ".X.XX...XX.X...", "......X......X.", "XX.XX.X.XXX.X..", ".....X.....X..X", "XXXXX..XX.X*XX.", "......X........", "XXX.X..X.X.X.X.", ".....X.X.X..X..", "X.XX.XX.X..X.X.", "XX.......X....X", "XXX.X.XX.X.X.XX"};
        //{"..........", ".X.XXXXXX.", "XX...X.*..", "X..X.XMXX."};
                //{".XMX.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X*X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", "........................................."};
                //{".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", "M........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", ".XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".........................................", "XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX.", ".X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.X.", ".*......................................."};
        System.out.println(decisions(maze));
    }

    public static int decisions(String[] maze) {
        int n= maze.length;
        int m= maze[0].length();
        Node[][] nodes = new Node[n][m];
        Node src=null, dest=null;
        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                nodes[i][j] = new Node(i,j,i,j);
                if(maze[i].charAt(j)=='M')
                    src = nodes[i][j];
                if(maze[i].charAt(j)=='*')
                    dest = nodes[i][j];
            }
        }

        doBFS(src, dest, maze, nodes);
        int decision = traversePath(src, dest, nodes, maze);
        return decision;
    }

    static int traversePath(Node src, Node dest, Node[][] nodes, String maze[]) {
        if(dest.py==dest.y && dest.px==dest.x) {
            return 0;
        }

        Node curNode = nodes[dest.px][dest.py];
        int cx = dest.x;
        int cy = dest.y;
        int decisions=0;

        while(true) {
            int curX = curNode.x;
            int curY = curNode.y;
            for(int i=0;i<4;i++) {
                int newX = curX+d[0][i];
                int newY = curY+d[1][i];
                if(checkValid(newX, newY, nodes.length, nodes[0].length) &&
                        maze[newX].charAt(newY)=='.' &&
                        !(newX==cx && newY==cy) &&
                        !(newX==curNode.px && newY==curNode.py)) {
                    decisions++;
                    break;
                }
            }

            cx=curX;
            cy=curY;
            if(curNode==src)
                break;
            curNode = nodes[curNode.px][curNode.py];
        }
        return decisions;
    }

    static void doBFS(Node src, Node dest, String[] maze, Node[][] nodes) {
        if(src==null||dest==null || src==dest)
                return ;

        int n= maze.length;
        int m= maze[0].length();

        Queue<Node> q = new LinkedList<>();
        q.add(src);

        src.visited=true;
        while(!q.isEmpty()) {
            Node front = q.poll();
            int x = front.x;
            int y = front.y;
            if(x==dest.x && y==dest.y) {
                return;
            }
            for(int i=0;i<4;i++) {
                int newX = x+d[0][i];
                int newY = y+d[1][i];
                if(checkValid(newX, newY, n, m) &&
                        maze[newX].charAt(newY)!='X' &&
                        nodes[newX][newY].visited!=true) {
                    nodes[newX][newY].visited = true;
                    nodes[newX][newY].px = x;
                    nodes[newX][newY].py = y;
                    q.add(nodes[newX][newY]);
                }
            }
        }
    }

    static boolean checkValid(int x, int y, int n, int m) {
        return x>=0 && x<n && y>=0 && y<m;
    }
}
