package main.magazzino.articolo;

public class Articolo {
    private String nome;
    private int quantità;
    private boolean èInSconto;
    private double prezzo;

    /**
     COSTRUTTORI
     *                                                    */
    public Articolo(String nome, int quantità, boolean èInSconto,double prezzo) {
        this.nome = nome;
        this.quantità = quantità;
        this.èInSconto = èInSconto;
        this.prezzo = prezzo;
    }
    public Articolo(String nome, int quantità,double prezzo) {
        this();
        this.nome = nome;
        this.quantità = quantità;
        this.prezzo= prezzo;

    }
    public Articolo() {
        this("NomeDefault",0,false,0.00);
    }
    public Articolo(String nome, int quantità) {
        this.nome = nome;
        this.quantità = quantità;
    }

    /**
     *                          GET
     * */
    public String getNome() {
        return nome;
    }
    public int getQuantità() {
        return quantità;
    }
    public boolean isèInSconto() {
        return èInSconto;
    }
    public double getPrezzo() {
        return prezzo;
    }
    /**
     *                          SET
     * */

    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setQuantità(int quantità) {
        this.quantità = quantità;
    }
    public void setèInSconto(boolean èInSconto) {
        this.èInSconto = èInSconto;
    }
    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public int aggiungiQuantità(int quantitàDaAggiungere){
        this.quantità+=quantitàDaAggiungere;
        return getQuantità();
    }
    @Override
    public String toString() {
        return "Articolo:  "+ nome + ", quantità= " + quantità + ", prezzo=" + prezzo + (èInSconto ? " è in sconto":" non è in sconto ");
    }
}
