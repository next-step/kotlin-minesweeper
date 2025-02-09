package tdd.minesweeper.domain.strategy

import tdd.minesweeper.domain.AdjacentDirection
import tdd.minesweeper.domain.Board
import tdd.minesweeper.domain.Location

class DefaultShouldOpenLocationFinder : ShouldOpenLocationFinder {
    override fun findAllShouldOpen(
        board: Board,
        location: Location,
    ): Set<Location> {
        val result = mutableSetOf<Location>()
        val context =
            SearchContext(board).apply {
                queue.add(location)
                visited.add(location)
            }

        while (context.queue.isNotEmpty()) {
            val current = context.queue.removeFirst()

            if (shouldSkip(context.board, current)) {
                continue
            }

            val cell = context.board.cells[current.toIndex(context.board.area.width)]

            // 셀 열기
            result.add(current)

            // 인접 지뢰 수가 0이면 인접 셀 추가 탐색
            if (cell.isExpandableToAdjacent()) {
                addAdjacentCandidates(context, current)
            }
        }

        return result.toSet()
    }

    private fun shouldSkip(
        board: Board,
        current: Location,
    ): Boolean {
        val currentCell = board.cells[current.toIndex(board.area.width)]
        return currentCell.isOpen()
    }

    private fun addAdjacentCandidates(
        context: SearchContext,
        current: Location,
    ) {
        AdjacentDirection.allAdjacentLocations(current)
            .filter { it.isValid(context.board.area) && it !in context.visited }
            .forEach { adjacent ->
                context.queue.add(adjacent)
                context.visited.add(adjacent)
            }
    }

    private class SearchContext(
        val board: Board,
        val visited: MutableSet<Location> = mutableSetOf(),
        val queue: ArrayDeque<Location> = ArrayDeque(),
    )
}
