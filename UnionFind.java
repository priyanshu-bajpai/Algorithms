import java.util.HashSet;

public class FriendlyRooks {

//    public static void main(String args[]) {
//        String[] board = new String []{"....R..........",
//                ".R...........R.",
//                "....R..........",
//                ".R........R....",
//                "....R..........",
//                "....R.....R...."};
//        System.out.println(getMinFriendlyColoring(board));
//    }

    public static int getMinFriendlyColoring(String[] board) {
        int n=board.length;
        int m=board[0].length();
        int [] parent = new int[n*m];
        for(int i=0;i<parent.length;i++) {
            parent[i] = i;
        }

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(board[i].charAt(j)=='R') {
                    unionRows(i, j, board, parent);
                    unionCols(i, j, board, parent);
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<n;i++) {
            for (int j = 0; j < m; j++) {
                if(board[i].charAt(j)=='R') {
                    set.add(find(parent, getIndex(i, j, board[i].length())));
                }
            }
        }

        return set.size();
    }

    static void unionRows(int row, int col,String [] board, int [] parent) {
        int cols = board[row].length();
        for(int j=0;j<cols;j++) {
            if(board[row].charAt(j)=='R') {
                union(parent, getIndex(row, j, cols), getIndex(row, col, cols));
            }
        }
    }

    static void unionCols(int row, int col,String [] board, int [] parent) {
        for(int i=0;i<board.length;i++) {
            if(board[i].charAt(col)=='R') {
                int cols = board[i].length();
                union(parent, getIndex(i, col, cols), getIndex(row, col, cols));
            }
        }
    }

    static void union(int[] parent, int idx1, int idx2) {
        if(find(parent, idx1)!=find(parent, idx2)) {
            parent[find(parent, idx1)] = parent[find(parent, idx2)];
        }
    }

    static int find(int[] parent, int idx) {
        if(parent[idx]==idx)
            return idx;
        parent[idx] = find(parent, parent[idx]);
        return parent[idx];
    }

    static int getIndex(int row, int col, int width) {
        return row*width + col;
    }
}
