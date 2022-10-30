/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatonlan;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Mathew alex
 */
public class Clientchat {
    
 private static JPanel contentPane;
 static JFrame chatWindow = new JFrame("Chat Application");
 static JButton btnNewButton_2 = new JButton("Admin");
static JButton btnNewButton_3 = new JButton("Remove");
static JLabel lblNewLabel = new JLabel("CHAT APPLICATION");
static JLabel lblNewLabel_1 = new JLabel("ONLINE USER");
JScrollPane scrollPane = new JScrollPane();
static JList list = new JList();
static JButton btnNewButton = new JButton("SEND MESSAGE");
static JButton btnNewButton_1 = new JButton("DISSCONECT");
 static JTextField textField = new JTextField();
JScrollPane scrollPane_1 = new JScrollPane();
static JTextArea chatarea = new JTextArea();
JLabel lblNewLabel_2 = new JLabel("");
static BufferedReader in;
static PrintWriter out;
static Clientchat ww;
static String nam;
  static Socket soc;
  static String[] fans;
  static int count=0;
  static ArrayList<Integer> privatemessage = new ArrayList<Integer>();
    
    Clientchat(){
   chatWindow.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

WindowListener exitListener = new WindowAdapter() {

    @Override
    public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showOptionDialog(
             chatWindow, "Are You Sure to Close Application?", 
             "Exit Confirmation", JOptionPane.YES_NO_OPTION, 
             JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            out.println("!@#$!@#$!@#");
            try {
                soc.close();
            } catch (IOException ex) {
                Logger.getLogger(Clientchat.class.getName()).log(Level.SEVERE, null, ex);
            }
   System.exit(0);
        }
    }
};chatWindow.addWindowListener(exitListener);
   chatWindow.setBounds(100, 100, 618, 469);
   contentPane = new JPanel();
contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

chatWindow.setContentPane(contentPane);
contentPane.setLayout(null);


lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
lblNewLabel.setBackground(Color.BLACK);
lblNewLabel.setForeground(Color.BLACK);
lblNewLabel.setBounds(10, 18, 145, 27);
contentPane.add(lblNewLabel);

lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 15));
lblNewLabel_1.setBounds(449, 36, 145, 34);
contentPane.add(lblNewLabel_1);

scrollPane.setBounds(449, 72, 145, 285);
contentPane.add(scrollPane);

list.setBackground(Color.white);
list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
jnknk();
scrollPane.setViewportView(list);

btnNewButton_2.setBackground(Color.WHITE);
btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 14));
btnNewButton_2.setBounds(310, 18, 120, 26);
                makeadmin();

btnNewButton_2.setVisible(false);

contentPane.add(btnNewButton_2);

btnNewButton_3.setBackground(Color.WHITE);
btnNewButton_3.setFont(new Font("Tahoma", Font.BOLD, 14));
btnNewButton_3.setBounds(435, 18, 120, 26);
             
adminremove();
btnNewButton_3.setVisible(false);
contentPane.add(btnNewButton_3);

btnNewButton.setBackground(Color.WHITE);
btnNewButton.setForeground(new Color(0, 0, 0));
btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
btnNewButton.setBounds(384, 381, 171, 33);
sendmessage();
contentPane.add(btnNewButton);

btnNewButton_1.setBackground(Color.WHITE);
btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 14));
btnNewButton_1.setBounds(170, 18, 129, 26);
disconnectmessage();
contentPane.add(btnNewButton_1);


textField.setBounds(10, 378, 330, 44);
contentPane.add(textField);
textField.setColumns(10);


scrollPane_1.setBounds(10, 72, 429, 285);
contentPane.add(scrollPane_1);


chatarea.setEditable(false);
scrollPane_1.setViewportView(chatarea);


lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
lblNewLabel_2.setBounds(20, 49, 396, 21);
contentPane.add(lblNewLabel_2);

