package uz.ultragroup.mod38_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import uz.ultragroup.mod38_kotlin.adapter.ModeVerticalAdapter1
import uz.ultragroup.mod38_kotlin.app.Mode381App
import uz.ultragroup.mod38_kotlin.databinding.FragmentFavBinding

class FavouriteFragment1 : Fragment() {
    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val adapter = ModeVerticalAdapter1()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.modeGroupList.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.modeGroupList.adapter = adapter
        Mode381App.mods?.let { adapter.setData(it) }
    }

}