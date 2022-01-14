package com.example.newsfeed

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class newslistadapter(private val listner:Newsitemclicked) : RecyclerView.Adapter<newsviewholder>() {

    private  val items:ArrayList<News> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): newsviewholder {


        val view =LayoutInflater.from(parent.context).inflate(R.layout.recycleview,parent,false)
        val viewholder=newsviewholder(view)
        view.setOnClickListener{
      listner.onItemClickec(items[viewholder.adapterPosition])

        }
        return viewholder


    }

    override fun onBindViewHolder(holder: newsviewholder, position: Int) {
        val currentitem= items[position]
        holder.titleView.text =currentitem.title
        holder.authorview.text =currentitem.author
        Glide.with(holder.imageview.context).load(currentitem.imageurl).into(holder.imageview)


    }

    override fun getItemCount(): Int {
       return items.size
    }

    fun updatenews(updateditems:ArrayList<News>)
    {
        items.clear()
        items.addAll(updateditems)

        notifyDataSetChanged()
    }

}

class newsviewholder(itemView: View):RecyclerView.ViewHolder(itemView){
val titleView: TextView = itemView.findViewById(R.id.title)
    val imageview:ImageView=itemView.findViewById(R.id.image)
    val authorview:TextView=itemView.findViewById(R.id.author)

}
interface Newsitemclicked{
    fun onItemClickec(item:News)
}