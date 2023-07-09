package domain

import domain.position.Positions

interface MinePositionGenerator {
    fun generate(): Positions
}
