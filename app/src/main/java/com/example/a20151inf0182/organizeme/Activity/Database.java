package com.example.a20151inf0182.organizeme.Activity;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Odair on 14/08/2018.
 */

public class Database extends SQLiteOpenHelper {
    public static String NOME_BANCO;
    public static String SQL;
    public static String [] Dados = new String[10];
    public static Context CONTEXT;
    public Database(Context context, String nomeBanco, String sql){
        super(context, nomeBanco, null, 1);
        this.NOME_BANCO = nomeBanco;
        this.SQL = sql;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(SQL);
        }catch (SQLException e) {
            Toast.makeText(CONTEXT, "Erro de script sql "+e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
