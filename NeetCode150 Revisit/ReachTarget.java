package ReviseAgain;

public class ReachTarget {

        /*
        *
        *You are standing at position 0 on an infinite number line. There is a destination at position target.

        You can make some number of moves numMoves so that:

        On each move, you can either go left or right.
        During the ith move (starting from i == 1 to i == numMoves), you take i steps in the chosen direction.
        Given the integer target, return the minimum number of moves required (i.e., the minimum numMoves) to reach the destination.
        *
        *
        *
        * */


    public int reachNumber(int target) {
        target = Math.abs(target);
        int move = 0;
        int sum = 0;
        while(true) {
            move++;
            sum += target;
            if(sum >= target && (sum - target) % 2 == 0 ) { // sum aage to nikale hi... ni nikalta to even position pr phuche jisse...ki peeche wale kisi ak step pr back jaake shi kr ske use...odd pr ni kr painge
                return move;
            }
        }
    }
}
