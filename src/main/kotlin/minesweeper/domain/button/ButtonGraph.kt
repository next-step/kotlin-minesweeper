package minesweeper.domain.button

import minesweeper.domain.position.Position
import minesweeper.domain.position.comma

class ButtonGraph private constructor(
    private val buttonGraph: Map<Position, Button>
) {
    val mines: Buttons = buttonGraph.values.filterIsInstance<Mine>().toButtons()

    private val rowIndexList: List<Int> = buttonGraph.keys.map { it.row }.toSet().sorted()

    constructor(buttons: Buttons) : this(buttons.associateBy { it.position })

    init {
        buttonGraph.values
            .filterIsInstance<PushableButton>()
            .forEach { pushableButton ->
                val position = pushableButton.position
                pushableButton.aroundMineCount = getMineCountAroundOf(position)
            }
    }

    operator fun get(row: Int, col: Int): Button = this[row comma col]

    operator fun get(key: Position): Button =
        buttonGraph[key]
            ?: throw IllegalArgumentException(
                "ButtonGraph does not have a button having position [$key]"
            )

    fun rowButtons(): List<Buttons> = rowIndexList.map { this[it] }

    fun isNotEmpty(): Boolean = buttonGraph.isNotEmpty()

    fun getMineCountAroundOf(position: Position): Int =
        getAroundButtons(position)
            .count { it is Mine }

    private fun getAroundButtons(position: Position): Buttons =
        position.getAroundPositions()
            .filter {
                containsPosition(it)
            }.map { this[it] }
            .toButtons()

    private operator fun get(row: Int): Buttons =
        buttonGraph.filterKeys { it.row == row }
            .map { it.value }
            .sortedBy { it.position }
            .toButtons()

    private fun containsPosition(position: Position): Boolean =
        buttonGraph.containsKey(position)
}
