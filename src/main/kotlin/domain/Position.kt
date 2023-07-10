package domain

data class Position(val x: Int, val y: Int) {

    fun move(movementBlock: MovementBlock): Position {
        return PositionMovement(this).apply(movementBlock).position()
    }

    private class PositionMovement(private val position: Position) : Movement {
        private var xPlusValue: Int = 0
        private var yPlusValue: Int = 0

        override fun up() {
            yPlusValue++
        }

        override fun down() {
            yPlusValue--
        }

        override fun left() {
            xPlusValue--
        }

        override fun right() {
            xPlusValue++
        }

        fun position() = Position(position.x + xPlusValue, position.y + yPlusValue)
    }
}

typealias MovementBlock = Movement.() -> Unit

interface Movement {
    fun up()

    fun down()

    fun left()

    fun right()
}
