package LLD.FileStorageSystem;

class FileSystemManager {
    private Folder root;

    public FileSystemManager(User rootUser) {
        this.root = new Folder("root", rootUser);
    }

    public Folder getRoot() {
        return root;
    }

    public void printFileSystem() {
        root.print(0);
    }
}
