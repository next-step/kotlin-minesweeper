package domain

import domain.block.BlockFactory
import domain.block.OpenAbleBlock
import domain.coord.AbstractCoordinate
import domain.coord.RelativeCoordinate.Companion.relativeOfCoords

class Field(
    val rows: Rows,
) {
    fun openBlock(y: Int, x: Int): GameStatus {
        require(rows.isRangeRowOf(y)) { "y =$y 값은 범위를 초과할 수 없어요" }
        require(rows.isRangeCellOf(x)) { "x =$x 값은 범위를 초과할 수 없어요" }

        val coordinate = AbstractCoordinate(y, x)

        val block = rows.blockOf(coordinate)

        if (block.isMine()) {
            return GameStatus.LOSE
        }

        if (block is OpenAbleBlock) {
            block.openBlock()
            val openAbleBlocks = coordinate.getOpenAbleNearBlocks()
            openAbleBlocks.forEach { it.openBlock() }
        }

        if (rows.isAllOpened()) {
            return GameStatus.WIN
        }

        return GameStatus.PROGRESSING
    }

    private fun AbstractCoordinate.getOpenAbleNearBlocks(): List<OpenAbleBlock> {
        return relativeOfCoords
            .filter { this.isPossiblePlus(it) }
            .map { rows.blockOf(this + it) }
            .filterIsInstance<OpenAbleBlock>()
    }

    companion object {
        fun create(height: Int, width: Int, mines: LocationOfMines): Field {
            val rows = Rows(
                (0 until height).map { y -> y.rows(width, mines) }
            )

            return Field(rows)
        }

        private fun Int.rows(maxWidth: Int, mines: LocationOfMines) = Row(
            (0 until maxWidth).map { x ->
                BlockFactory.create(AbstractCoordinate(this, x), mines)
            }
        )
    }
}
