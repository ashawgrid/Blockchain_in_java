package blockchain.Threads;

import blockchain.Model.Block;
import blockchain.Model.Message;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class DataReaderThread extends Thread {

    StringBuilder chatList;
    List<Block> blockChain;
    int size;
    Message message;

    public DataReaderThread(List<Block> blockChain, StringBuilder chatList, int size, Message message) {
        this.chatList = chatList;
        this.blockChain = blockChain;
        this.size = size;
        this.message = message;
    }

    @Override
    public void run() {
        size++;
        try (BufferedReader reader = new BufferedReader(new FileReader(new File("/Users/abarnawal/Java Intellijec Projects/Blockchain with Java/Blockchain with Java/task/src/blockchain/Data/ClientMessage.txt")))) {
            String line;
            while ((line = reader.readLine()) != null && blockChain.size() != size) {
                synchronized (this) {
                    chatList.append(line).append("\n");
                }
                Thread.sleep(1L);
            }

        } catch (IOException e) {
            throw new RuntimeException();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}