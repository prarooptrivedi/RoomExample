package com.example.roomsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingComponent
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.roomsample.databinding.ActivityMainBinding
import com.example.roomsample.db.Subscriber
import com.example.roomsample.db.SubscriberDatabase
import com.example.roomsample.db.SubscriberRepositry

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private lateinit var subcriberViewmodel: Subcriber_viewmodel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding=DataBindingUtil.setContentView(this,R.layout.activity_main)
        val dao=SubscriberDatabase.getInstace(application).subscriberDao
        val repositry=SubscriberRepositry(dao)
        val factory=SubscriberViewModelFactory(repositry)
        subcriberViewmodel=ViewModelProvider(this,factory).get(Subcriber_viewmodel::class.java)
        binding.myViewModel=subcriberViewmodel
        binding.lifecycleOwner=this
        initRecuclerView()
        subcriberViewmodel.message.observe(this, Observer {
            it.getContentIfNotHandled()?.let {
                Toast.makeText(this,it,Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initRecuclerView(){
        binding.rvSuscriber.layoutManager=LinearLayoutManager(this)
        displaySubscriberList()
    }

    private fun displaySubscriberList(){
        subcriberViewmodel.subscriber.observe(this, Observer {
            Log.i("MYTAg",it.toString())
            binding.rvSuscriber.adapter=MyRecyclerViewAdapter(it,{selectedItem:Subscriber->listItemclick(selectedItem)})
        })

    }
    private fun listItemclick(subscriber: Subscriber){
        //Toast.makeText(this,"selcted name is ${subscriber.name}",Toast.LENGTH_SHORT).show()
        subcriberViewmodel.iniUpdateandDelete(subscriber)
    }
}
