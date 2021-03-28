package domain

class TestRandomPositionIdFactory(private val returnValues: List<Int>) : RandomPositionIdFactory {
    override fun positionIds(count: Int, positionIdMax: Int): List<PositionId> {
        return returnValues.map { PositionId(it) }
    }
}
