import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class client extends JFrame
{
    Socket socket;

    //constructor
    String br;
    PrintWriter out;

/*     private JLabel heading=new JLabel("Pawan Rathod");
    private JTextArea messaageArea=new JTextArea();
    private JTextField messageInput=new JTextField();
    private Font font=new Font("Roboto",Font.PLAIN,20);*/

 

    String name;
    public client()
    {
        
        

        try{
        

            System.out.println("Sending request to server");
            socket=new Socket("127.0.0.1",7770);
            System.out.println("connection done.");

           br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out=new PrintWriter(socket.getOutputStream());
            
           // GUI method
            //createGUI();
           // handleEvents();
           startReading();
        startwriting();
        }

            catch(Exception e){
                e.printStackTrace();

            }

        }
       
    
   /* private void handleEvents() {
        messageInput.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyTyped'");
            }

            @Override
            public void keyPressed(KeyEvent e) {
                // TODO Auto-generated method stub
                throw new UnsupportedOperationException("Unimplemented method 'keyPressed'");
            }

            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    if(e.getKeyCode()==10)
                    {
                        System.out.println("press Enter key");
                    }
                } catch (Exception e) {  
                    e.printStackTrace();
                }

                
            }
            
        });
    }*/


   /*  private void createGUI() {
        this.setTitle("Watsapp messenger");
        this.setSize(300, 500);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
       // this.setIconImage(new ImageIcon("pwn.png"));
        this.getContentPane().setBackground(Color.gray);


         heading.setFont(font);
        messaageArea.setFont(font);
        messageInput.setFont(font);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        heading.setIcon(new ImageIcon("the.png"));
        heading.setHorizontalTextPosition(SwingConstants.CENTER);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        messageInput.setHorizontalAlignment(SwingConstants.CENTER);  
        

        this.setLayout(new BorderLayout());
        //adding component
        this.add(heading,BorderLayout.NORTH);
        this.add(messaageArea,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);
        
    }*/


   private void startReading() {
        Runnable ru=()->
        {
            System.out.println("Reader Started...");
            while(true)
            {
                try{
              String msg=br.readLine();
            if(msg.equals("exit"))
            {
                System.out.println("client want to terminate..");
                break;
            }
            System.out.println("client:"+msg);
        }
        catch(Exception e){

            e.printStackTrace();
        }
    }
};
new Thread(ru).start();
    }
    public void startwriting()
    {
        Runnable rn=()->
        {
            System.out.println("Writer Started");
            while(true)
            {
                try{
                BufferedReader br3=new BufferedReader(new InputStreamReader(System.in));
                String Content = br3.readLine();
                out.println(Content);
                out.flush();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        };
        new Thread(rn).start();

    }

    public static void main(String args[])
    {
        client cl =new client();
         System.out.println("this is client");

    }
}