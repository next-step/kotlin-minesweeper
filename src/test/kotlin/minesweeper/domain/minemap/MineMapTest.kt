package minesweeper.domain.minemap

import io.kotest.matchers.maps.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import minesweeper.domain.Position
import minesweeper.domain.Positions
import minesweeper.domain.strategy.FixedMinePositionStrategy
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

    @Test
    internal fun `생성된 지도의 빈칸은 8분면 주변의 지뢰 개수를 가지고 있다`() {
        // given : 너비 2, 높이 2, 지뢰 개수 2(0, 1)(2, 2) 설정값
        // C C C
        // * C C
        // C C *
        val mineMapConfig = MineMapConfig(
            width = 3,
            height = 3,
            mineCount = 2
        )
        val minePositions = Positions(listOf(Position(0, 1), Position(2, 2)))

        // when : 설정값으로 지도를 구성하면
        val sut = MineMap(
            mineMapConfig = mineMapConfig,
            minePositioningStrategy = FixedMinePositionStrategy(mineMapConfig, minePositions)
        )

        // then : 아래와 같이 빈칸에 지뢰 개수가 기록된다
        // 1 1 0
        // * 2 1
        // 1 2 *
        val map = sut.currentMap()
        (map[Position(0, 0)] as Empty).surroundingMineCount shouldBe 1
        (map[Position(1, 0)] as Empty).surroundingMineCount shouldBe 1
        (map[Position(2, 0)] as Empty).surroundingMineCount shouldBe 0
        (map[Position(1, 1)] as Empty).surroundingMineCount shouldBe 2
        (map[Position(2, 1)] as Empty).surroundingMineCount shouldBe 1
        (map[Position(0, 2)] as Empty).surroundingMineCount shouldBe 1
        (map[Position(1, 2)] as Empty).surroundingMineCount shouldBe 2
    }

    @Test
    internal fun `특정 위치를 열었을때 지도 내부가 아니면 예외가 발생한다`() {
        // given : 3 x 3 지도 구성
        val sut = MineMap(MineMapConfig(width = 3, height = 3, mineCount = 3))

        // when : 3 x 3 내부가 아닌 위치를 오픈
        assertThrows<IllegalArgumentException> { sut.open(Position(4, 0)) }
    }

    @Test
    internal fun `특정 위치를 열었을때 지뢰를 열었는지 알 수 있다`() {
        // given : 지뢰 위치가 (0, 0)로 고정된 3x3 지도 준비
        val mineMapConfig = MineMapConfig(width = 3, height = 3, mineCount = 1)
        val fixedMinePositionStrategy = FixedMinePositionStrategy(
            mineMapConfig = mineMapConfig,
            minePositions = Positions(listOf(Position(0, 0)))
        )
        val sut = MineMap(mineMapConfig, fixedMinePositionStrategy)

        // when : 지뢰가 아닌 위치를 오픈
        sut.open(Position(0, 1))

        // then : 지뢰 오픈 여부 false
        sut.mineOpened shouldBe false

        // and : 지뢰가 위치를 오픈
        sut.open(Position(0, 0))

        // then : 지뢰 오픈 여부 false
        sut.mineOpened shouldBe true
    }

    @Test
    internal fun `특정 위치를 열었을때 빈칸이면 지뢰가 없는 인접한 칸이 모두 열린다`() {
        // given : 지뢰 위치가 (2, 1)로 고정된 3x3 지도 준비
        // C C C
        // C C *
        // C C C
        val mineMapConfig = MineMapConfig(width = 3, height = 3, mineCount = 1)
        val fixedMinePositionStrategy = FixedMinePositionStrategy(
            mineMapConfig = mineMapConfig,
            minePositions = Positions(listOf(Position(2, 1)))
        )
        val sut = MineMap(mineMapConfig, fixedMinePositionStrategy)

        // when : 지뢰가 아닌 위치를 오픈
        sut.open(Position(0, 0))

        // then : 지뢰가 없는 인접한 칸이 모두 열린다
        // 0 1 C
        // 0 1 *
        // 0 1 C
        val map = sut.currentMap()
        map[Position(0, 0)]?.isOpened shouldBe true
        map[Position(0, 1)]?.isOpened shouldBe true
        map[Position(0, 2)]?.isOpened shouldBe true
        map[Position(1, 0)]?.isOpened shouldBe true
        map[Position(1, 1)]?.isOpened shouldBe true
        map[Position(1, 2)]?.isOpened shouldBe true
        map[Position(2, 0)]?.isOpened shouldBe false
        map[Position(2, 1)]?.isOpened shouldBe false
        map[Position(2, 2)]?.isOpened shouldBe false
    }

    @Test
    internal fun `모든 빈칸이 열렸는지 확인할 수 있다`() {
        // given : 지뢰가 매설된 3x3 지도 준비
        // C C *
        // C * *
        // * * *
        val mineMapConfig = MineMapConfig(3, 3, 6)
        val fixedMinePositionStrategy = FixedMinePositionStrategy(
            mineMapConfig = mineMapConfig,
            minePositions = Positions(
                listOf(
                    Position(0, 2),
                    Position(1, 1),
                    Position(1, 2),
                    Position(2, 0),
                    Position(2, 1),
                    Position(2, 2),
                )
            )
        )
        val sut = MineMap(mineMapConfig, fixedMinePositionStrategy)

        // when : 지뢰가 아닌 위치를 오픈
        sut.open(Position(0, 0))
        // then : 아직 빈칸이 전부 열리지 않음
        sut.checkAllEmptyOpened() shouldBe false

        // and : 지뢰가 아닌 위치를 오픈
        sut.open(Position(0, 1))
        // then : 아직 빈칸이 전부 열리지 않음
        sut.checkAllEmptyOpened() shouldBe false

        // and : 지뢰가 아닌 마지막 위치를 오픈
        sut.open(Position(1, 0))
        // then : 빈칸이 전부 열림
        sut.checkAllEmptyOpened() shouldBe true
    }
}
