/*  Esta clase tiene como objetivo generar un bloque de n cadenas de caracteres, siendo
    todas estas cadenas de longitud de 64 bytes (512 bits). La regla para la contrucción de
    este bloque es la siguiente: La cadena con índice 0 se obtienen aplicando el algoritmo 
    Sha-512 a un mensaje arbitrario; la cadena con indice i (i > 0) es el resultado de 
    aplicarle el algoritmo Sha-512 a la cadena con indice i-1.*/

public class BloqueN {
    String MsjOriginal;
    String[] bloque;

    BloqueN(String msj, int n){
        MsjOriginal = msj;                  
        bloque = construyeBloque(msj, n);
    }

    public static String[] construyeBloque(String msj, int n){
        String[] blq = new String[n];

        blq[0] = Sha512.digiereMensaje(msj);
        for(int i = 1; i < n; i++)
            blq[i] = Sha512.digiereMensaje(blq[i-1]);

        return blq;
    }
}
