
import java.util.ArrayList;


import java.util.Scanner;


class Partidas {

    Tablero tablero;
    static Integer contadorPantallas = 0;

    public Partidas(Tablero tablero) {
        this.tablero = tablero;
    }

    public static void laPartida(Tablero tablero, String nombre) throws InterruptedException {
        Scanner capt = new Scanner(System.in);

        while (true) {

            ArrayList<Integer> noHayMinas = tablero.noHayMinas();
            if (noHayMinas.isEmpty()) {
                tablero.posicionMinas();
                System.out.println("Se ha generado un tablero sin minas, intenta generar un tablero de mayor tamaño.");
                break;
            }
            ArrayList<Integer> minasRestantes = tablero.minasRestantes();
            if (minasRestantes.isEmpty()) {
                System.out.println("\033[42m\033[30m\033[1mHas ganado " + nombre + " !!!\033[0m");
                System.out.println("\033[42m\033[30m\033[1mAqui estaban las minas: \033[0m");
                tablero.posicionMinas();
                break;
            }

            tablero.printaTablero();

            String posicion = capt.nextLine();
            posicion = posicion.toLowerCase();


            int checkIfExists = tablero.checkIfExists(posicion);

            tablero.transformaPrimeraMina(posicion, contadorPantallas);
            if (checkIfExists != 0) {
                System.out.println("La posición introducida no existe, vuelve a probar");
            } else if (posicion.equals("b")) {
                System.out.println("Donde quieres colocar la bandera" + " " + nombre + "?");
                String bandera = capt.nextLine();
                int checkIfExistsForFlag = tablero.checkIfExists(bandera);
                if (checkIfExistsForFlag != 0) {
                    System.out.println("La posición introducida no existe, vuelve a probar");
                } else tablero.marcaBanderas(bandera);
            }
            int checkSiEsMina = tablero.checkSiEsMina(posicion.toUpperCase());

            if (checkSiEsMina == 1) {
                tablero.aplicaMinas(posicion);
                System.out.println("\033[1mBuena elección, la posición " + "\033[1;32m" + posicion + "\033[0m" + " no contiene ninguna mina.");
                mensajes();
                contadorPantallas++;
            }
            if (checkSiEsMina == 4) {
                System.out.println("\033[1;31m!!!!!!!!!!!!BOOOOOOOOOOOOOOOM¡¡¡¡¡¡¡¡¡¡¡¡¡¡\033[0m");
                tablero.posicionMinas();
                System.out.println("La posicion" + " " + "\033[1;31m" + posicion + "\033[0m" + " " + "contenia una mina, has perdido " + nombre);
                System.out.println("\033[1m\033[43m\033[30mHas superado" + " " + contadorPantallas + " " + "pantalla/s.\033[0m");
                break;
            }
            if (checkSiEsMina == 2) {
                System.out.println("Ya has elejido esta casilla anteriormente");
                System.out.println();
            }
            if (checkSiEsMina == 3) {
                System.out.println("\u001B[43m\u001B[30m\u001B[1mMENU DE AYUDA\u001B[0m");
                System.out.println("La partida consta de pantallas que tienes que ir superando, el objetivo es destapar toda casilla que no contenga una mina.");
                System.out.println("El juego consta de 3 dificultades: 1 facil, 2 intermedia, 3 dificil. La diferencia entre ellas es la cantidad de minas que contendra el tablero.");
                System.out.println("Pulsa B para colocar banderas, estas te ayudaran a marcar casillas donde crees que hay una mina.");
                System.out.println("Pulsa H en cualquier momento de la partida para ver este menu de ayuda.");
                System.out.println();
            }

        }
    }


    public static void printLento(String frase) throws InterruptedException {
        for (int i = 0; i < frase.length(); i++) {
            System.out.print(frase.charAt(i));
            Thread.sleep(50);
        }
        System.out.println();
    }

    public static void mensajes() {
        ArrayList<String> arrayConMensajes = new ArrayList<>();

        arrayConMensajes.add("Estás un paso más cerca de la victoria!");
        arrayConMensajes.add("Continúa así!");
        arrayConMensajes.add("Ya te queda una casilla menos!");
        arrayConMensajes.add("No te tiembla el pulso!");

        int randomIndex = (int) (Math.random() * arrayConMensajes.size());
        System.out.println("\033[1m\033[43m\033[30m" + arrayConMensajes.get(randomIndex) + "\033[0m");
    }


}