package domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.types.shouldBeInstanceOf

class CellTest : StringSpec({
    "지뢰가 아닌 셀은 Empty 상태이다." {
        val cell = Cell.create(false)
        cell shouldBe Cell.Empty
    }

    "지뢰가 있는 셀은 MineCell 상태이다." {
        val cell = Cell.create(true)
        cell shouldBe Cell.MineCell
    }

    "인접한 지뢰가 없는 Empty 셀은 Empty 상태를 유지한다." {
        val emptyCell = Cell.Empty
        val cells =
            Cells(
                listOf(
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            emptyCell,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                ),
            )

        val result = emptyCell.addNumberHint(1, 1, cells)
        result shouldBe Cell.Empty
    }

    "인접한 지뢰가 있는 Empty 셀은 적절한 숫자를 가진 NumberCell이 된다." {
        val emptyCell = Cell.Empty
        val cells =
            Cells(
                listOf(
                    Row(
                        listOf(
                            Cell.MineCell,
                            Cell.Empty,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            emptyCell,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            Cell.Empty,
                            Cell.MineCell,
                        ),
                    ),
                ),
            )

        val result = emptyCell.addNumberHint(1, 1, cells)
        result.shouldBeInstanceOf<Cell.NumberCell>()
        (result).count shouldBe 2
    }

    "MineCell은 숫자 힌트가 추가되어도 MineCell 상태를 유지한다." {
        val mineCell = Cell.MineCell
        val cells =
            Cells(
                listOf(
                    Row(
                        listOf(
                            Cell.MineCell,
                            Cell.Empty,
                        ),
                    ),
                    Row(
                        listOf(
                            Cell.Empty,
                            mineCell,
                        ),
                    ),
                ),
            )

        val result = mineCell.addNumberHint(1, 1, cells)
        result shouldBe Cell.MineCell
    }
})
