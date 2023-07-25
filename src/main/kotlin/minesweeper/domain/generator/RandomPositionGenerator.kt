package minesweeper.domain.generator

import minesweeper.domain.field.Position
import kotlin.random.Random

class RandomPositionGenerator : PositionGenerator {
    override fun get(maxWidth: Int, maxHeight: Int): Position {
        return Position(Random.nextInt(0, maxWidth), Random.nextInt(0, maxHeight))
    }
}
