import java.util.Map;
import java.util.Properties;

/**
 * 调用原生函数的接口
 * Created by dxy on 17-3-31.
 */
public class ResultInterface {
    static{
        Properties properties = System.getProperties();
        String os = properties.getProperty("os.name");
        System.out.println("当前操作系统为： "+ os);
        String path = properties.getProperty("user.dir");
        System.out.println("当前路径为: "+path);
        if(os.equals("Linux")){
            System.load(path+"/libCountMines.so");
        }else if(os.contains("Windows")){
            System.load(path+"/CountMines.dll");
        }else{
            System.out.println("尚不支持当前操作系统");
        }
    }
    public native void init(int rows,int columns,int mineNum);
    public native int countMines(int row, int column);
    ResultInterface(int rows,int columns,int mineNum)
    {
        init(rows,columns,mineNum);
    }

}
