package minesweeper.domain.explorer

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class VisitQueue {

    private val queue: ArrayDeque<Position> = ArrayDeque()

    fun push(position: Position) {
        queue.addLast(position)
    }

    fun pop(): Position {
        require(!isEmpty()) { "방문 큐에 요소가 존재하지 않습니다." }
        return queue.removeFirst()
    }

    fun isEmpty(): Boolean = queue.isEmpty()
    fun pushAll(positions: Positions) {
        positions.forEach(this::push)
    }
}
