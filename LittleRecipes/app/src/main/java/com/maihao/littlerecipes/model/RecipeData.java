package com.maihao.littlerecipes.model;

public class RecipeData {

    // 属性
    private String title;

    private String content;

    public RecipeData() {

    }

    public RecipeData(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // getter & setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }



}
