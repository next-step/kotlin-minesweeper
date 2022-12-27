package minesweeper.domain.button

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import minesweeper.util.cartesianProduct

class ButtonGraph(
    val height: Int,
    val width: Int,
    val totalMineCount: Int,
    private val buttonGraph: Map<Position, Button>
) {
    val rowIndexRange: IntRange = (0 until height)

    val colIndexRange: IntRange = (0 until width)

    val mines: List<Mine>
        get() = buttonGraph.filterValues { it is Mine }.map { it.value as Mine }

    init {
        require(height > 0) {
            "height should be greater than 0 [$height]"
        }

        require(width > 0) {
            "width should be greater than 0 [$width]"
        }

        require(totalMineCount in (0..height * width)) {
            "totalMineCount [$totalMineCount] should be in range ${(0..height * width)}"
        }

        require(mines.size == totalMineCount) {
            "totalMineCount [$totalMineCount] should be equal to mine count [${mines.size}]"
        }

        require(mines.all { it.row in rowIndexRange }) {
            "All mine positions' height should be in ${(0 until height)}"
        }

        require(mines.all { it.col in colIndexRange }) {
            "All mine positions' height should be in ${(0 until width)}"
        }
    }

    operator fun get(row: Int): Buttons =
        Buttons(
            buttonGraph.filterKeys { it.row == row }
                .map { it.value }
        )

    operator fun get(row: Int, col: Int): Button = this[Position(row, col)]

    operator fun get(key: Position): Button =
        buttonGraph[key]
            ?: throw IllegalArgumentException(
                "ButtonGraph does not have a button having position [$key]"
            )

    fun rowButtons(): List<Buttons> = rowIndexRange.map { this[it] }

    fun getAllPositions(): Positions = Positions(buttonGraph.keys.toList())

    fun isNotEmpty(): Boolean = buttonGraph.isNotEmpty()

    companion object {
        fun of(height: Int, width: Int, totalMineCount: Int, vararg minePositions: Position): ButtonGraph =
            of(height, width, totalMineCount, Positions(minePositions.toList()))

        fun of(height: Int, width: Int, totalMineCount: Int, minePositions: Positions): ButtonGraph {
            return ButtonGraph(
                height = height,
                width = width,
                totalMineCount = totalMineCount,
                buttonGraph = listOf(
                    (0 until height),
                    (0 until width)
                ).cartesianProduct()
                    .map { Position(it[0], it[1]) }
                    .map { if (minePositions.havePosition(it)) Mine(it) else PushableButton(it) }
                    .associateBy { it.position }
            )
        }
    }
}
