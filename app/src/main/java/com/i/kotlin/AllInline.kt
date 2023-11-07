package com.i.kotlin

import kotlinx.coroutines.runBlocking

class AllInline {

    //inline： 内联，修饰函数, 减少方法栈的层级与函数类型对象的创建
    private inline fun useInline(block: () -> Unit) {
        println("useInline")
        block()
    }

    //noinline： 不内联，修饰函数参数，则可以把函数参数当对象使用
    private inline fun useNoInline(noinline block: () -> Unit): () -> Unit {
        println("useNoInline")
        return block
    }

    //crossinline: 交叉内联，修饰函数参数，使函数参数能被间接调用，该Lambda中不能使用return
    private inline fun useCrossInline(crossinline block: () -> Unit) {
        println("useCrossInline")
        runBlocking {
            block() //间接调用
        }
    }

    fun main() {
        println("main")
        useInline {
            println("useInline end")
        }

        useNoInline {
            println("useNoInline end")
        }

        useCrossInline {
            println("useCrossInline end")
        }
    }
}