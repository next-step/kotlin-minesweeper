package minesweeper.domain

class MockPositions(val positions: List<Position>) : List<Position> by positions, PositionsMaker {

    override fun createRandomMinePosition(mineCount: Int): Positions {
        require(mineCount < positions.size) { "지뢰의 갯수는 게임보드의 칸 수 보다 적어야 합니다." }
        return Positions(positions.take(mineCount))
    }
}
