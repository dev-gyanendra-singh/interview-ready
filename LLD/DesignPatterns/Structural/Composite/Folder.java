package LLD.DesignPatterns.Structural.Composite;

import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent{
    private String name;
    List<FileSystemComponent> components = new ArrayList<FileSystemComponent>();
    Folder(String name){
        this.name = name;
    }

    void setComponent(FileSystemComponent component){
        components.add(component);
    }

    void removeComponent(FileSystemComponent component){
        components.remove(component);
    }

    @Override
    public void showDetails() {
       for(FileSystemComponent component : components){
           component.showDetails();
       }
    }
}
