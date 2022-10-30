/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package chatonlan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Mathew alex
 */
public class Serverchat {
    
     

      static ArrayList<String> userNames = new ArrayList<String>();

      static ArrayList<PrintWriter> printWriters = new ArrayList<PrintWriter>();

     

      public static void main(String[] args) throws Exception{


           System.out.println("Waiting for clients..."); 

           ServerSocket ss = new ServerSocket(9806);

           while (true)

           {

             Socket soc = ss.accept();

               System.out.println("Connection established");

             ConversationHandler handler = new ConversationHandler(soc);

             handler.start();

           }

            

      }
    
}

class ConversationHandler extends Thread

{

    Socket socket;

    BufferedReader in;

    PrintWriter out;

    String name;

  

   public ConversationHandler(Socket socket) throws IOException {

        this.socket = socket;

    }

    @Override
   public void run()

   {

        try

        {

            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out = new PrintWriter(socket.getOutputStream(), true);
            
           

            int count = 0;

            while (true)

            {

               if(count > 0)

               {

                    out.println("****#@");

               }

               else

               {

                    out.println("#$@^*#");

               }

            

               name = in.readLine();

            

               if (name == null)

               {
                        return;
                 
               }

        

               if (!Serverchat.userNames.contains(name))

               {

                  Serverchat.userNames.add(name);
                  break;

               }

             count++;

           }

           
if(Serverchat.userNames.size()==1){
    out.println("$$$$$$##"+name);

out.println("$^&*(#$"+name);

}else{
            out.println("$$$$$$##"+name);  
}
            Serverchat.printWriters.add(out);
            
            for (PrintWriter writer : Serverchat.printWriters) {

                    writer.println("$@#$%^&*"+ Serverchat.userNames);
                     writer.println(name +" has joined the chat");

                }

           

            while (true)

            {

                String message = in.readLine();

               

                if (message == null)

                {

                    return;

                }

               

                //pw.println(name + ": " + message);
                
                if(message.startsWith("!@#$!@#$!@#")){
                    String s = message.substring(11,message.length());
                    System.out.println(s);
                    Serverchat.printWriters.remove(out);
                    System.out.print(Serverchat.userNames.toString());
                    Serverchat.userNames.remove(name);
                     System.out.print(Serverchat.userNames.toString());
                for (PrintWriter writer : Serverchat.printWriters) {

                    writer.println("$@#$%^&*"+ Serverchat.userNames);
                    if(s.equalsIgnoreCase("admin")){
                    writer.println(name+" was removed by the admin");
                    }else{
                    writer.println(name+" has left the chat");
                    }
                }
                
                }else if(message.startsWith("#%@#$@#$")){
                
                String s= message.substring(8, message.length());
                System.out.println(s);
                s=s.replace('[', ' ').replace(']',' ');
            System.out.println(s);
            String[] fans=s.split(",");
            
                  int [] arr = new int [fans.length];
      for(int i=0; i<fans.length; i++) {
        arr[i] = Integer.parseInt(fans[i].trim());
      }
      
      int[] fans1= new int[(arr.length)/2];
      int inndex=0;
      for(int i =0;i<arr.length-1;i++){
          
          if(arr[i]==arr[i+1]){
          fans1[inndex]=arr[i];
          inndex++;
          }  
                }
      for (int w : fans1) {

                    Serverchat.printWriters.get(w).println("#$@!%^&");
      }
      
                
                
                }else if(message.startsWith("^&*(^&*")){
                String s= message.substring(8, message.length());
                System.out.println(s);
                s=s.replace('[', ' ').replace(']',' ');
            System.out.println(s);
            String[] fans=s.split(",");
            
                  int [] arr = new int [fans.length];
      for(int i=0; i<fans.length; i++) {
        arr[i] = Integer.parseInt(fans[i].trim());
      }
      
      int[] fans1= new int[(arr.length)/2];
      int inndex=0;
      for(int i =0;i<arr.length-1;i++){
          
          if(arr[i]==arr[i+1]){
          fans1[inndex]=arr[i];
          inndex++;
          }  
                }
      for (int w : fans1) {

                    Serverchat.printWriters.get(w).println("$^&*(#$");
      }
      
                }else if(message.startsWith("$@#^&%^")){
                
                String kiss = message.substring(7, message.length()).replace('[', ' ').trim();
                System.out.println(kiss);
                       String[] op = kiss.split("]");
                       System.out.println(Arrays.toString(op));
                
                
           String[] q =op[0].split(",");
           System.out.println(q.length);
           System.out.println(Arrays.toString(q));
          
                int [] arr = new int [q.length];
      for(int i=0; i<q.length; i++) {
        arr[i] = Integer.parseInt(q[i].trim());
      }
      
      int[] fans= new int[(arr.length)/2];
      int inndex=0;
      for(int i =0;i<arr.length-1;i++){
          
          if(arr[i]==arr[i+1]){
          fans[inndex]=arr[i];
          inndex++;
          }
      
      }
       System.out.println(Arrays.toString(fans));
       Serverchat.printWriters.get(Serverchat.userNames.indexOf(name)).println("you send a private message  "+op[1]);
      for (int w : fans) {

                    Serverchat.printWriters.get(w).println("private message by "+name + ": " + op[1]);
      }
                }else{

                for (PrintWriter writer : Serverchat.printWriters) {

                    writer.println(name + ": " + message);

                }
                }

            }

           

        }

        catch (IOException e)

        {

            System.out.println(e);

        }

   }

}
