package net.vidalibarraquer.uf2_pt1_pablosanjose;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class ManegadorDades extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    //NOM DE LA BASE DE DADES
    private static final String DATABASE_NAME = "vehicles";
    // NOM DE LA TAULA
    private static final String TABLE_VEHICLES = "registre";
    //CAMPS DE LA BASE DE DADES
    private static final String KEY_NOM = "nom";
    private static final String KEY_COGNOM = "cognom";
    private static final String KEY_TELEFON = "telefon";
    private static final String KEY_MARCA = "marca";
    private static final String KEY_MODEL = "model";
    private static final String KEY_MATRICULA = "matricula";


    public ManegadorDades(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREAR LA TABLA

        String Create_Table = "CREATE TABLE " + TABLE_VEHICLES + "( " + KEY_NOM + " TEXT, " + KEY_COGNOM + " TEXT, " + KEY_TELEFON + " TEXT, " +
                KEY_MARCA + " TEXT, " + KEY_MODEL + " TEXT, " + KEY_MATRICULA + " TEXT PRIMARY KEY" + ")";
        db.execSQL(Create_Table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        //DROP SI EXISTEIX
        db.execSQL("DROP TABLE  IF EXISTS " + TABLE_VEHICLES);
        // CREAR LA TABLA DE NOU
        onCreate(db);

    }

    public void addVehicles(Vehicle veh) {
        //
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valors = new ContentValues();
        valors.put(KEY_NOM, veh.getNom());
        valors.put(KEY_COGNOM, veh.getCognom());
        valors.put(KEY_TELEFON, veh.getTelefon());
        valors.put(KEY_MARCA, veh.getMarca());
        valors.put(KEY_MODEL, veh.getModel());
        valors.put(KEY_MATRICULA, veh.getMatricula());

        //Instertar a un registre
        db.insert(TABLE_VEHICLES,null,valors);
        // tancar  conexio base de dades
        db.close();
    }

    //Query que retorna tots els  registres de la taula
    public List<Vehicle> getAllVehicles(){
        List<Vehicle> veh = new ArrayList<Vehicle>();
        // QUERY DE TOTS ELS REGISTRES DE LA TAULA
        String selectTots ="SELECT * FROM "+ TABLE_VEHICLES;
        //Executem la query  //CUANDO SOLO RETORNA UN REGISTRO ES getReadableDatabase si son mas de uno seria getWritableDatabase()
        SQLiteDatabase db = this.getWritableDatabase();
        // guarda los resultados en la variable "cursor"
        Cursor cursor = db.rawQuery(selectTots, null);
        //desplaçament pel cursor

        if (cursor.moveToFirst()) {
            do{
                //creamos el objeto "vehicle" de la clase Vehicle
                Vehicle vehicle = new Vehicle();
                //creamos los atributos de la clase vehicle
                vehicle.setNom(cursor.getString(1));
                vehicle.setCognom(cursor.getString(2));
                vehicle.setTelefon(cursor.getString(3));
                vehicle.setMarca(cursor.getString(4));
                vehicle.setModel(cursor.getString(5));
                vehicle.setMatricula(cursor.getString(6));
                //añadimos ala LISTA el objeto "vehicle"
                veh.add(vehicle);

            } while (cursor.moveToNext());
        }

        return veh;

    }

    public Vehicle vehicle(int id){
        //creamos la variable que contendra el registro
        Vehicle vehicle = new Vehicle();
        //creamos la query que nos devolvera el registro
        String select = "SELECT * FROM "+ TABLE_VEHICLES + " WHERE " + KEY_MATRICULA + " = " + id;
        //creamos la conexion a la base de datos
        SQLiteDatabase db = this.getWritableDatabase();
        //creamos un cursor que contendra el registro
        Cursor cursor = db.rawQuery(select, null);
        //desplazamos el cursor
        if (cursor.moveToFirst()) {
            do{
                //creamos los atributos de la clase vehicle
                vehicle.setNom(cursor.getString(1));
                vehicle.setCognom(cursor.getString(2));
                vehicle.setTelefon(cursor.getString(3));
                vehicle.setMarca(cursor.getString(4));
                vehicle.setModel(cursor.getString(5));
                vehicle.setMatricula(cursor.getString(6));
            } while (cursor.moveToNext());
        }
        return vehicle;
    }


    public int updateVehicle(Vehicle veh){
        //Actualitzem la base de dades amb el vehiculo que li passem per parametre
        SQLiteDatabase db = this.getWritableDatabase();
        //Creem un array de valors
        ContentValues valors = new ContentValues();
        //afegim els valors dels atributs de la clase vehicle
        valors.put(KEY_NOM, veh.getNom());
        valors.put(KEY_COGNOM, veh.getCognom());
        valors.put(KEY_TELEFON, veh.getTelefon());
        valors.put(KEY_MARCA, veh.getMarca());
        valors.put(KEY_MODEL, veh.getModel());
        valors.put(KEY_MATRICULA, veh.getMatricula());
        //actualitzem el registre
        return db.update(TABLE_VEHICLES, valors, KEY_MATRICULA + " = ?", new String[] {String.valueOf(veh.getMatricula())});
    }

    public Vehicle vehicleMatricula(String matricula){
        //creamos la variable que contendra el registro
        Vehicle vehicle = new Vehicle();
        //creamos la query que nos devolvera el registro
        String select = "SELECT * FROM "+ TABLE_VEHICLES + " WHERE " + KEY_MATRICULA + " = '" + matricula + "'";
        //creamos la conexion a la base de datos
        SQLiteDatabase db = this.getWritableDatabase();
        //creamos un cursor que contendra el registro
        Cursor cursor = db.rawQuery(select, null);
        //desplazamos el cursor
        if (cursor.moveToFirst()) {
            do{
                //creamos los atributos de la clase vehicle
                vehicle.setNom(cursor.getString(1));
                vehicle.setCognom(cursor.getString(2));
                vehicle.setTelefon(cursor.getString(3));
                vehicle.setMarca(cursor.getString(4));
                vehicle.setModel(cursor.getString(5));
                vehicle.setMatricula(cursor.getString(6));
            } while (cursor.moveToNext());
        }
        return vehicle;
    }

    //Query per esborrar un registre de la Taula
    public void deleteVehicle(Vehicle veh){
        SQLiteDatabase db =this.getWritableDatabase();
        db.delete(TABLE_VEHICLES, KEY_MATRICULA+ "= ?",
                new String[]{String.valueOf(veh.getMatricula())});
        db.close();
    }
}

