package domain

import kotlin.random.Random

class DefaultRandomPositionIdFactory : RandomPositionIdFactory {
    override fun positionIds(count: Int, positionIdMax: Int): List<PositionId> {
        return (0 until count).map { PositionId(Random.nextInt(positionIdMax + 1)) }.toList()
    }
}
