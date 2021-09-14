package uz.ultragroup.mod38_kotlin.helpers

import android.content.Context
import org.json.JSONObject
import uz.ultragroup.mod38_kotlin.models.ModeItem
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


fun Context.parseJson(): ArrayList<ModeItem> {
    val list = ArrayList<ModeItem>()

    val jsonString = loadJSONFromAsset()
    val jsonObject = JSONObject(jsonString)
    val jsonObject1 = jsonObject.getJSONObject("0-r6_list")

    for (index in 0 until 20) {
        val itemJson: JSONObject = jsonObject1.getJSONObject("$index")
        list.add(
            ModeItem(
                order = index,
                title = itemJson.getString("0-r6d4"),
                description = itemJson.getString("0-r6i1"),
                fileName = itemJson.getString("0-r6t3"),
                imageUrl = itemJson.getString("0-r6f2"),
                0
            )
        )
    }
    return list
}

fun Context.loadJSONFromAsset(): String {
    var json: String? = null
    try {
        val inputStream: InputStream = this.assets.open("json/content.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        json = String(buffer, Charset.defaultCharset())
    } catch (ex: IOException) {
        ex.printStackTrace()
        return "null"
    }
    return json
}