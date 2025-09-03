package LLD.ChatApp;

import java.util.*;

public class ChatService {
    private Map<String, List<Message>> messageStore;

    public ChatService() {
        messageStore = new HashMap<>();
    }

    public void sendMessage(String senderId, String receiverId, String content) {
        String messageId = UUID.randomUUID().toString();
        Message message = new Message(messageId, senderId, receiverId, content, System.currentTimeMillis());
        String chatKey = getChatKey(senderId, receiverId);
        messageStore.putIfAbsent(chatKey, new ArrayList<>());
        messageStore.get(chatKey).add(message);
    }

    public List<Message> getChatHistory(String user1, String user2) {
        String chatKey = getChatKey(user1, user2);
        return messageStore.getOrDefault(chatKey, new ArrayList<>());
    }

    private String getChatKey(String user1, String user2) {
        return user1.compareTo(user2) < 0 ? user1 + "_" + user2 : user2 + "_" + user1;
    }
}