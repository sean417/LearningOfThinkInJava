package io.part1;

import java.io.File;
import java.io.IOException;

/**
 * Created by chenyang on 16/10/14.
 */
public class ProcessFiles {
    public interface Strategy{
        void process(File file);
    }
    private Strategy strategy;
    private String ext;
    public ProcessFiles(Strategy strategy,String ext){
        this.strategy=strategy;
        this.ext=ext;
    }

    public void start(String[] args){
        try {
            processDirectoryTree(new File("."));

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    public void
    processDirectoryTree(File root) throws IOException{
        for(File file:Directory.walk(root.getAbsoluteFile(),".*\\."+ext)){
            strategy.process(file.getCanonicalFile());
        }

    }
}
