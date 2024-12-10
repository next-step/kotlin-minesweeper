package minesweeper.entity

class FixedMineGenerator(
    private val fixedCoordinates: Set<Coordinate>,
) : MineGenerator {
    override fun generate(
        allCoordinates: List<Coordinate>,
        mineCount: MineCount,
    ): Set<Coordinate> {
        require(mineCount.value == fixedCoordinates.size) {
            "지뢰 개수와 고정된 좌표의 개수가 일치해야 합니다."
        }
        return fixedCoordinates
    }
}
