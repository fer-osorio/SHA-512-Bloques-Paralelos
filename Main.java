// Implementamos nuestro programa sobre alguna cadena de caracteres

import java.util.*;

public class Main{
    public static void main(String[] args){
        long inicio = System.nanoTime();

        Random r = new Random();
        char a;
        int i, j;
        String llave = "";
        /*Generando una cadena 'aleatoria' de 64 caracteres.*/
        for(i = 0; i < 64; i++){
            a = (char)(r.nextInt(255) + 1);
            llave += a;
        }

        String[] semillas = new String[64];
        /*Generando 64 palabras 'aleatorias'.*/
        for(i = 0; i < 64; i++){
            semillas[i] = "";
            for(j = 0; j < r.nextInt(64) + 1; j++){
                a = (char)(r.nextInt(255) + 1);
                semillas[i] += a;
            }
        }

        SHA512Paralelo64[] blq = new SHA512Paralelo64[64];
        for(i = 0; i < 64; i++)
            blq[i] = new SHA512Paralelo64(i, (int)(llave.charAt(i)), semillas[i]);

        for(i = 0; i < 64; i++)
            blq[i].start();

        boolean no_finalizado;
        do{
            no_finalizado = false;
            i = 0;
            while(!no_finalizado && i < 64){
                no_finalizado = no_finalizado || blq[i].isAlive();
                i++;
            }
        }while(no_finalizado);

        int k;
        for(k = 0; k < 64; k++)
            System.out.println(blq[k].indice + '\n' + ": " + blq[k].cadena_procesada + '\n');

        long fin = System.nanoTime();
        long tiempo = fin-inicio;
        System.out.println(tiempo);
    }
}