package minesweeper.entity

enum class MapElement(val represent: String) {
    GROUND("C"),
    MINE("*"),
    ZERO("0"),
    ONE("1"),
    TWO("2"),
    THREE("3"),
    FOUR("4");

    companion object {
        fun find(countOfMines: Int): MapElement {
            return MapElement.values().find { it -> (it.represent == countOfMines.toString()) } ?: ZERO
        }
    }
}
