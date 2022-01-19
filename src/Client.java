import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String [] args)
    {
        Socket socket = null;
        InputStreamReader inStream = null;
        OutputStreamWriter outWriter = null;

        BufferedReader buffRead = null;
        BufferedWriter buffWriter = null;

        try{
            socket = new Socket("localhost",2244);

            inStream = new InputStreamReader(socket.getInputStream());
            outWriter = new OutputStreamWriter(socket.getOutputStream());

            buffRead = new BufferedReader(inStream);
            buffWriter = new BufferedWriter(outWriter);

            Scanner scan = new Scanner(System.in);

            while(true){

                String msqtoSend = scan.nextLine();
                buffWriter.write(msqtoSend);
                buffWriter.newLine();
                buffWriter.flush();

                System.out.println("Server: " + buffRead.readLine());


                if(msqtoSend.equalsIgnoreCase("BYE")){
                    break;
                }
            }
        }catch (IOException e){
            System.out.println("Input/Output exception.");
            e.printStackTrace();
        }finally {
            try {
                if(socket != null)
                {
                    socket.close();
                }
                if(inStream != null)
                {
                    inStream.close();
                }
                if(outWriter != null){
                    outWriter.close();
                }
                if(buffRead != null){
                    buffRead.close();
                }if(buffWriter != null){
                    buffWriter.close();
                }
            }catch (IOException e){
                System.out.println("Input/Output exception2.");
                e.printStackTrace();
            }
        }
    }
}
