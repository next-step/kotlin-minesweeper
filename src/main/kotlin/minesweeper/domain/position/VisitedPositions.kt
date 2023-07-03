package minesweeper.domain.position

class VisitedPositions {

    private val visitedPositions: MutableSet<Position> = mutableSetOf()

    fun isVisit(position: Position): Boolean = visitedPositions.contains(position)

    fun visit(position: Position) {
        visitedPositions.add(position)
    }

    fun allVisitedPositions(): Positions = Positions(visitedPositions.toList())
}
