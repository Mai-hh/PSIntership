package com.maihao.littlerecipes.model;

public class RecipeData {

    // 属性
    private String title;

    private String content;

    private int imageId;

    private String ingredients;

    private String procedure;

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


    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getIngredients() {
        return ingredients;
    }

    public void setIngredients(String ingredients) {
        this.ingredients = ingredients;
    }

    public String getProcedure() {
        return procedure;
    }

    public void setProcedure(String procedure) {
        this.procedure = procedure;
    }
}
