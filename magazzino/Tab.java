package main.magazzino;

import main.magazzino.articolo.Articolo;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.*;

public class Tab {

    private String url;
    private Connection conn;
    private String userName;
    private String userPassword;/** verranno presi da user, ricordarsi la distinzione di venditore-compratore**/
    private boolean èVenditore;


    public boolean isèVenditore() {
        return èVenditore;
    }

    /**
     * Stabilisce una connessione a DB e inserisce la cartella di USERS se non presente (cartella credenziali)
     * */
    public Tab(String address,String dbName,String dbUser,String password) throws SQLException { /** da sistemare */
        this.url = "jdbc:mysql://"+address+"/"+"myshop";
        System.out.println(this.url);
        this.conn = DriverManager.getConnection(this.url,"root","");
        Statement st = this.conn.createStatement();
        st.executeUpdate("create table if not exists Magazzini(id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(25) NOT NULL, password VARCHAR(25) NOT NULL," +
                " venditore BOOLEAN NOT NULL,tempiDiConsegna INT, sogliaScontoNPezzi INT," +
                "                         scontoNPezzi DOUBLE," +
                "                         sogliaPerScontoTotale DOUBLE," +
                "                          scontoSuTotale DOUBLE)" );
    }

    /**
     * classico login
     * le credenziali vanno a cambiare i field di questo oggetto, per rendere più scorrevole il resto del codice
     * */
    public boolean login (String username, String userPassword) throws SQLException{

        PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM Magazzini where nome=? and password=?");
        pst.setString(1,username);
        pst.setString(2,userPassword);
        ResultSet re = pst.executeQuery();
        if(re.next()){
            this.userName=username;
            this.userPassword=userPassword;
            this.èVenditore=re.getBoolean("venditore");
            return true;
        }
        return false;
    }
    /**
     * registra solo se nome diverso.
     * return: TRUE se op riesce e crea magazzino
     * FALSE se op trova un altro nome uguale
     * */
    public boolean registrazione (String userName, String userPassword,boolean èVenditore) throws SQLException{
        PreparedStatement pst = this.conn.prepareStatement("SELECT id FROM Magazzini where nome=?");
        pst.setString(1,userName);
        ResultSet re = pst.executeQuery();
        if(re.next()){
            return false;
        }
        createMagazzino(userName,userPassword,èVenditore,0,0,0,0,0);
        this.userName=userName;
        this.userPassword=userPassword;
        this.èVenditore=èVenditore;
        return true;
    }
    public boolean registrazione (String userName,
                                  String userPassword,
                                  boolean èVenditore,
                                  int tempiDiConsegna,
                                  int sogliaScontoNPezzi,
                                  double scontoNPezzi,
                                  double sogliaPerScontoTotale,
                                  double scontoSuTotale) throws SQLException{
        PreparedStatement pst = this.conn.prepareStatement("SELECT id FROM Magazzini where nome=?");
        pst.setString(1,userName);
        ResultSet re = pst.executeQuery();
        if(re.next()){
            return false;
        }
        createMagazzino(userName,userPassword,èVenditore,tempiDiConsegna,sogliaScontoNPezzi,scontoNPezzi,sogliaPerScontoTotale,scontoSuTotale);
        this.userName=userName;
        this.userPassword=userPassword;
        this.èVenditore=èVenditore;
        return true;
    }
    /**
     * metodo per REGISTRAZIONE():
     * crea un magazzino di nome MagazzinoDi+username
     * inserisce i field di user per velocizzare il codice
     * */
    private void createMagazzino(String userName,
                                 String userPassword,
                                 boolean èVenditore,
                                 int tempiDiConsegna,
                                 int sogliaScontoNPezzi,
                                 double scontoNPezzi,
                                 double sogliaPerScontoTotale,
                                 double scontoSuTotale) throws SQLException{
        this.userName=userName;
        this.userPassword=userPassword;
        Statement st = this.conn.createStatement();
        st.execute("create table if not exists MagazzinoDi"+this.userName+"(id INT PRIMARY KEY AUTO_INCREMENT, nome VARCHAR(25) NOT NULL, quantità INT NOT NULL, scontato BOOLEAN NOT NULL, prezzo DOUBLE NOT NULL)");
        PreparedStatement pst= this.conn.prepareStatement("INSERT into Magazzini(nome,password,venditore,tempiDiConsegna,sogliaScontoNPezzi,scontoNPezzi,sogliaPerScontoTotale,scontoSuTotale) values(?,?,?,?,?,?,?,?)");
        pst.setString(1,this.userName);
        pst.setString(2,this.userPassword);
        pst.setBoolean(3,èVenditore);
        pst.setInt(4,tempiDiConsegna);
        pst.setInt(5,sogliaScontoNPezzi);
        pst.setDouble(6,scontoNPezzi);
        pst.setDouble(7,sogliaPerScontoTotale);
        pst.setDouble(8,scontoSuTotale);
        pst.executeUpdate();
    }

