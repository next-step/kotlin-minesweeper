package minesweeper.domain

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import org.junit.jupiter.api.Test

internal class MatrixTest {
    @Test
    fun `크기가 주어지면 해당 크기의 Matrix를 생성할 수 있다`() {
        val matrix = Matrix.of(2, 2)

        matrix.rows.size shouldBe 2
        matrix.rows[0].fields.size shouldBe 2
        matrix.rows[1].fields.size shouldBe 2
    }

    @Test
    fun `Matrix에 x,y 값을 통해 값을 구할 수 있다`() {
        val matrix = Matrix.of(2, 2)

        matrix[0, 0] shouldBe matrix.rows[0].fields[0]
    }

    @Test
    fun `Matrix에 x,y 값을 통해 값을 설정할 수 있다`() {
        val matrix = Matrix.of(2, 2)
        val mine = Mine()
        matrix[0, 0] = mine

        matrix[0, 0] shouldBe mine
        matrix.rows[0].fields[0] shouldBe mine
        matrix[0, 1] shouldNotBe mine
        matrix.rows[0].fields[1] shouldNotBe mine
    }

    @Test
    fun `Matrix에 좌표를 통해 값을 구할 수 있다`() {
        val matrix = Matrix.of(2, 2)
        val coordinate = Coordinate(0, 0)

        matrix[coordinate] shouldBe matrix.rows[0].fields[0]
        matrix[coordinate] shouldNotBe matrix.rows[0].fields[1]
    }

    @Test
    fun `Matrix에 좌표를 통해 값을 설정할 수 있다`() {
        val matrix = Matrix.of(2, 2)
        val coordinate = Coordinate(0, 0)
        val mine = Mine()
        matrix[coordinate] = mine

        matrix[coordinate] shouldBe mine
        matrix.rows[0].fields[0] shouldBe mine
        matrix[Coordinate(0, 1)] shouldNotBe mine
        matrix.rows[0].fields[1] shouldNotBe mine
    }
}
