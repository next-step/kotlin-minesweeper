package domain

class Ready : Status {
    override fun next() = Running()
    override fun isFinished() = false
}
