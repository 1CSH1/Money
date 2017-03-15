package com.csh.money;

import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author James-CSH
 * @since 3/10/2017 9:44 PM
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        amount = new javax.swing.JLabel();
        money = new javax.swing.JLabel();
        sureButton = new javax.swing.JButton();
        largeButton = new javax.swing.JButton();
        smallButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        //设置总计
        amount.setHorizontalAlignment(SwingConstants.CENTER);
        amount.setFont(new Font("黑体", Font.BOLD, 30));

        //设置金钱
        money.setHorizontalAlignment(SwingConstants.CENTER);
        money.setFont(new Font("华文行楷", Font.BOLD, 60));


        init();

        //设置按钮
        smallButton.setText("太穷了！最近没钱");
        smallButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                smallButtonActionPerformed(evt);
            }
        });

        sureButton.setText("好吧！就你了");
        sureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sureButtonActionPerformed(evt);
            }
        });

        largeButton.setText("太少了！小瞧本宫");
        largeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                largeButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(amount, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(87, Short.MAX_VALUE)
                                .addComponent(smallButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(sureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(43, 43, 43)
                                .addComponent(largeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(87, 87, 87))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(money, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(amount, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(sureButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(largeButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(smallButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(49, 49, 49))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                        .addGap(48, 48, 48)
                                        .addComponent(money, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addContainerGap(304, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>

    private void init() {
        if (0 == MainRun.canUseMoneys.size()) {
            JOptionPane.showMessageDialog(this, "恭喜！存储计划完成！去买想要的东西吧！", "提示", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            return ;
        }
        BigDecimal amountDec = new BigDecimal(0);

        for (int i=0; i<MainRun.canNoUseMoneys.size(); i++) {
            amountDec = amountDec.add(MainRun.canNoUseMoneys.get(i));
        }
        amountDec = amountDec.setScale(2, BigDecimal.ROUND_DOWN);
        amount.setText("总计：" + amountDec.doubleValue() + "元   已经登记了" + MainRun.canNoUseMoneys.size() + "天");


        int index = generateRandom(0, MainRun.canUseMoneys.size() - 1);
        MainRun.index = index;
        BigDecimal moneyDec = MainRun.canUseMoneys.get(index);
        moneyDec = moneyDec.setScale(2, BigDecimal.ROUND_DOWN);
        money.setText(moneyDec.doubleValue() + "元");

    }

    /**
     * 生成[min, max]的随机数
     * @param min
     * @param max
     * @return
     */
    private int generateRandom(int min, int max) {
        Random random = new Random();
        if (0 == min) {
            return random.nextInt(max + 1);
        }
        return random.nextInt(max) % (max - min + 1) + min;
    }

    //smallButton事件处理
    private void smallButtonActionPerformed(ActionEvent evt) {
        if (0 == MainRun.index) {
            JOptionPane.showMessageDialog(this, "这是最小的了", "提示", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }
        int index = generateRandom(0, MainRun.index - 1);
        MainRun.index = index;
        BigDecimal moneyDec = MainRun.canUseMoneys.get(index);
        moneyDec = moneyDec.setScale(2, BigDecimal.ROUND_DOWN);
        money.setText(moneyDec.doubleValue() + "元");
    }

    //sureButton事件处理
    private void sureButtonActionPerformed(ActionEvent evt) {
        if (0 == MainRun.canUseMoneys.size()) {
            JOptionPane.showMessageDialog(this, "恭喜！存储计划完成！去买想要的东西吧！", "提示", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
            return ;
        }
        //写入文件
        File file = new File(MainRun.filePath);
        if (!file.exists()) {
            JOptionPane.showMessageDialog(this, MainRun.filePath + "不存在", "错误", JOptionPane.ERROR_MESSAGE);
            return ;
        }

        String content = MainRun.canUseMoneys.get(MainRun.index) + "\n";
        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            bufferedWriter.append(content);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "写入文件" + MainRun.filePath + "失败", "错误", JOptionPane.ERROR_MESSAGE);
            return ;
        } finally {
            try {
                bufferedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        caculateAmount();

        //弹对话框，是否要继续，继续则调用init(),不继续则弹出计算后的框后关闭
        if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(this, "保存成功，是否继续？", "确认", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE)) {
            //继续
            MainRun.canUseMoneys = new ArrayList<BigDecimal>();
            MainRun.canNoUseMoneys = new ArrayList<BigDecimal>();
            MainRun.index = 0;
            MainRun.read();
            init();
        } else {
            //退出
            JOptionPane.showMessageDialog(this, "明天记得来存哦！！！拜拜", "Exit", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }

    }

    /**
     * 点击选择之后的计算总额
     */
    private void caculateAmount() {
        BigDecimal total = new BigDecimal(0);
        for (int i=0; i<MainRun.canNoUseMoneys.size(); i++) {
            total = total.add(MainRun.canNoUseMoneys.get(i));
        }
        total = total.add(MainRun.canUseMoneys.get(MainRun.index));
        total = total.setScale(2, BigDecimal.ROUND_DOWN);
        amount.setText("总计：" + total.doubleValue() + "元   已经登记了" + (MainRun.canNoUseMoneys.size() + 1) + "天");

    }

    //largeButton事件处理
    private void largeButtonActionPerformed(ActionEvent evt) {
        if (MainRun.canUseMoneys.size() - 1 == MainRun.index) {
            JOptionPane.showMessageDialog(this, "这是最大的了", "提示", JOptionPane.INFORMATION_MESSAGE);
            return ;
        }
        int index = generateRandom(MainRun.index + 1, MainRun.canUseMoneys.size() - 1);
        MainRun.index = index;
        BigDecimal moneyDec = MainRun.canUseMoneys.get(index);
        moneyDec = moneyDec.setScale(2, BigDecimal.ROUND_DOWN);
        money.setText(moneyDec.doubleValue() + "元");
    }

    public static void start() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //设置主页面信息
                MainFrame mainFrame = new MainFrame();
                mainFrame.setVisible(true);
                mainFrame.setResizable(false);
                mainFrame.setTitle("璇的存钱罐");
            }
        });
    }

    // Variables declaration - do not modify
    private javax.swing.JButton sureButton;
    private javax.swing.JButton largeButton;
    private javax.swing.JButton smallButton;
    private javax.swing.JLabel amount;
    private javax.swing.JLabel money;
    // End of variables declaration
}
