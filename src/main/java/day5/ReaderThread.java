package day5;

import java.io.*;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class ReaderThread implements Runnable {
    protected BlockingQueue<String> blockingQueue = null;
    protected String inputFile = null;

    public ReaderThread(BlockingQueue<String> blockingQueue, String inputFile){
        this.blockingQueue = blockingQueue;
        this.inputFile = inputFile;
    }

    @Override
    public void run() {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File("src/main/resources/"+ inputFile)));
            String buffer =null;
            while((buffer=br.readLine())!=null){
                if(buffer.isBlank()) {
                    blockingQueue.put(buffer);
                    continue;
                }
                String [] parts = buffer.split(" ");
                int i = 1;
                int sum = Integer.parseInt(parts[0]);
                for(int j = 1; j < parts.length; j++) {
                    if(Objects.equals(parts[j], "+")) {
                        i = 1;
                    } else if (Objects.equals(parts[j], "-")){
                        i = -1;
                    } else {
                        sum += (i * Integer.parseInt(parts[j]));
                    }
                }
                blockingQueue.put(buffer + " = " + sum);
            }
            blockingQueue.put("EOF");  //When end of file has been reached
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch(InterruptedException e){
            e.printStackTrace();
        }finally{
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