    /**
     * Restituisce una lista di chi Vende e basta
     * */
    public List<String> listaMagazziniVenditori ()throws SQLException{
        PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM Magazzini where venditore=1");
        ResultSet rst=pst.executeQuery();
        List<String> risultato=new ArrayList<>();
        while(rst.next()) {
            risultato.add(rst.getString("nome"));
        }
        return risultato;

    }
    /**
     * Restituisce una lista di chi può comprare
     * */
    public List<String> listaMagazziniPrivati ()throws SQLException{
        PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM Magazzini where venditore=0");
        ResultSet rst=pst.executeQuery();
        List<String> risultato=new ArrayList<>();
        while(rst.next()) {
            risultato.add(rst.getString("nome"));
            System.out.println(risultato);
        }
        return risultato;
    }

    public List<String>listaPossessoriArticolo (String nomeArticolo,int quantità)throws SQLException{
        List<String> venditori=listaMagazziniVenditori();
        List<String> venditoriPassati=new ArrayList<>();
        for (int i = 0; i < venditori.size(); i++) {
            Articolo uso= trovaArticolo(venditori.get(i),nomeArticolo,quantità);
           // System.out.println(uso);
            if(uso==null){
                continue;
            }
            venditoriPassati.add(venditori.get(i));
        }
        return venditoriPassati;
    }

    public void stampaPossibilitàAcquisto(String nomeArticolo,int quantità)throws SQLException{
        List<String> listaPossibiliVenditori= listaPossessoriArticolo(nomeArticolo,quantità);
        for (int i=0; i<listaPossibiliVenditori.size();i++){
            PreparedStatement pst=this.conn.prepareStatement("SELECT tempiDiConsegna FROM Magazzini where nome=?");
            pst.setString(1,listaPossibiliVenditori.get(i));
            ResultSet rst = pst.executeQuery();
            rst.next();
            String aggiunta= " tempi di consegna:"+rst.getString("tempiDiConsegna");
            listaPossibiliVenditori.set(i,listaPossibiliVenditori.get(i).concat(aggiunta));
        }
        listaPossibiliVenditori.forEach(System.out::println);
    }

