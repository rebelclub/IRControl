package com.rebelclub.ircontrol.core.ir

import android.content.Context
import android.hardware.ConsumerIrManager
import android.os.Build
import androidx.annotation.RequiresApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.math.roundToInt

/**
 * IRManager handles sending IR commands to devices using Android's ConsumerIrManager API.
 * This class provides utilities for transmitting IR signals with proper error handling
 * and frequency management.
 *
 * @param context Android application context
 */
class IRManager(private val context: Context) {

    private val consumerIrManager: ConsumerIrManager? = try {
        context.getSystemService(Context.CONSUMER_IR_SERVICE) as? ConsumerIrManager
    } catch (e: Exception) {
        null
    }

    /**
     * Checks if the device has IR transmitter hardware.
     *
     * @return true if device has IR transmitter, false otherwise
     */
    fun hasIRTransmitter(): Boolean {
        return consumerIrManager?.hasIrEmitter() ?: false
    }

    /**
     * Transmits an IR signal to the device.
     *
     * @param frequency IR carrier frequency in Hz
     * @param pattern Array of durations in microseconds. Odd indices represent on-time,
     *                even indices represent off-time
     * @throws IRTransmissionException if transmission fails
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun transmitIR(frequency: Int, pattern: IntArray) {
        if (!hasIRTransmitter()) {
            throw IRTransmissionException("Device does not have IR transmitter")
        }

        if (frequency <= 0) {
            throw IRTransmissionException("Invalid frequency: $frequency Hz")
        }

        if (pattern.isEmpty()) {
            throw IRTransmissionException("Pattern cannot be empty")
        }

        try {
            consumerIrManager?.transmit(frequency, pattern)
        } catch (e: Exception) {
            throw IRTransmissionException("Failed to transmit IR signal: ${e.message}", e)
        }
    }

    /**
     * Transmits an IR signal asynchronously.
     *
     * @param frequency IR carrier frequency in Hz
     * @param pattern Array of durations in microseconds
     * @return Result of the transmission operation
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    suspend fun transmitIRAsync(frequency: Int, pattern: IntArray): Result<Unit> {
        return try {
            withContext(Dispatchers.Default) {
                transmitIR(frequency, pattern)
            }
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    /**
     * Gets the carrier frequencies supported by the device.
     *
     * @return Array of supported frequencies in Hz, or empty array if not available
     */
    fun getSupportedCarrierFrequencies(): IntArray {
        return try {
            consumerIrManager?.carrierFrequencies?.map { it.minFrequency }?.toIntArray() ?: intArrayOf()
        } catch (e: Exception) {
            intArrayOf()
        }
    }

    /**
     * Converts frequency from kHz to Hz.
     *
     * @param frequencyKHz Frequency in kilohertz
     * @return Frequency in hertz
     */
    fun convertKHzToHz(frequencyKHz: Double): Int {
        return (frequencyKHz * 1000).roundToInt()
    }

    /**
     * Converts frequency from Hz to kHz.
     *
     * @param frequencyHz Frequency in hertz
     * @return Frequency in kilohertz
     */
    fun convertHzToKHz(frequencyHz: Int): Double {
        return frequencyHz / 1000.0
    }

    /**
     * Validates if the provided frequency is supported by the device.
     *
     * @param frequency Frequency in Hz
     * @return true if frequency is supported, false otherwise
     */
    fun isFrequencySupported(frequency: Int): Boolean {
        return try {
            val supported = getSupportedCarrierFrequencies()
            if (supported.isEmpty()) {
                // If we can't determine supported frequencies, allow transmission
                true
            } else {
                supported.contains(frequency)
            }
        } catch (e: Exception) {
            true
        }
    }

    /**
     * Gets the maximum transmit power of the IR transmitter in mW (milliwatts).
     *
     * @return Maximum power in mW, or -1 if not available
     */
    fun getMaxTransmitPowerMW(): Int {
        return try {
            consumerIrManager?.carrierFrequencies?.firstOrNull()?.power ?: -1
        } catch (e: Exception) {
            -1
        }
    }

    /**
     * Releases resources and cleans up the manager.
     */
    fun release() {
        // Resources are managed by the system, but this method can be used for future cleanup
    }

    /**
     * Custom exception for IR transmission errors.
     */
    class IRTransmissionException(message: String, cause: Throwable? = null) :
        Exception(message, cause)
}
