package com.metinemre.composeflow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class MyViewModel:ViewModel() {
    // flow
    val countDownTimerFlow = flow<Int> {
        //
        val countDownFrom = 10
        var counter = countDownFrom

        emit(countDownFrom)
        while (counter > 0) {
            delay(1000)
            counter--
            emit(counter)
        }
    }

    init {
        collectInViewModel()
    }

    private fun collectInViewModel() {

//        countDownTimerFlow.onEach {
//            println(it)
//        }.launchIn(viewModelScope)

        viewModelScope.launch {
            countDownTimerFlow
                .filter {
                    it %3 == 0
                }.map {
                    it + it
                }
                .collect{
                println("counter : ${it}")
            }
            // en sonuncuyu topla demek !!
//            countDownTimerFlow.collectLatest {
//                delay(50000)
//                println("counter : ${it}")
//            }
        }

    }


}