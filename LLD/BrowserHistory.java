package LLD;

import java.util.*;

class BrowserHistory {
    List<String> history;
    int currentIndex;

    public BrowserHistory(String homepage) {
        history = new ArrayList<>();
        history.add(homepage);
        currentIndex = 0;
    }

    public void visit(String url) {
        // Remove all forward history
        history = history.subList(0, currentIndex + 1);
        history.add(url);
        currentIndex++;
    }

    public String back(int steps) {
        currentIndex = Math.max(0, currentIndex - steps);
        return history.get(currentIndex);
    }

    public String forward(int steps) {
        currentIndex = Math.min(history.size() - 1, currentIndex + steps);
        return history.get(currentIndex);
    }

    public static void main(String[] args) {
        BrowserHistory browser = new BrowserHistory("leetcode.com");
        browser.visit("google.com");       // leetcode → google
        browser.visit("facebook.com");     // google → facebook
        browser.visit("youtube.com");      // facebook → youtube

        System.out.println(browser.back(1));     // facebook.com
        System.out.println(browser.back(1));     // google.com
        System.out.println(browser.forward(1));  // facebook.com

        browser.visit("linkedin.com");     // clears forward; now on linkedin
        System.out.println(browser.forward(2));  // still linkedin.com
        System.out.println(browser.back(2));     // google.com
        System.out.println(browser.back(7));     // leetcode.com
    }
}
