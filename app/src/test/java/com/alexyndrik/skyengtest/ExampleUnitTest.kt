package com.alexyndrik.skyengtest

import org.junit.Assert
import org.junit.Test

class ExampleUnitTest {

    @Test
    @Throws(Exception::class)
    fun getClassSimpleName_isExampleUnitTest() {
        val name = ExampleUnitTest::class.java.simpleName
        Assert.assertEquals(name, "ExampleUnitTest")
    }

    @Test
    @Throws(Exception::class)
    fun getClassSimpleName_isNotSkyeng() {
        val name =  ExampleUnitTest::class.java.simpleName
        Assert.assertNotEquals(name, "Skyeng")
    }



}