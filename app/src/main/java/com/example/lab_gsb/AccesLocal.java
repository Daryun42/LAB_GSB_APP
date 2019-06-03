package com.example.lab_gsb;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class AccesLocal {

    private String nomBase = "bdLabGSB.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accessBD;
    private SQLiteDatabase bd;
    public AccesLocal(Context contexte){
        accessBD = new MySQLiteOpenHelper(contexte, nomBase, null, versionBase);
    }

    public void ajout(Visite visite){
        bd = accessBD.getWritableDatabase();
        String req = "insert into visites (id,dateVisite,nomVisiteur,nomPraticien) values";
        req += "("+1+",\""+user.getDateVisite()+"\","+user.getNomVisiteur()+","+user.getNomPraticien()+")";
        bd.execSQL(req);
    }
}
