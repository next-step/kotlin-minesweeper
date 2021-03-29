package domain

interface PositionIdFactory {
    fun positionIds(count: Int, positionIdMax: Int): List<PositionId>
}
