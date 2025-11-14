public class SumOperation implements Operations<Long>{
    @Override
    public Long apply(Long a, Long b) {
        return a + b;
    }
    @Override
    public Long inverse(Long a, Long b) {
        return a - b;
    }
    @Override
    public Long identity() {
        return 0L;
    }
}
