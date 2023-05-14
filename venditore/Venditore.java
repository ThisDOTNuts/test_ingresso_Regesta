//package main.venditore;
//
//import main.magazzino.Magazzino;
//import main.magazzino.articolo.Articolo;
//
//public class Venditore {
//    private final String name;
//    private Magazzino magazzino;
//
//    public Venditore(String name, Magazzino magazzino) {
//        this.name = name;
//        this.magazzino = magazzino;
//    }
//
//    public Venditore(String name) {
//        this.name = name;
//        this.magazzino= new Magazzino("Magazzino di "+name);
//    }
//    public boolean aggiungiArticolo (Articolo articolo)throws NullPointerException{
//        return this.magazzino.aggiungiArticolo(articolo);
//    }
//    public boolean aggiungiArticolo(String nome,
//                                    int quantità,
//                                    boolean èInSconto,
//                                    double prezzo) {
//        return this.magazzino.aggiungiArticolo(nome, quantità, èInSconto, prezzo);
//    }
//
//    public String aggiungiQuantitàArticolo(Articolo articolo, int quantitàDaAggiungere) throws NullPointerException{
//        return this.magazzino.aggiungiQuantitàArticolo(articolo,quantitàDaAggiungere);
//
//    }
//    public String togliQuantitàArticolo(Articolo articolo, int quantitàDaTogliere)throws NullPointerException{
//        return this.magazzino.togliQuantitàArticolo(articolo,quantitàDaTogliere);
//    }
//    public int getQuantitàArticolo(Articolo articolo)throws NullPointerException{
//        return this.magazzino.getQuantitàArticolo(articolo);
//    }
//
//    public static class MyShop {
//        private final String name;
//        private Magazzino magazzino;
//
//        public MyShop(String name, Magazzino magazzino) {
//            this.name = name;
//            this.magazzino = magazzino;
//        }
//    }
//}
