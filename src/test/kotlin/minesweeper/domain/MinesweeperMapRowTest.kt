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

    Given("적당한 너비의 지뢰찾기 맵 한줄이 주어졌다") {
        val mapElementList = listOf(NumberMapElement(1), MineMapElement(), NumberMapElement(1))
        val mineMapRow = MinesweeperMapRow(mapElementList)
        When("맵 요소의 가려진 상태를 확인하면") {
            Then("가려져있는 상태가 반환된다") {
                mineMapRow.isCovered(0) shouldBe true
            }
        }

        When("지뢰가 아닌 맵요소가 지뢰인지 확인하면") {
            Then("지뢰가 아닌 것으로 반환된다") {
                mineMapRow.isMine(0) shouldBe false
            }
        }

        When("지뢰인 맵요소가 지뢰인지 확인하면") {
            Then("지뢰인 것으로 반환된다") {
                mineMapRow.isMine(1) shouldBe true
            }
        }

        When("맵요소를 열면") {
            Then("해당 맵 요소가 열린다") {
                mineMapRow.open(0)
                mineMapRow.isCovered(0) shouldBe false
            }
        }

        When("지뢰가 아닌 맵요소의 숫자를 가져오면") {
            Then("해당 숫자가 반환된다") {
                mineMapRow.getNumber(0) shouldBe 1
            }
        }

        When("지뢰인 맵요소의 숫자를 가져오면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { mineMapRow.getNumber(1) }
            }
        }

        When("현재 줄에서 열리지 않은 숫자 맵 요소의 개수를 구하면") {
            Then("해당 개수가 반환된다") {
                mineMapRow.getCoveredNumberMapElementCount() shouldBe 1
            }
        }
    }
})
