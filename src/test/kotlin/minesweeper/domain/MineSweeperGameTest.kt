package minesweeper.domain

import io.kotest.matchers.shouldBe
import minesweeper.domain.minemap.MineMap
import minesweeper.domain.minemap.MineMapConfig
import minesweeper.domain.strategy.FixedMinePositionStrategy
import org.junit.jupiter.api.Test

internal class MineSweeperGameTest {
    @Test
    internal fun `지뢰 찾기 게임을 실행하며 게임 종료되면 승리인지 패배인지 알 수 있다`() {
        // given : 지뢰 위치가 (0, 0)로 고정된 3x3 지도를 가진 지뢰 찾기 게임 준비, 해당 게임은 (0, 0)위치를 항상 연다
        val mineMapConfig = MineMapConfig(width = 3, height = 3, mineCount = 1)
        val fixedMinePositionStrategy = FixedMinePositionStrategy(
            mineMapConfig = mineMapConfig,
            minePositions = Positions(listOf(Position(0, 0)))
        )
        val mineSweeperGame = MineSweeperGame(
            mineMapConfig = mineMapConfig,
            minePositioningStrategy = fixedMinePositionStrategy,
            openPosition = { Position(0, 0) }
        )

        // when : 지뢰 찾기 게임 시작
        val gameResult = mineSweeperGame.run()

        // then : 게임 결과 Lose
        gameResult.win shouldBe false
    }
}
