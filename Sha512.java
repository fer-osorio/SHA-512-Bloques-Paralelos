// Nombre del programa: Sha512
// Autor: Alexis Fernando Osorio Sarabio
// Fecha de creación: 9 de abril del 2022

// En este trabajo pretendemos programar el algoritmo de seguridad Hash SHA-512.

// Creamos nuestra clase en donde guardaremos nuestro mensaje original y crearemos nuestro 
// mensaje digerido.
public class Sha512{
    String mensaje;
    String mensajeDijerido;

    Sha512(String mensaje){
      this.mensaje = mensaje; 
      this.mensajeDijerido = digiereMensaje(mensaje);
    }

    // Para cada uno de los bloques preparemos su respectivo bloque W.
    public static long[][] mensajesProgramados(long[][] M){
        long[][] W = new long[M.length][80];

        // Tomamos cada bloque.
        for(int i=0; i < M.length;i++){
            // Y generamos las palabras W_j.
            for(int j=0; j < 80; j++){
                if(j<16){
                    W[i][j] = M[i][j];
                    // Propósitos de depuración.
                    //System.out.printf("W(%d) = %016x\n", j, W[i][j]);
                }
                else{
                    W[i][j] = Funciones.sigma1(W[i][j-2]) + W[i][j-7] + 
                    Funciones.sigma0(W[i][j-15]) + W[i][j-16];
                }
            }
        }

        return W;
    }

    // Aqui implementamos el algoritmo Sha512
    public static String digiereMensaje(String msj){

        // Preparamos el mensaje antes de su procesamieto.
        byte[] msjRellenado = Preprocesado.rellena(msj);
        long[][] bloques = Preprocesado.convierteaBloques(msjRellenado);
        
        // Inicializamos las variables H_0,...,H7.
        long[] H = Constantes.H0.clone();

        //Preparamos los mensajes programados en el bloque W.
        long[][] W = mensajesProgramados(bloques);

        // Declaramos las variables que necesitarémos.
        long a = 0;
        long b = 0; 
        long c = 0;
        long d = 0;
        long e = 0;
        long f = 0;
        long g = 0;
        long h = 0;
        long T1 = 0;
        long T2 = 0;

        for(int i = 0; i<bloques.length; i++){
            // Actualizamos nuestras variables.
            a = H[0];
            b = H[1];
            c = H[2];
            d = H[3];
            e = H[4];
            f = H[5];
            g = H[6];
            h = H[7];

            for(int j=0; j<80; j++){
                T1=h+Funciones.sigmaMayus1(e)+Funciones.Ch(e,f,g)+Constantes.K[j]+W[i][j];
                T2 = Funciones.sigmaMayus0(a)+Funciones.Maj(a, b, c);
                h = g;
                g = f;
                f = e;
                e = d+T1;
                d = c;
                c = b;
                b = a;
                a = T1+T2;

                // Propositos de depuración.
                //System.out.printf("%d abcdefgh: %016x %016x %016x %016x %016x %016x %016x %016x\n", j, a, b, c, d, e, f, g, h);
                //System.out.println();
            }

            // Asignamos los valores intermedios de H_0,...,H_7
            H[0] += a;
            H[1] += b;
            H[2] += c;
            H[3] += d;
            H[4] += e;
            H[5] += f;
            H[6] += g;
            H[7] += h;
        }

        // Finalmente regresamos nuestro mensaje digerido.
        String msjDigerido = "";
        for(int i = 0; i < 8; i++){
            msjDigerido += String.format("%016x", H[i]);
        }

        return msjDigerido;
    }
}