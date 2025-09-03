package LLD.FileStorageSystem;

import java.util.ArrayList;
import java.util.List;

class File extends FileSystemEntity {
    private String content; // In real systems, this would be a file pointer or storage path
    private List<String> versionHistory;

    public File(String name, User owner, String content) {
        super(name, owner);
        this.content = content;
        this.versionHistory = new ArrayList<>();
        this.versionHistory.add(content); // initial version
    }

    public void updateContent(String newContent) {
        versionHistory.add(newContent);
        this.content = newContent;
    }

    public String getContent() {
        return content;
    }

    @Override
    public void print(int indent) {
        System.out.println("  ".repeat(indent) + "- File: " + name);
    }
}
