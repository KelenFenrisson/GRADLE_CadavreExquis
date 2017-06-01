package fr.iut_orleans.as_2016_2017.besancon_pubert.gradle_cadavreexquis.models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ProjectSQLiteOpenHelper extends SQLiteOpenHelper{

    public static final String NOM_BDD = "CadavreExquis.db";
    public static final int VERSION_BDD = 1;

    public static final String TABLE_UTILISATEUR = "UTILISATEUR";
    public static final String ID_UTILISATEUR = "id_utilisateur";
    public static final String LOGIN_UTILISATEUR = "login_utilisateur";
    public static final String MDP_UTILISATEUR = "mdp_utilisateur";
    public static final String CREATE_TABLE_UTILISATEUR =
            "CREATE TABLE "+TABLE_UTILISATEUR+
                    " ( " +
                    ID_UTILISATEUR+" INTEGER PRIMARY KEY,"+
                    LOGIN_UTILISATEUR+" VARCHAR(32) UNIQUE,"+
                    MDP_UTILISATEUR+" VARCHAR(32)"+
                    ");";
    public static final String DROP_TABLE_UTILISATEUR = "DROP TABLE IF EXISTS "+TABLE_UTILISATEUR+";";
    public static final String INSERT_UTILISATEURS = "INSERT INTO "+TABLE_UTILISATEUR+" VALUES (1,'DarkSasuke93','Hunter2'),(2,'JordanBG75','mabite'),(3,'Lolita12ans','coucou'),(4,'AnusDestroyer','leetsquad'),(5,'TontonToucheTouche','bonbons')(6,'MathieuPubert','mathieu')(7,'JulienBesançon','julien');";


    public static final String TABLE_HISTOIRE = "HISTOIRE";
    public static final String ID_HISTOIRE = "id_histoire";
    public static final String DATECREATION_HISTOIRE = "datecreation_histoire";
    public static final String TITRE_HISTOIRE = "titre_histoire";
    public static final String CREATE_TABLE_HISTOIRE =
            "CREATE TABLE "+TABLE_HISTOIRE+
                    " ( " +
                    ID_HISTOIRE+" INTEGER PRIMARY KEY,"+
                    DATECREATION_HISTOIRE+" INTEGER,"+
                    TITRE_HISTOIRE+" VARCHAR(50)"+
                    ");";
    public static final String DROP_TABLE_HISTOIRE = "DROP TABLE IF EXISTS "+TABLE_HISTOIRE+";";
    public static final String INSERT_HISTOIRES = "INSERT INTO "+TABLE_HISTOIRE+" VALUES (1,1496304426,'Le gros baton et le petit trou'),(2,1496314426,'Cendrillon : Reboot'),(3,1496324426,'Ce soir tu vas prendre ...');";


    public static final String TABLE_TEXTE = "TEXTE";
    public static final String ID_TEXTE = "id_texte";
    public static final String DATE_TEXTE = "date_texte";
    public static final String CONTENU_TEXTE = "contenu_texte";
    public static final String CREATE_TABLE_TEXTE =
            "CREATE TABLE "+TABLE_TEXTE+
                    " ( " +
                    ID_TEXTE+" INTEGER PRIMARY KEY,"+
                    ID_UTILISATEUR+" INTEGER,"+
                    DATE_TEXTE+" INTEGER,"+
                    CONTENU_TEXTE+" VARCHAR(1000),"+
                    ID_HISTOIRE+" INTEGER,"+
                    "FOREIGN KEY ("+ID_UTILISATEUR+") REFERENCES "+TABLE_UTILISATEUR+"("+ID_UTILISATEUR+")," +
                    "FOREIGN KEY ("+ID_HISTOIRE+") REFERENCES "+TABLE_HISTOIRE+"("+ID_HISTOIRE+")"+
                    ");";
    public static final String DROP_TABLE_TEXTE = "DROP TABLE IF EXISTS "+TABLE_TEXTE+";";
    public static final String INSERT_TEXTES = "INSERT INTO "+TABLE_HISTOIRE+" VALUES" +
            "(1,1,1496304426,'Il etait une fois une poutre en chêne massif qui cherchait une maison.',1)" +
            "(2,2,1496314426,'Un beau jour, dans ses recherches, elle trébucha sur un nid de poule.',1)" +
            "(3,3,1496324426,'Ce qui déforma sa jante et énerva la poule propriétaire du nid.',1)" +
            "(4,4,1496334426,'Mes oeufs sont tous cassés, dit la poule contrariée.',1)" +
            "(5,5,1496344426,'Moralité : il faut caresser les oeufs et ne pas les frotter.',1)" +
            "(6,6,1496354426,'Cendrillon, une jeune ado des quartiers pauvres, faisait le ménage chez la nouvelle pouf de son daron.',2)" +
            "(7,7,1496364426,'Mon pote est venu me chercher chez moi a 19h et m'a dit : ce soir mec, tu vas prendre ... L'apéro avec moi',3)";



    public static final String TABLE_EVALUER = "EVALUER";
    public static final String DATE_EVALUER = "date_evaluer";
    public static final String NOTE_EVALUER = "note_evaluer";
    public static final String COMMENTAIRE_EVALUER = "commentaire_evaluer";
    public static final String CREATE_TABLE_EVALUER =
            "CREATE TABLE "+TABLE_EVALUER+
                    " ( " +
                    ID_UTILISATEUR+" INTEGER,"+
                    ID_HISTOIRE+" INTEGER,"+
                    DATE_EVALUER+" INTEGER,"+
                    NOTE_EVALUER+" INTEGER(2),"+
                    COMMENTAIRE_EVALUER+" VARCHAR(140),"+
                    "  PRIMARY KEY ("+ID_UTILISATEUR+", "+ID_HISTOIRE+", "+DATE_EVALUER+")," +
                    "  FOREIGN KEY ("+ID_UTILISATEUR+") REFERENCES "+TABLE_UTILISATEUR+"("+ID_UTILISATEUR+")," +
                    "  FOREIGN KEY ("+ID_HISTOIRE+") REFERENCES "+TABLE_HISTOIRE+"("+ID_HISTOIRE+")"+
                    ");";
    public static final String DROP_TABLE_EVALUER = "DROP TABLE IF EXISTS "+TABLE_EVALUER+";";

    public ProjectSQLiteOpenHelper(Context context, String name, CursorFactory cursorFactory, int version){
        super(context, name, cursorFactory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_TABLE_UTILISATEUR);
        sqLiteDatabase.execSQL(CREATE_TABLE_HISTOIRE);
        sqLiteDatabase.execSQL(CREATE_TABLE_TEXTE);
        sqLiteDatabase.execSQL(CREATE_TABLE_EVALUER);
        sqLiteDatabase.execSQL(INSERT_HISTOIRES);
        sqLiteDatabase.execSQL(INSERT_TEXTES);
        sqLiteDatabase.execSQL(INSERT_UTILISATEURS);

    }

    @Override
    public void onOpen(SQLiteDatabase sqLiteDatabase) {
        super.onOpen(sqLiteDatabase);
    }

    @Override
    public void onConfigure(SQLiteDatabase sqLiteDatabase) {
        super.onConfigure(sqLiteDatabase);
        sqLiteDatabase.setForeignKeyConstraintsEnabled(true);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_EVALUER+";");
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_TEXTE+";");
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_HISTOIRE+";");
        sqLiteDatabase.execSQL("DROP TABLE " +TABLE_UTILISATEUR+";");
        onCreate(sqLiteDatabase);
    }

}