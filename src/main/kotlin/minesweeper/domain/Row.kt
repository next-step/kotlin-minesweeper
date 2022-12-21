package minesweeper.domain

class Row(val fields: MutableList<Field>) {
    operator fun set(x: Int, dot: Field) {
        require(x in (1..fields.size))

        this.fields[x - 1] = dot
    }

    operator fun get(x: Int): Field {
        require(x in (1..fields.size))

        return fields[x - 1]
    }

    companion object {
        fun init(width: Int): Row {
            return Row(
                MutableList(width) {
                    Field()
                }
            )
        }
    }
}
