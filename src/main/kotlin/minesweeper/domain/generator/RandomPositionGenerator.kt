package minesweeper.domain.generator

import minesweeper.domain.BoardSize
import minesweeper.domain.field.Position
import kotlin.random.Random

class RandomPositionGenerator : PositionGenerator {
    override fun get(boardSize: BoardSize, count: Int): Set<Position> {
        return generateSequence { Position(Random.nextInt(0, boardSize.width), Random.nextInt(0, boardSize.height)) }
            .distinct()
            .take(count)
            .toSet()
    }
}
