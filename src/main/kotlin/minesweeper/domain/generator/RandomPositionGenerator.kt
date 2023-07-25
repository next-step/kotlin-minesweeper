package minesweeper.domain.generator

import minesweeper.domain.field.Position
import kotlin.random.Random

class RandomPositionGenerator : PositionGenerator {
    override fun get(maxWidth: Int, maxHeight: Int, count: Int): Set<Position> {
        return generateSequence { Position(Random.nextInt(0, maxWidth), Random.nextInt(0, maxHeight)) }
            .distinct()
            .take(count)
            .toSet()
    }
}
