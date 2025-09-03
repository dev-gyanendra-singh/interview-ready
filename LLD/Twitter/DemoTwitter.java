package LLD.Twitter;

import java.util.*;
// A -> B
public class DemoTwitter {
    public static void main(String[] args) {

        TwitterService twitter = new TwitterService();

        twitter.createUser(1);
        twitter.createUser(2);

        twitter.postTweet(1, 101, 1,  "posting 1.2.3......");
        twitter.postTweet(2, 2,3,"posting 3.1.1......");

        twitter.follow(1, 2);

        List<Tweet> feed = twitter.getNewsFeed(1);
        for (Tweet t : feed) {
            System.out.println("Tweet ID: " + t.getTweetID() + " by User " + t.getUserID());
        }

        twitter.unfollow(1, 2);

        feed = twitter.getNewsFeed(1);
        System.out.println("After unfollowing:");
        for (Tweet t : feed) {
            System.out.println("Tweet ID: " + t.getTweetID() + " by User " + t.getUserID());
        }
    }
}
