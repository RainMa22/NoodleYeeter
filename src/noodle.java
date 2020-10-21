import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class noodle extends ArrayList<ArrayList<Object>> {
}
class collection{
    public int x,y;
    public double degree;
    public BufferedImage image;
    public collection(BufferedImage bi, AffineTransformOp op,double degree){
        image=op.filter(bi,null);
        this.degree=degree;
        x=1920/2-bi.getWidth()/2;
        y=1080/2-bi.getHeight()/2;
    }
}