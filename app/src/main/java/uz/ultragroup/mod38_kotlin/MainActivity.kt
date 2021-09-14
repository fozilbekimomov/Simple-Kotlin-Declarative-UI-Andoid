package uz.ultragroup.mod38_kotlin

import android.graphics.Color
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ContextThemeWrapper
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.viewpager2.widget.ViewPager2
import uz.ultragroup.mod38_kotlin.adapter.ViewPagerAdapter

class MainActivity : AppCompatActivity() {

    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(createRootView())
        createSearchBar()
        rootLayout?.addView(searchBarLayout)

        createActionBar()
        rootLayout?.addView(actionBarLayout)

        createTabLayout()
        rootLayout?.addView(tabLayout)


        createViewPager2()
        rootLayout?.addView(viewPager)

        setListeners()

        setViewState()

    }


    private fun setViewState() {
        searchBarLayout?.visibility = View.INVISIBLE
        viewPagerAdapter = ViewPagerAdapter(this)
        viewPager?.adapter = viewPagerAdapter
    }

    private fun setListeners() {
        searchIcon?.setOnClickListener {
            toggleActionBar()
            searchEditText?.requestFocus()
            searchEditText?.requestLayout()
        }
        searchCloseIcon?.setOnClickListener {
            toggleActionBar()
        }

        allGroup?.setOnClickListener {
            viewPager?.currentItem = 0
        }

        favGroup?.setOnClickListener {
            viewPager?.currentItem = 1
        }

        viewPager?.registerOnPageChangeCallback(pageListener)

    }

    val pageListener = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            when (position) {
                0 -> allGroup?.performClick()
                else -> favGroup?.performClick()
            }
        }
    }

    override fun onDestroy() {
        viewPager?.unregisterOnPageChangeCallback(pageListener)
        super.onDestroy()
    }

    fun changeActionBar(isShowActionBar: Boolean) {

        if (isShowActionBar) {
            actionBarLayout?.visibility = View.INVISIBLE
            searchBarLayout?.visibility = View.VISIBLE
        } else {
            actionBarLayout?.visibility = View.VISIBLE
            searchBarLayout?.visibility = View.INVISIBLE
        }

    }

    /**
     * Views and layouts start
     * */


    var viewPager: ViewPager2? = null
    private fun createViewPager2(): ViewPager2? {

        viewPager = ViewPager2(this)

        val param = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        param.height = 0
        param.topToBottom = mainTabLayoutID
        param.bottomToBottom = ConstraintSet.PARENT_ID
        param.startToStart = ConstraintSet.PARENT_ID
        param.endToEnd = ConstraintSet.PARENT_ID


        viewPager?.id = mainViewPagerID
        viewPager?.layoutParams = param

        return viewPager
    }


    var rootLayout: ConstraintLayout? = null
    fun createRootView(): ConstraintLayout? {
        val rootParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        rootLayout = ConstraintLayout(this)
        rootLayout?.layoutParams = rootParam
        rootLayout?.id = mainRootID
        rootLayout?.setBackgroundColor(Color.parseColor("#299E12"))
        return rootLayout
    }

    var actionBarLayout: ConstraintLayout? = null
    fun createActionBar(): ConstraintLayout? {

        val paramActionBar = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        val tv = TypedValue()
        var actionBarHeight = 0
        if (this.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }

        paramActionBar.height = actionBarHeight

        actionBarLayout = ConstraintLayout(this)
        actionBarLayout?.layoutParams = paramActionBar
        actionBarLayout?.id = actionBarID

        paramActionBar.topToTop = ConstraintSet.PARENT_ID
        actionBarLayout?.setBackgroundColor(Color.parseColor("#147105"))

        createTitle()
        actionBarLayout?.addView(titleScreen)

        createSearchIcon()
        actionBarLayout?.addView(searchIcon)

        return actionBarLayout
    }

    var searchBarLayout: ConstraintLayout? = null
    fun createSearchBar(): ConstraintLayout? {

        val paramSearchBar = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        val tv = TypedValue()
        var actionBarHeight = 0
        if (this.theme.resolveAttribute(android.R.attr.actionBarSize, tv, true)) {
            actionBarHeight =
                TypedValue.complexToDimensionPixelSize(tv.data, resources.displayMetrics)
        }

        paramSearchBar.height = actionBarHeight

        searchBarLayout = ConstraintLayout(this)
        searchBarLayout?.layoutParams = paramSearchBar
        searchBarLayout?.id = searchBarID

        paramSearchBar.topToTop = ConstraintSet.PARENT_ID
        searchBarLayout?.setBackgroundColor(Color.parseColor("#0E4205"))

        createSearchEditText()
        searchBarLayout?.addView(searchEditText)

        createSearchCloseIcon()
        searchBarLayout?.addView(searchCloseIcon)

        return searchBarLayout
    }

    var titleScreen: AppCompatTextView? = null
    fun createTitle(): AppCompatTextView? {

        val textSize = resources.getDimension(R.dimen.home_title_size)

        val titleParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        titleScreen = AppCompatTextView(ContextThemeWrapper(this, R.style.LibreFranklinBlack900))

        titleScreen?.text = "Mods"
        titleScreen?.textSize = 20f
        titleScreen?.setTextColor(Color.WHITE)

        titleParam.startToStart = ConstraintSet.PARENT_ID
        titleParam.topToTop = ConstraintSet.PARENT_ID
        titleParam.bottomToBottom = ConstraintSet.PARENT_ID

        val marginStart = resources.getDimension(R.dimen._16dp)


        titleParam.marginStart = marginStart.toInt()
        titleScreen?.id = mainTitleID

        titleScreen?.layoutParams = titleParam
        return titleScreen
    }

    var searchIcon: AppCompatImageButton? = null
    fun createSearchIcon(): AppCompatImageButton? {
        searchIcon = AppCompatImageButton(this)
        val paramSearch = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        val outValue = TypedValue()
        theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true)
        searchIcon?.setBackgroundResource(outValue.resourceId)
        paramSearch.width = 0

        paramSearch.dimensionRatio = "1:1"

        searchIcon?.setImageResource(R.drawable.ic_search_icon)

        paramSearch.endToEnd = ConstraintSet.PARENT_ID
        paramSearch.topToTop = ConstraintSet.PARENT_ID
        paramSearch.bottomToBottom = ConstraintSet.PARENT_ID

        searchIcon?.id = mainSearchIconID
        searchIcon?.layoutParams = paramSearch
        return searchIcon
    }

    var searchCloseIcon: AppCompatImageButton? = null
    fun createSearchCloseIcon(): AppCompatImageButton? {
        searchCloseIcon = AppCompatImageButton(this)
        val paramSearchClose = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        val outValue = TypedValue()
        theme.resolveAttribute(android.R.attr.selectableItemBackgroundBorderless, outValue, true)
        searchCloseIcon?.setBackgroundResource(outValue.resourceId)
        paramSearchClose.width = 0

        paramSearchClose.dimensionRatio = "1:1"

        searchCloseIcon?.setImageResource(R.drawable.ic_clear)

        paramSearchClose.endToEnd = ConstraintSet.PARENT_ID
        paramSearchClose.topToTop = ConstraintSet.PARENT_ID
        paramSearchClose.bottomToBottom = ConstraintSet.PARENT_ID

        searchCloseIcon?.id = mainSearchCloseID
        searchCloseIcon?.layoutParams = paramSearchClose
        return searchCloseIcon
    }

    var searchEditText: AppCompatEditText? = null
    fun createSearchEditText(): AppCompatEditText? {
        searchEditText =
            AppCompatEditText(ContextThemeWrapper(this, R.style.LibreFranklinMedium500))

        val editParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )
        editParam.width = 0
        editParam.startToStart = ConstraintSet.PARENT_ID
        editParam.endToEnd = ConstraintSet.PARENT_ID

        val marginStart = resources.getDimension(R.dimen._16dp)
        editParam.marginStart = marginStart.toInt()

        searchEditText?.textSize = 16f
        searchEditText?.setTextColor(Color.WHITE)
        searchEditText?.id = mainSearchEditTextID

        searchEditText?.setBackgroundDrawable(null)

        searchEditText?.layoutParams = editParam
        return searchEditText
    }


    var tabLayout: RadioGroup? = null
    var allGroup: RadioButton? = null
    var favGroup: RadioButton? = null
    fun createTabLayout(): RadioGroup? {
        tabLayout = RadioGroup(this)
        val tabParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )

        tabLayout?.setBackgroundColor(Color.parseColor("#147105"))

        tabParam.width = 0
        tabParam.height = 0
        tabParam.dimensionRatio = "375:76"
        tabParam.topToBottom = actionBarID
        tabParam.endToEnd = ConstraintSet.PARENT_ID
        tabParam.startToStart = ConstraintSet.PARENT_ID

        tabLayout?.id = mainTabLayoutID

        tabLayout?.weightSum = 2f
        tabLayout?.orientation = RadioGroup.HORIZONTAL
        allGroup = getTabRadioButton(R.drawable.all_icon)
        allGroup?.id = mainAllID
        tabLayout?.addView(allGroup)
        favGroup = getTabRadioButton(R.drawable.fav_icon)
        favGroup?.id = mainFavouriteID
        tabLayout?.addView(favGroup)

        tabLayout?.check(mainAllID)
        tabLayout?.layoutParams = tabParam
        return tabLayout
    }

    fun getTabRadioButton(resId: Int): RadioButton {
        val radioButton = RadioButton(this)
        val tabParam = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        radioButton.setBackgroundResource(resId)
        radioButton.buttonDrawable = null

        radioButton.layoutParams = tabParam
        return radioButton
    }

    /**
     * Views and layouts end
     * */

    var isShowActionBar = true
    fun toggleActionBar() {
        changeActionBar(isShowActionBar)
        isShowActionBar = !isShowActionBar
    }


}