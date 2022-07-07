package minesweeper.domain.cell

import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import minesweeper.Coordinates
import minesweeper.domain.MineCount

internal class CoordinatesTest : FreeSpec({

    "입력된 높이, 너비만큼의 넓이를 가진 좌표 컬렉션을 만들 수 있다." {
        // when
        val coordinates = Coordinates.from(3, 5)

        // then
        coordinates.values.shouldHaveSize(15)
    }

    "주어진 지뢰 개수만큼 랜덤한 위치에 지뢰를 매설 할 수 있다." {
        // given
        val coordinates = Coordinates.from(3, 3)

        // when
        val cells = coordinates.randomMine(MineCount(2))

        // then
        cells.count { it.value == Mine } shouldBe 2
    }

    "지뢰 개수가 좌표 개수보다 많은 경우 예외가 발생한다." {
        val coordinates = Coordinates.from(3, 3)
        shouldThrowExactly<IllegalArgumentException> { coordinates.randomMine(MineCount(10)) }
    }
})
