package zeke.faison.zekelcs.service

import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.string.shouldNotBeBlank
import io.kotest.matchers.string.shouldNotBeEmpty
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import zeke.faison.zekelcs.model.LCSContent

class LCSServiceTest {

    private val lcsService = LCSService()

    @BeforeEach
    fun setUp() {
    }

    @Nested
    @DisplayName("A set of strings is invalid when")
    inner class InvalidSets {

        @Test
        @DisplayName("there are duplicate strings present")
        fun duplicateStrings() {
            val strings = listOf("com", "cast", "com", "dog")
            val setOfStrings = strings.map { LCSContent(it) }

            val result = lcsService.areAllStringsAreUnique(setOfStrings)

            result shouldNotBe true
        }

    }

    @Nested
    @DisplayName("A set of strings is valid when")
    inner class ValidSets {

        @Test
        @DisplayName("every string in the set is unique")
        fun uniqueStrings() {
            val strings = listOf("com", "cast", "dog", "woof")
            val setOfStrings = strings.map { LCSContent(it) }

            val result = lcsService.areAllStringsAreUnique(setOfStrings)

            result shouldBe true

        }
    }

    @Nested
    @DisplayName("the lcs service provides the longest common substring when")
    inner class findLongestCommonString{

        @Test
        @DisplayName("a common substring exists")
        fun commonSubString() {
            val strings = listOf("caster", "broadcast", "casting")
            val setOfStrings = strings.map { LCSContent(it) }
            val result = lcsService.findLongestCommonString(setOfStrings)

            result.shouldNotBeBlank()
            result shouldBe "cast"

        }
    }

}