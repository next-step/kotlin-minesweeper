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

    @Test
    fun `지뢰가 없는 인접한 칸들이 같이 열린다`() {
        val matrix = Matrix.of(3, 3)
        val coordinate = Coordinate(1, 1)
        matrix.coordinates
            .forEach { matrix.rows[it.rows][it.cols].landSafe(0) }

        matrix.open(coordinate)

        val allOpened = CoordinateDirection.around(coordinate)
            .all { matrix.rows[it.rows][it.cols].isOpened() }

        allOpened shouldBe true
    }

    @Test
    fun `주변에 지뢰가 있다면 인접한 칸들은 같이 열리지 않는다`() {
        val matrix = Matrix.of(2, 2)
        matrix.rows[0][0].landMine()
        matrix.rows[0][1].landSafe(1)
        matrix.rows[1][0].landSafe(1)
        matrix.rows[1][1].landSafe(1)

        matrix.open(Coordinate(1, 1))

        matrix.rows[1][0].isClosed() shouldBe true
        matrix.rows[0][1].isClosed() shouldBe true
    }

    @Test
    fun `주변에 있는 지뢰 개수를 알 수 있다`() {
        val matrix = Matrix.of(2, 2)
        matrix.rows[0][0].landMine()

        matrix.aroundMineCount(Coordinate(0, 1)) shouldBe 1
        matrix.aroundMineCount(Coordinate(1, 0)) shouldBe 1
        matrix.aroundMineCount(Coordinate(1, 1)) shouldBe 1
    }
}
