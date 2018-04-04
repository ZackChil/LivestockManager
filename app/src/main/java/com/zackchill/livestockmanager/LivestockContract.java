package com.zackchill.livestockmanager;

public final class LivestockContract
{
    private LivestockContract(){}

    public static class LiveStockEntry
    {
        /*
        SQLite Strings for the Animal table.
         */
        public static final String ANIMAL_TABLE_NAME = "Animal";
        public static final String ANIMAL_ID = "id";
        public static final String ANIMAL_TYPE = "type";
        public static final String ANIMAL_NAME = "name";
        public static final String ANIMAL_GENDER = "gender";
        public static final String ANIMAL_WEIGHTKG = "weightKg";
        public static final String ANIMAL_FERTILE = "fertile";
        public static final String ANIMAL_NEUTERED = "neutered";
        public static final String ANIMAL_BIRTHDAY = "birthDay";
        public static final String ANIMAL_DEATHDAY = "deathDay";

        /*
        SQLite Strings for the Area table.
         */
        public static final String AREA_TABLE_NAME = "Area";
        public static final String AREA_ID = "id";
        public static final String AREA_NAME = "name";
        public static final String AREA_ENCLSED = "enclsed";
        public static final String AREA_GRAZABLE = "grazable";
        public static final String AREA_MAXSIZE = "maxSize";
        public static final String AREA_ELECTRICFENCE = "electricFence";
        public static final String AREA_SHELTER =  "shelter";

        /*
        SQLite Strings for the LookupArea table.
         */
        public static final String LOOKUPAREA_TABLE_NAME = "LookupArea";
        public static final String LOOKUPAREA_ANIMALID = "animalid";
        public static final String LOOKUPAREA_AREAID = "areaid";

        /*
        SQLite Strings for the LookupType table.
         */
        public static final String LOOKUPTYPE_TABLE_NAME = "LookupType";
        public static final String LOOKUPTYPE_TYPE = "type";
        public static final String LOOKUPTYPE_GENDER = "gender";
        public static final String LOOKUPTYPE_NAME = "name";
        public static final String LOOKUPTYPE_MINAGE = "minAge";
        public static final String LOOKUPTYPE_MAXAGE = "maxAge";
        public static final String LOOKUPTYPE_FERTILE = "fertile";
        public static final String LOOKUPTYPE_NEUTERED = "neutered";
        public static final String LOOKUPTYPE_MINKIDS = "minKids";
        public static final String LOOKUPTYPE_MAXKIDS = "maxKids";

        /*
        SQLite Strings for the Parentage table.
         */
        public static final String PARENTAGE_TABLE_NAME = "Parentage";
        public static final String PARENTAGE_ANIMALID = "animalid";
        public static final String PARENTAGE_MOTHERID = "motherid";
        public static final String PARENTAGE_FATHERID = "fatherid";

        /*
        SQLite Strings for the MedSched table.
         */
        public static final String MEDSCHED_TABLE_NAME = "MedSched";
        public static final String MEDSCHED_ID = "id";
        public static final String MEDSCHED_ANIMALID = "animalid";
        public static final String MEDSCHED_STARTTIME = "startTime";
        public static final String MEDSCHED_NAME = "name";
        public static final String MEDSCHED_MGPERKG = "mgPERKg";
        public static final String MEDSCHED_ONETIME = "oneTime";
        public static final String MEDSCHED_GIVENID = "givenId";
        public static final String MEDSCHED_RDAY = "rDay";
        public static final String MEDSCHED_RMONTH = "rMonth";
        public static final String MEDSCHED_RYEAR = "rYear";
        public static final String MEDSCHED_RHOUR = "rHour";

        /*
        SQLite Strings for the Given table.
         */
        public static final String GIVEN_TABLE_NAME = "Given";
        public static final String GIVEN_ID = "id";
        public static final String GIVEN_MEDSCHEDID = "medSchedid";
        public static final String GIVEN_DATEGIVEN = "dateGiven";

    }
}
