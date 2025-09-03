package LLD.DesignPatterns.Structural.Composite;

import java.awt.*;

public class File implements FileSystemComponent{
    String name;
    File(String name){
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("Name: " + name + " File: " + name);
    }
}
