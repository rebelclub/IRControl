package com.ircontrol.core.ir

import android.content.Context
import android.hardware.ConsumerIrManager

object IrUtils {

    fun hasIr(context: Context): Boolean {
        val ir = context.getSystemService(Context.CONSUMER_IR_SERVICE)
                as? ConsumerIrManager
        return ir?.hasIrEmitter() == true
    }
}
