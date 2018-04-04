package com.zackchill.livestockmanager;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LivestockDbHelper extends SQLiteOpenHelper
{
    public static final String DATABASE_NAME = "Livestock_db";
    public static final int DATABASE_VERSION = 1;

    //SQLite statement for creating Animal table.
    //!!! I added float data type for weightKg !!!
    public static final String CREATE_ANIMAL_TABLE = "CREATE TABLE "
            + LivestockContract.LiveStockEntry.ANIMAL_TABLE_NAME
            + "(" + LivestockContract.LiveStockEntry.ANIMAL_ID + " integer primary key autoincrement, "
            + LivestockContract.LiveStockEntry.ANIMAL_TYPE + " varchar (255) not null, "
            + LivestockContract.LiveStockEntry.ANIMAL_NAME + " varchar (255) not null, "
            + LivestockContract.LiveStockEntry.ANIMAL_GENDER + " varchar (1) not null, "
            + LivestockContract.LiveStockEntry.ANIMAL_WEIGHTKG + " float not null, "
            + LivestockContract.LiveStockEntry.ANIMAL_FERTILE + " boolean default true not null, "
            + LivestockContract.LiveStockEntry.ANIMAL_NEUTERED + " boolean default false not null, "
            + LivestockContract.LiveStockEntry.ANIMAL_BIRTHDAY + " Text not null, "
            + LivestockContract.LiveStockEntry.ANIMAL_DEATHDAY + " Text, "
            + "unique(name)"
            + "CHECK (deathDay < date('now'))"
            + "CHECK (julianday(deathDay) >= julianday(birthDay) OR deathDay IS NULL));";

    //SQLite statement for creating Area table.
    public static final String CREATE_AREA_TABLE = "CREATE TABLE "
            + LivestockContract.LiveStockEntry.AREA_TABLE_NAME
            + "(" + LivestockContract.LiveStockEntry.AREA_ID + " integer primary key autoincrement, "
            + LivestockContract.LiveStockEntry.AREA_NAME + " varchar (255) not null, "
            + LivestockContract.LiveStockEntry.AREA_ENCLSED + " boolean default true not null, "
            + LivestockContract.LiveStockEntry.AREA_GRAZABLE + " boolean not null, "
            + LivestockContract.LiveStockEntry.AREA_MAXSIZE + " integer not null, "
            + LivestockContract.LiveStockEntry.AREA_ELECTRICFENCE + " boolean default false not null, "
            + LivestockContract.LiveStockEntry.AREA_SHELTER + " boolean not null);";

    //SQLite statement for creating LookupArea table.
    public static final String CREATE_LOOKUPAREA_TABLE = "CREATE TABLE "
            + LivestockContract.LiveStockEntry.LOOKUPAREA_TABLE_NAME
            + "(" + LivestockContract.LiveStockEntry.LOOKUPAREA_ANIMALID + " integer not null, "
            + LivestockContract.LiveStockEntry.LOOKUPAREA_AREAID + " integer not null, "
            + "FOREIGN KEY(animalid) REFERENCES Animal(id), "
            + "FOREIGN KEY(areaid) REFERENCES Area(id));";

    //SQLite statement for creating LookupType table.
    public static final String CREATE_LOOKUPTYPE_TABLE = "CREATE TABLE "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_TABLE_NAME
            + "(" + LivestockContract.LiveStockEntry.LOOKUPTYPE_TYPE + " varchar (255) not null, "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_GENDER + " varchar (1) not null, "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_NAME + " varchar (255) not null, "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_MINAGE + " Real, "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_MAXAGE + " Real, "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_FERTILE + " boolean, "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_NEUTERED + " boolean default false, "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_MINKIDS + " integer default 0, "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_MAXKIDS + " integer, "
            + "unique(type, gender, minAge, maxAge, neutered));";

    //SQLite statement for creating Parentage table.
    public static final String CREATE_PARENTAGE_TABLE = "CREATE TABLE "
            + LivestockContract.LiveStockEntry.PARENTAGE_TABLE_NAME
            + "(" + LivestockContract.LiveStockEntry.PARENTAGE_ANIMALID + " integer, "
            + LivestockContract.LiveStockEntry.PARENTAGE_MOTHERID + " integer, "
            + LivestockContract.LiveStockEntry.PARENTAGE_FATHERID + " integer, "
            + "FOREIGN KEY(animalid) REFERENCES Animal(id), "
            + "FOREIGN KEY(motherid) REFERENCES Animal(id), "
            + "FOREIGN KEY(fatherid) REFERENCES Animal(id));";

    //SQLite statement for creating MedSched table.
    public static final String CREATE_MEDSCHED_TABLE = "CREATE TABLE "
            + LivestockContract.LiveStockEntry.MEDSCHED_TABLE_NAME
            + "(" + LivestockContract.LiveStockEntry.MEDSCHED_ID + " integer primary key autoincrement, "
            + LivestockContract.LiveStockEntry.MEDSCHED_ANIMALID + " integer not null, "
            + LivestockContract.LiveStockEntry.MEDSCHED_STARTTIME + " Text, "
            + LivestockContract.LiveStockEntry.MEDSCHED_NAME + " varchar (255) not null, "
            + LivestockContract.LiveStockEntry.MEDSCHED_MGPERKG + " float, "
            + LivestockContract.LiveStockEntry.MEDSCHED_ONETIME + " boolean default true, "
            + LivestockContract.LiveStockEntry.MEDSCHED_GIVENID + " integer, "
            + LivestockContract.LiveStockEntry.MEDSCHED_RDAY + " integer, "
            + LivestockContract.LiveStockEntry.MEDSCHED_RMONTH + " integer, "
            + LivestockContract.LiveStockEntry.MEDSCHED_RYEAR + " integer, "
            + LivestockContract.LiveStockEntry.MEDSCHED_RHOUR + " integer, "
            + "FOREIGN KEY(animalid) REFERENCES Animal(id), "
            + "FOREIGN KEY(givenid) REFERENCES Given(id));";

    //SQLite statement for creating Given table.
    public static final String CREATE_GIVEN_TABLE = "CREATE TABLE "
            + LivestockContract.LiveStockEntry.GIVEN_TABLE_NAME
            + "(" + LivestockContract.LiveStockEntry.GIVEN_ID + " integer primary key autoincrement, "
            + LivestockContract.LiveStockEntry.GIVEN_MEDSCHEDID + " integer not null, "
            + LivestockContract.LiveStockEntry.GIVEN_DATEGIVEN + " Text not null, "
            + "FOREIGN KEY(medSchedid) REFERENCES MedSched(id));";

    //SQLite statement for dropping Animal table.
    public static final String DROP_ANIMAL_TABLE = "drop table if exists "
            + LivestockContract.LiveStockEntry.ANIMAL_TABLE_NAME;

    //SQLite statement for dropping Area table.
    public static final String DROP_AREA_TABLE = "drop table if exists "
            + LivestockContract.LiveStockEntry.AREA_TABLE_NAME;

    //SQLite statement for dropping LookupArea table.
    public static final String DROP_LOOKUPAREA_TABLE = "drop table if exists "
            + LivestockContract.LiveStockEntry.LOOKUPAREA_TABLE_NAME;

    //SQLite statement for dropping LookupType table.
    public static final String DROP_LOOKUPTYPE_TABLE = "drop table if exists "
            + LivestockContract.LiveStockEntry.LOOKUPTYPE_TABLE_NAME;

    //SQLite statement for dropping Parentage table.
    public static final String DROP_PARENTAGE_TABLE = "drop table if exists "
            + LivestockContract.LiveStockEntry.PARENTAGE_TABLE_NAME;

    //SQLite statement for dropping MedSched table.
    public static final String DROP_MEDSCHED_TABLE = "drop table if exists "
            + LivestockContract.LiveStockEntry.MEDSCHED_TABLE_NAME;

    //SQLite statement for dropping Given table.
    public static final String DROP_GIVEN_TABLE = "drop table if exists "
            + LivestockContract.LiveStockEntry.GIVEN_TABLE_NAME;





    public LivestockDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("Database Op", "Livestock database created...");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase)
    {
        //Create tables by executing the SQL strings.
        sqLiteDatabase.execSQL(CREATE_ANIMAL_TABLE);
        Log.d("Database Op", "Animal table created...");
        sqLiteDatabase.execSQL(CREATE_AREA_TABLE);
        Log.d("Database Op", "Area table created...");
        sqLiteDatabase.execSQL(CREATE_LOOKUPAREA_TABLE);
        Log.d("Database Op", "LookupArea table created...");
        sqLiteDatabase.execSQL(CREATE_LOOKUPTYPE_TABLE);
        Log.d("Database Op", "LookupType table created...");
        sqLiteDatabase.execSQL(CREATE_PARENTAGE_TABLE);
        Log.d("Database Op", "Parentage table created...");
        sqLiteDatabase.execSQL(CREATE_MEDSCHED_TABLE);
        Log.d("Database Op", "MedSched table created...");
        sqLiteDatabase.execSQL(CREATE_GIVEN_TABLE);
        Log.d("Database Op", "Given table created...");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        //Drop tables by executing SQL strings.
        sqLiteDatabase.execSQL(DROP_ANIMAL_TABLE);
        sqLiteDatabase.execSQL(DROP_AREA_TABLE);
        sqLiteDatabase.execSQL(DROP_LOOKUPAREA_TABLE);
        sqLiteDatabase.execSQL(DROP_LOOKUPTYPE_TABLE);
        sqLiteDatabase.execSQL(DROP_PARENTAGE_TABLE);
        sqLiteDatabase.execSQL(DROP_MEDSCHED_TABLE);
        sqLiteDatabase.execSQL(DROP_GIVEN_TABLE);
        onCreate(sqLiteDatabase);
    }
}
