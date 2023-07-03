package model

import model.minemark.Mine
import model.minemark.MineMark

class MineInstallation(
    private val count: Int,
    private val mark: MineMark = Mine(),
    private val nextPosition: (maxX: Int, maxY: Int) -> Position,
) {
    init {
        require(count > 0) { "install count must be positive. but provided `$count`" }
    }

    fun installed(mineBoard: MineBoard): InstalledMineBoard {
        validateMineBoardSize(mineBoard)
        var currentMineBoard: MineBoard = mineBoard
        repeat(count) {
            val position: Position = differentMarkPosition(currentMineBoard)
            currentMineBoard = currentMineBoard.replacedMark(position, mark)
        }
        return InstalledMineBoard(currentMineBoard)
    }

    private fun validateMineBoardSize(mineBoard: MineBoard) {
        require(count <= mineBoard.size) {
            "install count must be less than or equal to board size. mine board size(`${mineBoard.size}`), mineCount(`$count`)"
        }
    }

    private fun differentMarkPosition(mineBoard: MineBoard): Position {
        var position: Position
        do {
            position = nextPosition(mineBoard.maxXPosition, mineBoard.maxYPosition)
            validateContainsPosition(mineBoard, position)
        } while (mineBoard.isEqualMarkInPosition(position, mark))
        return position
    }

    private fun validateContainsPosition(mineBoard: MineBoard, position: Position) {
        check(mineBoard.contains(position)) {
            "position must be in mine board to replaced. but provided position(`$position`), mine board(`$mineBoard`)"
        }
    }
}
