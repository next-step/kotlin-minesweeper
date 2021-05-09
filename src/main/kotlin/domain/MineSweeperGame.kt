package domain

import domain.position.Position
import domain.square.mine.MineFactory
import model.GameData

class MineSweeperGame(
    private val gameData: GameData,
    mineFactory: MineFactory = MineFactory()
) {
    val board = Board(mineFactory.createMines(gameData), gameData)
    var hasLose: Boolean = false
    var hasWon: Boolean = false
    val hasDone: Boolean
        get() = hasWon || hasLose

    fun open(openPosition: Position) {

        check(!board.hasOpened(openPosition)) { "이미 열려있는 위치입니다. $openPosition" }

        if (board.isMine(openPosition)) {
            hasLose = true
            return
        }

        board.openSquare(openPosition)

        if (board.hasNoMineAround(openPosition)) {
            openPosition.aroundPositions()
                .asSequence()
                .filter { it.isInBoard(gameData.height, gameData.width) }
                .filter { !board.hasOpened(it) }
                .forEach { open(it) }
        }

        if (board.hasAllOpened()) {
            hasWon = true
        }
    }
}
