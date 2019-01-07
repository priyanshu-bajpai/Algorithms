public class QuadTreeIImpl {

    static public class Node {
        int minx;
        int miny;
        int maxx;
        int maxy;

        int val;
        int ycord;
        int xcord;
        int sum;
        Node quad[];

        public Node (int xmax, int xmin, int ymin, int ymax, int val, int xcoord, int ycoord) {
            minx=xmin;
            miny=ymin;
            maxx = xmax;
            maxy=ymax;

            this.val=val;
            quad = new Node[4];
            xcord = xcoord;
            ycord = ycoord;
            sum=val;
        }
    }

    public static void main(String args[]) {
        Node root = addVal(null, 3, 7, 1, Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MAX_VALUE);
        root = addVal(root, 8, 1, 3);
        root = addVal(root, 6, 6, 60);
        root = addVal(root, 2, 6, 2);
        root = addVal(root, -1, 7, 8);
        root = addVal(root, 8, 6, 2);
        root = addVal(root, 5, 9, 2);

        updateVal(root, 8,6,12);
        System.out.println(query(root, -2, 0, 15, 15));
    }

    public static Node addVal(Node root, int x, int y, int val) {
        return addVal(root, x, y, val, Integer.MIN_VALUE, Integer.MAX_VALUE,
                Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static Node addVal(Node root, int x, int y, int val, int minx, int maxx, int miny, int maxy) {
        if(root==null) {
            return createNode(x, y, val, minx, maxx,
                    miny, maxy);
        }
        else {

            if(x>=root.xcord && y>=root.ycord) {
                root.quad[0] = addVal(root.quad[0], x, y, val, root.xcord, root.maxx,
                        root.ycord, root.maxy);
            }
            else if(x>=root.xcord && y<root.ycord) {
                root.quad[1] = addVal(root.quad[1], x, y, val, root.xcord, root.maxx,
                        root.miny, root.ycord);
            }
            else if(x<root.xcord && y<root.ycord) {
                root.quad[2] = addVal(root.quad[2], x, y, val, root.minx, root.xcord,
                        root.miny, root.ycord);
            }
            else {
                root.quad[3] = addVal(root.quad[3], x, y, val, root.minx, root.xcord,
                        root.ycord, root.maxy);
            }
        }

        root.sum+=val;
        return root;
    }

    static int query(Node root, int x1, int y1, int x2, int y2) {
        if(root==null) {
            return 0;
        }

        if(x1<=root.minx && x2>=root.maxx &&
                y1<=root.miny && y2>=root.maxy) {
            return root.sum;
        }

        int sum =  query(root.quad[0], x1, y1, x2, y2) + query(root.quad[1], x1, y1, x2, y2)+
        query(root.quad[2], x1, y1, x2, y2)+query(root.quad[3], x1, y1, x2, y2);
        int x=root.xcord;
        int y=root.ycord;

        if(x1<=x && x2>=x && y1<=y && y2>=y) {
            sum+=root.val;
        }

        return sum;
    }



    public static int updateVal(Node root, int x, int y, int val) {
        int diff=0;
        if(root==null) {
            return diff;
        }

        if(root.xcord == x && root.ycord==y) {
            diff= val-root.val;
            root.sum+=diff;
            root.val=val;
            return diff;
        }

        else {
            if(x>=root.xcord && y>=root.ycord) {
                diff = updateVal(root.quad[0], x, y, val);
            }
            else if(x>=root.xcord && y<root.ycord) {
                diff = updateVal(root.quad[1], x, y, val);
            }
            else if(x<root.xcord && y<root.ycord) {
                diff = updateVal(root.quad[2], x, y, val);
            }
            else {
                diff = updateVal(root.quad[3], x, y, val);
            }
        }

        root.sum+=diff;
        return diff;
    }

    static Node createNode(int xcord, int ycord, int val, int minx,
                           int maxX, int miny, int maxY) {
        return new Node( maxX, minx, miny, maxY, val, xcord, ycord);
    }
}
