package minesweeper.domain.service

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.GameBoardState

class GameStateTest : BehaviorSpec({
    given("게임판에 열린 지뢰 셀이 1개 이상인 상황이면") {
        val gameBoardState =
            GameBoardState(
                countOfTotalCells = 25,
                countOfClosedCells = 24,
                countOfLandmineCells = 1,
                countOfTotalLandmines = 5,
            )

        `when`("해당 게임은") {
            val result = GameState.from(gameBoardState)

            then("패배한 것이다") {
                result shouldBe GameState.LOSE
            }
        }
    }

    given("게임판에 전체 지뢰 수와 닫혀있는 셀 수가 같으면") {
        val gameBoardState =
            GameBoardState(
                countOfTotalCells = 25,
                countOfClosedCells = 5,
                countOfLandmineCells = 0,
                countOfTotalLandmines = 5,
            )

        `when`("해당 게임은") {
            val result = GameState.from(gameBoardState)

            then("승리한 것이다") {
                result shouldBe GameState.WIN
            }
        }
    }

    given("게임판에 닫힌 셀의 수가 전체 지뢰 수보다 많으면") {
        val gameBoardState =
            GameBoardState(
                countOfTotalCells = 25,
                countOfClosedCells = 6,
                countOfLandmineCells = 0,
                countOfTotalLandmines = 5,
            )

        `when`("해당 게임은") {
            val result = GameState.from(gameBoardState)

            then("지속해야 한다") {
                result shouldBe GameState.CONTINUE
            }
        }
    }
})
