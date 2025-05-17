package com.example.demo2.config;

import org.shoulder.batch.progress.BatchActivityEnum;

/**
 * <a href="http://localhost:8080/ui/activities/page.html?progressId=_shoulderMockAndTest&activityId=MySimpleTaskEnum"></a>
 */
public enum MySimpleTaskEnum implements BatchActivityEnum<MySimpleTaskEnum> {
    TASK1("👂", "识别用户意图", 0, 0),
    TASK2("🧠", "改写查询、扩写查询", 0, 0),
    TASK3("🔍", "查找相关数据", 0, 0),
    TASK4("🧠", "总结信息", 0, 0),
    ;

    private final String taskKey;
    private final String description;
    private final int displayBlockNum;
    private final int getDisplayColumnNum;
    private final String displayEmoji;

    MySimpleTaskEnum(String displayEmoji, String description, int displayBlockNum, int getDisplayColumnNum) {
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