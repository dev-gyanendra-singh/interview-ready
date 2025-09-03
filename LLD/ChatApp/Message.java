package LLD.ChatApp;

public class Message {
    String messageId;
    String senderId;
    String receiverId;
    String content;
    long timestamp;

    public Message(String messageId, String senderId, String receiverId, String content, long timestamp) {
        this.messageId = messageId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.content = content;
        this.timestamp = timestamp;
    }
}
