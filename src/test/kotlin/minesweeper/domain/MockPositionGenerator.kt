package minesweeper.domain

class MockPositionGenerator : PositionGenerator {

    override fun generate(end: Int): Int {
        return end
    }
}
