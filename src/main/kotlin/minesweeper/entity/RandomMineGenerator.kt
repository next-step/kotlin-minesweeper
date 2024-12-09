package minesweeper.entity

class RandomMineGenerator : MineGenerator {
    override fun generate(
        allCoordinates: List<Coordinate>,
        mineCount: MineCount,
    ): Set<Coordinate> {
        require(mineCount.value <= allCoordinates.size) {
            "지뢰 개수는 전체 셀 수를 초과할 수 없습니다."
        }
        return allCoordinates.shuffled().take(mineCount.value).toSet()
    }
}
