package uz.datatalim.myfavouritecat.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import uz.datatalim.myfavouritecat.R
import uz.datatalim.myfavouritecat.model.BreedsItem
import uz.datatalim.myfavouritecat.model.CatItem

class CatAdapter: RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    private val list = ArrayList<CatItem>()

    fun submitList(newlist: ArrayList<CatItem>) {
        list.addAll(newlist)
        notifyDataSetChanged()
    }


    class CatViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val ivCat = view.findViewById<ImageView>(R.id.ivCats)
        val tvName = view.findViewById<TextView>(R.id.tvCatName)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatViewHolder {
        return CatViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent, false)
        )
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: CatViewHolder, position: Int) {
        val cat = list[position]
        holder.apply {

            Glide.with(ivCat).load(cat.url).into(ivCat)
            //tvName.text = cat.breeds[0].name

        }
    }
}