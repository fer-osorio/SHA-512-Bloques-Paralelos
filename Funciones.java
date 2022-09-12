public class Funciones {

    public static long ROTR(long x, int n){
        return (x>>>n)|(x<<(Long.SIZE-n));
    }

    public static long Ch(long x, long y , long z){
        return (x&y)^(~x&z);
    }

    public static long Maj(long x, long y, long z){
        return (x&y)^(x&z)^(y&z);
    }

    public static long sigmaMayus0(long x){
        return ROTR(x,28)^ROTR(x,34)^ROTR(x,39);
    }

    public static long sigmaMayus1(long x){
        return ROTR(x,14)^ROTR(x,18)^ROTR(x,41);
    }

    public static long sigma0(long x){
        return ROTR(x,1)^ROTR(x,8)^(x>>>7);
    }

    public static long sigma1(long x){
        return ROTR(x,19)^ROTR(x,61)^(x>>>6);
    }
}
