package domain

enum class Direction {
    UPPER_LEFT {
        override fun apply(x: Int, y: Int): Position = Position(x - 1, y - 1)
    },
    ABOVE {
        override fun apply(x: Int, y: Int): Position = Position(x - 1, y)
    },
    UPPER_RIGHT {
        override fun apply(x: Int, y: Int): Position = Position(x - 1, y + 1)
    },
    LEFT {
        override fun apply(x: Int, y: Int): Position = Position(x, y - 1)
    },
    RIGHT {
        override fun apply(x: Int, y: Int): Position = Position(x, y + 1)
    },
    BELOW_LEFT {
        override fun apply(x: Int, y: Int): Position = Position(x + 1, y - 1)
    },
    BELOW {
        override fun apply(x: Int, y: Int): Position = Position(x + 1, y)
    },
    BELOW_RIGHT {
        override fun apply(x: Int, y: Int): Position = Position(x + 1, y + 1)
    };

    abstract fun apply(x: Int, y: Int): Position
}
