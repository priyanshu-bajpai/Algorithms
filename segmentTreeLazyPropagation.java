class Solution {
    static int []lazy;

    public List<Integer> countSmaller(int[] nums) {
        if(nums.length==0)
            return new ArrayList<>();

        HashMap<Integer, Integer> compressed = getCompressedMap(nums);
        int n = compressed.size();
        int x = (int) (Math.ceil(Math.log(n) / Math.log(2)));
        int max_size = 2 * (int) Math.pow(2, x) - 1;
        
        int[] tree = new int[4*n];
        lazy = new int[4*n];

        Integer[] res = new Integer[nums.length];
        for (int i=nums.length-1;i>=0;i--) {
            int st = compressed.get(nums[i]);
            int end = n-1;

            res[i]  = rangeQuery(tree, 0, 0, n-1, st, st);
            updateRange(tree, 0, 0, n-1, st+1, end, 1);
        }

        return Arrays.asList(res);
    }

    static void updateRange(int[] tree, int node, int start, int end, int l, int r, int val) {
        if(start>end || start>r || end<l) {
            return;
        }
        
        int left = node*2+1;
        int right = node*2+2;
        if(lazy[node]!=0) {
            tree[node]+=(end-start+1) * lazy[node];
            if(start!=end) {
                lazy[left] += lazy[node];
                lazy[right] += lazy[node];
            }
            lazy[node]=0;
        }

        if(start>=l && end<=r) {
            tree[node] +=(end-start+1)*val;
            if(start!=end) {
                lazy[left]+=val;
                lazy[right]+=val;
            }

            return;
        }

        int mid = (start+end)/2;
        updateRange(tree, left, start, mid, l, r, val);
        updateRange(tree, right, mid+1, end, l, r, val);
        tree[node]=tree[left]+tree[right];
        return;
    }


    static int rangeQuery(int[] tree, int node, int start, int end, int l, int r) {
        if (start > end || start > r || end < l) {
            return 0;
        }

        int left = node * 2 + 1;
        int right = node * 2 + 2;

        if (lazy[node] != 0) {
            tree[node] += (end - start + 1) * lazy[node];
            if(start!=end) {
                lazy[left] += lazy[node];
                lazy[right] += lazy[node];
            }
            lazy[node] = 0;
        }

        if (start >= l && end <= r) {
            return tree[node];
        }

        int mid = (start + end) / 2;
        return rangeQuery(tree, left, start, mid, l, r) +
                rangeQuery(tree, right, mid + 1, end, l, r);
    }

    static HashMap<Integer, Integer> getCompressedMap(int [] nums) {
        HashMap<Integer, Integer> map = new HashMap<>();
        HashSet<Integer> set = new HashSet<>();
        for(int i=0;i<nums.length;i++) {
            set.add(nums[i]);
        }
        Integer[] arr = set.toArray(new Integer[set.size()]);
        Arrays.sort(arr);

        for(int i=0;i<arr.length;i++) {
            map.put(arr[i], i);
        }

        return map;
    }
}