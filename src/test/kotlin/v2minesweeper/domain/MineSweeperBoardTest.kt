package v2minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MineSweeperBoardTest : FunSpec({
    test("지뢰찾기 게임이 끝난는지 확인한다.") {
        // given
        listOf(
            row(
                mapOf(
                    Position(1 to 1) to MineZone(true),
                    Position(1 to 2) to SafeZone(true),
                    Position(2 to 1) to SafeZone(true),
                    Position(2 to 2) to SafeZone(true)
                ),
                true
            ),
            row(
                mapOf(
                    Position(1 to 1) to SafeZone(false),
                    Position(1 to 2) to SafeZone(true),
                    Position(2 to 1) to SafeZone(true),
                    Position(2 to 2) to SafeZone(true)
                ),
                true
            ),
            row(
                mapOf(
                    Position(1 to 1) to MineZone(false),
                    Position(1 to 2) to SafeZone(true),
                    Position(2 to 1) to SafeZone(true),
                    Position(2 to 2) to SafeZone(true)
                ),
                false
            ),
            row(
                mapOf(
                    Position(1 to 1) to SafeZone(false),
                    Position(1 to 2) to SafeZone(false),
                    Position(2 to 1) to SafeZone(false),
                    Position(2 to 2) to SafeZone(false)
                ),
                false
            )
        ).forEach { (zones, expected) ->
            // given
            val mineSweeperBoard = MineSweeperBoard(Zones(zones))

            // when
            val result = mineSweeperBoard.isPlaying()

            // then
            result shouldBe expected
        }
    }
})
