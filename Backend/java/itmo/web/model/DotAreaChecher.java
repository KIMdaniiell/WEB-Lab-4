package itmo.web.model;

public class DotAreaChecher {
    public boolean check(double x, double y, double r){

        return isRightTop(x,y,r) || isRightBottom(x,y,r) || isLeftBottom(x,y,r);
    }
    private static boolean isRightTop(double x, double y, double r){
        return ( x >= 0 ) &&
                ( y >= 0 ) &&
                ( x*x + y*y <= r*r );
    }
    private static boolean isRightBottom(double x, double y, double r){
        return  ( x >= 0 ) &&
                ( y <= 0 ) &&
                ( x + (-y)*2 <= r );
    }
    private static boolean isLeftBottom(double x, double y, double r){
        return  ( x <= 0 ) &&
                ( y <= 0 ) &&
                ( x >= -r ) &&
                ( y >= -r/2 );
    }
}
