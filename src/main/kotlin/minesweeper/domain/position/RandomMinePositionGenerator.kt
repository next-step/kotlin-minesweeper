package minesweeper.domain.position

import minesweeper.domain.board.BoardSettings

object RandomMinePositionGenerator : MinePositionGenerator {

    override fun generate(settings: BoardSettings): List<Position> {
        val area = settings.width * settings.height

        return (0 until area)
            .shuffled()
            .take(settings.mineCounts)
            .map { Position.from(it, settings) }
    }
}
