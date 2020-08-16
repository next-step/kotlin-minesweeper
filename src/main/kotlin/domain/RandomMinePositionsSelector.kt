package domain

class RandomMinePositionsSelector : MinePositionsSelectStrategy {

    override fun getMinePositionsFrom(positions: List<Position>, count: Int): List<Position> {
        require(positions.size > count) { "전체 위치의 개수(${positions.size}) 보다 지뢰의 개수($count)가 같거나 많을 수 없습니다." }
        return positions.shuffled().subList(0, count)
    }
}
