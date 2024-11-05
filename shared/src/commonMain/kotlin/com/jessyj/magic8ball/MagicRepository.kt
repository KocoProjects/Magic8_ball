class MagicRepository {
    private val magic8Ball = Magic8Ball()

    fun shakeBall(): String {
        return magic8Ball.getAnswer()
    }

    fun askWithMood(mood: Magic8Ball.MoodType): String {
        return magic8Ball.getAnswer(mood)
    }

    fun getAnswerHistory(): Map<Magic8Ball.MoodType, Int> {
        return magic8Ball.getAnswerStats()
    }
}