package com.foodmvvm.utils

import com.google.gson.FieldNamingPolicy
import com.google.gson.FieldNamingStrategy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.lang.Exception

object Json : JsonImpl() {
}

/**
 * Parse json error
 */
class JsonParseException(msg: String, cause: Throwable? = null) : Exception(msg, cause)

/**
 * Wrapper for GSON
 */
open class JsonImpl {

    protected var gson: Gson

    init {
        gson = GsonBuilder().apply {
            setPrettyPrinting()
            setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
        }.create()
    }

    /**
     * String to object
     */
    @Throws(JsonParseException::class)
    inline fun <reified A> toObject(json: String): A? {
        try {
            return gson.fromJson(json, A::class.java)
        } catch (t: Throwable) {
            throw JsonParseException(t.message ?: "", t)
        }
    }

    /**
     * Object to String
     */
    fun toJson(obj: Any): String? {
        try {
            return gson.toJson(obj)
        } catch (t: Throwable) {
            throw JsonParseException(t.message ?: "", t)
        }
    }

}