chatWindow.setVisible(true);

       

        textField.setEditable(false);

        chatarea.setEditable(false);

       

        



    }
    
    void startChat() throws Exception

    {
        
        boolean isvalid = false;
        
        String  ipAddress;
        
        while(!isvalid){

       ipAddress = JOptionPane.showInputDialog(

                chatWindow,

                "Enter IP Address:",

                "IP Address Required!!",

                JOptionPane.PLAIN_MESSAGE);
       
       try{
       soc = new Socket(ipAddress, 9806);
       isvalid= true;
       }catch(IOException e){
 JOptionPane.showMessageDialog(chatWindow,"Please enter a valid Ip address","Alert",JOptionPane.WARNING_MESSAGE);     

isvalid = false;
       }
       
        }
        

 //soc = new Socket(ipAddress, 9806);




 in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

       out = new PrintWriter(soc.getOutputStream(), true);

      // in = new BufferedReader(new InputStreamReader(soc.getInputStream()));

       //out = new PrintWriter(soc.getOutputStream(), true);

       while (true)

       {

         String str = in.readLine();

           if (str.equals("#$@^*#"))

           {
//JOptionPane pane = new JOptionPane();
//JFrame frame = new JFrame();
           String name = JOptionPane.showInputDialog(

                       chatWindow,

                       "Enter a unique name:",

                       "Name Required!!",

                       JOptionPane.PLAIN_MESSAGE);
           
           if(name== null){
           System.exit(0);
           
           
           }
           
           //if((JOptionPane.CLOSED_OPTION)){
           //System.exit(0);
           //}
         
         

               out.println(name);

              

           }

           else if(str.equals("****#@"))

           {

           String name = JOptionPane.showInputDialog(

                       chatWindow,

                       "Enter another name:",

                       "Name Already Exits!!",

                       JOptionPane.WARNING_MESSAGE);

          

               out.println(name);

           }

           else if (str.startsWith("$$$$$$##"))

           {

               textField.setEditable(true);

               lblNewLabel_2.setText("You are logged in as: "+str.substring(8));
               nam=str.substring(8);
              
    
              chatWindow.setTitle(str.substring(8)+" 's chatbox");
              
            
           }
           
           else if(str.startsWith("$^&*(#$")){
           
           btnNewButton_2.setVisible(true);
btnNewButton_3.setVisible(true);
textField.setEditable(true);

           
           
           }
           else if(str.startsWith("$@#$%^&*"))
           
           {
           String s = str.substring(8);
           System.out.println(s);
           s=s.replace('[', ' ').replace(']',' ');
            System.out.println(s);
            fans=s.split(",");
            
            list.setListData(fans);
           
           }else if(str.startsWith("#$@!%^&")){
           out.println("!@#$!@#$!@#admin");
           JOptionPane.showMessageDialog(chatWindow,"You were removed by the admin","Alert",JOptionPane.WARNING_MESSAGE);     
           //out.println("!@#$!@#$!@#admin");
           System.exit(0);
           
           }
           
           
           else

           {

               chatarea.append(str + "\n");

           }

       }

   }
    
 

    public static void main(String[] args) throws Exception {
       ww = new Clientchat();
        ww.startChat();
    }
    
    
    public static void jnknk(){ 
    list.addListSelectionListener(new ListSelectionListener() {
    @Override
    public void valueChanged(ListSelectionEvent event) {
       
       System.out.println(Serverchat.printWriters.size());
            privatemessage.add(list.getSelectedIndex());
           System.out.println(privatemessage.toString());
       
        
    }
});
    }
    
    public static void makeadmin() {
        btnNewButton_2.addActionListener(
        new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                makeadminenter();
            }
        }
        );
    }
    
     public static void makeadminenter(){
    if(Clientchat.privatemessage.isEmpty()){
    JOptionPane.showMessageDialog(chatWindow,"Please the user to make admin","Alert",JOptionPane.WARNING_MESSAGE);     

    
    }else{
    
    Clientchat.out.println("^&*(^&*"+privatemessage);
    
    list.clearSelection();
    privatemessage.clear();
    
    }
     }
     
     public static void adminremove() {
        btnNewButton_3.addActionListener(
        new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                adminremoveenter();
            }
        }
        );
    }
    
     public static void adminremoveenter(){
         
         if(Clientchat.privatemessage.isEmpty()){
    JOptionPane.showMessageDialog(chatWindow,"Please select the user to remove ","Alert",JOptionPane.WARNING_MESSAGE);     

    
    }else{
    
    Clientchat.out.println("#%@#$@#$"+privatemessage);
    
    list.clearSelection();
    privatemessage.clear();
    
    }
     }
    
    

    public static void sendmessage() {
        btnNewButton.addActionListener(
        new java.awt.event.ActionListener()
        {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                sendmessageenter();
            }
        }
        );
    }
    
    public static void sendmessageenter(){
    if(privatemessage.isEmpty()){
    Clientchat.out.println(Clientchat.textField.getText());
    }else{
        
    Clientchat.out.println("$@#^&%^"+privatemessage+Clientchat.textField.getText());
    list.clearSelection();
    privatemessage.clear();
    System.out.println("$@#^&%^"+privatemessage+Clientchat.textField.getText());
    }
            Clientchat.textField.setText("");
    
    }

    public static void disconnectmessage() {
        btnNewButton_1.addActionListener(
        new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    disconnectenter();
                } catch (IOException ex) {
                    Logger.getLogger(Clientchat.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        );
        
        
    }
    
    public static void disconnectenter() throws IOException{
   Clientchat.out.println("!@#$!@#$!@#");
   soc.close();
   System.exit(0);
   
    
    }
    

      
      
}

