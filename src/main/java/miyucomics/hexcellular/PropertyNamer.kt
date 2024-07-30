package miyucomics.hexcellular

import java.util.*

object PropertyNamer {
	private val random = Random()
	private val consonants = charArrayOf('p', 't', 'k', 's', 'm', 'n', 'l', 'j', 'w')
	private val vowels = charArrayOf('a', 'e', 'i', 'o', 'u')

	fun generatePropertyName(): String {
		val word = StringBuilder()
		if (random.nextBoolean()) word.append(vowels[random.nextInt(vowels.size)])
		val numberOfSyllables = 1 + random.nextInt(5)
		for (i in 0 until numberOfSyllables) word.append(generateSyllable())
		return word.toString()
	}

	private fun generateSyllable(): String {
		val syllable = StringBuilder().append(consonants[random.nextInt(consonants.size)]).append(vowels[random.nextInt(vowels.size)])
		if (random.nextBoolean()) syllable.append('n')
		return syllable.toString()
	}
}