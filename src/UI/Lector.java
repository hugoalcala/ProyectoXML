package UI;

import java.util.InputMismatchException;
import java.util.Scanner;


public class Lector {
    public static int pedirInt(String texto){
        int pedido = 0;
        try{
            Scanner sc = new Scanner(System.in);
            System.out.println(texto);
            pedido = sc.nextInt();
            sc.nextLine();
            return pedido;
        }catch (InputMismatchException e){
            System.out.println("El escaner de numeros falla");
        }
        return pedido;
    }
    public static String pedirString(String texto){
        String pedido = "";
        try {
            Scanner sc = new Scanner(System.in);
            System.out.println(texto);
            pedido = sc.nextLine();
            return pedido;
        }catch (Exception e){
            System.out.println("El escaner de texto falla");
        }
        return pedido;
    }

}
