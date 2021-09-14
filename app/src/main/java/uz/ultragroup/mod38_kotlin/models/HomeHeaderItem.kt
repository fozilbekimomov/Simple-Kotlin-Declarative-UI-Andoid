package uz.ultragroup.mod38_kotlin.models

class HomeHeaderItem(
    val headerTitle: String,
    val headerData: ArrayList<ModeItem>
) : BaseType {
    override fun getType(): Int {
        return BaseType.TYPE_HEADER
    }
}