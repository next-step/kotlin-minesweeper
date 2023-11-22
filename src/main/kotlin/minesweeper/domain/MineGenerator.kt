package minesweeper.domain

import minesweeper.tdddomain.MineSweeperIndex2
import minesweeper.tdddomain.MineSweeperMap2

object MineGenerator {

    fun generate(mineSweeperIndexes: MineSweeperIndexes, mineCount: Int): Mines {
        require(mineSweeperIndexes.mineSweeperIndexes.size >= mineCount) { ERROR_MESSAGE }
        val mines = mineSweeperIndexes.mineSweeperIndexes
            .map { Mine(it.position) }
            .shuffled()
            .take(mineCount)
        return Mines(mines)
    }

    fun generate(height: Height, width: Width, mineCount: Int): MineSweeperMap2 {
        val positions = (Height.MINIMUM_HEIGHT..height.value).map {
            createRow(it, width)
        }.flatten()

        require(positions.size >= mineCount) { ERROR_MESSAGE }
        val minePositions = positions.shuffled().take(mineCount)
        return MineSweeperMap2(createMineSweeperIndexes(positions, minePositions))
    }

    private fun createMineSweeperIndexes(positions: List<Position>, minePositions: List<Position>): List<MineSweeperIndex2> {
        return positions.map { position ->
            val isMine = minePositions.contains(position)
            createMineSweeperIndex(position, isMine)
        }
    }

    private fun createMineSweeperIndex(position: Position, isMine: Boolean): MineSweeperIndex2 {
        if (isMine) {
            return MineSweeperIndex2(position, MineStatus.MINE)
        }
        return MineSweeperIndex2(position, MineStatus.NOT_MINE)
    }

    private fun createRow(y: Int, width: Width): List<Position> {
        return (1..width.value).map { Position(it, y) }
    }

    private const val ERROR_MESSAGE = "지뢰의 개수는 지뢰판의 크기보다 작아야 합니다."
}
