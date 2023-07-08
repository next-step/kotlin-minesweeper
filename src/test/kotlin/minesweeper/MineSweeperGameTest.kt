package minesweeper

import domain.CellType
import domain.GameResult
import domain.MineCountNumber
import domain.MineSweeperGame
import domain.MineSweeperMap
import domain.Position
import domain.PositiveNumber
import domain.RandomMinePositionGenerator
import domain.toPositiveNumber
import io.kotest.matchers.shouldBe
import minesweeper.domain.Mine
import minesweeper.domain.Normal
import minesweeper.domain.TestMinePositionGenerator
import minesweeper.domain.mineSweeperMapOf
import org.junit.jupiter.api.Test

class MineSweeperGameTest {

    @Test
    fun `높이와 너비가 가지는 영역에 지뢰를 랜덤하게 위치시킨다`() {
        // [give]
        val height = PositiveNumber(3)
        val width = PositiveNumber(7)
        val mineCount = MineCountNumber(10, height, width)
        val mapProperty = MineSweeperMap.Property(height, width)

        // [when]
        val minePositionGenerator = RandomMinePositionGenerator.of(mapProperty, mineCount)
        val mineSweeperMap = MineSweeperMap(mapProperty, minePositionGenerator)
        val mineSweeperGame = MineSweeperGame(mineSweeperMap, mineCount)

        // [then]
        val actualMineCount = mineSweeperGame.mineSweeperMap.value.sumOf { row ->
            row.count { it.property.type == CellType.MINE }
        }
        actualMineCount shouldBe mineCount.value
    }

    @Test
    fun `특정 좌표를 open 했을 때, 지뢰가 아니면서 주변 지뢰 개수가 1개 이상일 때, 해당 좌표만 open 된다`() {
        // [given]
        val height = 3
        val width = 3
        val mapProperty = MapProperty(height, width)
        val position = Position.fromInt(1, 1)
        val minePositionGenerator = TestMinePositionGenerator(1 to 2)
        val mineCount = minePositionGenerator.getMineCount(height, width)

        val mineSweeperMap = MineSweeperMap(mapProperty, minePositionGenerator)
        val mineSweeperGame = MineSweeperGame(mineSweeperMap, mineCount)

        // [when]
        val openResult = mineSweeperGame.open(position)
        val isEnd = mineSweeperGame.isEnd()

        /** [then]
         * C(1)  *   C(1)
         * C(1) C(1) C(1)
         * C(0) C(0) C(0)
         */
        openResult shouldBe true
        isEnd shouldBe false
        mineSweeperMap.value shouldBe mineSweeperMapOf(height, width, minePositionGenerator) {
            row(Normal(true), Mine(), Normal())
            row(Normal(), Normal(), Normal())
            row(Normal(), Normal(), Normal())
        }.build()
    }

    @Test
    fun `특정 좌표를 open 했을 때, 지뢰가 아니면서 주변 지뢰 개수가 0개일 때, 지뢰가 없는 인접한 칸이 모두 열리게 된다`() {
        // [given]
        val height = 3
        val width = 3
        val mapProperty = MapProperty(height, width)
        val position = Position.fromInt(3, 2)
        val minePositionGenerator = TestMinePositionGenerator(
            1 to 1,
            1 to 3,
        )
        val mineCount = minePositionGenerator.getMineCount(height, width)

        val mineSweeperMap = MineSweeperMap(mapProperty, minePositionGenerator)
        val mineSweeperGame = MineSweeperGame(mineSweeperMap, mineCount)

        // [when]
        val openResult = mineSweeperGame.open(position)
        val isEnd = mineSweeperGame.isEnd()

        /** [then]
         *   *  C(2)  *
         * C(1) C(2) C(1)
         * C(0) C(0) C(0)
         */
        openResult shouldBe true
        isEnd shouldBe false
        mineSweeperMap.value shouldBe mineSweeperMapOf(height, width, minePositionGenerator) {
            row(Mine(), Normal(), Mine())
            row(Normal(true), Normal(true), Normal(true))
            row(Normal(true), Normal(true), Normal(true))
        }.build()
    }

    @Test
    fun `특정 좌표를 open 했을 때, 지뢰라면, 게임이 패배한다`() {
        // [given]
        val height = 3
        val width = 3
        val mapProperty = MapProperty(height, width)
        val position = Position.fromInt(1, 1)
        val minePositionGenerator = TestMinePositionGenerator(1 to 1)
        val mineCount = minePositionGenerator.getMineCount(height, width)

        val mineSweeperMap = MineSweeperMap(mapProperty, minePositionGenerator)
        val mineSweeperGame = MineSweeperGame(mineSweeperMap, mineCount)

        // [when]
        val openResult = mineSweeperGame.open(position)
        val isEnd = mineSweeperGame.isEnd()
        val gameResult = mineSweeperGame.getResult()

        // [then]
        openResult shouldBe false
        isEnd shouldBe true
        gameResult shouldBe GameResult.LOSE
    }

    @Test
    fun `지뢰가 아닌 칸이 모두 열리면, 게임이 승리한다`() {
        // [given]
        val height = 2
        val width = 2
        val mapProperty = MapProperty(height, width)
        val position = Position.fromInt(1, 1)
        val minePositionGenerator = TestMinePositionGenerator(
            1 to 2,
            2 to 1,
            2 to 2,
        )
        val mineCount = minePositionGenerator.getMineCount(height, width)

        val mineSweeperMap = MineSweeperMap(mapProperty, minePositionGenerator)
        val mineSweeperGame = MineSweeperGame(mineSweeperMap, mineCount)

        // [when]
        val openResult = mineSweeperGame.open(position)
        val isEnd = mineSweeperGame.isEnd()
        val gameResult = mineSweeperGame.getResult()

        /** [then]
         *   *  C(2)  *
         * C(1) C(2) C(1)
         * C(0) C(0) C(0)
         */
        openResult shouldBe true
        isEnd shouldBe true
        gameResult shouldBe GameResult.WIN
        mineSweeperMap.value shouldBe mineSweeperMapOf(height, width, minePositionGenerator) {
            row(Normal(true), Mine())
            row(Mine(), Mine())
        }.build()
    }

    private fun MapProperty(height: Int, width: Int): MineSweeperMap.Property {
        return MineSweeperMap.Property(height.toPositiveNumber(), width.toPositiveNumber())
    }
}
