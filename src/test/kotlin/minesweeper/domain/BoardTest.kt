package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test

internal class BoardTest {
    @Test
    fun `지도에 지뢰를 설치할 수 있다`() {
        val rows = Matrix.of(2, 2)
        val board = Board(rows)
        board.createRandomMine(4)

        board.matrix[1, 1].shouldBeInstanceOf<Mine>()
        board.matrix[1, 2].shouldBeInstanceOf<Mine>()
        board.matrix[2, 1].shouldBeInstanceOf<Mine>()
        board.matrix[2, 2].shouldBeInstanceOf<Mine>()
    }

    @Test
    fun `지뢰가 설치된 지도에 안전지대를 설정하고 주변에 존재하는 지뢰 개수를 나타낼 수 있다`() {
        val rows = Matrix.of(2, 2)
        val board = Board(rows)

        board.matrix[1, 1] = Mine()
        board.matrix[1, 2] = Mine()
        board.createSafe()

        board.matrix[2, 1].shouldBeInstanceOf<Safe>()
        board.matrix[2, 1].toString() shouldBe "2"

        board.matrix[2, 2].shouldBeInstanceOf<Safe>()
        board.matrix[2, 2].toString() shouldBe "2"
    }
}
