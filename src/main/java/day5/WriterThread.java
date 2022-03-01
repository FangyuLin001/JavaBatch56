package day5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;

public class WriterThread implements Runnable{
    protected BlockingQueue<String> blockingQueue = null;
    protected String outputFile = null;

    public WriterThread(BlockingQueue<String> blockingQueue, String outputFile){
        this.blockingQueue = blockingQueue;
        this.outputFile = outputFile;
    }

    @Override
    public void run() {
        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new File("target/" + outputFile));

            while(true){
                String buffer = blockingQueue.take();
                //Check whether end of file has been reached
                if(buffer.equals("EOF")){
                    break;
                }
                writer.println(buffer);
            }


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            writer.close();
        }
    }
}
