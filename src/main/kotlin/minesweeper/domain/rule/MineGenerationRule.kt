package minesweeper.domain.rule

import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell
import minesweeper.domain.Coordinate

interface MineGenerationRule {
    fun generate(metadata: BoardMetadata): Map<Coordinate, Cell>
}
