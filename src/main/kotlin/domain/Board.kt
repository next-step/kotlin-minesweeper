package domain

import domain.position.Position
import domain.square.NormalSquare
import domain.square.Square
import domain.square.mine.Mine
import domain.square.mine.MineFactory
import domain.square.mine.Mines
import model.GameData

class Board(
    private val gameData: GameData,
    mineFactory: MineFactory = MineFactory()
) {
    val squares: List<List<Square>>
    private val mines: Mines = mineFactory.createMines(gameData)

    init {
        squares = (0 until gameData.height).map { row ->
            makeRowSquare(row)
        }.toList()
    }

    private fun makeRowSquare(row: Int): List<Square> =
        (0 until gameData.width).map { col -> Position(row, col) }.map {
            makeSquare(it)
        }.toList()

    private fun makeSquare(it: Position): Square = if (mines.isMine(it)) Mine(it) else NormalSquare(it, mines)
}
