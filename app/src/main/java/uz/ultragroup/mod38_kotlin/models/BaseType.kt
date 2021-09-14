package uz.ultragroup.mod38_kotlin.models

interface BaseType {
    companion object {
        val TYPE_HEADER: Int
            get() = 0

        val TYPE_FOOTER: Int
            get() = 1
    }

    fun getType(): Int
}