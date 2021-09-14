package uz.ultragroup.mod38_kotlin

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import uz.ultragroup.mod38_kotlin.adapter.HomeModsAdapter1
import uz.ultragroup.mod38_kotlin.app.Mode381App
import uz.ultragroup.mod38_kotlin.databinding.FragmentHomeBinding
import uz.ultragroup.mod38_kotlin.models.BaseType
import uz.ultragroup.mod38_kotlin.models.HomeFooterItem

class HomeFragment1 : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val adapter = HomeModsAdapter1()

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
        binding.modeGroupList.adapter = adapter
        binding.modeGroupList.layoutManager = LinearLayoutManager(requireContext())
    }


}