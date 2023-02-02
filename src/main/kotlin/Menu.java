import java.util.Scanner;

public class Menu {

    public static void menu() {
        System.out.println("\u001B[43m\u001B[30m\u001B[1mBIENVENIDO AL BUSCAMINAS POR JOHN LARGAO\u001B[0m");
        while (true) {
            Scanner capt = new Scanner(System.in);

            System.out.println("1.Ayuda");
            System.out.println("2.Comenzar partida");
            System.out.println("3.Cerrar programa");
            try {
                int opcionInicial = Integer.parseInt(capt.nextLine());
                if (opcionInicial == 2) {
                    System.out.println("Que tamaño quieres para el tablero?");
                    int tableroSize = Integer.parseInt(capt.nextLine());
                    if (tableroSize > 26) {
                        System.out.println("El tamaño maximo del tablero es 26. Vuelve a intentarlo.");
                        menu();
                    }
                    System.out.println("Con que dificultad quieres jugar? Recuerda 1 para facil, 2 intermedia, 3 dificil");
                    int dificultad = Integer.parseInt(capt.nextLine());
                    if (dificultad != 1 && dificultad != 2 && dificultad != 3) {
                        System.out.println("Solo hay 3 dificultades, 1 facil, 2 intermedia, 3 dificil.");
                        menu();
                    }
                    System.out.println("Introduce tu nombre: ");
                    String nombre = capt.nextLine();
                    System.out.println("La partida esta apunto de empezar, buena suerte...");
                    Tablero mapa = new Tablero(tableroSize, dificultad);  // Tamaño del tablero
                    Partidas.printLento("Cargando partida ...");
                    Partidas.laPartida(mapa, nombre);
                    break;
                } else if (opcionInicial == 1) {
                    System.out.println("\u001B[43m\u001B[30m\u001B[1mMENU DE AYUDA\u001B[0m");
                    System.out.println("La partida consta de pantallas que tienes que ir superando, el objetivo es destapar toda casilla que no contenga una mina.");
                    System.out.println("El juego consta de 3 dificultades: 1 facil, 2 intermedia, 3 dificil. La diferencia entre ellas es la cantidad de minas que contendra el tablero.");
                    System.out.println("Pulsa B para colocar banderas, estas te ayudaran a marcar casillas donde crees que hay una mina.");
                    System.out.println("Pulsa H en cualquier momento de la partida para ver este menu de ayuda.");
                    System.out.println();
                } else if (opcionInicial == 3) {
                    break;
                } else {
                    System.out.println("Opcion incorrecta, vuelve a intentarlo.");
                    System.out.println();
                }
            } catch (NumberFormatException e) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo.");
                System.out.println();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }


}
