package domain

data class BoardSize(val width: Width, val height: Height) {
    val numberOfSpaces: Int = width.value * height.value
}
