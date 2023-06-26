package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MinesweeperMapRowTest : BehaviorSpec({
    Given("맵의 너비가 0이하로 주어졌다") {
        val width = 0
        val mineIndices = IntArray(0)
        When("해당 정보로 지뢰찾기 맵 한줄을 만들면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MinesweeperMapRow.of(mineIndices, width) }
            }
        }
    }

    Given("지뢰의 위치 리스트가 맵의 너비보다 더 많이 주어졌다") {
        val width = 10
        val mineIndices = IntArray(width + 1) { it }
        When("해당 정보로 지뢰찾기 맵 한줄을 만들면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MinesweeperMapRow.of(mineIndices, width) }
            }
        }
    }

    Given("지뢰의 위치 리스트가 주어졌다") {
        val width = 10
        val mineIndices = IntArray(width / 2) { it * 2 }
        When("해당 정보로 지뢰찾기 맵 한줄을 만들면") {
            Then("맵 한줄이 정상적으로 생성된다") {
                val expectedRow = listOf(
                    MapElement.MINE,
                    MapElement.NORMAL,
                    MapElement.MINE,
                    MapElement.NORMAL,
                    MapElement.MINE,
                    MapElement.NORMAL,
                    MapElement.MINE,
                    MapElement.NORMAL,
                    MapElement.MINE,
                    MapElement.NORMAL,
                )
                MinesweeperMapRow.of(mineIndices, width) shouldBe MinesweeperMapRow(expectedRow)
            }
        }
    }
})
