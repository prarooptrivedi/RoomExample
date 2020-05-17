package com.example.roomsample

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomsample.databinding.ListItemBinding
import com.example.roomsample.db.Subscriber
import com.example.roomsample.generated.callback.OnClickListener

class MyRecyclerViewAdapter(private val subscriberlist: List<Subscriber>,private val clickListener:(Subscriber)->Unit):RecyclerView.Adapter<MyViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val layoutInflater=LayoutInflater.from(parent.context)
        val binding:ListItemBinding=
            DataBindingUtil.inflate(layoutInflater,R.layout.list_item,parent,false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return subscriberlist.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(subscriberlist[position],clickListener)

    }
}

class MyViewHolder(val databing:ListItemBinding):RecyclerView.ViewHolder(databing.root){
    fun bind(subscriber: Subscriber,clickListener:(Subscriber)->Unit){
        databing.textName.text=subscriber.name
        databing.emailTextName.text=subscriber.email
        databing.listitemlayout.setOnClickListener{
            clickListener(subscriber)
        }
    }
}