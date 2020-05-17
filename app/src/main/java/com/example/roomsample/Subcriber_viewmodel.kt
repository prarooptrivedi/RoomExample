package com.example.roomsample

import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomsample.db.Subscriber
import com.example.roomsample.db.SubscriberRepositry
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Subcriber_viewmodel(private val repositry: SubscriberRepositry):ViewModel(),Observable {
    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }
    private var isUpdateorDelete=false
    private lateinit var    subscriberToUpdateOrDelete : Subscriber
    val subscriber=repositry.subscriber
    @Bindable
    val inputName=MutableLiveData<String>()
    @Bindable
    val inputEmail=MutableLiveData<String>()
    @Bindable
    val saveOrUpdateButtonText=MutableLiveData<String>()

    @Bindable
        val clearAllOrUpdateButtonText=MutableLiveData<String>()

    private val  statusMessage=MutableLiveData<Event<String>>()
        val message:LiveData<Event<String>>
    get()=statusMessage

    init {
        saveOrUpdateButtonText.value="Save"
        clearAllOrUpdateButtonText.value="Clear All"
    }
    fun saveOrUpdate(){
        if (isUpdateorDelete){
            subscriberToUpdateOrDelete.name=inputName.value!!
            subscriberToUpdateOrDelete.email=inputEmail.value!!
        update(subscriberToUpdateOrDelete)
        }
        else{
            val name:String?=inputName.value!!
            val email:String?=inputEmail.value!!
            insert(Subscriber(0,name!!,email!!))
            inputEmail.value=null
            inputName.value=null
        }

    }
    fun clearAllDelete(){
        if(isUpdateorDelete){
            delete(subscriberToUpdateOrDelete)
        }
        else{
            clearAll()
        }

    }
    fun insert(subscriber: Subscriber):Job= viewModelScope.launch {
        val newRowId: Long = repositry.insert(subscriber)
        if (newRowId > -1) {
            statusMessage.value = Event("Subscriber Insert Sucessfully $newRowId")
        }
        else{
            statusMessage.value = Event("Error Ocured")
        }
    }

    fun update(subscriber: Subscriber):Job=viewModelScope.launch {
      val noOfRowId=  repositry.update(subscriber)
        if (noOfRowId > -1) {
            inputName.value = null
            inputEmail.value = null
            isUpdateorDelete = false
            subscriberToUpdateOrDelete = subscriber
            saveOrUpdateButtonText.value = "Save"
            clearAllOrUpdateButtonText.value = "Clear All"
            statusMessage.value = Event("Subscriber Updated Sucessfully $noOfRowId")
        }
        else{
            statusMessage.value = Event("Error Ocured")
        }
    }

    fun delete(subscriber: Subscriber):Job=viewModelScope.launch {
       val NoOfRowsDelete= repositry.delete(subscriber)
        if (NoOfRowsDelete > 0) {
            inputName.value = null
            inputEmail.value = null
            isUpdateorDelete = false
            subscriberToUpdateOrDelete = subscriber
            saveOrUpdateButtonText.value = "Save"
            clearAllOrUpdateButtonText.value = "Clear All"
            statusMessage.value = Event("Subscriber Deleted Sucessfully $NoOfRowsDelete")

        }
        else{
            statusMessage.value = Event("Error Ocured")
        }
    }
    fun clearAll():Job=viewModelScope.launch {
     val noOfRowsDeleted=   repositry.deleteAll()
        if (noOfRowsDeleted>0){
            statusMessage.value=Event("$noOfRowsDeleted  Subscriber Delete Sucessfully")
        }
        else{
            statusMessage.value = Event("Error Ocured")
        }

    }


    fun iniUpdateandDelete(subscriber: Subscriber){
        inputName.value=subscriber.name
        inputEmail.value=subscriber.email
        isUpdateorDelete=true
        subscriberToUpdateOrDelete=subscriber
        saveOrUpdateButtonText.value="Update"
        clearAllOrUpdateButtonText.value="Delete"
    }
}