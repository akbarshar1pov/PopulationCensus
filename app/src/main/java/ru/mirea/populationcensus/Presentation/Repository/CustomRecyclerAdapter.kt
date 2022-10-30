package ru.mirea.populationcensus.Presentation.Repository

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ru.mirea.populationcensus.Domain.Model.Person
import ru.mirea.populationcensus.R

class CustomRecyclerAdapter(private val formList: ArrayList<Person>) : RecyclerView
.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val city: TextView = itemView.findViewById(R.id.city)
        val fio: TextView = itemView.findViewById(R.id.fio)
        val sex: TextView = itemView.findViewById(R.id.sex)
        val age: TextView = itemView.findViewById(R.id.age)
        val family : TextView = itemView.findViewById(R.id.familyStat)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = formList[position]
        holder.city.text = currentItem.city
        holder.sex.text = currentItem.sex
        holder.age.text = currentItem.age
        holder.family.text = currentItem.family
        holder.fio.text = currentItem.fio
    }

    override fun getItemCount() = formList.size
}