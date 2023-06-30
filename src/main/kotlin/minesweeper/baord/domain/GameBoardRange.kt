package minesweeper.baord.domain

class GameBoardRange(val height: Int, val width: Int) {

    fun calculateArea(): Int = height * width
}
