package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class MineGeneratorTest {

    @Test
    fun `입력받은 지뢰 개수 만큼 무작위로 지뢰가 위치할 좌표를 만든다`() {
        val mineCount = 5
        val gameBoard = GameBoard(5, 5)
        val mineGenerator = MineGenerator(gameBoard, mineCount)

        val minePoints = mineGenerator.generateRandomPoints()

        mineCount shouldBe minePoints.size
    }
}
