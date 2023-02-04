import java.lang.IndexOutOfBoundsException

class Tablero(size: Int, dificultad: Int) {

    var tablero = arrayListOf<ArrayList<Casilla>>()


    init {

        var id1 = -1
        var id2 = 0
        val newTablero = arrayListOf<ArrayList<Casilla>>()
        val letras = arrayListOf(
            "A",
            "B",
            "C",
            "D",
            "E",
            "F",
            "G",
            "H",
            "I",
            "J",
            "K",
            "L",
            "M",
            "N",
            "O",
            "P",
            "Q",
            "R",
            "S",
            "T",
            "U",
            "V",
            "W",
            "X",
            "Y",
            "Z"
        )


        for (i in 0..size) {
            newTablero.add(arrayListOf())
            for (y in 0..size) {
                if (i == 0 && y != 0) {
                    newTablero[i].add(Casilla(letras[id1], i, y, isMine = false, isOpened = true, false)) //abro las casillas que pertenecen al borde, asi no necesito abrirlas despues.
                } else if (i == 0) {
                    newTablero[i].add(Casilla("#", i, y, isMine = false, isOpened = true, false))
                } else if (y == 0) {
                    newTablero[i].add(Casilla(id2.toString(), i, y, isMine = false, isOpened = true, false))
                } else {
                    //LAS DIFICULTADES LAS ESTABLEZCO HACIENDO UN .RANDOM DE UN RANGO DE NUMEROS Y ESTABLEZCO LAS MINAS CUANDO ME DA 0.
                    if (dificultad == 1) {
                        val isMine = (0..7).random() //DIFICULTAD MAS FACIL
                        if (isMine == 0) {
                            newTablero[i].add(Casilla(id2.toString(), i, y, isMine = true, false, true))
                        } else {
                            newTablero[i].add(Casilla(id2.toString(), i, y, isMine = false, false, true))
                        }
                    }
                    if (dificultad == 2) { //DIFICULTAD INTERMEDIA
                        val isMine = (0..4).random()
                        if (isMine == 0) {
                            newTablero[i].add(Casilla(id2.toString(), i, y, isMine = true, false, true))
                        } else {
                            newTablero[i].add(Casilla(id2.toString(), i, y, isMine = false, false, true))
                        }
                    }
                    if (dificultad == 3) { //DIFICULTAD MAS DIFICIL.
                        val isMine = (0..1).random()
                        if (isMine == 0) {
                            newTablero[i].add(Casilla(id2.toString(), i, y, isMine = true, false, true))
                        } else {
                            newTablero[i].add(Casilla(id2.toString(), i, y, isMine = false, false, true))
                        }
                    }
                }
                id1++
            }
            id2++
        }


        //ANTERIOMENTE HE ASIGNADO UN NUMERO AL ID DE CADA CASILLA PERO NO HE PODIDO ASIGNARLE LA LETRA, ASI QUE AQUI LE ASIGNO LA LETRA DE TAL MANERA QUE CADA ID QUEDA DE LA SIGUIENTE MANERA: A1,B5,C2 ETC..
        var changeId = 0
        for (u in 0 until newTablero.size) {
            for (l in 0 until newTablero[0].size) {
                if (newTablero[u][l].esPosicion) {
                    try {
                        newTablero[u][l].id = letras[changeId] + newTablero[u][l].id
                    } catch (e: IndexOutOfBoundsException) {
                        continue
                    }
                    changeId++
                }
            }
            changeId = 0
        }
        tablero = newTablero
    }

    fun posicionMinas() { //ESTA FUNCION PRINTA LAS MINAS EN EL TABLERO UNA VEZ LA PARTIDA SE HA ACABADO.

        for (i in 0 until tablero.size) {
            print("|")
            for (y in 0 until tablero[0].size) {
                if (tablero[i][y].isMine) {
                    print("\u001B[41m\u001B[30m\u001B[1mX\u001B[0m")
                } else if (!tablero[i][y].isMine && tablero[i][y].esPosicion) {
                    print("0")
                } else if (!tablero[i][y].isMine && !tablero[i][y].esPosicion)
                    print(tablero[i][y].id)
                print("|")
            }
            println()
        }
        println()
    }


    fun aplicaMinas(posicion: String) { //Esta funcion utiliza la funcion calculaMinas para calcular las minas y después las aplica a la variable minas de la casilla escogida por el usuario.

        for (i in 0 until tablero.size) {
            for (y in 0 until tablero[0].size) {
                val aMinuscula = tablero[i][y].id.lowercase()
                if (posicion == tablero[i][y].id || posicion == aMinuscula && !tablero[i][y].isMine) {
                    calculaMinas(i, y)
                }
            }
        }
    }


    fun checkSiEsMina(posicion: String): Int { //Esta funcion comprueba varias cosas: si la posicion es una mina, si no es una mina, si ya has elegido la casilla anteriormente y si quieres abrir el menu de ayuda.

        var contador = 0            // UTILIZO UN CONTADOR PARA OBTENER EL RESULTADO DE LAS CONDICIONES
        for (i in 0 until tablero.size) {
            for (y in 0 until tablero[0].size) {

                if (posicion == tablero[i][y].id && !tablero[i][y].isMine && !tablero[i][y].isOpened) {  // NO ES UNA MINA. OBTENDREMOS SU NUMERO DE MINAS MAS ADELANTE CON APLICAMINAS.
                    contador = 1
                } else if (posicion == tablero[i][y].id && !tablero[i][y].isMine && tablero[i][y].isOpened && posicion != "B") { // ESTA POSICION NO ES UNA MINA PERO YA SE HA ABIERTO ANTERIORMENTE.
                    contador = 2
                } else if (posicion == "H") { // MENU DE AYUDA.
                    contador = 3
                } else if (posicion == tablero[i][y].id && tablero[i][y].isMine && !tablero[i][y].isOpened) { // ES UNA MINA. SE ACABO LA PARTIDA
                    contador = 4
                }

            }
        }
        return contador
    }

