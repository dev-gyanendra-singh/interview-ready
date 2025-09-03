package LLD.Twitter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class TwitterService {
    private final Map<Integer, User> userMap;

    public TwitterService() {
        this.userMap = new HashMap<>();
    }

    public void createUser(int userId) {
        userMap.putIfAbsent(userId, new User(userId));
    }

    public void postTweet(int userId, int tweetId, long timestamp, String tweetMessage) {
        User user = getOrCreateUser(userId);
        Tweet tweet = new Tweet(tweetId, userId, timestamp, tweetMessage);
        user.postTweet(tweet);
    }

    private User getOrCreateUser(int userId) {
       return userMap.computeIfAbsent(userId, k -> new User(userId));
    }

    public void follow(int followerId, int followeeId) {
        User follower = getOrCreateUser(followerId);
        User followee = getOrCreateUser(followeeId);

        follower.follow(followeeId);
        followee.addFollower(followerId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return; // can't unfollow yourself

        User follower = getOrCreateUser(followerId);
        User followee = getOrCreateUser(followeeId);

        follower.unfollow(followeeId);
        followee.removeFollower(followerId);
    }

    public Set<Integer> getFollowers(int userId) {
        return getOrCreateUser(userId).getFollowers();
    }

    public Set<Integer> getFollowing(int userId) {
        return getOrCreateUser(userId).getFollowing();
    }

    public List<Tweet> getNewsFeed(int userId) {
        User user = getOrCreateUser(userId);
        Set<Integer> sources = new HashSet<>(user.getFollowing());
        sources.add(userId); // include own tweets manually

        PriorityQueue<Tweet> pq = new PriorityQueue<>(
                (a, b) -> Integer.compare((int)b.getTimestamp(), (int)a.getTimestamp())
        );

        for (int id : sources) {
            List<Tweet> tweets = getOrCreateUser(id).getTweets();
            for (Tweet t : tweets) {
                pq.offer(t);
            }
        }

        List<Tweet> result = new ArrayList<>();
        int count = 0;
        while (!pq.isEmpty() && count < 10) {
            result.add(pq.poll());
            count++;
        }

        return result;
    }



}
