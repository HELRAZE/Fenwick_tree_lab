public class Tree {
    final private int[] tree;
    final private int size;

    public Tree(int arr_size) {
        this.size = arr_size;
        this.tree = new int[size + 1];
    }

    public int getSize()
    {
        return size;
    }

    public void build(int[] arr) { //итеративно вызывается update для каждого эл-та массива
        for (int i = 0;i < size; ++i) {
            update(i, arr[i]);
        }
    }

    public void printTree() //печать
    {
        for (int i = 1; i < size + 1; ++i)
        {
            System.out.println(tree[i]);
        }
    }

    public void update(int index, int delta) {
        int i = index + 1;// индексация с 1
        while (i <= size) {
            tree[i] += delta;
            i += (i & (-i));
        }
    }

    public int getPrefixSum(int index) {
        int i = index + 1;
        int sum = 0;

        while (i > 0) {
            sum += tree[i];
            i -= (i & (-i));
        }
        return sum;
    }

    public int getRangeSum(int left, int right) {
        int sumToRight = getPrefixSum(right);
        int sumBeforeLeft = 0;
        if (left > 0) {
            sumBeforeLeft = getPrefixSum(left - 1);
        }
        return sumToRight - sumBeforeLeft;
    }

}
