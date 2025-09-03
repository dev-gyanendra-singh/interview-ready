package LLD.FileStorageSystem;

import java.util.ArrayList;
import java.util.List;

class Folder extends FileSystemEntity {
    private List<FileSystemEntity> children;

    public Folder(String name, User owner) {
        super(name, owner);
        this.children = new ArrayList<>();
    }

    public void addEntity(FileSystemEntity entity) {
        children.add(entity);
    }

    public void removeEntity(String entityId) {
        children.removeIf(child -> child.getId().equals(entityId));
    }

    public List<FileSystemEntity> getChildren() {
        return children;
    }

    @Override
    public void print(int indent) {
        System.out.println("  ".repeat(indent) + "+ Folder: " + name);
        for (FileSystemEntity child : children) {
            child.print(indent + 1);
        }
    }

}

