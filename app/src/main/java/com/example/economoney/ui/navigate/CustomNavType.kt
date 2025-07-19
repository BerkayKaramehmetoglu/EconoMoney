package com.example.economoney.ui.navigate

import android.net.Uri
import androidx.navigation.NavType
import androidx.savedstate.SavedState
import com.example.economoney.data.entity.Coins
import kotlinx.serialization.json.Json

object CustomNavType {
    private val customJson = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }
    val CoinsType = object : NavType<Coins>(
        isNullableAllowed = false
    ) {
        override fun put(bundle: SavedState, key: String, value: Coins) {
            bundle.putString(key, customJson.encodeToString(value))
        }

        override fun get(bundle: SavedState, key: String): Coins? {
            return customJson.decodeFromString(bundle.getString(key) ?: return null)
        }

        override fun parseValue(value: String): Coins {
            return Json.decodeFromString(Uri.decode(value))
        }

        override fun serializeAsValue(value: Coins): String {
            return Uri.encode(Json.encodeToString(value))
        }
    }
}