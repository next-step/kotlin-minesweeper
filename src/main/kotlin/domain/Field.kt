package domain

import domain.block.Block
import domain.block.BlockFactory
import domain.block.OpenAbleBlock
import domain.coord.AbstractCoordinate
import domain.coord.Coordinate
import domain.coord.RelativeCoordinate.Companion.relativeOfCoords

class Field(
    val rows: List<Row>,
) {
    fun openBlock(y: Int, x: Int): GameStatus {
        require(y < rows.size) { "y =$y 값은 범위를 초과할 수 없어요" }
        require(x < rows[0].cells.size) { "x =$x 값은 범위를 초과할 수 없어요" }

        val coordinate = AbstractCoordinate(y, x)

        val block = block(coordinate)

        if (block.isMine()) {
            return GameStatus.LOSE
        }

        if (block is OpenAbleBlock) {
            block.openBlock()
            val openAbleBlocks = coordinate.getOpenAbleNearBlocks()
            openAbleBlocks.forEach { it.openBlock() }
        }

        if (rows.allOpened()) {
            return GameStatus.WIN
        }

        return GameStatus.PROGRESSING
    }

    private fun block(coordinate: Coordinate): Block {
        return rows[coordinate.y.value].cells[coordinate.x.value]
    }

    private fun AbstractCoordinate.getOpenAbleNearBlocks(): List<OpenAbleBlock> {
        return relativeOfCoords
            .filter { this.isPossiblePlus(it) }
            .map { block(this + it) }
            .filterIsInstance<OpenAbleBlock>()
    }

    companion object {
        fun create(height: Int, width: Int, mines: LocationOfMines): Field {
            val rows = (0 until height).map { y -> y.rows(width, mines) }

            return Field(rows)
        }

        private fun Int.rows(maxWidth: Int, mines: LocationOfMines) = Row(
            (0 until maxWidth).map { x ->
                BlockFactory.create(AbstractCoordinate(this, x), mines)
            }
        )
    }
}

private fun List<Row>.allOpened(): Boolean {
    return this.all { it.isAllOpened() }
}
