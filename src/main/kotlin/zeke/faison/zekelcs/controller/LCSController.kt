package zeke.faison.zekelcs.controller

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import zeke.faison.zekelcs.model.LCSContent
import zeke.faison.zekelcs.model.LongCommonSubStringResponse
import zeke.faison.zekelcs.model.LongestCommonSubStringRequest
import zeke.faison.zekelcs.service.LCSService

@RestController
class LCSController(
    private val lcsService: LCSService
) {

    @PostMapping("/lcs")
    fun getLongestCommonSubString(
        @Validated @RequestBody request: LongestCommonSubStringRequest
    ): LongCommonSubStringResponse {
        require(request.setOfStrings.isNotEmpty()) {"you may not provide an empty set"}
        return when(lcsService.areAllStringsAreUnique(request.setOfStrings)) {
            true -> {LongCommonSubStringResponse(listOf(LCSContent(lcsService.findLongestCommonString(request.setOfStrings))))}
            else -> throw IllegalArgumentException("the values are not distinct")
        }
    }


}