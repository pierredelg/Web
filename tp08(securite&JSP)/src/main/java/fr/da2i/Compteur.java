package fr.da2i;

public class Compteur {
    private int compteur;

    public void incr(){
        compteur++;
    }

    @Override
    public String toString() {
        return "" + compteur;
    }
}
