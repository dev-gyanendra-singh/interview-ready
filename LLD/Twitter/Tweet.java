package LLD.Twitter;

public class Tweet {
    private long tweetID;
    private long userID;
    private long timestamp;
    String text;

    public Tweet(long tweetID, long userID, long timestamp, String text) {
        this.tweetID = tweetID;
        this.userID = userID;
        this.timestamp = timestamp;
        this.text = text;
    }

    public long getTweetID() {
        return tweetID;
    }

    public void setTweetID(long tweetID) {
        this.tweetID = tweetID;
    }

    public long getUserID() {
        return userID;
    }

    public void setUserID(long userID) {
        this.userID = userID;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
