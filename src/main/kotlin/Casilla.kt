class Casilla (var id: String, val i: Int, val j: Int, var isMine: Boolean, var isOpened : Boolean, val esPosicion: Boolean) {

    var isFlagged = false
    var minas = 0
    /*var bandera = ""*/


    // ID : SIRVE PARA IDENTIFICAR CADA CASILLA, ESTE ID SERA EL QUE INTRODUCIRA EL USUARIO EN LA TERMINAL (A1,A5...)
    // I y J: FILE Y COLUMNA
    // isMine: ESTA VARIABLE SIRVE PARA DIFERENCIAR LAS CASILLAS QUE SON MINA DE LAS QUE NO LO SON.
    // isOpened: ESTA VARIABLE SIRVE PARA SABER QUE CASILLAS YA SIDO ABIERTAS DURANTE LA PARTIDA.
    // esPosicion: ESTA VARIABLE SIRVE PARA DIFERENCIAR LAS POSICIONES QUE EL USUARIO PUEDE ELEJIR DE LAS QUE FORMAN PARTE DE LOS BORDES.


}