package minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.shouldForAll
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe
import io.kotest.matchers.throwable.shouldHaveMessage

class MineMapTest : StringSpec({
    "MineMap을 생성하면 모두 닫힌 셀이 생성됨을 확인한다." {
        //given
        val mineMap = MineMap.createMineMap(5, 5, 5)
        //when
        val cellList = mineMap.snapshot()
        //then
        cellList.shouldForAll { it.state shouldBe CellState.CLOSED }
    }
    "openCell 함수를 호출한 결과 해당 셀의 상태는 오픈임을 확인한다." {
        //given
        val mineMap = MineMap.createMineMap(5, 5, 5)
        //when
        val openCell = mineMap.openCell(Position(0, 0))
        //then
        openCell.state shouldBe CellState.OPENED
    }

    "openCell 함수를 호출할 때 해당 셀이 없다면 IllegalStateExceptinon이 발생한다." {
        //given
        val mineMap = MineMap.createMineMap(5, 5, 5)
        //when
        //then
        shouldThrow<IllegalStateException> { mineMap.openCell(Position(6, 0)) }
            .shouldHaveMessage("해당 위치의 셀을 찾을 수 없습니다. 위치: (6,0)")
    }

    "getClosedNearPositions 함수를 호출한 결과 해당 셀의 주변의 닫힌 셀을 리턴함을 확인한다." {
        //given
        val mineMap = MineMap.createMineMap(5, 5, 5)
        //when
        mineMap.openCell(Position(1, 0))
        val closedCells = mineMap.getClosedNearPositions(Position(0, 0))
        //then
        closedCells shouldContainExactly listOf(Position(0, 1), Position(1, 1))
    }

    "isNotGameClear 함수를 호출한 결과 닫힌 셀의 개수와 지뢰 셀의 개수가 다르면 true를 리턴한다." {
        //given
        val mineMap = MineMap.createMineMap(5, 5, 5)
        //when
        val equals = mineMap.isNotGameClear()
        //then
        equals shouldBe true
    }

    "isNotGameClear 함수를 호출한 결과 닫힌 셀의 개수와 지뢰 셀의 개수가 같으면 false를 리턴한다." {
        //given
        val mineMap = MineMap.createMineMap(5, 5, 25)
        //when
        val equals = mineMap.isNotGameClear()
        //then
        equals shouldBe false
    }
})
