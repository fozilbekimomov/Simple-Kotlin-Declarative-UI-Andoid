package uz.ultragroup.mod38_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.ultragroup.mod38_kotlin.adapter.ModeVerticalAdapter
import uz.ultragroup.mod38_kotlin.app.Mode381App

class FavouriteFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        createFavouriteRoot()
        return rootLayout
    }

    private val adapter = ModeVerticalAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        favList?.layoutManager = GridLayoutManager(requireContext(), 2)
        favList?.adapter = adapter
        Mode381App.mods?.let { adapter.setData(it) }
    }

    var rootLayout: ConstraintLayout? = null
    fun createFavouriteRoot(): ConstraintLayout? {

        rootLayout = ConstraintLayout(requireContext())

        val param = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        createFavList()
        rootLayout?.addView(favList)
        rootLayout?.layoutParams = param
        return rootLayout
    }

    var favList: RecyclerView? = null
    fun createFavList(): RecyclerView? {
        favList = RecyclerView(requireContext())
        val param = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT
        )

        favList?.layoutParams = param
        favList?.id = favouriteListID

        return favList
    }

}