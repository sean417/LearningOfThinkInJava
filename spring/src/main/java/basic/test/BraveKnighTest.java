package basic.test;

import basic.DI.service.Quest;
import basic.DI.service.imp.BraveKnight;
import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Administrator on 2017/3/15.
 */
public class BraveKnighTest {
    @Test
    public void knightShouldEmbarkOnQuest(){
            Quest mockQuest=mock(Quest.class);
            BraveKnight knight=new BraveKnight(mockQuest);
            knight.embarkOnQuest();
            verify(mockQuest,times(1)).embark();
    }
}
