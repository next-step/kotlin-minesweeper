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

    test("특정 위치를 open한다.") {
        // given
        val mineSweeperBoard = MineSweeperBoard(
            mapOf(
                (1 to 1) to SafeZone(),
                (1 to 2) to SafeZone(),
                (2 to 1) to SafeZone(),
                (2 to 2) to SafeZone()
            ).toZones()
        )

        // when
        mineSweeperBoard.open(Position(1 to 1))

        // then
        mineSweeperBoard[Position(1 to 1)].isHidden shouldBe false
    }

    test("특정 위치의 지뢰 갯수가 0개이면 인접한 칸이 모두 열린다.") {
        // given
        val mineSweeperBoard = MineSweeperBoard(
            mapOf(
                (1 to 1) to SafeZone(),
                (1 to 2) to SafeZone(),
                (1 to 3) to SafeZone(),
                (2 to 1) to SafeZone(),
                (2 to 2) to SafeZone(),
                (2 to 3) to SafeZone(),
                (3 to 1) to SafeZone(),
                (3 to 2) to SafeZone(),
                (3 to 3) to MineZone()
            ).toZones()
        )

        // when
        mineSweeperBoard.open(Position(1 to 1))

        // then
        mineSweeperBoard[Position(1 to 1)].isHidden shouldBe false
        mineSweeperBoard[Position(1 to 2)].isHidden shouldBe false
        mineSweeperBoard[Position(1 to 3)].isHidden shouldBe false
        mineSweeperBoard[Position(2 to 1)].isHidden shouldBe false
        mineSweeperBoard[Position(2 to 2)].isHidden shouldBe false
        mineSweeperBoard[Position(2 to 3)].isHidden shouldBe false
        mineSweeperBoard[Position(3 to 1)].isHidden shouldBe false
        mineSweeperBoard[Position(3 to 2)].isHidden shouldBe false
        mineSweeperBoard[Position(3 to 3)].isHidden shouldBe true
    }

    test("게임 결과를 출력하는 기능") {
        // given
        listOf(
            row(
                MineSweeperBoard(
                    mapOf(
                        (1 to 1) to SafeZone(false),
                        (1 to 2) to SafeZone(false),
                        (2 to 1) to SafeZone(false),
                        (2 to 2) to SafeZone(false)
                    ).toZones()
                ),
                GameResult.WIN
            ),
            row(
                MineSweeperBoard(
                    mapOf(
                        (1 to 1) to MineZone(false),
                        (1 to 2) to SafeZone(),
                        (2 to 1) to SafeZone(),
                        (2 to 2) to SafeZone()
                    ).toZones()
                ),
                GameResult.LOSE
            )
        ).forEach { (mineSweeperBoard, expected) ->
            // when
            val result = mineSweeperBoard.getResult()

            // then
            result shouldBe expected
        }
    }
})
