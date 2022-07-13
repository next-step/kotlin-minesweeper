package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.Coordinates
import minesweeper.dto.MineCount

internal class CoordinatesTest : FreeSpec({

    "입력된 지뢰판 높이, 너비만큼의 넓이를 가진 좌표 리스트를 만들 수 있다." {
        // when
        val coordinates = Coordinates.from(3, 5)

        // then
        coordinates.values.shouldHaveSize(15)
    }

    "랜덤한 위치에 주어진 지뢰 개수만큼의 지뢰를 매설 할 수 있다." {
        // given
        val coordinates = Coordinates.from(3, 3)

        // when
        val cells = coordinates.buryMinesRandomly(MineCount(2))

        // then
        cells.count { it.value == Mine() } shouldBe 2
    }

    "지뢰 개수가 좌표수보다 많은 경우 예외가 발생한다." {
        val coordinates = Coordinates.from(3, 3)
        val exception =
            shouldThrowExactly<IllegalArgumentException> {
                coordinates.buryMinesRandomly(
                    MineCount(
                        10
                    )
                )
            }
        exception.message shouldBe "좌표 개수보다 지뢰 개수가 많을 수 없습니다."
    }
})
