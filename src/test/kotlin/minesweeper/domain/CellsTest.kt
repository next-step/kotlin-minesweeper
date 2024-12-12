package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

class CellsTest {
    @Test
    fun `Column 생성 테스트`() {
        Cells(Width(10), Height(10)).size shouldBe 10
    }

    @ParameterizedTest
    @CsvSource(
        "11, 0",
        "0, 11",
    )
    fun `잘못된 위치의 Cell 상태를 변화 Test`(
        x: Int,
        y: Int,
    ) {
        shouldThrow<IllegalArgumentException> {
            Cells(Width(10), Height(10)).setCellStatus(Location(x, y), CellState.ONE)
        }
    }
}
