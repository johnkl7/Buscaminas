import java.util.ArrayList;
import java.util.Scanner;

class Partidas  {
    /*val size = tablero.size*/

    static Integer contadorPantallas = 0;
    Tablero tablero;

    public Partidas (Tablero tablero) {
        this.tablero = tablero;
    }

    public static void laPartida (Tablero tablero, String nombre) throws InterruptedException {
        Scanner capt = new Scanner(System.in);
        /*if (contadorPantallas == 0){
            slowPrint("Cargando partida...");}*/
        while (true) {
            tablero.posicionMinas();
            ArrayList<Integer> noHayMinas = tablero.noHayMinas();
            if (noHayMinas.isEmpty()) {
                tablero.posicionMinas();
                System.out.println("Se ha generado un tablero sin minas, intenta generar un tablero de mayor tamaño.");
                break;
            }
            ArrayList<Integer> minasRestantes = tablero.minasRestantes();
            if (minasRestantes.isEmpty()) {
                System.out.println("Has ganado $nombre!");
                System.out.println("Aqui estaban las minas: ");
                tablero.posicionMinas();
                break;
            }

            tablero.printaTablero();

            String posicion = capt.nextLine();

            //String posicionToLowerCase = capt.nextLine().toLowerCase();

            int checkIfExists = tablero.checkIfExists(posicion);

            tablero.transformaPrimeraMina(posicion, contadorPantallas);
            if (checkIfExists != 0) {
                System.out.println("La posición introducida no existe, vuelve a probar");
                laPartida(tablero,nombre);
                /*tablero.partida(mapa, nombre)*/
            }

            else if (posicion.equals("B") || posicion.equals("b")) {
                System.out.println("Donde quieres colocar la bandera" + " " + nombre + "?");
                String bandera = capt.nextLine();
                tablero.marcaBanderas(bandera);
            }

            Integer check = tablero.check(posicion.toUpperCase());
            if (check == 1) {
                tablero.aplicaMinas(posicion);

                System.out.println("Buena elección...");
                contadorPantallas++;
            }
            if (check == 0) {
                System.out.println("!!!!!!!!!!!!BOOOOOOOOOOOOOOOM¡¡¡¡¡¡¡¡¡¡¡¡¡¡");
                tablero.posicionMinas();
                System.out.println(nombre + " " + "has superado" + " " + contadorPantallas + " " + "pantallas.");
                break;
            }
            if (check == 2) {
                System.out.println("Ya has elejido esta casilla anteriormente");
                System.out.println();
            }
            if (check == 3){
                System.out.println("\u001B[43m\u001B[30m\u001B[1mMENU DE AYUDA\u001B[0m");
                System.out.println("La partida consta de pantallas que tienes que ir superando, el objetivo es destapar toda casilla que no contenga una mina.");
                System.out.println("El juego consta de 3 dificultades: 1 facil, 2 intermedia, 3 dificil. La diferencia entre ellas es la cantidad de minas que contendra el tablero.");
                System.out.println("Pulsa B para colocar banderas, estas te ayudaran a marcar casillas donde crees que hay una mina.");
                System.out.println("Pulsa H en cualquier momento de la partida para ver este menu de ayuda.");
                System.out.println();
            }
        }
    }

    /*fun slowPrint (a:String)  {

        for (i in a){
            print(i)
            Thread.sleep(65)
        }
        println()
    }*/


}