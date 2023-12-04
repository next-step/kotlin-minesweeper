package minesweeper

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeTypeOf
import minesweeper.domain.MineSweeper
import minesweeper.domain.MineSweeperSize
import minesweeper.domain.cell.SafeCell

class MineSweeperTest : FunSpec({
    context("abcd") {
        test("case1") {
            val mineSweeper = MineSweeper(
                MineSweeperSize(width = 2, height = 2),
                listOf(0, 1, 2)
            )

            val actual = mineSweeper.mineMap.getOrDefault(1, emptyList())[1]

            actual.shouldBeTypeOf<SafeCell>()
            actual.countOfAdjacentMine shouldBe 3
        }

        test("case2") {
            val mineSweeper = MineSweeper(
                MineSweeperSize(width = 3, height = 3),
                listOf(0, 1, 2, 3, 5, 6, 7, 8)
            )

            val actual = mineSweeper.mineMap.getOrDefault(1, emptyList())[1]

            actual.shouldBeTypeOf<SafeCell>()
            actual.countOfAdjacentMine shouldBe 8
        }

        test("case3") {
            val mineSweeper = MineSweeper(
                MineSweeperSize(width = 3, height = 3),
                listOf(0, 1, 2, 4, 6, 7, 8)
            )

            val actual = mineSweeper.mineMap.getOrDefault(1, emptyList())[0]

            actual.shouldBeTypeOf<SafeCell>()
            actual.countOfAdjacentMine shouldBe 5
        }
    }
})
