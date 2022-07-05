package minesweeper.domain.cell

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainExactlyInAnyOrder
import io.kotest.matchers.collections.shouldHaveSize
import minesweeper.Coordinate

internal class CoordinatesTest : FreeSpec({

    "입력된 높이, 너비만큼의 넓이를 가진 좌표 컬렉션을 만들 수 있다." {
        // when
        val coordinates = Coordinates.from(height = 3, width = 5)

        // then
        coordinates.values.shouldHaveSize(15)
    }

    "주어진 좌표에는 지뢰 매설, 아닌 좌표에는 땅을 매설 할 수 있다." {
        // given
        val coordinates = Coordinates.from(2, 2)

        val mineCoordinates = Coordinates(
            listOf(
                Coordinate(x = 0, y = 1),
                Coordinate(x = 1, y = 1),
            )
        )

        // when
        val cells = coordinates.mineAt(mineCoordinates = mineCoordinates)

        // then
        cells.filter { it.dot == Mine }.map { it.coordinate }
            .shouldContainExactlyInAnyOrder(mineCoordinates.values)
    }
})