    public void stampaPossibilitàAcquisto(List<String> venditoriPossibili,String nomeArticolo,int quantità)throws SQLException{
        for (int i=0; i<venditoriPossibili.size();i++){
            PreparedStatement pst=this.conn.prepareStatement("SELECT * FROM Magazzini where nome=?");
            pst.setString(1,venditoriPossibili.get(i));
            ResultSet rst = pst.executeQuery();
            rst.next();
            String aggiunta= " tempi di consegna:"+rst.getString("tempiDiConsegna");
            int sogliaNPz= rst.getInt("sogliaScontoNPezzi");
            double scontoNPz =rst.getDouble("scontoNPezzi");
            double sogliaScontoTot= rst.getDouble("sogliaPerScontoTotale");
            double scontoSuTot= rst.getDouble("scontoSuTotale");
            pst = this.conn.prepareStatement("SELECT id FROM MagazzinoDi"+venditoriPossibili.get(i)+" where nome=?");
            pst.setString(1,nomeArticolo);
            ResultSet prezzo= pst.executeQuery();
            prezzo.next();
            pst= this.conn.prepareStatement("SELECT prezzo FROM MagazzinoDi"+venditoriPossibili.get(i)+" where id=?");
            pst.setInt(1,prezzo.getInt("id"));
            prezzo=pst.executeQuery();
            prezzo.next();
            double prezzoD= faIlPrezzo(prezzo.getDouble("prezzo"),quantità,sogliaNPz,scontoNPz,sogliaScontoTot,scontoSuTot);

            String aggiuntaPrezzoCalcolato="  prezzo proposto:"+prezzoD;
            venditoriPossibili.set(i,venditoriPossibili.get(i).concat(aggiunta).concat(aggiuntaPrezzoCalcolato));
        }
        for (int i = 0; i < venditoriPossibili.size(); i++) {
            System.out.println("Scelta:"+i+" "+venditoriPossibili.get(i));
        }
    }
    public double faIlPrezzo(double prezzo,int quantità,int sogliaNPezzi,double scontoNPezzi,double sogliaPerScontoTotale,double scontoSuTotale){
        boolean controlloQuantità= quantità>=sogliaNPezzi;
        double prezzoTotale= controlloQuantità ? ((prezzo*quantità)/100)*(100-scontoNPezzi) : (prezzo*quantità);
        controlloQuantità=prezzoTotale>= sogliaPerScontoTotale;
        prezzoTotale= controlloQuantità? (prezzoTotale/100)*(100-scontoSuTotale) : prezzoTotale;
        return prezzoTotale;
    }





    public void comprare(String nomeArticolo,int quantità)throws SQLException{
        List<String> venditoriPossibili=listaPossessoriArticolo(nomeArticolo,quantità);
        if (!venditoriPossibili.isEmpty()){
            List<String> copia=List.copyOf(venditoriPossibili);
            int index = sceltaVenditore(venditoriPossibili,nomeArticolo,quantità);
            comprareFinale(copia.get(index),nomeArticolo,quantità);

        }else {
            System.out.println("Non ci sono venditori disponibili per la sua richiesta");
        }
    }
    public int sceltaVenditore(List<String> venditoriPossibili,String nomeArticolo,int qt)throws SQLException,RuntimeException {
        Scanner scanner2 = new Scanner(System.in);
        stampaPossibilitàAcquisto(venditoriPossibili,nomeArticolo,qt);

        int scelta=0;
        do {
        System.out.print("\nInserire scelta:");
        scelta = scanner2.nextInt();
            System.out.println(scelta);
        if(scelta<0||scelta>=venditoriPossibili.size()){
            System.out.println("Scelta non possibile");
            continue;
        }
        break;
        }while(true);
        //scanner2.close(); mi da errore
        return scelta;

    }
    public void comprareFinale(String sceltaMagazzinoVenditore,String nomeArticolo,int quantitàDiProdotto)throws SQLException{

        Statement stm =this.conn.createStatement();
        PreparedStatement prst =this.conn.prepareStatement("SELECT * FROM MagazzinoDi"+sceltaMagazzinoVenditore+" where nome=?");
        prst.setString(1,nomeArticolo);
        ResultSet id=prst.executeQuery();
        id.next();
        System.out.println(id.getString("nome")+","
                +id.getInt("quantità")+","
                +id.getBoolean("scontato")+","
                +id.getDouble("prezzo")+")"+"username: "+this.userName);
        int resset= stm.executeUpdate("UPDATE MagazzinoDi"+ sceltaMagazzinoVenditore+" SET quantità= quantità -"+quantitàDiProdotto+ " WHERE id="+id.getInt("id") );
        prst =this.conn.prepareStatement("SELECT id FROM MagazzinoDi"+this.userName+" where nome=?");
        prst.setString(1,nomeArticolo);
        ResultSet  rst =prst.executeQuery();
        if(rst.next()){
            resset = prst.executeUpdate("UPDATE MagazzinoDi" + this.userName + " SET quantità= quantità +" + quantitàDiProdotto + " WHERE id=" + id.getInt("id"));
        }else {
            System.out.println("ciao");
            System.out.println(id.getString("nome"));
            System.out.println("ciao");
            stm.execute("INSERT INTO MagazzinoDi" + this.userName + " (nome,quantità,scontato,prezzo) values ('" + id.getString("nome") + "',"
                    + id.getInt("quantità") + ","
                    + id.getBoolean("scontato") + ","
                    + id.getDouble("prezzo") + ")");
        }
    }

