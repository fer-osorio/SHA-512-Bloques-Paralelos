public class SHA512Paralelo64 extends Thread{
    /*Asociamos un indice a cada objeto.*/
    public int indice;
    /*Número de veces que aplicaremos el algoritmo SHA-512.*/
    /*Estos enteros deben estar entre 0 y 255.*/
    private int NumAplicaciones;
    /*Semilla para la aplicacion del algoridmo SHA-512.*/
    private String cadena_inicial;
    /*Guardamos el resultado de las aplicaciones sucesivas de nuestro
        algoritmo.*/
    public String cadena_procesada;

    SHA512Paralelo64(int i, int na, String ci){
        if (i > 63)
            Error.error("SHA512Paralelo64.SHA512Paralelo64", 
            "La variable 'i' debe ser menor a 64.");
        if (i < 0)
            Error.error("SHA512Paralelo64.SHA512Paralelo64",
            "La variable 'i' debe ser mayor o igual a cero.");
        indice = i;
        
        if (na > 256)
            Error.error("SHA512Paralelo64.SHA512Paralelo64", 
            "La variable 'na' debe ser menor o igual a 256.");
        if (na <= 0)
            Error.error("SHA512Paralelo64.SHA512Paralelo64", 
            "La variable 'na' debe ser mayor a cero.");
        NumAplicaciones = na;

        if (ci == "")
            Error.error("SHA512Paralelo64.SHA512Paralelo64",
            "La cadena 'ci' no puede ser la cadena vacía.");
        cadena_inicial = ci;

        cadena_procesada = "";
    }

    public void run(){
        cadena_procesada = BloqueN.construyeBloque(cadena_inicial, NumAplicaciones)[NumAplicaciones-1];
        //System.out.println(indice + ": " +  cadena_procesada + '\n');
    }
}
