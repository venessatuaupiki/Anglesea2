package com.example.anglesea.DataAccess;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.anglesea.DataAccess.Administration.Administration;
import com.example.anglesea.DataAccess.Administration.AdministrationDao;
import com.example.anglesea.DataAccess.Drug.Drug;
import com.example.anglesea.DataAccess.Drug.DrugDao;
import com.example.anglesea.DataAccess.Nurse.Nurse;
import com.example.anglesea.DataAccess.Nurse.NurseDao;
import com.example.anglesea.DataAccess.Patient.Patient;
import com.example.anglesea.DataAccess.Patient.PatientDao;
import com.example.anglesea.DataAccess.Room.RoomDao;

@Database(entities =
{
        Patient.class,
        Drug.class,
        com.example.anglesea.DataAccess.Room.Room.class,
        Nurse.class,
        Administration.class
}, version = 9)
public abstract class DB extends RoomDatabase
{
    private static DB instance;

    public abstract PatientDao patient();
    public abstract DrugDao drug();
    public abstract RoomDao room();
    public abstract NurseDao nurse();
    public abstract AdministrationDao administration();

    public static DB get(Context context)
    {
        if(instance == null)
            instance = Room.databaseBuilder(context.getApplicationContext(), DB.class, "PersonalOrganiserDatabase")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();

        return instance;
    }

    public static void destroyInstance()
    {
        instance = null;
    }
}