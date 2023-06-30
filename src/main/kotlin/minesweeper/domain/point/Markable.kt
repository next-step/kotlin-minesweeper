package minesweeper.domain.point

interface Markable {
    fun isMarked(): Boolean
    fun marking(): Boolean
}
