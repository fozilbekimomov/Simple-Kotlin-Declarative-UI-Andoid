package uz.ultragroup.mod38_kotlin.adapter

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.ultragroup.mod38_kotlin.R
import uz.ultragroup.mod38_kotlin.homeItemGroupID
import uz.ultragroup.mod38_kotlin.homeItemGroupListID
import uz.ultragroup.mod38_kotlin.homeItemGroupTitleID
import uz.ultragroup.mod38_kotlin.models.BaseType
import uz.ultragroup.mod38_kotlin.models.HomeFooterItem
import uz.ultragroup.mod38_kotlin.models.HomeHeaderItem

class HomeModsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val modes = ArrayList<BaseType>()
    fun setData(data: ArrayList<BaseType>) {
        this.modes.clear()
        this.modes.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            BaseType.TYPE_HEADER -> {
                HeaderViewHolder(getHomeGroupView(parent.context))
            }

            else -> {
                FooterViewHolder(getHomeGroupView(parent.context))
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

    inner class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: AppCompatTextView
        private val list: RecyclerView

        init {
            title = itemView.findViewById(homeItemGroupTitleID)
            list = itemView.findViewById(homeItemGroupListID)
            list.layoutManager =
                LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
        }

        fun bindData(headerItem: HomeHeaderItem) {
            title.text = headerItem.headerTitle
        }
    }

    inner class FooterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val title: AppCompatTextView
        private val list: RecyclerView
        private val adapter: ModeVerticalAdapter

        init {
            title = itemView.findViewById(homeItemGroupTitleID)
            list = itemView.findViewById(homeItemGroupListID)
            list.layoutManager = GridLayoutManager(itemView.context, 2)
            adapter = ModeVerticalAdapter()
            list.adapter = adapter
        }

        fun bindData(footerItem: HomeFooterItem) {
            title.text = footerItem.footerTitle
            adapter.setData(footerItem.footerData)
        }
    }

    fun getHomeGroupView(context: Context): ConstraintLayout {

        val param = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        val constraintLayout = ConstraintLayout(context)
        constraintLayout.id = homeItemGroupID

        val textView = AppCompatTextView(context)
        val textParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        textView.id = homeItemGroupTitleID
        textView.setTextColor(Color.parseColor("#116104"))
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            textView.setTextAppearance(context, R.style.LibreFranklinExtraBold800)
        } else {
            textView.setTextAppearance(R.style.LibreFranklinExtraBold800)
        }

        val marginStart = context.resources.getDimension(R.dimen._16dp)


        textView.textSize = 16f
        textParam.topToTop = ConstraintSet.PARENT_ID
        textParam.startToStart = ConstraintSet.PARENT_ID
        textParam.marginStart = marginStart.toInt()
        textParam.topMargin = marginStart.toInt()
        textView.layoutParams = textParam

        val recyclerView = RecyclerView(context)
        val listParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        recyclerView.id = homeItemGroupListID

        listParam.topToBottom = homeItemGroupTitleID
        listParam.startToStart = ConstraintSet.PARENT_ID
        listParam.endToEnd = ConstraintSet.PARENT_ID
        listParam.topMargin = context.resources.getDimension(R.dimen._8dp).toInt()

        recyclerView.layoutParams = listParam


        constraintLayout.layoutParams = param
        constraintLayout.addView(textView)
        constraintLayout.addView(recyclerView)
        return constraintLayout
    }


}