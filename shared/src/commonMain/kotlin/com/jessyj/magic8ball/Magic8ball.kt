class Magic8Ball{
    // Enum to represent different response types
    enum class MoodType{
        POSITIVE,
        NEGATIVE,
        NEUTRAL,
        MYSTERIOUS,
    }

    // Data class to structure our responses
    data class Answer(
        val text: String,        // The actual answer text
        val mood: MoodType,      // The category/mood of the answer
        val probability: Int     // Weight for random selection (1-100)
    )
    private val answers = listOf(
        Answer("The code speaks true", MoodType.POSITIVE, 80),
        Answer("All tests are passing", MoodType.POSITIVE, 75),
        Answer("The bugs align in your favor", MoodType.POSITIVE, 70),
        Answer("Git commits point to yes", MoodType.POSITIVE, 65),

        // Tech-themed negative responses
        Answer("404: Answer not found", MoodType.NEGATIVE, 60),
        Answer("Null pointer to success", MoodType.NEGATIVE, 55),
        Answer("Expected: true, Actual: false", MoodType.NEGATIVE, 50),
        Answer("Connection timed out", MoodType.NEGATIVE, 45),

        // Mysterious tech responses
        Answer("The AI is contemplating", MoodType.MYSTERIOUS, 40),
        Answer("Quantum state unclear", MoodType.MYSTERIOUS, 35),
        Answer("Debugging in progress...", MoodType.MYSTERIOUS, 30),
        Answer("Recursion depth exceeded", MoodType.MYSTERIOUS, 25),

        // Neutral tech responses
        Answer("Cache needs clearing", MoodType.NEUTRAL, 20),
        Answer("Requires more data", MoodType.NEUTRAL, 15),
        Answer("Response in beta testing", MoodType.NEUTRAL, 10),
        Answer("Loading next update...", MoodType.NEUTRAL, 5)
    )

    // Keep track of previous answers to avoid repetition
    private var previousAnswers = mutableListOf<Answer>()
    private val maxPreviousAnswers = 6

    fun getAnswer(preferredMood: MoodType? = null): String {
        // Filter by mood if specified
        val eligibleAnswers = if (preferredMood != null) {
        answers.filter { it.mood == preferredMood }
        } else {
            answers
        }
        val totalWeight = eligibleAnswers.sumOf { it.probability }
        var randomNum = (0..totalWeight).random()

        var selectedAnswer: Answer? = null
        for (answer in eligibleAnswers) {
            randomNum -= answer.probability
            if (randomNum <= 0) {
                selectedAnswer = answer
                break
            }
        }

        // Fallback in case of empty result
        selectedAnswer = selectedAnswer ?: eligibleAnswers.random()

        // Avoid recent repetition
        if (selectedAnswer in previousAnswers) {
            return getAnswer(preferredMood) // Recursively try again
        }

        // Update previous answers list
        previousAnswers.add(selectedAnswer)
        if (previousAnswers.size > maxPreviousAnswers) {
            previousAnswers.removeAt(0)
        }

        return selectedAnswer.text

    }

    // Get answer with specific mood
    fun getPositiveAnswer() = getAnswer(MoodType.POSITIVE)
    fun getNegativeAnswer() = getAnswer(MoodType.NEGATIVE)
    fun getMysteriousAnswer() = getAnswer(MoodType.MYSTERIOUS)
    fun getNeutralAnswer() = getAnswer(MoodType.NEUTRAL)

    // Get statistics about previous answers
    fun getAnswerStats(): Map<MoodType, Int> {
        return previousAnswers.groupingBy { it.mood }.eachCount()
    }


}