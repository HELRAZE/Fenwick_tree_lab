public interface Operations<T> {
    T apply(T a, T b);
    T inverse(T a, T b);
    T identity();
}
