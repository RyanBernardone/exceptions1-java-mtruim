package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    //"throws ParseException" basicamente diz que o metodo main pode lançar uma excessão do tipo
    //ParseException
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.print("Número do quarto: ");
            int number = sc.nextInt();
            System.out.print("Data de check-in (DD/MM/AAAA): ");
            Date checkIn = sdf.parse(sc.next());
            System.out.print("Data de check-out (DD/MM/AAAA): ");
            Date checkOut = sdf.parse(sc.next());


            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reserva: \n" + reservation.toString());

            System.out.println();
            System.out.println("Insira novas datas de check-in e check-out: ");
            System.out.print("Data de check-in (DD/MM/AAAA): ");
            checkIn = sdf.parse(sc.next());
            System.out.print("Data de check-out (DD/MM/AAAA): ");
            checkOut = sdf.parse(sc.next());

            reservation.updateDates(checkIn, checkOut);
            System.out.println("Reserva: \n" + reservation.toString());
        }catch (ParseException err){
            System.out.println("Formato de data inválido!");
        }catch (DomainException err){
            System.out.println("Erro na reserva: " + err.getMessage());
        }catch (RuntimeException err){
            System.out.println("Um erro inesperado aconteceu!");
        }

        sc.close();
    }
}
