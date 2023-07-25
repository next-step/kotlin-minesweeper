package minesweeper.domain.generator

import minesweeper.domain.BoardMeta
import minesweeper.domain.field.Position
import kotlin.random.Random

class RandomPositionGenerator : PositionGenerator {
    override fun get(boardMeta: BoardMeta): Set<Position> {
        return generateSequence { Position(Random.nextInt(0, boardMeta.width), Random.nextInt(0, boardMeta.height)) }
            .distinct()
            .take(boardMeta.mineCount)
            .toSet()
    }
}
