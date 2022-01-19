import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String [] args)
    {
        Socket socket = null;
        InputStreamReader in_stream = null;
        OutputStreamWriter out_writer = null;
        BufferedWriter buff_writer = null;
        BufferedReader buff_read = null;

        //waits for socket request over network

        try {
            ServerSocket server_soc = new ServerSocket(2244);

            while (true) {
                socket = server_soc.accept();

                in_stream = new InputStreamReader(socket.getInputStream());
                out_writer = new OutputStreamWriter(socket.getOutputStream());

                buff_read = new BufferedReader(in_stream);
                buff_writer = new BufferedWriter(out_writer);

                while (true){
                    String client_msg = buff_read.readLine();
                    System.out.println("Client: " + client_msg);

                    buff_writer.write("MSG received");
                    buff_writer.newLine();
                    buff_writer.flush();

                    if(client_msg.equalsIgnoreCase("BYE"))
                    {
                        break;
                    }
                }
            }

            }catch (IOException e)
            {
                System.out.println("Input/Output exception");
            }

        try {
            socket.close();
            in_stream.close();
            out_writer.close();
            buff_read.close();
            buff_writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
