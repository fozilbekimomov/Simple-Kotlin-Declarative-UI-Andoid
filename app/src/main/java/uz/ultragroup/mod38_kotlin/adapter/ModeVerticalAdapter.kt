package uz.ultragroup.mod38_kotlin.adapter

import android.content.Context
import android.content.res.AssetManager
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ToggleButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.RecyclerView
import uz.ultragroup.mod38_kotlin.*
import uz.ultragroup.mod38_kotlin.helpers.RatingBarSvg
import uz.ultragroup.mod38_kotlin.helpers.TextViewOutline
import uz.ultragroup.mod38_kotlin.helpers.getStateListDrawable
import uz.ultragroup.mod38_kotlin.models.ModeItem
import java.io.IOException
import java.io.InputStream


class ModeVerticalAdapter : RecyclerView.Adapter<ModeVerticalAdapter.ViewHolder>() {

    private val modes = ArrayList<ModeItem>()
    fun setData(data: ArrayList<ModeItem>) {
        this.modes.clear()
        modes.addAll(data)
        notifyDataSetChanged()
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view.rootView) {

        private val imageView: AppCompatImageView
        private val modeTitle: TextViewOutline

        init {
            imageView = itemView.findViewById(itemVerticalImageID)
            modeTitle = itemView.findViewById(itemVerticalTitleID)
        }

        fun bindData(modeItem: ModeItem) {
            Log.d("ModeVerticalAdapter", "bindData: ${modeItem.title}")
            val assetManager: AssetManager = itemView.context.assets
            var inputStream: InputStream? = null
            try {
                inputStream = assetManager.open("images/${modeItem.imageUrl}")
            } catch (ex: IOException) {
                ex.printStackTrace()
            }

            val bitmap = BitmapFactory.decodeStream(inputStream)
            imageView.setImageBitmap(bitmap)
            modeTitle.text = modeItem.title
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(getItemViewMode(parent.context))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(modes[position])
    }

    override fun getItemCount(): Int {
        return modes.size
    }


    fun getItemViewMode(context: Context): ConstraintLayout {

        val param = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        val constraintLayout = ConstraintLayout(context)

        constraintLayout.setBackgroundColor(Color.parseColor("#147105"))
        constraintLayout.id = itemVerticalRootID
        param.setMargins(6, 6, 6, 6)


        val imageView = AppCompatImageView(context)
        imageView.id = itemVerticalImageID
        val paramImage = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        paramImage.height = 0
        paramImage.dimensionRatio = "181:176"
        paramImage.topToTop = ConstraintSet.PARENT_ID
        paramImage.bottomToTop = itemVerticalTitleContainerID
        imageView.scaleType = ImageView.ScaleType.CENTER_CROP
        imageView.layoutParams = paramImage


        val titleContainer = ConstraintLayout(context)
        val containerParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        titleContainer.id = itemVerticalTitleContainerID

        containerParam.height = 0
        containerParam.dimensionRatio = "181:64"
        containerParam.topToBottom = itemVerticalImageID
        containerParam.bottomToBottom = ConstraintSet.PARENT_ID

        titleContainer.layoutParams = containerParam

        val favToggle = ToggleButton(context)
        val toggleParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        favToggle.setBackgroundDrawable(
            context.getStateListDrawable(
                R.drawable.ic_fav_off,
                R.drawable.ic_fav_on
            )
        )
        toggleParam.width = context.resources.getDimension(R.dimen._24dp).toInt()
        toggleParam.height = context.resources.getDimension(R.dimen._24dp).toInt()
        favToggle.text = ""
        favToggle.textOff = ""
        favToggle.textOn = ""

        favToggle.setPadding(6, 6, 6, 6)
        toggleParam.endToEnd = ConstraintSet.PARENT_ID
        toggleParam.topToTop = ConstraintSet.PARENT_ID
        toggleParam.bottomToBottom = ConstraintSet.PARENT_ID
        toggleParam.marginEnd = context.resources.getDimension(R.dimen._8dp).toInt()
        favToggle.id = itemVerticalFavID

        favToggle.layoutParams = toggleParam


        val modeTitle = TextViewOutline(context)
        val titleParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
//        modeTitle.setPadding(20,0,0,0)
        modeTitle.setTextColor(Color.WHITE)
        modeTitle.setOutlineColor(Color.BLACK)
        modeTitle.setOutlineSize(8)
//        modeTitle.textSize=context.resources.getDimension(R.dimen.home_item_title_size)
        modeTitle.textSize = 14f
        modeTitle.id = itemVerticalTitleID

        titleParam.width = 0

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            modeTitle.setTextAppearance(context, R.style.LibreFranklinExtraBold700)
        } else {
            modeTitle.setTextAppearance(R.style.LibreFranklinExtraBold700)
        }

        titleParam.startToStart = ConstraintSet.PARENT_ID
        titleParam.endToStart = itemVerticalFavID
        titleParam.bottomToBottom = ConstraintSet.PARENT_ID
        titleParam.topToTop = ConstraintSet.PARENT_ID
        titleParam.marginStart = context.resources.getDimension(R.dimen._8dp).toInt()
        modeTitle.layoutParams = titleParam

//        val ratingBar = RatingBar(context, null, 0,R.style.customRatingBar)
        val ratingBar = RatingBarSvg(context, R.style.customRatingBarSVG)
        ratingBar.id = itemVeticalRatingID
        ratingBar.numStars = 3
        ratingBar.stepSize = 1f
        val ratingParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        ratingParam.topToTop = ConstraintSet.PARENT_ID
        ratingParam.endToEnd = ConstraintSet.PARENT_ID
        ratingParam.topMargin = context.resources.getDimension(R.dimen._8dp).toInt()
        ratingParam.marginEnd = context.resources.getDimension(R.dimen._8dp).toInt()
        ratingBar.layoutParams = ratingParam
        ratingBar.setIsIndicator(true)

        titleContainer.addView(modeTitle)
        titleContainer.addView(favToggle)

        constraintLayout.layoutParams = param
        constraintLayout.addView(imageView)
        constraintLayout.addView(ratingBar)
        constraintLayout.addView(titleContainer)
        return constraintLayout
    }


}