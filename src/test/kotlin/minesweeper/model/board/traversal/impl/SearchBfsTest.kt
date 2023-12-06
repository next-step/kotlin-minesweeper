package minesweeper.model.board.traversal.impl

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.collections.shouldHaveSize
import minesweeper.model.board.minedeploy.impl.SpecifiedCoordinatesStrategy
import minesweeper.model.board.toBoardLimit
import minesweeper.model.board.toMines
import minesweeper.model.point.CoordinateFixture.toCoordinate

class SearchBfsTest : StringSpec({

    "탐색한다" {
        val limit = (4 to 4).toBoardLimit()
        val searchBfs = SearchBfs(
            limit, SpecifiedCoordinatesStrategy(
                0 to 0,
                1 to 1,
                2 to 2,
                3 to 3
            ).deployPoints(limit).toMines(limit)
        )
        val actual = searchBfs.traversal((3 to 0).toCoordinate())

        //assert
        actual shouldHaveSize 5
        actual shouldContainAll setOf(
            (3 to 0).toCoordinate(),
            (3 to 1).toCoordinate(),
            (3 to 2).toCoordinate(),
            (2 to 0).toCoordinate(),
            (2 to 1).toCoordinate(),
        )

        //comment
        val describeTest = """
            테스트코드 설명
            
            [기호] 
            - X : 탐색으로 도달하지 못하는 영역
            - * : 지뢰가 매설된 지역 (역시 동일하게 탐색으로 도달하지 못함)
            - O : 탐색으로 도달하는 지역
            
            [그림]
            * X X X
            O * X X
            O O * X
            O O O *
                     
            [탐색으로 도달해야하는 좌표]
            Coordinate(vertical=Vertical(value=3), horizontal=Horizontal(value=0)) 
            Coordinate(vertical=Vertical(value=3), horizontal=Horizontal(value=1)) 
            Coordinate(vertical=Vertical(value=3), horizontal=Horizontal(value=2))
            Coordinate(vertical=Vertical(value=2), horizontal=Horizontal(value=0))
            Coordinate(vertical=Vertical(value=2), horizontal=Horizontal(value=1)) 
            
        """.trimIndent()
        val demo = """
            [실제 게임 플레이시 기대하는 동작]
            높이를 입력하세요.
            4
            너비를 입력하세요.
            4
            지뢰는 몇 개인가요?
            4
            지뢰찾기 게임 시작
            open : 3,0
            C C C C
            C C C C
            1 2 C C
            0 1 2 C
            
        """.trimIndent()
        print(describeTest)
        print(demo)
    }
})