    public void close() throws SQLException{
        this.conn.close();
    }
    /**
     * Trova articolo tramite nome in magazzino di chi richiama il metodo
     * */
    public Articolo trovaArticolo (String nomeArticolo) throws SQLException{
        PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM MagazzinoDi"+this.userName+" where nome=?");
        pst.setString(1,nomeArticolo);
        ResultSet re = pst.executeQuery();
        if(re.next()) {
            return new Articolo(nomeArticolo,re.getInt("quantità"),re.getBoolean("scontato"),re.getDouble("prezzo") );
        }
        return null;
    }
    /**
     * Trova articolo tramite NOME nel magazzino passato
     * */
    public Articolo trovaArticolo (String nomeArticolo,String nomeMagazzino) throws SQLException{
        PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM "+nomeMagazzino+" where nome=?");
        pst.setString(1,nomeArticolo);
        ResultSet re = pst.executeQuery();
        if(re.next()) {
            return new Articolo(nomeArticolo,re.getInt("quantità"),re.getBoolean("scontato"),re.getDouble("prezzo") );
        }
        return null;
    }

    public Articolo trovaArticolo (String nomeMagazzino,String nomeArticolo,int richiesta) throws SQLException{
        PreparedStatement pst = this.conn.prepareStatement("SELECT * FROM MagazzinoDi"+nomeMagazzino+" where nome=? and quantità>=?");
        pst.setString(1,nomeArticolo);
        pst.setInt(2,richiesta);
        ResultSet re = pst.executeQuery();
        if(re.next()) {
            if (re.getInt("quantità")<richiesta){
                return null;
            }
            return new Articolo(nomeArticolo,re.getInt("quantità"),re.getBoolean("scontato"),re.getDouble("prezzo") );
        }
        return null;
    }

    /**
     * inserisce articolo nel proprio magazzino utilizzando raw data
     * */
    public void inserisciArticolo (String nome, int quantità, boolean scontato, double prezzo) throws SQLException{
        PreparedStatement pst = this.conn.prepareStatement("INSERT into magazzinodi"+this.userName+"(nome,quantità,scontato,prezzo) values(?,?,?,?)");
        pst.setString(1,nome);
        pst.setInt(2,quantità);
        pst.setBoolean(3,scontato);
        pst.setDouble(4,prezzo);
        pst.execute();
    }

    /**
     * inserisce articolo nel proprio magazzino utilizzando Articolo esistente
     * */
    public void inserisciArticolo (Articolo articolo) throws SQLException{
        PreparedStatement pst = this.conn.prepareStatement("INSERT into magazzinodi"+this.userName+"(nome,quantità,scontato,prezzo) values(?,?,?,?)");
        pst.setString(1,articolo.getNome());
        pst.setInt(2,articolo.getQuantità());
        pst.setBoolean(3,articolo.isèInSconto());
        pst.setDouble(4,articolo.getPrezzo());
        pst.execute();
    }
    public void stampaPropriaLista()throws SQLException{
        Statement stm = this.conn.createStatement();
        ResultSet rst= stm.executeQuery("SELECT * FROM MagazzinoDi"+this.userName);
        while(rst.next()){

        }
    }
}


