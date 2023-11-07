package com.i.kotlin

class AllInline {

    fun main(){
        println("aaa")

        useInline()

    }

    private inline fun useInline(){
        println("bbb")
    }
}