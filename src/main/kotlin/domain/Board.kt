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
    val squares: List<Row>

    init {
        squares = (0 until gameData.height).map { row ->
            makeRowSquare(row)
        }.toList()
    }

    private fun makeRowSquare(row: Int): Row {
        val squaresOfRow = (0 until gameData.width).map { col -> Position(row, col) }.map {
            makeSquare(it)
        }.toList()
        return Row(squaresOfRow)
    }


    private fun makeSquare(it: Position): Square = if (mines.isMine(it)) Mine(it) else NormalSquare(it, mines)
}
