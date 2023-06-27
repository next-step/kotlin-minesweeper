package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe

class MineLocationsTest : BehaviorSpec({

    Given("맵의 높이가 0이하로 주어졌다") {
        val height = 0
        val width = 10
        val mineIndices = emptyList<Int>()
        When("해당 정보로 지뢰의 좌표를 생성하면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MineLocations.of(mineIndices, height, width) }
            }
        }
    }

    Given("맵의 너비가 0이하로 주어졌다") {
        val height = 10
        val width = 0
        val mineIndices = emptyList<Int>()
        When("해당 정보로 지뢰의 좌표를 생성하면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MineLocations.of(mineIndices, height, width) }
            }
        }
    }

    Given("지뢰의 위치가 맵의 크기보다 많다") {
        val height = 10
        val width = 10
        val mineIndices = List(height * width + 1) { it }
        When("해당 정보로 지뢰의 좌표를 생성하면") {
            Then("예외가 던져진다") {
                shouldThrow<IllegalArgumentException> { MineLocations.of(mineIndices, height, width) }
            }
        }
    }

    Given("올바른 지뢰 위치 정보가 주어졌다") {
        val height = 10
        val width = 10
        val mineIndices = List(height * width / 2) { it * 2 }
        When("해당 정보로 지뢰의 위치를 생성하면") {
            Then("정상적으로 지뢰 좌표가 생성된다") {
                val expectedRow = MineLocationRow(List(width / 2) { it * 2 })
                val expectedLocation = MineLocations(List(height) { expectedRow })
                MineLocations.of(mineIndices, height, width) shouldBe expectedLocation
            }
        }
    }
})
