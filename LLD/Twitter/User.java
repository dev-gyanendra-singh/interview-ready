package LLD.Twitter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
    private final int userId;
    private final List<Tweet> tweets;
    private final Set<Integer> followers;
    private final Set<Integer> following;

    public User(int userId) {
        this.userId = userId;
        this.tweets = new ArrayList<>();
        this.followers = new HashSet<>();
        this.following = new HashSet<>();
    }

    public int getUserId() {
        return userId;
    }

    public List<Tweet> getTweets() {
        return tweets;
    }

    public Set<Integer> getFollowers() {
        return followers;
    }

    public Set<Integer> getFollowing() {
        return following;
    }

    public void postTweet(Tweet tweet) {
        tweets.add(tweet);
    }

    public void follow(int followeeId) {
        following.add(followeeId);
    }

    public void unfollow(int followeeId) {
        following.add(followeeId);
    }

    public void addFollower(int followerId) {
        followers.add(followerId);
    }

    public void removeFollower(int followerId) {
        followers.remove(followerId);
    }

}
