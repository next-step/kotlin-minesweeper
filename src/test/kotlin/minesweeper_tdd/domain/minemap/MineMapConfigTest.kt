package minesweeper_tdd.domain.minemap

import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MineMapConfigTest {
    @ParameterizedTest
    @CsvSource(
        "3, 3, 3",
        "2, 5, 3",
    )
    internal fun `너비와 높이 그리고 지뢰 개수를 가진다`(
        widthValue: Int,
        heightValue: Int,
        mindCountValue: Int,
    ) {
        val sut = MineMapConfig(
            width = widthValue,
            height = heightValue,
            mineCount = mindCountValue,
        )

        sut.width shouldBe widthValue
        sut.height shouldBe heightValue
        sut.mineCount shouldBe mindCountValue
    }

    @ParameterizedTest
    @CsvSource(
        "1, 3, 3",
        "2, 1, 3",
        "2, 2, 0",
    )
    internal fun `너비와 높이는 1보다 커야하며 지뢰 개수는 0보다 커야 한다 그렇지 않으면 예외가 발생한다`(
        widthValue: Int,
        heightValue: Int,
        mindCountValue: Int,
    ) {
        assertThrows<IllegalArgumentException> {
            MineMapConfig(
                width = widthValue,
                height = heightValue,
                mineCount = mindCountValue,
            )
        }
    }

    @Test
    internal fun `지뢰 개수가 너비와 높이의 곱보다 크면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            MineMapConfig(
                width = 2,
                height = 2,
                mineCount = 5,
            )
        }
    }
}
