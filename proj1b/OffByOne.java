public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        int res;
        res = x - y;
        return (Math.abs(res) == 1);

    }
}
