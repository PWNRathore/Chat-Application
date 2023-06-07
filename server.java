import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
class Server
{
    ServerSocket server;
    Socket socket;

    BufferedReader Br;
    PrintWriter out;
    //constructor 
    public Server()
    {
        try{
        server=new ServerSocket(7770);
        System.out.println("server is ready to accept connection");
        System.out.println("waiting...");
        socket=server.accept();

        Br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
        out=new PrintWriter(socket.getOutputStream());

        startReading();
        startwriting();

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
     public void startReading()
     {
        Runnable r1=()->{

            System.out.println("reader started...");
            while(true)
            {
                try
               {
                 String msg=Br.readLine();
                      if(msg.equals("exit.."))
                         {
                           System.out.println("client terminated the chat");
                                   break;
                               }
                                  System.out.println("client :"+msg);
                                 }
            catch(Exception e)
             {
                e.printStackTrace();
          }
        
     }
    };
    new Thread(r1).start();
}
     public void startwriting()
     {

        Runnable r2=()->{
            System.out.println("writer started...");
            while(true)
            {
                try
                {
                    BufferedReader br1=new BufferedReader(new InputStreamReader(System.in));
                    String content = br1.readLine();
                    out.println(content);
                    out.flush();
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }
            }

        };
        new Thread(r2).start();
     }
     
    public static void main(String[] args) {
        System.out.println("this is server...going to start server ");
        Server sr=new Server();
    }
}