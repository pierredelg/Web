package fr.da2i;

public class Message {
    private int i;

    public Message(){
        i=0;
    }

    public String toString(){
        i++;
        return "C'est le "+i+"ème Hello World !";
    }
}
