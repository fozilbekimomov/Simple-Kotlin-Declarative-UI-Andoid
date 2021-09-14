package uz.ultragroup.mod38_kotlin.models

class HomeFooterItem(
    val footerTitle: String,
    val footerData: ArrayList<ModeItem>
) : BaseType {
    override fun getType(): Int {
        return BaseType.TYPE_FOOTER
    }
}