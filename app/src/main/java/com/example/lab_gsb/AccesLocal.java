package com.example.lab_gsb;

import android.content.Context;

public class AccesLocal {

    private String nomBase = "bdLabGSB.sqlite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accessBD;

    public AccesLocal(Context contexte){
        accessBD = new MySQLiteOpenHelper(contexte, nomBase, null, versionBase);
    }
}
