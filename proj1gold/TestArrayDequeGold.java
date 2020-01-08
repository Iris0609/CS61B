import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {
    static StudentArrayDeque<Integer> sad = new StudentArrayDeque<>();
    static ArrayDequeSolution<Integer> ads = new ArrayDequeSolution<>();
    @Test
    public void testStudentArrayDeque() {

        boolean flag = true;
        int cnt = 0;
        String message = "";

        while (flag) {
            double rand = StdRandom.uniform();
            int randint = StdRandom.uniform(100);
            int s = ads.size();
            Integer correct, incorrect;



            if (rand <0.25) {
                sad.addLast(randint);
                ads.addLast(randint);
                correct = ads.get(ads.size() - 1);
                incorrect = sad.get(ads.size() - 1);
                message += "addLast(" + randint +") \n";
            } else if (rand < 0.5 && s > 0) {
                incorrect = sad.removeFirst();
                correct = ads.removeFirst();
                message += "removeFirst() \n";
            } else if (rand < 0.75 && s > 0) {
                incorrect = sad.removeLast();
                correct = ads.removeLast();
                message += "removeLast() \n";
            } else {
                sad.addFirst(randint);
                ads.addFirst(randint);
                correct = ads.get(0);
                incorrect = sad.get(0);
                message += "addFirst(" + randint +") \n";
            }

//            if(correct != incorrect) {
//                flag = false;
//            }
           // System.out.println(message);
            assertEquals(message, correct, incorrect);


            cnt += 1;

        }




    }

}
