package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import minesweeper.util.BinaryMineLocationGenerator

class MinesweeperMapTest : BehaviorSpec({

    Given("지뢰찾기 전체 맵의 높이가 0이하로 주어졌다") {
        val height = 0
        val width = 10
        val mineCount = 0
        When("해당 정보로 지뢰찾기 맵을 생성하면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MinesweeperMap.of(height, width, mineCount) }
            }
        }
    }

    Given("지뢰찾기 전체 맵의 너비가 0이하로 주어졌다") {
        val height = 10
        val width = 0
        val mineCount = 0
        When("해당 정보로 지뢰찾기 맵을 생성하면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MinesweeperMap.of(height, width, mineCount) }
            }
        }
    }

    Given("지뢰찾기 전체 맵의 크기보다 더 많은 지뢰의 개수가 주어졌다") {
        val height = 10
        val width = 10
        val mineCount = height * width + 1
        When("해당 정보로 지뢰찾기 맵을 생성하면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MinesweeperMap.of(height, width, mineCount) }
            }
        }
    }

    Given("올바른 지뢰찾기 정보가 주어졌다") {
        val height = 10
        val width = 10
        val mineCount = height * width / 2
        When("해당 정보로 지뢰찾기 맵을 생성하면") {
            Then("정상적으로 생성된다") {
                val expectedTopBottomRow = MinesweeperMapRow(listOf(MapElement.MINE, MapElement.FOUR, MapElement.MINE, MapElement.FOUR, MapElement.MINE, MapElement.FOUR, MapElement.MINE, MapElement.FOUR, MapElement.MINE, MapElement.TWO))
                val expectedMiddleRow = MinesweeperMapRow(listOf(MapElement.MINE, MapElement.SIX, MapElement.MINE, MapElement.SIX, MapElement.MINE, MapElement.SIX, MapElement.MINE, MapElement.SIX, MapElement.MINE, MapElement.THREE))
                val expectedRowList = listOf(
                    expectedTopBottomRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedMiddleRow,
                    expectedTopBottomRow,
                )
                val expectedMap = MinesweeperMap(expectedRowList)
                MinesweeperMap.of(height, width, mineCount, BinaryMineLocationGenerator) shouldBe expectedMap
            }
        }
    }
})
