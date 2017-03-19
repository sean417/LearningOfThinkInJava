package basic.DI.service.imp;

import basic.DI.service.Quest;

import java.io.PrintStream;

/**
 * Created by Administrator on 2017/3/15.
 */
public class SlayDragonQuest implements Quest{
    private PrintStream stream;

    public SlayDragonQuest(PrintStream stream) {
        this.stream = stream;
    }

    public void embark() {
        stream.println("Embarking on quest to slay the dragon!");
    }
}
