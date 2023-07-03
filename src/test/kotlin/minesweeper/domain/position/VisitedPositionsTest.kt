package minesweeper.domain.position

import io.kotest.matchers.collections.shouldContainAll
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class VisitedPositionsTest {

    @Test
    fun `방문하지 않은 위치면 방문여부 확인은 false 리턴`() {
        val visitedPositions = VisitedPositions()

        visitedPositions.isVisit(Position(x = 100, y = 100)) shouldBe false
    }

    @Test
    fun `방문한 위치면 방문여부 확인은 true 리턴`() {
        val visitedPositions = VisitedPositions()
        val visitPosition = Position(x = 100, y = 100)
        visitedPositions.visit(visitPosition)

        visitedPositions.isVisit(visitPosition) shouldBe true
    }

    @Test
    fun `방문한 위치는 중복되지 않는다`() {
        val visitedPositions = VisitedPositions()
        val visitPositions = Positions((1..10).map { Position(x = 1, y = 1) })

        visitPositions.forEach(visitedPositions::visit)

        val allVisitedPositions = visitedPositions.allVisitedPositions()

        allVisitedPositions.size shouldBe 1
        allVisitedPositions shouldContainAll Positions(listOf(Position(x = 1, y = 1)))
    }
}
