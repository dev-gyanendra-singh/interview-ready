package LLD.FileStorageSystem;

public class FileStorageDemo {
    public static void main(String[] args) {
        User alice = new User("u1", "Alice");
        User bob = new User("u2", "Bob");

        FileSystemManager fs = new FileSystemManager(alice);

        Folder projectFolder = new Folder("ProjectX", alice);
        fs.getRoot().addEntity(projectFolder);

        File readme = new File("README.txt", alice, "Initial content");
        projectFolder.addEntity(readme);

        readme.setPermission(bob, PermissionType.READ);
        projectFolder.setPermission(bob, PermissionType.READ);

        readme.updateContent("Updated content");

        fs.printFileSystem();
    }

}
