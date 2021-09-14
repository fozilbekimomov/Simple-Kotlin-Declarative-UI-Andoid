package uz.ultragroup.mod38_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.ultragroup.mod38_kotlin.adapter.HomeModsAdapter
import uz.ultragroup.mod38_kotlin.app.Mode381App
import uz.ultragroup.mod38_kotlin.models.BaseType
import uz.ultragroup.mod38_kotlin.models.HomeFooterItem

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createFavouriteRoot()
        return rootLayout
    }

    private val adapter = HomeModsAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setViewState()
        setData()
    }

    private fun setData() {
        val data = ArrayList<BaseType>()
        Mode381App.mods?.let { HomeFooterItem("ALL MODS", it) }?.let { data.add(it) }
        adapter.setData(data)
    }

    private fun setViewState() {
        homeList?.adapter = adapter
        homeList?.layoutManager = LinearLayoutManager(requireContext())
    }

    var rootLayout: ConstraintLayout? = null
    fun createFavouriteRoot(): ConstraintLayout? {

        rootLayout = ConstraintLayout(requireContext())

        val param = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        createFavList()
        rootLayout?.addView(homeList)
        rootLayout?.layoutParams = param
        return rootLayout
    }

    var homeList: RecyclerView? = null
    fun createFavList(): RecyclerView? {
        homeList = RecyclerView(requireContext())
        val param = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        homeList?.layoutParams = param
        homeList?.id = favouriteListID

        return homeList
    }
}