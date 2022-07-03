package minesweeper.domain

import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrowExactly
import io.kotest.core.spec.style.StringSpec
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MineSweeperBoardFactoryKtTest : StringSpec({
    "지뢰찾기 보드를 생성하는 기능" {
        // given
        val height = 5
        val width = 5
        val countOfMine = 1

        // when
        val mineSweeperBoard = factory {
            height(height)
            width(width)
            countOfMine(countOfMine)
        }

        // then
        assertSoftly(mineSweeperBoard.zones) {
            size shouldBe height * width
            // get(0).size shouldBe width
            // get(1).size shouldBe width
            // get(2).size shouldBe width
            // get(3).size shouldBe width
            // get(4).size shouldBe width
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
})
