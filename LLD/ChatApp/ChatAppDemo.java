package LLD.ChatApp;

import java.util.List;

public class ChatAppDemo {
    public static void main(String[] args) {
        ChatService chat = new ChatService();

        chat.sendMessage("Alice", "Bob", "Hi Bob!");
        chat.sendMessage("Bob", "Alice", "Hello Alice!");
        chat.sendMessage("Alice", "Bob", "How are you?");

        List<Message> history = chat.getChatHistory("Bob", "Alice");
        for (Message msg : history) {
            System.out.println(msg.senderId + " -> " + msg.receiverId + ": " + msg.content);
        }
    }
}
