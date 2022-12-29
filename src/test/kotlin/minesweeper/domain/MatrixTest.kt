package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

internal class MatrixTest {
    @Test
    fun `크기가 주어지면 해당 크기의 Matrix를 생성할 수 있다`() {
        val matrix = Matrix.of(2, 2)

        matrix.rows.size shouldBe 2
        matrix.rows[0].size shouldBe 2
        matrix.rows[1].size shouldBe 2
    }
}
