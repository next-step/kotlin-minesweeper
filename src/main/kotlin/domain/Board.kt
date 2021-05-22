package domain

import domain.position.Position
import domain.square.NormalSquare
import domain.square.Square
import domain.square.mine.Mine
import domain.square.mine.Mines
import model.GameData

class Board(
    private val mines: Mines,
    private val gameData: GameData
) {
    val squares: Squares

    init {
        val rows = (0 until gameData.height).map { row ->
            makeRowSquare(row)
        }
        squares = Squares(rows)
    }

    private fun makeRowSquare(row: Int): Row {
        val squaresOfRow = (0 until gameData.width).map { col -> Position(row, col) }.map {
            makeSquare(it)
        }
        return Row(squaresOfRow)
    }

    private fun makeSquare(it: Position): Square = if (mines.isMine(it)) Mine(it) else NormalSquare(it, mines)

    fun hasOpened(openPosition: Position): Boolean {
        return squares.get(openPosition).isOpen
    }

    fun isMine(openPosition: Position): Boolean {
        return mines.isMine(openPosition)
    }

    fun hasNoMineAround(openPosition: Position): Boolean {
        return squares.get(openPosition).mineCountAround == 0
    }

    fun hasAllOpened(): Boolean {
        return squares.hasAllOpened()
    }

    fun openSquare(openPosition: Position) {
        squares.get(openPosition).open()
    }
}
