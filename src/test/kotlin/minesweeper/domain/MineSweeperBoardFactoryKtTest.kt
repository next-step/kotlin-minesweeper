package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class MineSweeperBoardFactoryKtTest : StringSpec({
    "지뢰찾기 보드를 생성하는 기능" {
        // given
        val height = 2
        val width = 2
        val countOfMine = 1

        // when
        val mineSweeperBoard = factory {
            height(height)
            width(width)
            countOfMine(countOfMine)
            strategy { _: Int, _: Int, _: Int ->
                mapOf(
                    Position(1, 1) to MineZone,
                    Position(1, 2) to SafeZone,
                    Position(2, 1) to SafeZone,
                    Position(2, 2) to SafeZone,
                )
            }
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
        val mineSweeperBoard = factory {
            height(2)
            width(2)
            countOfMine(1)
            strategy { _: Int, _: Int, _: Int ->
                mapOf(
                    Position(1, 1) to MineZone,
                    Position(1, 2) to SafeZone,
                    Position(2, 1) to SafeZone,
                    Position(2, 2) to SafeZone,
                )
            }
        }

        // when
        val actual = mineSweeperBoard.openAllZone()

        // then
        actual[Position(1, 1)] shouldBe 0
        actual[Position(1, 2)] shouldBe 1
        actual[Position(2, 1)] shouldBe 1
        actual[Position(2, 2)] shouldBe 1
    }
})
