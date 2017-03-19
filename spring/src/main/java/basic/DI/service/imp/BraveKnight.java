package basic.DI.service.imp;

import basic.DI.service.Knight;
import basic.DI.service.Quest;

/**
 * Created by Administrator on 2017/3/15.
 */
public class BraveKnight implements Knight{
    private Quest quest;

    public BraveKnight(Quest quest) {
        this.quest = quest;
    }
    public void embarkOnQuest() {
        quest.embark();
    }
}
