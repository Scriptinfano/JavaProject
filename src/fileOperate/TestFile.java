package fileOperate;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;

class MyFileFilter implements FileFilter {
    @Override
    public boolean accept(File fileName) {
        Boolean png = fileName.getName().toLowerCase().endsWith(".png");
        Boolean jpg = fileName.getName().toLowerCase().endsWith(".jpg");
        Boolean jpeg = fileName.getName().toLowerCase().endsWith(".jpeg");
        if (png) {
            return true;
        } else if (jpeg) {
            return true;
        } else if (jpg) {
            return true;
        } else
            return false;
    }
}

public class TestFile {
    public static void main(String[] args) {

    }

    public static void test1() throws IOException {
        String separator = File.separator;//获取系统分隔符
        File path = new File("D:" + separator + "test");//声明目录
        if (!path.exists())
            path.mkdirs();
        //判断是否为目录
        System.out.println("这是目录？" + path.isDirectory());
        File file = new File(path.getAbsolutePath() + separator + "fuck_you.txt");
        if (!file.exists())
            if (file.createNewFile()) {
                System.out.println("文件不存在，目前已创建");
            }
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.getPath());
        System.out.println(file.isFile());
        System.out.println(file.length());
        File path2 = new File("D:" + separator);
        String[] allFiles = path2.list();
        System.out.println("遍历D盘下所有文件夹：");
        for (int i = 0; i < allFiles.length; i++) {
            System.out.println(allFiles[i]);
        }
    }

    public static void test2() {
        String separator = File.separator;//获取系统分隔符
        File path = new File("D:" + separator + "test");//声明目录
        File[] pictures = path.listFiles(new MyFileFilter());
        if (pictures != null) {
            for (File f : pictures) {
                System.out.print(f.getName() + " ");
            }
        }
    }
}
