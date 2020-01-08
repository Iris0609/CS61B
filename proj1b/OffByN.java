public class OffByN implements CharacterComparator {
    private int N;
    public OffByN(int n) {
        N = n;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int res;
        res = x - y;
        return (Math.abs(res) == N);

    }
}
