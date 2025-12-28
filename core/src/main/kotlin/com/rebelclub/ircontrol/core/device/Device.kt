package com.rebelclub.ircontrol.core.device

/**
 * Enum representing different types of IR devices
 */
enum class DeviceType {
    TV,
    SOUNDBAR,
    AIR_CONDITIONER,
    PROJECTOR,
    SET_TOP_BOX,
    DVD_PLAYER,
    AMPLIFIER,
    OTHER
}

/**
 * Data class representing an IR control device
 *
 * @property id Unique identifier for the device
 * @property name Display name of the device
 * @property type Type of the device
 * @property manufacturer Manufacturer of the device
 * @property model Model number of the device
 * @property isActive Whether the device is currently active
 */
data class Device(
    val id: String,
    val name: String,
    val type: DeviceType,
    val manufacturer: String,
    val model: String,
    val isActive: Boolean = true
)
