package minesweeper.domain.strategy

import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import minesweeper.Coordinate
import minesweeper.domain.cell.Coordinates

internal class RandomMineDeployStrategyTest : FreeSpec({

    "지뢰 랜덤 배치 전략을 실행하면 주어진 좌표 내에서 주어진 개수만큼의 배치 좌표를 만들어낸다." {
        // given
        val coordinates = Coordinates(
            listOf(
                Coordinate(x = 0, y = 0),
                Coordinate(x = 0, y = 1),
                Coordinate(x = 1, y = 0),
                Coordinate(x = 1, y = 1)
            )
        )

        // when
        val result = RandomMineDeployStrategy.execute(coordinates, 2)

        // then
        result.values.shouldHaveSize(2)
        coordinates.values.shouldContainAll(result.values)
    }
})
