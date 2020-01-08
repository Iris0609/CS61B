public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque q = new LinkedListDeque();
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            q.addLast(c);
        }

        return q;
    }
    public boolean isPalindrome(String word) {

        /** iterate
        if (word.length() < 2) {
            return true;
        }

        int half;
        half = word.length() / 2;
        for (int i = 0; i<half; i++) {
            if (word.charAt(i) != word.charAt(word.length() - i - 1)) {
                return false;
            }
        }
        return true;*/

        /** recursive without Deque
        return isPalindromeHelper(word);
        */
        /** recursive with Deque*/
        Deque q = wordToDeque(word);
        return isPalidromeHelper(q);

    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque q = wordToDeque(word);

        return isPalidromeHelper(q, cc);


    }

    private boolean isPalindromeHelper(String word) {
        if (word.length() < 2) {
            return true;
        } else {
            return (word.charAt(0) == word.charAt(word.length() - 1)) && isPalindromeHelper(word.substring(1, word.length() - 1));
        }


    }


    private boolean isPalidromeHelper(Deque<Character> wordDeque) {
        Character first, last;
        if (wordDeque.size() < 2) {
            return true;
        }
        first =  wordDeque.removeFirst();
        last = wordDeque.removeLast();

        return (first == last) && isPalidromeHelper(wordDeque);

    }

    private boolean isPalidromeHelper(Deque<Character> wordDeque, CharacterComparator cc) {
        Character first, last;
        if (wordDeque.size() < 2) {
            return true;
        }
        first =  wordDeque.removeFirst();
        last = wordDeque.removeLast();

        return (cc.equalChars(first, last)) && isPalidromeHelper(wordDeque, cc);

    }


}
