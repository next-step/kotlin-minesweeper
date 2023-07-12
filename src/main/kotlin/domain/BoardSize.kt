package domain

data class BoardSize(
    val width: Int,
    val height: Int
) {
    val area: Int
        get() = width * height
}
