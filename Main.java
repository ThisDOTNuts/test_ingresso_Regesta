package main;

import main.magazzino.Tab;

import java.sql.SQLException;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Tab tab = new Tab("localhost","myshop","root","");
        Metodi menu= new Metodi();

        LOOP_1 :while(true)
            if(menu.loginMenu(tab)!=3) {
                while(true) {
                    if (menu.switchMenù(tab) == 2) {
                    break LOOP_1;
                }

            }

        }
        tab.close();
    }catch (ClassNotFoundException | SQLException e){
        e.printStackTrace();
    }
        catch (RuntimeException g){
            g.printStackTrace();
            System.out.println(Arrays.toString(g.getStackTrace()));
            System.out.println("Input Miss Match");
            System.out.println("Utilizza i caratteri corretti");
        }
    }

}