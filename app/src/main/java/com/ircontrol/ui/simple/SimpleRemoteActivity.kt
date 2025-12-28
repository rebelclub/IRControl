package com.ircontrol.ui.simple

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ircontrol.R
import com.ircontrol.core.ir.IrSender
import com.ircontrol.core.ir.IrUtils

class SimpleRemoteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_simple_remote)

        if (!IrUtils.hasIr(this)) {
            Toast.makeText(this,
                "Bu telefonda IR yo‘q",
                Toast.LENGTH_LONG).show()
            finish()
            return
        }

        val ir = IrSender(this)

        findViewById<Button>(R.id.btnPower).setOnClickListener {
            // MISOL POWER kodi (keyin haqiqiy bazadan keladi)
            ir.send(
                38000,
                intArrayOf(
                    9000, 4500, 560, 560, 560, 560,
                    560, 1690, 560, 560
                )
            )
        }
    }
}
