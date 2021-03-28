package domain

interface RandomPositionIdFactory {
    fun positionIds(count: Int, positionIdMax: Int): List<PositionId>
}
