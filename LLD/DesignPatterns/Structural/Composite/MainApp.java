package LLD.DesignPatterns.Structural.Composite;

public class MainApp {
    public static void main(String[] args) {
        FileSystemComponent file1 = new File("Resume.docx");
        FileSystemComponent file2 = new File("Budget.xlsx");

        Folder subFolder = new Folder("Work");
        subFolder.setComponent(file1);
        subFolder.setComponent(file2);

        FileSystemComponent file3 = new File("Song.mp3");
        Folder rootFolder = new Folder("MyDocuments");
        rootFolder.setComponent(subFolder);
        rootFolder.setComponent(file3);

        rootFolder.showDetails();
    }
}

