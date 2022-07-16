package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.kotest.matchers.types.shouldBeSameInstanceAs

class MineSweeperBoardFactoryKtTest : StringSpec({
    "지뢰찾기 보드를 생성하는 기능" {
        // given
        val height = 2
        val width = 2
        val countOfMine = 1
        val mockGeneratorStrategy = MineGeneratorStrategy { _, _, _ ->
            Zones(
                mapOf(
                    Position(1, 1) to MineZone(),
                    Position(1, 2) to SafeZone(),
                    Position(2, 1) to SafeZone(),
                    Position(2, 2) to SafeZone(),
                )
            )
        }

        // when
        val mineSweeperBoard = factory {
            height(height)
            width(width)
            countOfMine(countOfMine)
            strategy(mockGeneratorStrategy)
        }

        // then
        assertSoftly(mineSweeperBoard.zones) {
            size shouldBe height * width
            get(Position(1, 1)).shouldBeInstanceOf<MineZone>()
            get(Position(1, 2)).shouldBeInstanceOf<SafeZone>()
            get(Position(2, 1)).shouldBeInstanceOf<SafeZone>()
            get(Position(2, 2)).shouldBeInstanceOf<SafeZone>()
        }
    }

    "높이, 너비, 지뢰 갯수 조건을 만족하지 않으면 지뢰찾기 보드를 생성할 수 없다." {
        listOf(
            row(0, 1, 1),
            row(1, 0, 1),
            row(1, 1, 0),
        ).forEach { (height, width, countOfMine) ->
            // when // then
            shouldThrowExactly<IllegalArgumentException> {
                factory {
                    height(height)
                    width(width)
                    countOfMine(countOfMine)
                }
            }
        }
    }

    "지뢰 찾기 보드판의 보드의 주변 지뢰 갯수를 오픈한다. " {
        // given
        val mockMineGeneratorStrategy = MineGeneratorStrategy { _, _, _ ->
            mapOf(
                Position(1, 1) to MineZone(),
                Position(1, 2) to SafeZone(),
                Position(2, 1) to SafeZone(),
                Position(2, 2) to SafeZone(),
            ).let { Zones(it) }
        }

        val mineSweeperBoard = factory {
            height(2)
            width(2)
            countOfMine(1)
            strategy(mockMineGeneratorStrategy)
        }

        // when
        val actual = mineSweeperBoard.openAllZone()

        // then
        actual[Position(1, 1)] shouldBe 0
        actual[Position(1, 2)] shouldBe 1
        actual[Position(2, 1)] shouldBe 1
        actual[Position(2, 2)] shouldBe 1
    }

    "지뢰 찾기 보드의 게임 상태를 확인한다." {
        listOf(
            row(
                mapOf(
                    Position(1, 1) to MineZone(true),
                    Position(1, 2) to SafeZone(true),
                    Position(2, 1) to SafeZone(true),
                    Position(2, 2) to SafeZone(true),
                ).let { Zones(it) },
                true,
            ),
            row(
                mapOf(
                    Position(1, 1) to MineZone(true),
                    Position(1, 2) to SafeZone(false),
                    Position(2, 1) to SafeZone(true),
                    Position(2, 2) to SafeZone(true),
                ).let { Zones(it) },
                true,
            ),
            row(
                mapOf(
                    Position(1, 1) to MineZone(false),
                    Position(1, 2) to SafeZone(true),
                    Position(2, 1) to SafeZone(true),
                    Position(2, 2) to SafeZone(true),
                ).let { Zones(it) },
                false,
            ),
            row(
                mapOf(
                    Position(1, 1) to MineZone(true),
                    Position(1, 2) to SafeZone(false),
                    Position(2, 1) to SafeZone(false),
                    Position(2, 2) to SafeZone(false),
                ).let { Zones(it) },
                false,
            ),
        ).forEach { (zones, expected) ->
            // given
            val mineSweeperBoard = MineSweeperBoard(zones)

            // when
            val actual = mineSweeperBoard.isPlaying

            // then
            actual shouldBe expected
        }
    }

    "게임 결과를 확인한다." {
        listOf(
            row(
                mapOf(
                    Position(1, 1) to MineZone(true),
                    Position(1, 2) to SafeZone(false),
                    Position(2, 1) to SafeZone(false),
                    Position(2, 2) to SafeZone(false),
                ).let { Zones(it) },
                MineSweeperResult.WIN,
            ),
            row(
                mapOf(
                    Position(1, 1) to MineZone(false),
                    Position(1, 2) to SafeZone(false),
                    Position(2, 1) to SafeZone(true),
                    Position(2, 2) to SafeZone(true),
                ).let { Zones(it) },
                MineSweeperResult.LOSE,
            ),
        ).forEach { (zones, expected) ->
            // given
            val mineSweeperBoard = MineSweeperBoard(zones)

            // when
            val actual = mineSweeperBoard.getResult()

            // then
            actual shouldBeSameInstanceAs expected
        }
    }
})
