package uz.ultragroup.mod38_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.ultragroup.mod38_kotlin.databinding.HomeModsBinding
import uz.ultragroup.mod38_kotlin.models.BaseType
import uz.ultragroup.mod38_kotlin.models.HomeFooterItem
import uz.ultragroup.mod38_kotlin.models.HomeHeaderItem

class HomeModsAdapter1 : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val modes = ArrayList<BaseType>()
    fun setData(data: ArrayList<BaseType>) {
        this.modes.clear()
        this.modes.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BaseType.TYPE_HEADER -> {
                HeaderViewHolder(
                    HomeModsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }

            else -> {
                FooterViewHolder(
                    HomeModsBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            BaseType.TYPE_HEADER -> {
                val h = holder as HeaderViewHolder
                h.bindData(modes[position] as HomeHeaderItem)
            }
            else -> {
                val h = holder as FooterViewHolder
                h.bindData(modes[position] as HomeFooterItem)
            }
        }

    }

    override fun getItemViewType(position: Int): Int {
        return modes[position].getType()
    }

    override fun getItemCount(): Int {
        return modes.size
    }

    inner class HeaderViewHolder(private val binding: HomeModsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        init {

            binding.modeList.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bindData(headerItem: HomeHeaderItem) {
            binding.modeGroupTitle.text = headerItem.headerTitle
        }
    }

    inner class FooterViewHolder(private val binding: HomeModsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val adapter: ModeVerticalAdapter1

        init {

            binding.modeList.layoutManager = GridLayoutManager(itemView.context, 2)
            adapter = ModeVerticalAdapter1()
            binding.modeList.adapter = adapter
        }

        fun bindData(footerItem: HomeFooterItem) {
            binding.modeGroupTitle.text = footerItem.footerTitle
            adapter.setData(footerItem.footerData)
        }
    }


}