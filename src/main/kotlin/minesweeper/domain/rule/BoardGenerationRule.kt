package minesweeper.domain.rule

import minesweeper.domain.BoardMetadata
import minesweeper.domain.Cell

interface BoardGenerationRule {
    fun generate(metadata: BoardMetadata): List<List<Cell>>
}