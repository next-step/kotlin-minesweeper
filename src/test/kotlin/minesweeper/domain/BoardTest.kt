package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class BoardTest {
    @Test
    fun `지도에 지뢰를 설치할 수 있다`() {
        val matrix = Matrix.of(2, 2)
        val board = Board(matrix, 4)

        board.matrix[0, 0].shouldBeInstanceOf<Mine>()
        board.matrix[0, 1].shouldBeInstanceOf<Mine>()
        board.matrix[1, 0].shouldBeInstanceOf<Mine>()
        board.matrix[1, 1].shouldBeInstanceOf<Mine>()
    }

    @Test
    fun `지도에 설치할 지뢰 개수는 지도 크기를 넘지 못한다`() {
        val matrix = Matrix.of(2, 2)

        assertThrows<IllegalArgumentException>("지뢰 개수는 지도에 존재하는 모든 필드의 수보다 클 수 없습니다.") {
            Board(matrix, 5)
        }
    }

    @Test
    fun `지뢰가 설치된 지도에 안전지대를 설정하고 주변에 존재하는 지뢰 개수를 나타낼 수 있다`() {
        val matrix = Matrix.of(2, 2)
        matrix[0, 0] = Mine()
        matrix[0, 1] = Mine()

        val board = Board(matrix, 0)

        board.matrix[1, 0].shouldBeInstanceOf<Safe>()
        board.matrix[1, 0].toString() shouldBe "2"

        board.matrix[1, 1].shouldBeInstanceOf<Safe>()
        board.matrix[1, 1].toString() shouldBe "2"
    }
}
