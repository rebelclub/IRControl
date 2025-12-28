package com.rebelclub.ircontrol.core.command

/**
 * Sealed class representing universal IR control commands
 * Created: 2025-12-28 23:01:53 UTC
 */
sealed class UniversalCommand {
    /**
     * TV control command
     * @param code The IR code to send to the TV
     * @param deviceId The TV device identifier
     */
    data class TVCommand(
        val code: String,
        val deviceId: String = "TV"
    ) : UniversalCommand()

    /**
     * Air Conditioner control command
     * @param code The IR code to send to the AC
     * @param temperature The target temperature setting
     * @param deviceId The AC device identifier
     */
    data class ACCommand(
        val code: String,
        val temperature: Int? = null,
        val deviceId: String = "AC"
    ) : UniversalCommand()

    /**
     * Input command for handling user input
     * @param inputValue The input value from the user
     */
    data class InputCommand(
        val inputValue: String
    ) : UniversalCommand()
}
