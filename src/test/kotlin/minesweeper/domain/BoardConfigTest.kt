package minesweeper.domain

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class BoardConfigTest {

    @Test
    fun `보드 설정이 정상적으로 된다`() {
        val boardConfig = BoardConfig(
            height = 3,
            width = 3,
            mineCount = 2,
        )

        boardConfig.height shouldBe 3
        boardConfig.width shouldBe 3
        boardConfig.mineCount shouldBe 2
    }

    @CsvSource(
        "0, 0, 0",
        "0, 0, -1",
        "0, -1, 0",
        "-1, 0, 0",
        "-1, -1, -1",
    )
    @ParameterizedTest
    fun `지뢰찾기 보드에 0 이하의 값이 들어오면 예외가 발생한다`(rows: Int, columns: Int, mineCount: Int) {
        assertThrows<IllegalArgumentException> {
            BoardConfig(
                height = rows,
                width = columns,
                mineCount = mineCount,
            )
        }
    }

    @Test
    fun `지뢰찾기 보드에 지뢰 개수가 전체 칸의 개수보다 크면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            BoardConfig(
                height = 3,
                width = 3,
                mineCount = 10,
            )
        }
    }

    @Test
    fun `지뢰찾기 보드의 땅 개수를 반환한다`() {
        val boardConfig = BoardConfig(
            height = 3,
            width = 3,
            mineCount = 2,
        )

        boardConfig.getLandCount() shouldBe 7
    }
}
