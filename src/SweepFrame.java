import javax.swing.*;
import java.awt.*;
import java.util.Stack;

/**
 * Created by dxy on 17-3-31.
 * 界面布局
 */
class SweepFrame extends JFrame implements MapClickListener{

    private static int ROWS = 14;
    private static int COLUMNS = 14;
    private static int MINE_NUM = 50;


    private MinePanel[][] mineMap= new MinePanel[ROWS][COLUMNS];

    private ResultInterface resultInterface = new ResultInterface(ROWS,COLUMNS,MINE_NUM);
    SweepFrame(){

        setBounds(100, 100, 550, 520);
        setResizable(true);
        init();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void init(){
        setTitle("last_group扫雷程序");
        setLayout(new BorderLayout());
        JPanel gridPanel = getGridPanel(ROWS, COLUMNS);
//        JPanel boardPanel = getBoard();
        add("Center", gridPanel);
//        add("East", boardPanel);
        this.revalidate();
    }

    private void reStart(){
        for(int i=0;i<ROWS;i++){
            for(int j=0;j<COLUMNS;j++)
                mineMap[i][j].init();
        }
    }

    private JPanel getGridPanel(int rows,int columns){
        GridLayout gl = new GridLayout(rows,columns);
        gl.setVgap(3);
        gl.setHgap(3);
        JPanel panel = new JPanel();
        panel.setLayout(gl);
        for(int i=0;i<rows*columns;i++){
            MinePanel minePanel = new MinePanel(i/rows,i%columns);
            this.mineMap[i/rows][i%columns] = minePanel;
            minePanel.addMapClickListener(this);
            panel.add(minePanel);
        }
        return panel;
    }

    private JPanel getBoard(){
        JPanel jPanel = new JPanel();
        jPanel.setBackground(new Color(150,50,50));
        jPanel.setPreferredSize(new Dimension(150,500));
        return jPanel;
    }

    @Override
    public void click(int row, int column) {
        int count = resultInterface.countMines(row,column);
        this.mineMap[row][column].showPattern(count);
        if(count == 0){
            extendZeroArea(row,column);
        }
        if(count == -1){
            showAllMines();
            gameOver(false);
        }
    }


    /**
     * 如果结果是零，扩展
     * @param row 所在行
     * @param column 所在列
     */
    private void extendZeroArea(int row,int column) {
        int count;
        Stack<MinePanel> stack = new Stack<>();
        stack.push(mineMap[row][column]);
//        int i = 0;
        while (!stack.empty()) {
//            i++;
            MinePanel minePanel = stack.pop();
            int x = minePanel.getRow();
            int y = minePanel.getColumn();

            minePanel.setPressed();
            minePanel.showPattern(resultInterface.countMines(x, y));
            if (x >= 1 && !mineMap[x - 1][y].isPressed()) {
                count = resultInterface.countMines(x - 1, y);
                if (count == 0)
                    stack.push(mineMap[x - 1][y]);
                else {
                    mineMap[x - 1][y].showPattern(count);
                    mineMap[x - 1][y].setPressed();
                }
            }
            if (x < ROWS - 1 && !mineMap[x + 1][y].isPressed()) {
                count = resultInterface.countMines(x + 1, y);
                if (count == 0)
                    stack.push(mineMap[x + 1][y]);
                else {
                    mineMap[x + 1][y].showPattern(count);
                    mineMap[x + 1][y].setPressed();
                }
            }
            if (y >= 1 && !mineMap[x][y - 1].isPressed()) {
                count = resultInterface.countMines(x, y - 1);
                if (count == 0)
                    stack.push(mineMap[x][y - 1]);
                else {
                    mineMap[x][y - 1].showPattern(count);
                    mineMap[x][y - 1].setPressed();
                }
            }
            if (y < COLUMNS - 1 && !mineMap[x][y + 1].isPressed()) {
                count = resultInterface.countMines(x, y + 1);
                if (count == 0)
                    stack.push(mineMap[x][y + 1]);
                else {
                    mineMap[x][y + 1].showPattern(count);
                    mineMap[x][y + 1].setPressed();
                }
            }
        }
//        System.out.println(i);
    }

    private void gameOver(boolean successful){
        if(successful){
            System.out.println("扫雷成功");
            System.out.println();
//            JOptionPane.showMessageDialog(null, "您真是太厉害啦！", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
        }
        else {
            System.out.println("扫雷失败");
            System.out.println();
            JOptionPane.showMessageDialog(null, "踩到雷啦?", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
        }
        System.out.println("游戏重新开始！");
        this.reStart();
    }

    private void showAllMines(){
        int i, j;
        for(i=0;i<ROWS;i++){
            for(j=0;j<COLUMNS;j++){
                if(resultInterface.countMines(i,j)==-1){
                    mineMap[i][j].setPressed();
                    mineMap[i][j].showPattern(-1);
                }
            }
        }
    }
}
