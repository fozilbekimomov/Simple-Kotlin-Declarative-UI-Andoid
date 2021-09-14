package uz.ultragroup.mod38_kotlin.models

data class ModeItem(
    val order: Int,
    val title: String,
    val description: String,
    val fileName: String,
    val imageUrl: String,
    val starCount: Int
)