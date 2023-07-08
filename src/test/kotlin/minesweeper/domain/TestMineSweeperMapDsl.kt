package minesweeper.domain

import domain.AroundMineCount
import domain.Cell
import domain.CellProperty
import domain.Position
import domain.toPositiveNumber

class TestMineSweeperMap(val value: Array<Array<Cell>>)

class TestMineSweeperMapBuilder(
    private val height: Int,
    private val width: Int,
    private val minePositionGenerator: TestMinePositionGenerator,
    private var rows: Array<Array<Cell>> = emptyArray(),
) {
    fun row(vararg testCells: TestCell) {
        val row = this.rows.size + 1
        val minePositions = minePositionGenerator.generate()
        val cells = testCells.mapIndexed { index, value ->
            val column = index + 1
            val isMine = value is Mine
            val validPositions = Position.fromInt(row, column)
                .getValidAdjacentPositions(height.toPositiveNumber(), width.toPositiveNumber())
            val aroundMineCount = validPositions.getAroundMineCount(minePositions)
            makeCell(row, column, isMine, aroundMineCount).apply {
                if (value.isOpen) this.property.setOpen()
            }
        }

        rows = rows.plusElement(cells.toTypedArray())
    }

    private fun makeCell(row: Int, column: Int, isMine: Boolean, aroundMineCount: AroundMineCount) =
        Cell(Position.fromInt(row, column), CellProperty.of(isMine) { aroundMineCount })

    fun build(): Array<Array<Cell>> {
        return TestMineSweeperMap(rows).value
    }
}

fun mineSweeperMapOf(
    height: Int,
    width: Int,
    minePositionGenerator: TestMinePositionGenerator,
    block: TestMineSweeperMapBuilder.() -> Unit,
): TestMineSweeperMapBuilder {
    return TestMineSweeperMapBuilder(height, width, minePositionGenerator).apply(block)
}
