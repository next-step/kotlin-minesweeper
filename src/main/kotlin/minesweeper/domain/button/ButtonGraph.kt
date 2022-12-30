package minesweeper.domain.button

import minesweeper.domain.position.Position
import minesweeper.domain.position.comma

class ButtonGraph private constructor(
    private val buttonGraph: Map<Position, Button>
) {
    private val rowIndexSet: Set<Int> = buttonGraph.keys.map { it.row }.toSet()

    val mines: Buttons = Buttons(buttonGraph.values.filterIsInstance<Mine>())

    constructor(buttons: Buttons) : this(buttons.associateBy { it.position })

    operator fun get(row: Int, col: Int): Button = this[row comma col]

    operator fun get(key: Position): Button =
        buttonGraph[key]
            ?: throw IllegalArgumentException(
                "ButtonGraph does not have a button having position [$key]"
            )

    fun rowButtons(): List<Buttons> = rowIndexSet.map { this[it] }

    fun isNotEmpty(): Boolean = buttonGraph.isNotEmpty()

    private operator fun get(row: Int): Buttons =
        Buttons(
            buttonGraph.filterKeys { it.row == row }
                .map { it.value }
        )
}
