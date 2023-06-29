package tdd.minesweeper.ui.response

import tdd.minesweeper.domain.MineBoard
import tdd.minesweeper.domain.type.GameProgressStatus
import tdd.minesweeper.domain.type.SymbolType

data class MineBoardResponse(
    val mineBoardId: Int,
    val gameProgressStatus: GameProgressStatus,
    val symbols: List<List<SymbolType>>
) {
    companion object {
        fun of(entity: MineBoard, status: GameProgressStatus): MineBoardResponse = MineBoardResponse(
            mineBoardId = entity.getId() ?: throw IllegalArgumentException("아직 생성되지 않은 엔티티입니다. Input: $entity"),
            gameProgressStatus = status,
            symbols = entity.convertRowsTo { it.getSymbol() }
        )
    }
}
