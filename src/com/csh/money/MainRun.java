package com.csh.money;

import javax.swing.*;
import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author James-CSH
 * @since 3/11/2017 9:22 AM
 */
public class MainRun {

    public final static String filePath = "F:\\money.txt";

    public static BigDecimal money;
    public static BigDecimal amount;
    public static List<BigDecimal> allMoneys = new ArrayList<BigDecimal>();
    public static List<BigDecimal> canUseMoneys = new ArrayList<BigDecimal>();
    public static List<BigDecimal> canNoUseMoneys = new ArrayList<BigDecimal>();

    public static BigDecimal from;
    public static BigDecimal to;
    public static BigDecimal add;
    public static int day;
    public static int index;


    public static void main(String[] args) {
        //1.检查是否有文件，没有文件则创建
        while (!checkFile());

        //2.检查是否有初始化，没有的话就初始化
        while (!checkFirstLine()) {
            StartDialog.start();
        }

        //3.获取可以使用的金额（读取已经使用的金额，将所有金额减去已经使用的金额）
        read();

        //启动主界面
        MainFrame.start();
    }

    /**
     * 检查是否有文件
     */
    public static boolean checkFile() {
        File file = new File(filePath);
        file.setReadable(true);
        file.setWritable(true);
        file.setExecutable(true);
        if (!file.exists()) {
            try {
                if (!file.createNewFile()) {
                    JOptionPane.showMessageDialog(null, "创建文件" + filePath + "失败！", "错误", JOptionPane.ERROR_MESSAGE);
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "创建文件" + filePath + "失败！", "错误", JOptionPane.ERROR_MESSAGE);
                return false;
            }

        }
        return true;
    }

    /**
     * 检查初始化数据
     * @return
     */
    public static boolean checkFirstLine() {
        File file = new File(filePath);
        file.setReadable(true);
        file.setWritable(true);
        file.setExecutable(true);
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            line = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "找不到文件" + filePath, "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "读取文件数据错误", "错误", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (null != line && !line.isEmpty()) {
            int index1 = line.indexOf("-");
            int index2 = line.indexOf("=");
            String fromStr = line.substring(0, index1);
            String dayStr = line.substring(index1 + 1, index2);
            String addStr = line.substring(index2 + 1);
            try {
                BigDecimal from = new BigDecimal(Double.parseDouble(fromStr));
                int day = Integer.parseInt(dayStr);
                BigDecimal add = new BigDecimal(Double.parseDouble(addStr));
                MainRun.from = from;
                MainRun.day = day;
                MainRun.add = add;
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "数据格式有错误，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
                return false;
            }
            return true;
        }
        JOptionPane.showMessageDialog(null, "数据格式有错误，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    /**
     * 读取数据
     */
    public static void read() {
        File file = new File(filePath);
        file.setReadable(true);
        file.setWritable(true);
        file.setExecutable(true);
        BufferedReader bufferedReader;
        String line = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(file));
            //读2次
            line = bufferedReader.readLine();
            line = bufferedReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "找不到文件" + filePath + "，软件自动关闭", "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return ;
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "读取文件数据错误，软件自动关闭", "错误", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
            return;
        }

        //初始化allMoneys
        BigDecimal temp = from;
        for (int i=0; i<day; i++) {
            BigDecimal t = temp.add(add.multiply(BigDecimal.valueOf(i)));
            allMoneys.add(t);
        }

        //初始化canNoUseMoneys
        while (null != line)  {
            BigDecimal canNoMoney;
            try {
                canNoMoney = new BigDecimal(line);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "有数据错误，请检查" + filePath + "文件", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            canNoUseMoneys.add(canNoMoney);
            try {
                line = bufferedReader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        canUseMoneys = new ArrayList<>(allMoneys);
        canUseMoneys.removeAll(canNoUseMoneys);
    }
}
