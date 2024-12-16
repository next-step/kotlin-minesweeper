package domain

enum class Directions(
    val horizontalDirection: Int,
    val verticalDirection: Int,
    val openDirection: Boolean,
) {
    UP(0, -1, true),
    DOWN(0, 1, true),
    LEFT(-1, 0, true),
    RIGHT(1, 0, true),
    UP_LEFT(-1, -1, false),
    UP_RIGHT(1, -1, false),
    DOWN_LEFT(-1, 1, false),
    DOWN_RIGHT(1, 1, false),
}
