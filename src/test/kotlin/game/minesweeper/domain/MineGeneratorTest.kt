package game.minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.data.forAll
import io.kotest.data.row
import io.kotest.matchers.shouldBe

class MineGeneratorTest : StringSpec({

    "지뢰 생성은 같은 곳에 중복해서 생성되지 않고, 지뢰 개수만큼 랜덤으로 생성된다." {
        forAll(
            row(10),
            row(9),
            row(8),
            row(7),
            row(6),
            row(2),
            row(1),
        ) { minesNumber: Int ->
            val gameBoard = Array(10) { Array(10) { "C" } }
            MineGenerator.create(gameBoard, minesNumber)

            val minesCount = gameBoard.sumOf { row -> row.count { cell -> cell == "*" } }
            minesCount shouldBe minesNumber
        }
    }
})
