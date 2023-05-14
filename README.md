# Test ingresso REGESTA
Questo è l'esercizio assegnatomi da Regesta come test d'ingresso.

Si tratta di un programma per la gestione di un magazzino, dove chi compra ha la possibilità di scegliere tra molteplici venditori

## Architettura
+ L'input viene fornito da console.
+ è stato usato mySQL come DB
+ stata anche aggiunta una funzione di login
## Utilizzo
Viene richiesta una autenticazione (Registrazione/Login)

in caso di registrazione verranno richiesti diversi input per immagazzinare le info su db, mentre per il login avviene una verifica dei dati sul DB degli utenti già registrati, se combaciano si può accedere.

Dopo la verifica in ingresso le scelte possibili dipendono dal campo "è venditore" scelto durante la registrazione.

Un venditore può aggiungere articoli al suo magazzino.
Un privato può acquistare articoli.