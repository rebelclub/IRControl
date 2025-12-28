package com.rebelclub.ircontrol.core.connection

/**
 * Enum representing the different types of connections supported.
 */
enum class ConnectionType {
    IR,
    BLUETOOTH,
    WIFI
}

/**
 * Enum representing the priority levels for connections.
 */
enum class ConnectionPriority {
    LOW,
    MEDIUM,
    HIGH,
    CRITICAL
}

/**
 * Data class representing the capabilities of a connection.
 */
data class ConnectionCapabilities(
    val maxRange: Int,
    val supportsEncryption: Boolean,
    val latencyMs: Int,
    val maxBandwidthKbps: Int
)
