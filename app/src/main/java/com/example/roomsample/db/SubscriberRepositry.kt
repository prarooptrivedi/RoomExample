package com.example.roomsample.db

class SubscriberRepositry(private val dao:SubscriberDao) {
    val subscriber=dao.getAllSubscribers()

    suspend fun insert(subscriber: Subscriber):Long{
        return  dao.insertsbscriber(subscriber)
    }
    suspend fun update(subscriber: Subscriber):Int{
       return dao.updatesbscriber(subscriber)
    }
    suspend fun delete(subscriber: Subscriber):Int{
       return dao.deletesbscriber(subscriber)
    }
    suspend fun deleteAll():Int{
        return dao.deleteAll()
    }
}