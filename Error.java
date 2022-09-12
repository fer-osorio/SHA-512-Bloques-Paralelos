/*  Creamos esta clase para manejar comportamientos anormales que puedan surgir
    a lo largo del programa.*/

public class Error {
    /*ubicacion:: Describe la clase y el método en donde, presuntamente, se encuentra 
        un comportamiento no esperado.*/
    /*especificacion:: Describe el comportamiento anormal.*/
    public static void error(String ubicacion, String especificacion){
        System.out.print("Error en " + ubicacion + ": " + especificacion + "\n");
        System.exit(-1);
    }
}
