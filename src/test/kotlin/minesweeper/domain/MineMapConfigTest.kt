package minesweeper.domain

import io.kotest.matchers.shouldBe
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
}
