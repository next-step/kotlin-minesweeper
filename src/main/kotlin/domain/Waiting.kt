package domain

class Waiting : Status {
    override fun next() = Ready()
    override fun isFinished() = false
}
