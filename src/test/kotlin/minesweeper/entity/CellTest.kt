package minesweeper.entity

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class CellTest : BehaviorSpec({

    Given("Cell 객체를 생성할 때") {

        When("Mine 타입의 Cell을 생성하면") {
            val coordinate = Coordinate(2, 3)
            val mineCell = Cell.Mine(coordinate)

            Then("해당 Cell은 Mine 타입이어야 한다") {
                mineCell.shouldBeInstanceOf<Cell.Mine>()
                mineCell.coordinate shouldBe coordinate
            }

            Then("해당 Cell은 안전하지 않아야 한다") {
                mineCell.isSafe() shouldBe false
            }
        }

        When("Empty 타입의 Cell을 생성하면") {
            val coordinate = Coordinate(5, 6)
            val emptyCell = Cell.Empty(coordinate)

            Then("해당 Cell은 Empty 타입이어야 한다") {
                emptyCell.shouldBeInstanceOf<Cell.Empty>()
                emptyCell.coordinate shouldBe coordinate
            }

            Then("해당 Cell은 안전해야 한다") {
                emptyCell.isSafe() shouldBe true
            }
        }
    }
    Given("Cell 객체를 생성한 후") {
        val coordinate = Coordinate(2, 3)
        val mineCell = Cell.Mine(coordinate)
        val emptyCell = Cell.Empty(coordinate)

        When("Cell을 open하면") {
            mineCell.open()
            emptyCell.open()

            Then("해당 Cell은 open 상태여야 한다") {
                mineCell.isRevealed shouldBe true
                emptyCell.isRevealed shouldBe true
            }
        }
    }
})
