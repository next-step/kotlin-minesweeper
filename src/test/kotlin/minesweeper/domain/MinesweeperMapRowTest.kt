package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MinesweeperMapRowTest : BehaviorSpec({
    Given("맵의 너비가 0이하로 주어졌다") {
        val rowNumber = 0
        val width = 0
        val height = 1
        val mineLocations = MineLocations(emptyList(), width, height)

        When("해당 정보로 지뢰찾기 맵 한줄을 만들면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MinesweeperMapRow.of(rowNumber, width, mineLocations) }
            }
        }
    }

    Given("지뢰의 위치 리스트가 주어졌다") {
        val rowNumber = 0
        val width = 10
        val height = 1
        val mineLocations = MineLocations(listOf(MineLocationRow(List(width / 2) { it * 2 })), width, height)

        When("해당 정보로 지뢰찾기 맵 한줄을 만들면") {
            Then("맵 한줄이 정상적으로 생성된다") {
                val expectedRow = listOf(
                    MineMapElement(),
                    NumberMapElement(2),
                    MineMapElement(),
                    NumberMapElement(2),
                    MineMapElement(),
                    NumberMapElement(2),
                    MineMapElement(),
                    NumberMapElement(2),
                    MineMapElement(),
                    NumberMapElement(1),
                )
                MinesweeperMapRow.of(rowNumber, width, mineLocations) shouldBe MinesweeperMapRow(expectedRow)
            }
        }
    }
})
