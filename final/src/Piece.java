public class Piece {
    String name;
    char unicode;

    public Piece(String name, char unicode) {
        this.name = name;
        this.unicode = unicode;
    }

    public String getName (){
        return name;
    }

    public char getUnicode (){
        return unicode;
    }
}
