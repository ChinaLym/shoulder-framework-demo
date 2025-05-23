package com.example.demo2.config;

import org.shoulder.batch.progress.BatchActivityEnum;

/**
 * <a href="http://localhost:8080/ui/activities/page.html?progressId=_shoulderMockAndTest&activityId=MyTaskEnum"></a>
 */
public enum MyTaskEnum implements BatchActivityEnum<MyTaskEnum> {
    TASK_BLOCK1_MAIN_1("1.1.1", "任务1", 1, 1),

    TASK_BLOCK1_MAIN_2("1.1.2", "任务2", 1, 1),

    TASK_BLOCK1_MAIN_3("2.1.1", "任务3.1", 2, 0),

    TASK_BLOCK1_MAIN_4("2.2.1", "任务3.2.1", 2, 1),
    TASK_BLOCK1_MAIN_5("2.2.2", "任务3.2.2", 2, 1),

    TASK_BLOCK1_MAIN_10("3.1.1", "任务3", 3, 0),
    ;

    private final String taskKey;
    private final String description;
    private final int displayBlockNum;
    private final int getDisplayColumnNum;
    private final String displayEmoji;

    MyTaskEnum(String displayEmoji, String description, int displayBlockNum, int getDisplayColumnNum) {
        this.taskKey = name();
        this.displayEmoji = displayEmoji;
        this.description = description;
        this.displayBlockNum = displayBlockNum;
        this.getDisplayColumnNum = getDisplayColumnNum;
    }

    @Override
    public String getKey() {
        return taskKey;
    }

    @Override
    public boolean hasSubTask() {
        return false;
    }

    @Override
    public String getDisplayName() {
        return description;
    }

    @Override
    public String getDisplayEmoji() {
        return displayEmoji;
    }

    @Override
    public int displayBlockNum() {
        return displayBlockNum;
    }

    @Override
    public int getDisplayColumnNum() {
        return getDisplayColumnNum;
    }
}