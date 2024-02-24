package com.ministudio.encriptacion_seguridad_informatica.Clases

import android.content.Context
import android.widget.EditText
import android.widget.Toast
import java.nio.charset.StandardCharsets
import java.security.Key
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec

class UTF_8_encriptacion {

    private val ALGORITHM = "AES"
    private val SECRET_KEY = "1234567890123456"

    fun encryptAndSetEditText(data: String, editText: EditText) {


        try {
            val key = generateKey()
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.ENCRYPT_MODE, key)
            val encryptedBytes = cipher.doFinal(data.toByteArray(StandardCharsets.UTF_8))
            val encryptedText = bytesToHex(encryptedBytes)
            editText.setText("")
            editText.setText(encryptedText)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun decryptAndSetEditText(encryptedData: String, editText: EditText) {


        try {
            val key = generateKey()
            val cipher = Cipher.getInstance(ALGORITHM)
            cipher.init(Cipher.DECRYPT_MODE, key)
            val encryptedBytes = hexToBytes(encryptedData)
            val decryptedBytes = cipher.doFinal(encryptedBytes)
            val decryptedText = String(decryptedBytes, StandardCharsets.UTF_8)
            editText.setText(decryptedText)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun generateKey(): Key {
        return SecretKeySpec(SECRET_KEY.toByteArray(StandardCharsets.UTF_8), ALGORITHM)
    }

    private fun bytesToHex(bytes: ByteArray): String {
        val hexArray = "0123456789ABCDEF".toCharArray()
        val hexChars = CharArray(bytes.size * 2)
        for (j in bytes.indices) {
            val v = bytes[j].toInt() and 0xFF
            hexChars[j * 2] = hexArray[v ushr 4]
            hexChars[j * 2 + 1] = hexArray[v and 0x0F]
        }
        return String(hexChars)
    }

    private fun hexToBytes(hex: String): ByteArray {
        val len = hex.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((hex[i].digitToIntOrNull(16) ?: -1 shl 4)
            + hex[i + 1].digitToIntOrNull(16)!! ?: -1).toByte()
            i += 2
        }
        return data
    }
}
