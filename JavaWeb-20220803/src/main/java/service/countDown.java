package service;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

public class countDown extends JFrame {
  private int min = 0;
  
  private int sec = 0;
  
  private ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
  
  private ScheduledFuture future;
  
  private int initDelay = 1;
  
  private int delay = 1;
  
  private boolean status = true;
  
  private String s;
  
  private JButton addOneMin;
  
  private JButton addThreeMin;
  
  private JButton addTwoMin;
  
  private JLabel lab01;
  
  private JButton start;
  
  private JButton stop;
  
  public countDown() {
    initComponents();
  }
  
  private void initComponents() {
    this.addThreeMin = new JButton();
    this.addTwoMin = new JButton();
    this.addOneMin = new JButton();
    this.start = new JButton();
    this.stop = new JButton();
    this.lab01 = new JLabel();
    setDefaultCloseOperation(3);
    this.addThreeMin.setFont(new Font("", 0, 18));
    this.addThreeMin.setText("3 min");
    this.addThreeMin.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            countDown.this.addThreeMinActionPerformed(evt);
          }
        });
    this.addTwoMin.setFont(new Font("", 0, 18));
    this.addTwoMin.setText("2 min");
    this.addTwoMin.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            countDown.this.addTwoMinActionPerformed(evt);
          }
        });
    this.addOneMin.setFont(new Font("", 0, 18));
    this.addOneMin.setText("1 min");
    this.addOneMin.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            countDown.this.addOneMinActionPerformed(evt);
          }
        });
    this.start.setFont(new Font("", 0, 24));
    this.start.setText("Start");
    this.start.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            countDown.this.startActionPerformed(evt);
          }
        });
    this.stop.setFont(new Font("", 0, 24));
    this.stop.setText("Stop and Reset");
    this.stop.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
            countDown.this.stopActionPerformed(evt);
          }
        });
    this.lab01.setFont(new Font("", 0, 36));
    this.lab01.setHorizontalAlignment(0);
    this.lab01.setText("00:00");
    GroupLayout layout = new GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addGap(69, 69, 69)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addGroup(layout.createSequentialGroup()
              .addComponent(this.addThreeMin, -2, 160, -2)
              .addGap(14, 14, 14)
              .addComponent(this.addTwoMin, -2, 160, -2)
              .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
              .addComponent(this.addOneMin, -2, 160, -2))
            .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
              .addComponent(this.start, -2, 240, -2)
              .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767)
              .addComponent(this.stop, -2, 240, -2))
            .addComponent(this.lab01, -1, -1, 32767))
          .addContainerGap(70, 32767)));
    layout.setVerticalGroup(layout
        .createParallelGroup(GroupLayout.Alignment.LEADING)
        .addGroup(layout.createSequentialGroup()
          .addGap(43, 43, 43)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
            .addComponent(this.addOneMin, -1, 36, 32767)
            .addComponent(this.addThreeMin, -1, -1, 32767)
            .addComponent(this.addTwoMin, -1, -1, 32767))
          .addGap(32, 32, 32)
          .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(this.start, -2, 54, -2)
            .addComponent(this.stop, -2, 54, -2))
          .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
          .addComponent(this.lab01, -1, -1, 32767)
          .addGap(60, 60, 60)));
    pack();
  }
  
  private void addThreeMinActionPerformed(ActionEvent evt) {
    if (this.status) {
      this.min += 3;
      labString();
    } 
  }
  
  private void startActionPerformed(ActionEvent evt) {
    if (this.status && this.min > 0) {
      this.status = false;
      Runnable r = new Runnable() {
          public void run() {
            if (countDown.this.min == 0 && countDown.this.sec == 0) {
              countDown.this.status = true;
              countDown.this.future.cancel(!countDown.this.future.isCancelled());
            } 
            if (countDown.this.sec > 0)
              countDown.this.sec = countDown.this.sec - 1; 
            if (countDown.this.min > 0 && countDown.this.sec == 0) {
              countDown.this.min = countDown.this.min - 1;
              countDown.this.sec = 59;
            } 
            countDown.this.labString();
          }
        };
      this.future = this.service.scheduleAtFixedRate(r, this.initDelay, this.delay, TimeUnit.SECONDS);
    } 
  }
  
  private void addTwoMinActionPerformed(ActionEvent evt) {
    if (this.status) {
      this.min += 2;
      labString();
    } 
  }
  
  private void addOneMinActionPerformed(ActionEvent evt) {
    if (this.status) {
      this.min++;
      labString();
    } 
  }
  
  private void stopActionPerformed(ActionEvent evt) {
    this.future.cancel(!this.future.isCancelled());
    this.status = true;
    this.min = 0;
    this.sec = 0;
    this.lab01.setText("00:00");
  }
  
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
          public void run() {
            (new countDown()).setVisible(true);
          }
        });
  }
  
  private void labString() {
    if (this.min == 0 && this.sec == 0) {
      this.lab01.setText("Time Up");
      return;
    } 
    String mins = String.valueOf(this.min);
    String secs = String.valueOf(this.sec);
    if (this.min < 10)
      mins = "0" + mins; 
    if (this.sec < 10)
      secs = "0" + secs; 
    this.lab01.setText(mins + ":" + secs);
  }
}