    fun marcaBanderas(posicion: String) {  //ESTA FUNCION MARCA BANDERAS EN EL TABLERO CAMBIANDO EL isFlagged A TRUE.

        for (i in 0 until tablero.size) {
            for (y in 0 until tablero[0].size) {
                val aMinuscula = tablero[i][y].id.lowercase()
                if (posicion == tablero[i][y].id || posicion == aMinuscula) {
                    tablero[i][y].isFlagged = true
                }
            }
        }

    }

    fun minasRestantes(): ArrayList<Int> { //ESTA FUNCION SIRVE PARA SABER CUANTAS POSICIONES QUE NO SON MINA QUEDAN POR ABRIR. DE ESTA MANERA SE CUANDO EL JUGADOR HA GANADO LA PARTIDA.

        val arrayParaComprobarMinas = arrayListOf<Int>()
        for (i in 0 until tablero.size) {
            for (y in 0 until tablero[0].size) {
                if (!tablero[i][y].isMine && tablero[i][y].esPosicion) {
                    //val ts = tablero[i][y]
                    if (!tablero[i][y].isOpened) {
                        arrayParaComprobarMinas.add(1)
                    }
                }
            }
        }
        return arrayParaComprobarMinas
    }

    fun checkIfExists(posicion: String): Int { // Esta funcion  se asegura de que la posicion introducida por el usuario exista.

        val arrayConPosicionesAccesibles = arrayListOf("B", "H", "b", "h")
        var contador = 0
        for (i in 0 until tablero.size) {
            for (y in 0 until tablero[0].size) {
                if (tablero[i][y].esPosicion) {
                    arrayConPosicionesAccesibles.add(tablero[i][y].id)
                    arrayConPosicionesAccesibles.add(tablero[i][y].id.lowercase())
                }
            }
        }
        if (posicion !in arrayConPosicionesAccesibles) {
            contador++
        }
        return contador
    }


    fun calculaMinas(
        i: Int,
        y: Int
    ) { //Esta función itera alrededor de la posicion que el jugador ha introducido y calcula las minas que hay.
        tablero[i][y].isOpened = true
        var contadorDeMinas = 0
        for (q in i - 1..i + 1) {
            for (x in y - 1..y + 1) {
                try {
                    if (tablero[q][x].isMine) {
                        try {
                            contadorDeMinas++
                            val minasTotales = tablero[i][y].minas + contadorDeMinas
                            tablero[i][y].minas = minasTotales
                            contadorDeMinas = 0
                        } catch (e: IndexOutOfBoundsException) {
                            continue
                        }
                    }
                } catch (e: IndexOutOfBoundsException) {
                    continue
                }
            }
        }
    }

    fun printaTablero() { //Esta funcion hace un print elaborado del tablero.

        for (i in 0 until tablero.size) {
            print("|")
            for (y in 0 until tablero[0].size) {
                if (tablero[i][y].esPosicion) {
                    if (tablero[i][y].isFlagged) {
                        val bandera = tablero[i][y].minas
                        print("\u001B[43m\u001B[30m\u001B[1m$bandera\u001B[0m") //PRINTA DE COLOR AMARILLO LAS POSICIONES QUE TIENEN UNA BANDERA
                        print("|")
                    } else if (!tablero[i][y].isFlagged && tablero[i][y].isOpened) {  //PRINTA DE COLOR VERDE LAS POSICIONES DESPEJADAS EXITOSAMENTE.
                        val minas = tablero[i][y].minas
                        print("\u001B[42m\u001B[30m\u001B[1m$minas\u001B[0m")
                        print("|")
                    } else if (!tablero[i][y].isFlagged && !tablero[i][y].isOpened) { //PRINTA LAS POSICIONES AUN NO ELEJIDAS DE MANERA NORMAL.
                        val minasCerradas = tablero[i][y].minas
                        print(minasCerradas)
                        print("|")
                    }
                }
                if (!tablero[i][y].esPosicion) {
                    val noEsPosicion = tablero[i][y].id
                    print(noEsPosicion)
                    print("|")
                }
            }
            println()
        }
    }

    fun noHayMinas(): ArrayList<Int> {   //Con los tableros pequeños a veces no se generan minas, esta funcion se asegura de que el jugador no juege una partida sin minas en el tablero.

        val arrayConMinas = arrayListOf<Int>()
        for (i in 0 until tablero.size) {
            for (y in 0 until tablero[0].size) {
                if (tablero[i][y].isMine) {
                    arrayConMinas.add(1)
                }
            }
        }
        return arrayConMinas
    }

    fun transformaPrimeraMina(
        posicion: String,
        contadorDePantallas: Int
    ) {  //Con esta funcion la primera posicion escogida por el jugador  nunca puede ser una mina. contador de pantallas a 0 quiere decir primera ronda.

        for (i in 0 until tablero.size) {
            for (y in 0 until tablero[0].size) {
                val aMinuscula = tablero[i][y].id.lowercase()
                if (posicion == tablero[i][y].id || posicion == aMinuscula && tablero[i][y].isMine && contadorDePantallas == 0) {
                    tablero[i][y].isMine = false
                }
            }
        }
    }


}