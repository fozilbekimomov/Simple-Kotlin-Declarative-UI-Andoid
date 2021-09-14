package uz.ultragroup.mod38_kotlin.app

import android.app.Application
import android.util.Log
import uz.ultragroup.mod38_kotlin.helpers.parseJson
import uz.ultragroup.mod38_kotlin.models.ModeItem
import java.io.File

class Mode381App : Application() {
    companion object {
        var mods: ArrayList<ModeItem>? = null
    }

    override fun onCreate() {
        super.onCreate()
        mods = parseJson()
        for (i in mods!!) {
            Log.d("Mode381App", "onCreate: $i")
        }
    }

    fun changeStateFav(modeItem: ModeItem): ArrayList<Int> {
        val file = File(this.cacheDir, "favs.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        val lastData = file.readText()
        file.printWriter().use {
            it.print("$lastData${modeItem.order}*")
        }
        val newData = file.readText()
        val favId = ArrayList<Int>()
        val d = newData.split("%")
        d.forEach {
            favId.add(it.toInt())
        }
        return favId
    }

    fun getFavIds(): ArrayList<Int> {
        val file = File(this.cacheDir, "favs.txt")
        if (!file.exists()) {
            file.createNewFile()
        }
        val newData = file.readText()
        val favId = ArrayList<Int>()
        val d = newData.split("%")
        d.forEach {
            favId.add(it.toInt())
        }
        return favId
    }

    fun checkFav(id: Int): Boolean {
        return getFavIds().contains(id)
    }


}