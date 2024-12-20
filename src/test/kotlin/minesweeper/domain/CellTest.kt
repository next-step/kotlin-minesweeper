package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.booleans.shouldNotBeTrue
import io.kotest.matchers.shouldBe

class CellTest : StringSpec({
    "지뢰는 * 표시로 나타난다" {
        val cell = Cell(Mine())
        cell.isMine().shouldBeTrue()
        cell.displayString() shouldBe "*"
    }

    "지뢰가 아니라면 인접한 지뢰의 개수로 나타난다" {
        val cell = Cell(Empty(adjacentMines = 1))
        cell.isMine().shouldNotBeTrue()
        cell.displayString() shouldBe "1"
    }

    "지뢰 여부가 주어지면 알맞은 Cell 을 생성한다" {
        val cell = Cell.from(isMinePosition = true)
        cell.isMine() shouldBe true
    }

    "지뢰 여부와 인접한 지뢰 개수가 주어지면 알맞은 Cell 을 생성한다" {
        val cell = Cell.from(isMinePosition = false, adjacentMines = 3)
        cell.isMine() shouldBe false
        cell.displayString() shouldBe "3"
    }
})
