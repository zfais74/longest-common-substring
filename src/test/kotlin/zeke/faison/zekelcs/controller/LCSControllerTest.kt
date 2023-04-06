package zeke.faison.zekelcs.controller

import io.kotest.matchers.collections.shouldNotBeEmpty
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import zeke.faison.zekelcs.model.LCSContent
import zeke.faison.zekelcs.model.LongestCommonSubStringRequest
import zeke.faison.zekelcs.service.LCSService

@ExtendWith(MockKExtension::class)
class LCSControllerTest {

    @MockK
    private lateinit var lcsService: LCSService

    @InjectMockKs
    private lateinit var lcsController: LCSController

    @Nested
    @DisplayName("The LCS controller fails to get the longest common substring when")
    inner class longestCommonSubstringFailures{

        @Test
        @DisplayName("the set of strings are empty")
        fun emptySetOfSubStrings() {
            val lcsRequest = LongestCommonSubStringRequest(
                setOfStrings = emptyList()
            )

            assertThrows<IllegalArgumentException> {
                lcsController.getLongestCommonSubString(
                    request = lcsRequest
                )
            }
        }

        @Test
        @DisplayName("the set of strings are not unique")
        fun duplicateStringsInTheSet(){
            val strings = listOf("com", "cast", "com")
            val setOfStrings = strings.map { LCSContent(it) }
            val lcsRequest = LongestCommonSubStringRequest(
                setOfStrings = setOfStrings
            )

            every { lcsService.areAllStringsAreUnique(setOfStrings) } returns false

            assertThrows<IllegalArgumentException> {
                lcsController.getLongestCommonSubString(
                    request = lcsRequest
                )
            }
        }
    }

    @Nested
    @DisplayName("The LCS controller gets the longest common substring when")
    inner class longestCommonSubStringSuccess{
        @Test
        @DisplayName("the set of strings is valid and has a common substring")
        fun validSetOfSubStrings() {
            val strings = listOf("caster", "casting", "broadcast")
            val setOfStrings = strings.map { LCSContent(it) }
            val lcsRequest = LongestCommonSubStringRequest(
                setOfStrings = setOfStrings
            )

            every {lcsService.areAllStringsAreUnique(setOfStrings)} returns true
            every { lcsService.findLongestCommonString(setOfStrings) } returns "cast"

            val result = lcsController.getLongestCommonSubString(lcsRequest)

            result.lcs.shouldNotBeEmpty()
            result.lcs.first().value shouldBe "cast"
        }
    }
}