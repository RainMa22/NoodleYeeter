import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public class Main {
    public static ArrayList<collection> collections=new ArrayList<collection>(0);
    public static void main(String[] args) throws Exception {
        BufferedImage bi = ImageIO.read(new File("noodle2.png"));
        BufferedImage bii = new BufferedImage(1920
                , 1080, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = (Graphics2D) bii.createGraphics();
        g2d.setColor(new Color(0, 255, 0));
        g2d.fillRect(0, 0, bii.getWidth(), bii.getHeight());
        g2d.dispose();
        ImageIO.write(bii, "png", new File("new.png"));
        new File("final").mkdirs();
        for (int i = 0; i < 300; i++) {
            double b=Math.random()*360;
            collections.add(new collection(bi,op(bi,b),b));
        }
        long temp=0;
        boolean b1=collections.get(collections.size()-1).x-100<=1920&&collections.get(collections.size()-1).y-100<=1080;
        boolean b2=collections.get(collections.size()-1).x+100<=0&&collections.get(collections.size()-1).y+100>=0;
        while(b1||b2){
            b1=collections.get(collections.size()-1).x-100<=1920&&collections.get(collections.size()-1).y-100<=1080;
            b2=collections.get(collections.size()-1).x+100<=0&&collections.get(collections.size()-1).y+100>=0;
            BufferedImage biii=bii.getSubimage(0,0,bii.getWidth(),bii.getHeight());
            g2d=(Graphics2D) biii.createGraphics();
            ImageIO.write(collections.get(0).image,"png",new File("help.png"));
            for (int i = 0; i < collections.size(); i++) {
                if (collections.get(i).x>1920+100&&collections.get(i).y+100>1080){
                    i++;
                }
                boolean b=collections.get(i).x==1920/2-collections.get(i).image.getWidth()/2
                        &&
                        collections.get(i).y==1080/2-collections.get(i).image.getHeight()/2;
                //for (int j = 0; j < 25; j++) {
                collections.get(i).x+=125*Math.cos(Math.toRadians(collections.get(i).degree));
                collections.get(i).y+=125*Math.sin(Math.toRadians(collections.get(i).degree));
                g2d.drawImage(collections.get(i).image,collections.get(i).x,collections.get(i).y,null);
                if (b) break;
            //}

            }

            g2d.dispose();
            ImageIO.write(biii,"png", new File("final/"+String.valueOf(temp) + ".png"));
            temp++;

        }
    }

    static AffineTransformOp op(BufferedImage image, double degree) {
        double rotationRequired = Math.toRadians(degree);
        double locationX = image.getWidth() / 2;
        double locationY = image.getHeight() / 2;
        AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
        AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BICUBIC);
        return op;
    }
}