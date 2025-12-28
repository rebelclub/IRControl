package com.ircontrol.core.ir

import android.content.Context
import android.hardware.ConsumerIrManager

class IrSender(context: Context) {

    private val irManager =
        context.getSystemService(Context.CONSUMER_IR_SERVICE)
                as ConsumerIrManager

    fun send(frequency: Int, pattern: IntArray) {
        if (irManager.hasIrEmitter()) {
            irManager.transmit(frequency, pattern)
        }
    }
}
