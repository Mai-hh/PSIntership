package com.maihao.littlerecipes.util;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

import com.maihao.littlerecipes.database.RecipeDataSQLHelper;
import com.maihao.littlerecipes.model.RecipeData;

public class Utility {

    public static boolean putRecipeData(RecipeData data, RecipeDataSQLHelper sqlHelper) {

        if (data == null) {
            return false;
        }

        SQLiteDatabase db = sqlHelper.getWritableDatabase();

        // 拿对象内的数据
        String title = data.getTitle();
        String content = data.getContent();
        int imageId = data.getImageId();
        String ingredients = data.getIngredients();
        String procedure = data.getProcedure();

        // 提交数据到数据库
        ContentValues values = new ContentValues();
        values.put("title", title);
        values.put("content", content);
        values.put("imageId", imageId);
        values.put("ingredients", ingredients);
        values.put("procedure", procedure);
        db.insert("Recipes", null, values);
        values.clear();

        return true;

    }

}
