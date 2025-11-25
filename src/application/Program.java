package application;

import model.entities.Reservation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    //"throws ParseException" basicamente diz que o metodo main pode lançar uma excessão do tipo
    //ParseException
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.print("Número do quarto: ");
        int number = sc.nextInt();
        System.out.print("Data de check-in (DD/MM/AAAA): ");
        Date checkIn = sdf.parse(sc.next());
        System.out.print("Data de check-out (DD/MM/AAAA): ");
        Date checkOut = sdf.parse(sc.next());

        //Essa válidação deveria ser feita no construtor, mas o construtor não pode retornar Strings
        if (!checkOut.after(checkIn)){
            System.out.println("Datas inválidas! Check-in precisa ser feito antes do check-out");
        }else {
            Reservation reservation = new Reservation(number, checkIn, checkOut);
            System.out.println("Reserva: \n" + reservation.toString());

            System.out.println();
            System.out.println("Insira novas datas de check-in e check-out: ");
            System.out.print("Data de check-in (DD/MM/AAAA): ");
            checkIn = sdf.parse(sc.next());
            System.out.print("Data de check-out (DD/MM/AAAA): ");
            checkOut = sdf.parse(sc.next());

            String error = reservation.updateDates(checkIn, checkOut);
            if (error != null){
                System.out.println("Erro na reserva: " + error);
            }else{
                System.out.println("Reserva: \n" + reservation.toString());
            }
        }
    }
}
