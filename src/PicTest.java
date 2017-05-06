import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.SyncFailedException;

/**
 * Created by dxy on 17-4-1.
 */
public class PicTest extends JFrame {

    public static void main(String args[]) throws IOException {

            PicTest picTest = new PicTest();

    }
    PicTest(){
        System.out.println(new File("").getAbsolutePath());
        System.out.println(System.getProperty("user.dir"));
    }


//    public PicTest() throws IOException {
//        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//        this.setBounds(100,100,300,300);
//        this.setLayout(new BorderLayout());
//        JPanel panel = new JPanel();
//        panel.setLayout(new BorderLayout());
//        JLabel label = new JLabel();
//
//        Image image = null;
//        try {
//            image = ImageIO.read(new File("flag.png"));
//        } catch (IOException e) {
//            image = ImageIO.read(new File("pic/flag.png"));
//        }
//
//        label.setIcon(new ImageIcon(image));
//        panel.add(label);
//        this.add(panel);
//        this.setVisible(true);
//    }
}
