package minesweeper

import io.kotest.matchers.shouldBe
import minesweeper.domain.Empty
import minesweeper.domain.FixedMinePositionStrategy
import minesweeper.domain.Mine
import minesweeper.domain.MineMap
import minesweeper.domain.MineMapConfig
import minesweeper.domain.Position
import minesweeper.domain.Positions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MineMapTest {

    @ParameterizedTest
    @CsvSource(
        "3, 4, 10, 10, 2",
        "10, 10, 10, 10, 90"
    )
    internal fun `지뢰를 매설하면 지뢰 개수 만큼 지도에 지뢰가 추가된다`(
        height: Int,
        width: Int,
        mineCount: Int,
        afterPlantMineCount: Int,
        afterPlantEmptyCount: Int,
    ) {
        val sut = MineMap(
            MineMapConfig(
                height = height,
                width = width,
                mineCount = mineCount,
            )
        )
        val mapAfterPlantMine = sut.getCurrentMap()
        mapAfterPlantMine.values.filterIsInstance<Mine>().size shouldBe afterPlantMineCount
        mapAfterPlantMine.values.filterIsInstance<Empty>().size shouldBe afterPlantEmptyCount
    }

    @Test
    internal fun `특정 위치를 열었을때 지뢰를 열었는지 알 수 있다`() {
        // given : 지뢰 위치가 고정된 지도 준비
        val mineMapConfig = MineMapConfig(3, 3, 3)
        val fixedMinePositionStrategy = FixedMinePositionStrategy(
            mineMapConfig = mineMapConfig,
            minePositions = Positions(listOf(Position(0, 0), Position(1, 1), Position(2, 2)))
        )
        val sut = MineMap(mineMapConfig, fixedMinePositionStrategy)

        // when : 지뢰 위치를 오픈
        sut.open(Position(0, 0))

        // then :
        sut.mineOpened shouldBe true
    }

    @Test
    internal fun `특정 위치를 열었을때 빈칸이면 지뢰가 없는 인접한 칸이 모두 열린다`() {
        // given : 지뢰 위치가 (2, 1)로 고정된 3x3 지도 준비
        // C C C
        // C C *
        // C C C
        val mineMapConfig = MineMapConfig(3, 3, 1)
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
        val currentMap = sut.getCurrentMap()
        currentMap[Position(0, 0)]?.isOpened shouldBe true
        currentMap[Position(0, 1)]?.isOpened shouldBe true
        currentMap[Position(0, 2)]?.isOpened shouldBe true
        currentMap[Position(1, 0)]?.isOpened shouldBe true
        currentMap[Position(1, 1)]?.isOpened shouldBe true
        currentMap[Position(1, 2)]?.isOpened shouldBe true
        currentMap[Position(2, 0)]?.isOpened shouldBe false
        currentMap[Position(2, 1)]?.isOpened shouldBe false
        currentMap[Position(2, 2)]?.isOpened shouldBe false
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
