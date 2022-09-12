// Primera parte del algoritmo. En esta parte preparamos el mensaje para poder implementar
// el algoritmo Sha512.

import java.math.BigInteger;

// Como su nombre lo dice, el objetivo de esta clase es preprocesar el mensaje.
public class Preprocesado {

    public static byte[] rellena(String msj){
        byte[] msjBytes = msj.getBytes();
        int l = msjBytes.length;

        // Necesitaremos apilar al menos 17 bytes; uno para el bit '1' y 16 para la 
        // logitud del mensaje. Luego apilamos tantos bytes con 0's como sea necesario 
        // para que el tamaño sea congruente con 128.
        int tamano = l + 17;
        while(tamano%Constantes.TAMANO_BLOQUE != 0)
            tamano++;

        // Este será nuestro mensaje ya rellenado.
        byte[] msjRelleno = new byte[tamano];

        // Copiando mensaje.
        for(int i = 0; i < l; i++)
            msjRelleno[i] = msjBytes[i];

        // Añadimos el '1'.
        msjRelleno[l] = (byte)0x80;

        // Convertimos la longitud del mensaje original (multiplicada por 8 porque
        // byte = 8 bits) a un arreglo de bytes usando la clase 'BigInteger'.
        byte[] Lbytes = BigInteger.valueOf(l*8).toByteArray();

        // Ahora colocamos la longitud al final del mensaje rellenado.
        for(int i = Lbytes.length; i > 0; i--)
            msjRelleno[tamano-i] = Lbytes[Lbytes.length-i];

        return msjRelleno;
    }

    // Dado un arreglo de bytes, esta función toma 8 bytes a partir del indice 'i' y los 
    // convierte a un entero largo (long int).
    public static long convArrBytesLong(byte[] arr, int i){
        long x = 0;
        for(int j = 0; j < 8; j++){
            x = (x<<8) + (arr[i+j]&0xff);
        }

        return x;
    }

    // Partimos nuestro mensaje en bloques de 128 bytes (1024 bits) y cada bloque 
    // lo dividimos es sub-bloques de 8 bytes (64 bits).
    // Aqui estamos suponiendo que la longitud de 'arr' es un multimplo de la constante
    // TAMANO_BLOQUE, definida en la clase Constantes.
    public static long[][] convierteaBloques(byte[] arr){
        int N = arr.length/Constantes.TAMANO_BLOQUE;

        long[][] bloques = new long[N][16];

        // Creamos los bloques.
        for(int i = 0; i < N; i++){
            // Creamos los sub-bloques.
            for(int j = 0; j < 16; j++){
                bloques[i][j] = convArrBytesLong(arr, i*128+j*8);
            }
        }

        return bloques;
    }
}
