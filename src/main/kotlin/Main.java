import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        while (true) {
            Scanner capt = new Scanner(System.in);

            System.out.println("\u001B[43m\u001B[30m\u001B[1mBIENVENIDO AL BUSCAMINAS POR JOHN LARGAO\u001B[0m");
            System.out.println("1.Ayuda");
            System.out.println("2.Comenzar partida");
            System.out.println("3.Cerrar programa");
            try {
                Integer opcionInicial = Integer.valueOf(capt.nextLine());
                if (opcionInicial == 2) {
                    System.out.println("Que tamaño quieres para el tablero?");
                    Integer tamañoTablero = Integer.valueOf(capt.nextLine());
                    System.out.println("Con que dificultad quieres jugar? Recuerda 1 para facil, 2 intermedia, 3 dificil");
                    Integer dificultad = Integer.valueOf(capt.nextLine());;
                    System.out.println("La partida esta apunto de empezar, buena suerte...");
                    //Thread.sleep(1000);
                    Tablero mapa = new Tablero(tamañoTablero, dificultad);  // Tamaño del tablero
                    Partidas partida = new Partidas(mapa);
                    /* partida.laPartida(mapa, "JOHN")*/
                    //partida.laPartida(mapa,"Jhn")

                    Partidas partidaJava = new Partidas(mapa);
                    partidaJava.laPartida(mapa,"john");

                    break;
                } else if (opcionInicial == 1) {
                    System.out.println("\u001B[43m\u001B[30m\u001B[1mMENU DE AYUDA\u001B[0m");
                    System.out.println("La partida consta de pantallas que tienes que ir superando, el objetivo es destapar toda casilla que no contenga una mina.");
                    System.out.println("El juego consta de 3 dificultades: 1 facil, 2 intermedia, 3 dificil. La diferencia entre ellas es la cantidad de minas que contendra el tablero.");
                    System.out.println("Pulsa B para colocar banderas, estas te ayudaran a marcar casillas donde crees que hay una mina.");
                    System.out.println("Pulsa H en cualquier momento de la partida para ver este menu de ayuda.");
                    System.out.println();
                    Thread.sleep(3000);
                } else if (opcionInicial == 3) {
                    break;
                }
                else {
                    System.out.println("Opcion incorrecta, vuelve a intentarlo.");
                    System.out.println();
                }
            } catch ( NumberFormatException e) {
                System.out.println("Opcion incorrecta, vuelve a intentarlo.");
                System.out.println();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}





