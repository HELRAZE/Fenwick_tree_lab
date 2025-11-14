public class FenwickTree<T> {
    final private T[] tree;
    final private int size;
    final private Operations<T> oper;

    public FenwickTree(int arr_size, Operations<T> oper) {
        this.size = arr_size;
        this.oper = oper;
        this.tree = (T[]) new Object[size + 1];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = oper.identity();
        }
    }

    public int getSize() {
        return size;
    }

    public void build(T[] arr) { //итеративно вызывается update для каждого эл-та массива
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

    public void update(int index, T delta) {
        int i = index + 1;// индексация с 1
        while (i <= size) {
            tree[i] = oper.apply(tree[i], delta);
            i += (i & (-i));
        }
    }

    public T getPrefixSum(int index) {
        int i = index + 1;
        T sum = oper.identity();

        while (i > 0) {
            sum = oper.apply(sum, tree[i]);
            i -= (i & (-i));
        }
        return sum;
    }

    public T getRangeSum(int left, int right) {
        T sumToRight = getPrefixSum(right);
        if (left == 0) {
            return sumToRight;
        }
        T sumBeforeLeft = getPrefixSum(left - 1);

        return oper.inverse(sumToRight, sumBeforeLeft);
    }

}
