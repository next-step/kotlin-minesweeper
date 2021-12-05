package minesweeper.domain.position

import minesweeper.domain.board.BoardSettings

interface MinePositionGenerator {

    fun generate(settings: BoardSettings): List<Position>
}
