package uz.ultragroup.mod38_kotlin.adapter

import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.ultragroup.mod38_kotlin.databinding.ItemModeBinding
import uz.ultragroup.mod38_kotlin.models.ModeItem
import java.io.IOException
import java.io.InputStream


class ModeVerticalAdapter1 : RecyclerView.Adapter<ModeVerticalAdapter1.ViewHolder>() {

    private val modes = ArrayList<ModeItem>()
    fun setData(data: ArrayList<ModeItem>) {
        this.modes.clear()
        modes.addAll(data)
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemModeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(modeItem: ModeItem) {

            val assetManager: AssetManager = itemView.context.assets
            var inputStream: InputStream? = null
            try {
                inputStream = assetManager.open("images/${modeItem.imageUrl}")
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            val bitmap = BitmapFactory.decodeStream(inputStream)
            binding.modeImage.setImageBitmap(bitmap)
            binding.modeTitle.text = modeItem.title
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemModeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(modes[position])
    }

    override fun getItemCount(): Int {
        return modes.size
    }


}