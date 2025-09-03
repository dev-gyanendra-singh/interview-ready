package LLD.DesignTextEditer;

import java.util.*;

class Char {
    char c;
    boolean bold;
    boolean italic;
    boolean underline;

    public Char(char c) {
        this.c = c;
        this.bold = false;
        this.italic = false;
        this.underline = false;
    }

    public Char(char c, boolean bold, boolean italic, boolean underline) {
        this.c = c;
        this.bold = bold;
        this.italic = italic;
        this.underline = underline;
    }

    // Deep copy constructor
    public Char(Char other) {
        this.c = other.c;
        this.bold = other.bold;
        this.italic = other.italic;
        this.underline = other.underline;
    }

    @Override
    public String toString() {
        StringBuilder style = new StringBuilder();
        if (bold) style.append("B");
        if (italic) style.append("I");
        if (underline) style.append("U");
        if (style.length() == 0) style.append("N");
        return c + "(" + style.toString() + ")";
    }
}

class TextEditor {
    private List<Char> document;
    private Deque<List<Char>> undoStack;
    private Deque<List<Char>> redoStack;

    public TextEditor() {
        document = new ArrayList<>();
        undoStack = new ArrayDeque<>();
        redoStack = new ArrayDeque<>();
    }

    private List<Char> deepCopy(List<Char> list) {
        List<Char> copy = new ArrayList<>();
        for (Char ch : list) {
            copy.add(new Char(ch));
        }
        return copy;
    }

    private void saveState() {
        undoStack.push(deepCopy(document));
        redoStack.clear();
    }

    public void insert(int pos, String text) {
        if (pos < 0 || pos > document.size()) {
            throw new IndexOutOfBoundsException("Invalid position");
        }
        saveState();
        for (int i = 0; i < text.length(); i++) {
            document.add(pos + i, new Char(text.charAt(i)));
        }
    }

    public void delete(int pos, int length) {
        if (pos < 0 || pos + length > document.size()) {
            throw new IndexOutOfBoundsException("Invalid position or length");
        }
        saveState();
        for (int i = 0; i < length; i++) {
            document.remove(pos);
        }
    }

    public void applyStyle(int start, int end, Boolean bold, Boolean italic, Boolean underline) {
        if (start < 0 || end > document.size() || start > end) {
            throw new IndexOutOfBoundsException("Invalid start or end");
        }
        saveState();
        for (int i = start; i < end; i++) {
            Char ch = document.get(i);
            if (bold != null) ch.bold = bold;
            if (italic != null) ch.italic = italic;
            if (underline != null) ch.underline = underline;
        }
    }

    public void undo() {
        if (undoStack.isEmpty()) {
            System.out.println("Nothing to undo");
            return;
        }
        redoStack.push(deepCopy(document));
        document = undoStack.pop();
    }

    public void redo() {
        if (redoStack.isEmpty()) {
            System.out.println("Nothing to redo");
            return;
        }
        undoStack.push(deepCopy(document));
        document = redoStack.pop();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Char ch : document) {
            sb.append(ch.toString());
        }
        return sb.toString();
    }
}

public class TextEditer {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.insert(0, "Hello World");
        System.out.println("After insert: " + editor);

        editor.applyStyle(6, 11, true, null, null); // bold "World"
        System.out.println("After bold 'World': " + editor);

        editor.delete(5, 1); // delete space
        System.out.println("After deleting space: " + editor);

        editor.undo();
        System.out.println("After undo: " + editor);

        editor.redo();
        System.out.println("After redo: " + editor);
    }
}
