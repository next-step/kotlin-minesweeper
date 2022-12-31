package minesweeper.domain

enum class Status(private val events: Set<EVENT>) {
    PROCESSING(setOf(EVENT.WIN, EVENT.LOSE)),
    WINNING(emptySet()),
    LOSING(emptySet());

    fun next(event: EVENT): Status {
        check(this.events.contains(event)) { "다음 상태로 변경이 불가능 합니다." }

        return event.nextState()
    }

    fun isProcess(): Boolean = this == PROCESSING
}

enum class EVENT() {
    WIN {
        override fun nextState(): Status = Status.WINNING
    },
    LOSE {
        override fun nextState(): Status = Status.LOSING
    };

    abstract fun nextState(): Status
}
