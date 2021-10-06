package com.maihao.littlerecipes.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class RecipeDataSQLHelper extends SQLiteOpenHelper {

    private Context mContext;

    public static final String CREATE_RECIPES = "CREATE TABLE Recipes ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "title TEXT, "
            + "content TEXT, "
            + "imageId INTEGER, "
            + "ingredients TEXT, "
            + "procedure TEXT)";

    public RecipeDataSQLHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_RECIPES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Recipes");
        onCreate(sqLiteDatabase);
    }
}
