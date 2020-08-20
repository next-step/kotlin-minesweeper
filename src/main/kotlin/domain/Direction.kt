package domain

enum class Direction(val distanceX: Int, val distanceY: Int) {
    TOP(0, 1),
    DOWN(0, -1),
    LEFT(-1, 0),
    LEFT_TOP(-1, 1),
    LEFT_DOWN(-1, -1),
    RIGHT(1, 0),
    RIGHT_TOP(1, 1),
    RIGHT_DOWN(1, -1);
}
