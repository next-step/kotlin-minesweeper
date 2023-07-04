package mine.sweeper.field.domain

import mine.sweeper.domain.Position

class MineField(position: Position) : Field(position) {
    override val value: String = "*"
}
