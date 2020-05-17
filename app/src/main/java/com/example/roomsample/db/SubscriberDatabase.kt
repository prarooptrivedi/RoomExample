package com.example.roomsample.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.time.Instant

@Database(entities = [Subscriber::class],version = 1)
abstract class SubscriberDatabase:RoomDatabase() {


    abstract val subscriberDao:SubscriberDao
    companion object
    {
        @Volatile
        private var INSTANCE:SubscriberDatabase?=null

        fun getInstace(context: Context):SubscriberDatabase{
            synchronized(this){
                var instance:SubscriberDatabase?= INSTANCE

                if (instance==null){
                    instance=Room.databaseBuilder(
                        context.applicationContext,
                        SubscriberDatabase::class.java,
                        "subscriber_data_table"
                    ).build()


                }
                return instance
            }

        }
    }


}