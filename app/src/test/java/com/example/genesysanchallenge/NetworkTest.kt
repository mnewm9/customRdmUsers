package com.example.genesysanchallenge

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.ObsoleteCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.URL

class CustomizedNetworkClientTest {

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Test
    fun test() {
        runBlocking {
            val url = URL("https://randomuser.me/api/?results=500")
            val json = url.readText()
            print(json)
        }
    }
}