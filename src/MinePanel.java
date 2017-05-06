import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

/**
 * Created by dxy on 17-3-31.
 * 雷
 */
public class MinePanel extends JPanel implements MouseListener{

    private int row;
    private int column;
    private MapClickListener mapClickListener;

    private boolean operable = true;
    private boolean pressed = false;
    private boolean signed = false;

    private JLabel numLabel = null;
    private JLabel flagLabel = null;
    private JLabel mineLabel = null;

    private Color focused = new Color(86, 96, 88);
    private Color normal = new Color(153, 167, 156);

    MinePanel(int row, int column){
        super();
        this.row =row;
        this.column = column;
        this.setPreferredSize(new Dimension(27,27));
        addMouseListener(this);
        this.init();
    }

    void addMapClickListener(MapClickListener mapClickListener){
        this.mapClickListener = mapClickListener;
    }

    public void init(){
        operable = true;
        this.setBackground(normal);
        this.pressed = false;
        this.signed = false;
        if(flagLabel!=null) this.remove(flagLabel);
        if(mineLabel!=null) this.remove(mineLabel);
        if(numLabel!=null)  this.remove(numLabel);
        this.setLayout(new BorderLayout());
        this.revalidate();
    }

    void showPattern(int i){
        if(i!=-1) {
            numLabel = new JLabel(i==0?"":i+"", JLabel.CENTER);
            numLabel.setFont(new Font("", Font.BOLD, 14));
            numLabel.setForeground(Color.WHITE);
            numLabel.setBackground(Color.WHITE);
            this.add(numLabel);
        }else{
//            System.out.println("踩到雷了");
            this.setBackground(null);
            Image image = null;
            try {
                image = ImageIO.read(new File("mine.png"));
            } catch (IOException e) {
                try {
                    image = ImageIO.read(new File("src/mine.png"));
                } catch (IOException e1) {

                }
            }
            if(image!=null) {
                mineLabel = new JLabel();
                mineLabel.setIcon(new ImageIcon(image));
                this.add(mineLabel);
            }else{
                mineLabel = new JLabel("*",JLabel.CENTER);
                mineLabel.setFont(new Font("", Font.BOLD, 20));
                this.add(mineLabel);
            }

        }
        this.revalidate();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.isMetaDown()&&!pressed){
            if(!signed) {
                Image image = null;
                try {
                    image = ImageIO.read(new File("flag_small.png"));
                } catch (IOException e3) {
                    try {
                        image = ImageIO.read(new File("src/flag_small.png"));
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                }
                if(image!=null) {
                    flagLabel = new JLabel();
                    flagLabel.setIcon(new ImageIcon(image));
                    this.add(flagLabel);
                }else{
                    flagLabel = new JLabel("!",JLabel.CENTER);
                    flagLabel.setFont(new Font("", Font.BOLD, 20));
                    this.add(flagLabel);
                }
            }else{
//                System.out.println("取消标记");s
                this.remove(flagLabel);
            }
            revalidate();
            this.signed = !this.signed;
        }else {
            if(!pressed) {
                setPressed();
                if(signed)  this.remove(flagLabel);
                this.mapClickListener.click(row, column);
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

    }

    public void setPressed(){
        this.pressed = true;
        this.setBackground(focused);
        if(signed)  this.remove(flagLabel);
        this.revalidate();
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if(!pressed&&operable)
            this.setBackground(focused);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if(!pressed&&operable)
            this.setBackground(normal);
    }

    public boolean isPressed() {
        return pressed;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
