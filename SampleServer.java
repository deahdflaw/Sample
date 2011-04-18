import java.io.*;
import java.net.*;

class SampleServer extends Thread
{
    Socket s;
    int num;

    public static void main(String args[])
    {
        try
        {
            int i = 0;

            ServerSocket server = new ServerSocket(3128, 0);

            System.out.println("server is started");

            while(true)
            {
                new SampleServer(i, server.accept());
                i++;
            }
        }
        catch(Exception e)
        {System.out.println("init error: "+e);} 
    }

    public SampleServer(int num, Socket s)
    {
        this.num = num;
        this.s = s;

        setDaemon(true);
        setPriority(NORM_PRIORITY);
        start();
    }

    public void run()
    {
        try
        {

            InputStream is = s.getInputStream();

            OutputStream os = s.getOutputStream();

            byte buf[] = new byte[64*1024];

            int r = is.read(buf);

            String data = new String(buf, 0, r);

            data = ""+num+": "+"\n"+data;

            os.write(data.getBytes());

            s.close();
        }
        catch(Exception e)
        {System.out.println("init error: "+e);}
    }
}
