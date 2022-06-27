package minesweeper.domain

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineSweeperTest : StringSpec({

    "주변 셀에 지뢰가 없으면 0 으로 표시한다." {
        val mineMap = MineMap(2, 2, 0)
        val sweptMineMap = MineSweeper.sweepe(mineMap.map())

        sweptMineMap.flatten().count { it is MineCell } shouldBe 0
        sweptMineMap.flatten().all { it.mineCountAround == 0 } shouldBe true
    }

    "기준 셀 오른쪽에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweepe(listOf(listOf(Cell(), MineCell())))

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[0][0].mineCountAround shouldBe 1
    }

    "기준 셀 왼쪽에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweepe(listOf(listOf(MineCell(), Cell())))

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[0][1].mineCountAround shouldBe 1
    }

    "기준 셀 아래에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweepe(listOf(listOf(Cell()), listOf(MineCell())))

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[0][0].mineCountAround shouldBe 1
    }

    "기준 셀 위에 지뢰가 있으면 1로 표시된다." {
        val sweptMineMap = MineSweeper.sweepe(listOf(listOf(MineCell()), listOf(Cell())))

        sweptMineMap.flatten().count { it is MineCell } shouldBe 1
        sweptMineMap[1][0].mineCountAround shouldBe 1
    }
})
