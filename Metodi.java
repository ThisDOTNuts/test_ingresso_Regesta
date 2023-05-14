package main;

import main.magazzino.Tab;

import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Metodi {
    private Scanner scanner = new Scanner(System.in);




    public void loginMenuVisual(){

        System.out.println("Benvenuto\n"+
                            "1) Registrati\n"+
                                "2) Accedi\n"+
                                    "3)Esci");
        System.out.print("Scegli la tua operazione: ");

    }
    public int loginMenu( Tab db)throws SQLException,RuntimeException{
        loginMenuVisual();
        if (scanner.hasNextInt()){
            int scelta= scanner.nextInt();

            switch (scelta) {
                case 1:
                    chiamaRegistrazione(db);
                    break;
                case 2:
                    chiamaLogin(db);
                    break;
                case 3:
                    return scelta;
                default:
                    loginMenu(db);
            }
        }
        return 0;
    }
    public void chiamaLogin(Tab db)throws SQLException,RuntimeException{
        while(true) {
            System.out.print("Inserisci username:");
            String username = scanner.next();
            System.out.println();
            System.out.print("Inserisci password:");
            String password = scanner.next();
            if(db.login(username, password)){
                break;
            }
            System.out.println("I dati Inseriti non sono corretti, riprova");
        }
    }
    public void chiamaRegistrazione(Tab db)throws SQLException,RuntimeException{
        while(true) {
            System.out.print("Inserisci username:");
            String username = scanner.next();
            System.out.println();
            System.out.print("Inserisci password:");
            String password = scanner.next();
            System.out.println("Sei un venditore? ");
            boolean isSeller=siNo();
            if (!isSeller){
                if (db.registrazione(username,password,isSeller)) {
                    break;
                }
                System.out.println("Esiste già un utente con questo nome");
                continue;
            }
            scanner.nextLine();
            System.out.print("Inserisci i tempi di consegna (in numero di giorni):");
            int nGiorni= scanner.nextInt();
            System.out.println();
            System.out.print("inserisci la soglia da raggiungere per applicare lo sconto in base al numero dei pezzi:");
            int sogliaNPz=scanner.nextInt();
            System.out.println();
            System.out.println("Inserisci lo sconto in % da applicare quando viene superata la soglia dei pezzi:");
            double scontoNPz = scanner.nextDouble();
            System.out.println();
            System.out.println("inserisci la soglia da raggiungere per applicare lo sconto in base alla spesa totale su un articolo:");
            double sogliaTot= scanner.nextDouble();
            System.out.println();
            System.out.println("Inserisci lo sconto in % da applicare quando viene superata la soglia della spesa totale:");
            double scontoTot = scanner.nextDouble();
            if (db.registrazione(username,password,isSeller,nGiorni,sogliaNPz,scontoNPz,sogliaTot,scontoTot)){
                break;
            }
            System.out.println("Errore in registrazione, ricominciare");
        }
    }


    private boolean siNo() throws RuntimeException{
        boolean isSeller;
        while (true) {
            System.out.print("1: SI  2: NO");
            int venditore =scanner.nextInt();
            if (venditore>2||venditore<=0){
                System.out.println("errore, scegli di nuovo");
                continue;
            }
            isSeller = venditore==1;
            break;
        }
        return isSeller;
    }

    public int switchMenù (Tab db) throws SQLException{
        if (db.isèVenditore()){
            return venditoreMenù(db);
        }
        return privatoMenù(db);

    }
    public int privatoMenù(Tab db)throws SQLException,RuntimeException{

            System.out.println("Scegli una opzione\n"+"1)Compra articolo\n"+"2)Esci");
            int scelta = scanner.nextInt();
        System.out.println(scelta+"porca madonna");
                switch (scelta) {
                    case 1:
                        richiamoCompra(db);
                        return scelta;
                    case 2:
                        return scelta;

                    default:
                        System.out.println("Scelta non contemplata");
                }
                return 0;
    }

    public void richiamoCompra (Tab db) throws SQLException,RuntimeException{
        System.out.print("Inserisci nome articolo:");
        String nomeArticolo = scanner.next();
        System.out.println();
        System.out.print("Inserisci numero pezzi articolo:");
        scanner.nextLine();
        int nPezzi= scanner.nextInt();
        db.comprare(nomeArticolo,nPezzi);
    }


    public int venditoreMenù (Tab db)throws SQLException,RuntimeException{
        while(true) {
            System.out.println("Scegli una opzione\n"+"1)Inserisci articolo\n"+"2)Esci");
            int scelta = scanner.nextInt();
            switch (scelta) {
                case 1:
                    richiamoIserisciArticolo(db);
                    return scelta;
                case 2:
                    return scelta;
                default:
                    System.out.println("Scelta non contemplata");
            }
        }

    }


    public void richiamoIserisciArticolo(Tab db) throws SQLException,RuntimeException{
        System.out.print("Inserisci nome articolo:");
        String nomeArticolo = scanner.next();
        System.out.println();
        System.out.print("Inserisci numero pezzi articolo:");
        scanner.nextLine();
        int nPezzi= scanner.nextInt();
        System.out.println();
        scanner.nextLine();
        System.out.print("Inserisci prezzo articolo:");
        scanner.nextLine();
        double prezzo= scanner.nextDouble();
        System.out.println();
        db.inserisciArticolo(nomeArticolo,nPezzi,false,prezzo);


    }



}
