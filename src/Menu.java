import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;

public class Menu {
    static Quarto standard = new Quarto("Standard", 100f);
    static Quarto luxo = new Quarto("Luxo", 200f);
    static Quarto presidencial = new Quarto("Presidencial", 350f);
    static void main() {
        Scanner input = new Scanner(System.in);
        Reserva[] reservas = new Reserva[10];
        int reservations = 0;
        IO.println("Boas-vindas! O que deseja fazer?");
        while(reservations < reservas.length){
            printMenu();
            switch (input.nextInt()){
                case 1:
                    Reserva aux = newReservation();

                    if(aux != null){
                        reservas[reservations] = aux;
                        reservations++;
                    };
                    break;
                case 2:
                    sortReservations(reservas);
                    break;
                case 3:
                    searchReservation(reservas);
                    break;
                case 4:
                    return;
                default:
                    IO.println("Informe uma opção válida");
                    break;
            }
        }

    }

    static void printMenu(){
        String[] options = new String[]{
                "1. Nova reserva",
                "2. Listar reservas",
                "3. Buscar reserva por nome",
                "4. Sair"
        };
        IO.println("Menu de opções");
        IO.println("_____________");
        for (String option : options){
            IO.println(option);
        }
    }


    static Reserva newReservation() {
        Scanner input = new Scanner(System.in);

        Quarto room = null;
        int duration;

        IO.println("Informe o nome do hóspede: ");
        String guest = input.nextLine();

        do {
            IO.println("Qual o tipo de quarto? (Digite apenas o número)");
            IO.println("1. Standard \n2. Luxo \n3. Presidencial");

            switch (input.nextInt()) {
                case 1:
                    room = standard;
                    break;
                case 2:
                    room = luxo;
                    break;
                case 3:
                    room = presidencial;
                    break;
                default:
                    IO.println("Digite um valor válido");
            }

        } while (Objects.equals(room, null));

        IO.println("Para quantos dias é a reserva? (Apenas números)");

        do {
            duration = input.nextInt();

            if (duration <= 0) {
                IO.println("Informe um valor válido:");
            }
        } while (duration <= 0);

        Reserva temp = new Reserva(guest, room, duration);

        IO.println("Deseja confirmar a reserva? (1 = Sim / 2 = Não)");
        IO.println(temp);

        if (input.nextInt() == 1) {
            IO.println("Reserva confirmada!");
            return temp;
        } else {
            IO.println("Reserva cancelada");
            return null;
        }
    }


    static void searchReservation(Reserva[] reservas){
        Scanner input = new Scanner(System.in);
        IO.println("Informe o nome do hóspede:");
        String guest = input.nextLine();

        IO.println("Reservas encontradas:");
        boolean found = false;
        for (Reserva reserva : reservas){
            if (reserva != null && reserva.getGuest().contains(guest)){
                IO.println(reserva);
                IO.println("_______________");
                found = true;
            }
        }
        if(!found){IO.println("Nenhuma reserva encontrada para esse hóspede");}
    }

    static void sortReservations(Reserva[] reservas){
        Scanner input = new Scanner(System.in);
        IO.println("Como deseja ordenar as reservas?");
        IO.println("1. Por número de diárias \n2. Por valor total da reserva");
        int option = input.nextInt();

        reservas = Arrays.stream(reservas).filter(Objects::nonNull).toArray(Reserva[]::new);

        switch (option){
            case 1:
                for (int i = 0; i < reservas.length; i++) {
                    for (int j = i; j < reservas.length; j++) {
                        if(reservas[j].getDuration() > reservas[i].getDuration()){
                            Reserva aux = reservas[i];
                            reservas[i] = reservas[j];
                            reservas[j] = aux;
                        }
                    }
                }
                break;
            case 2:
                for (int i = 0; i < reservas.length; i++) {
                    for (int j = i; j < reservas.length; j++) {
                        if(reservas[j].getTotalValue() > reservas[i].getTotalValue()){
                            Reserva aux = reservas[i];
                            reservas[i] = reservas[j];
                            reservas[j] = aux;
                        }
                    }
                }
                break;
            default:
                break;
        }

        for(Reserva reserva : reservas){
            IO.println(reserva);
            IO.println("_____________");
        }
    }
}
