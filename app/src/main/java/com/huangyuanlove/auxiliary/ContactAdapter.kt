package com.huangyuanlove.auxiliary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.huangyuanlove.auxiliary.bean.Contact
import java.io.File

class ContactAdapter(val data:List<Contact>):RecyclerView.Adapter<ContactAdapter.MyViewHolder>() {


    var onItemClick:OnItemClick? = null


    inner class MyViewHolder(view: View):RecyclerView.ViewHolder(view){
            val avatar = view.findViewById<ImageView>(R.id.avatar)
            val name = view.findViewById<TextView>(R.id.name)
        val root = view.findViewById<View>(R.id.root)
    }

     override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):MyViewHolder{
         val view = LayoutInflater.from(parent.context).inflate(R.layout.item_contact,parent,false)
         return MyViewHolder(view)
     }

    override fun onBindViewHolder(holder:MyViewHolder,position:Int){
        val contact = data[position]
        with(holder){
            name.text = contact.name
            avatar.load(File(contact.avatar)){
                crossfade(true)
            }
            root.setOnClickListener{
                onItemClick?.onItemClick(contact)
            }
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

}
interface OnItemClick{
    fun onItemClick(contact:Contact)
}