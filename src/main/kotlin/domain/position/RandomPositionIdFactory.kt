package domain.position

import kotlin.random.Random

class RandomPositionIdFactory : PositionIdFactory {
    override fun positionIds(count: Int, positionIdMax: Int): List<PositionId> {
        val resultIds = mutableListOf<PositionId>()
        (0 until count).forEach { resultIds.addRandomPositionId(positionIdMax + 1) }
        return resultIds
    }
}

private fun MutableList<PositionId>.addRandomPositionId(until: Int) {

    require(this.size < until) { "더 이상 random position을 추가할 수 없습니다." }

    var randomPositionId: PositionId
    do {
        randomPositionId = PositionId(Random.nextInt(until))
    } while (this.contains(randomPositionId))

    this.add(randomPositionId)
}
