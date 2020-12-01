/*
 * Mark Tasker
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JOptionPane;


public class ATMMachineSimulation extends JFrame{
    private JPanel wireframe;
    private JButton withdrawcmd,depositcmd,transfercmd,balancecmd;
    private JRadioButton checkingradio,savingsradio;
    private ButtonGroup radiogroup;  
    private JTextField inputfield;
    private int width=400; private int height=250;
    private int margin=40;
    private int cmd_width=150; private int cmd_height=30;
    private int inputfield_width=250; private int inputfield_height=28;
    private int amount_increments=20;
    private int amount_provided;
    private int balance;
    public ATMMachineSimulation(){  
        JFrame atm=new JFrame(" Java Simulated ATM Machine");   
        atm.setResizable(false);
        atm.setLocationRelativeTo(null);
        atm.setDefaultCloseOperation(EXIT_ON_CLOSE);
        atm.setSize(width,height);
        atm.setLayout(new BorderLayout());
        wireframe=new JPanel();
        wireframe.setBorder(BorderFactory.createEmptyBorder(margin, margin, margin, margin));      
        withdrawcmd=new JButton("Withdraw");
        withdrawcmd.setPreferredSize(new Dimension(cmd_width,cmd_height));
        withdrawcmd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
              String user_amount = inputfield.getText();
              try{
                  amount_provided=Integer.parseInt(user_amount);
                  if(amount_provided%amount_increments==0){
                      Account acc=new Account();
                      acc.withdraw(amount_provided);                      
                  }else{
                      JOptionPane.showMessageDialog(null,"Ensure the amount is in increments of $20","Wrong Amount",JOptionPane.ERROR_MESSAGE);
                  }
                 
              }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Please provide a numeric value","Wrong Input Format",JOptionPane.ERROR_MESSAGE);  
              }
            }
        });
        wireframe.add(withdrawcmd);
        depositcmd=new JButton("Deposit");
        depositcmd.setPreferredSize(new Dimension(cmd_width,cmd_height));
        depositcmd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
              String user_amount = inputfield.getText();
              try{
                  amount_provided=Integer.parseInt(user_amount);
                  Account acc=new Account();
                  acc.deposit(amount_provided);
                  JOptionPane.showMessageDialog(null,"Amount Successfully Deposited.","Amount Deposit Success Prompt", JOptionPane.INFORMATION_MESSAGE);
              }catch(NumberFormatException e){
                JOptionPane.showMessageDialog(null,"Please provide a numeric value","Wrong Input Format",JOptionPane.ERROR_MESSAGE);  
              }
            }
        });
        wireframe.add(depositcmd);
        transfercmd=new JButton("Transfer");
        transfercmd.setPreferredSize(new Dimension(cmd_width,cmd_height));
        transfercmd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
              String user_amount = inputfield.getText();
              try{
                  amount_provided=Integer.parseInt(user_amount);
                  Account acc=new Account();
                  acc.transfer(amount_provided);   
                  JOptionPane.showMessageDialog(null,"Funds Successfully Transferred: " + amount_provided,"Funds Transfer",JOptionPane.INFORMATION_MESSAGE);
              }catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Please provide a numeric value","Wrong Input Format",JOptionPane.ERROR_MESSAGE);  
              }
            }
        });
        wireframe.add(transfercmd);
        balancecmd=new JButton("Balance");
        balancecmd.setPreferredSize(new Dimension(cmd_width,cmd_height));
        balancecmd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
              Account acct=new Account();
              balance=acct.getBalance();
              JOptionPane.showMessageDialog(null,"Your Account Balance : "+balance, "Accunt Balance Prompt",JOptionPane.INFORMATION_MESSAGE);
            }
        });
        wireframe.add(balancecmd);
        
        radiogroup=new ButtonGroup();
        checkingradio=new JRadioButton("Checking");   
        checkingradio.setSelected(true);
        radiogroup.add(checkingradio);
        wireframe.add(checkingradio);
        savingsradio=new JRadioButton("Savings");
        radiogroup.add(savingsradio);
        wireframe.add(savingsradio);
        inputfield=new JTextField();
        inputfield.setPreferredSize(new Dimension(inputfield_width,inputfield_height));
        wireframe.add(inputfield);
        atm.add(wireframe,BorderLayout.CENTER);
        atm.setVisible(true);
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here  
        new ATMMachineSimulation();       
    }
    public class InsufficientFunds {
    public InsufficientFunds(){
        
    }
    public void check_funds(){
        
       if(amount_provided>balance){
           JOptionPane.showMessageDialog(null,"Insufficient Balance","Balance ",JOptionPane.ERROR_MESSAGE);
       }
    }
}

public static class Account {
    private static int amount;
    private static int balance;
    private static double service_charges=1.50;
    private static int num_withdrawals;
    private final static int max_withdrawals=4;
    //The constructor method
    public Account(){
        this.amount=amount;
        this.balance=balance;
        this.num_withdrawals=num_withdrawals;
    }
    
    //Withdrawing some amount
    
    //Depositing some cash
   void deposit(int amount_provided) {
        balance+=amount_provided;
    }
    //Transferring funds from one account to another
    void transfer(int amount_provided) {
        balance+=amount_provided;
    }
    //Obtaining Acccount Balance
    int getBalance() {
        return balance;
    }    

    void withdraw(int amount_provided){
         if (amount_provided>balance){
            JOptionPane.showMessageDialog(null,"Insufficient Account Balance.","Operation Impossible",JOptionPane.ERROR_MESSAGE);
        }else{
               if(num_withdrawals<max_withdrawals){
                    balance-=amount_provided;
               }else{
                    balance=(int) (balance-amount_provided-service_charges);
               }
               JOptionPane.showMessageDialog(null,"Amount successfully Withdrawn.","Amount Withdraw Success Prompt", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
}
