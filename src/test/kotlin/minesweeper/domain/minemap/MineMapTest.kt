package minesweeper.domain.minemap

import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.strategy.FixedMinePositionStrategy
import org.junit.jupiter.api.Test

internal class MineMapTest {
    @Test
    internal fun `지도 설정값과 지뢰 매설 전략을 받아서 지도를 구성한다`() {
        // given : 너비 2, 높이 2, 지뢰 개수 1(위치 1,1) 설정값
        val mineMapConfig = MineMapConfig(
            width = 2,
            height = 2,
            mineCount = 1
        )
        val minePositions = Positions(listOf(Position(1, 1)))

        // when : 설정값으로 지도를 구성하면
        val sut = MineMap(
            mineMapConfig = mineMapConfig,
            minePositioningStrategy = FixedMinePositionStrategy(mineMapConfig, minePositions)
        )

        // then : 지도의 아이템 개수는 4개, 지뢰가 심어진 곳은 (1, 1) 그외는 모두 빈칸이다.
        val currentMap = sut.currentMap()
        currentMap shouldHaveSize 4
        currentMap[Position(1, 1)].shouldBeInstanceOf<Mine>()
        currentMap[Position(0, 0)].shouldBeInstanceOf<Empty>()
        currentMap[Position(0, 1)].shouldBeInstanceOf<Empty>()
        currentMap[Position(1, 0)].shouldBeInstanceOf<Empty>()
    }
}
