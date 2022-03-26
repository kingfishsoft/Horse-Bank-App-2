package com.kingfishapps.viewmodel

import com.kingfishapps.time.TimeSeries
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ProcessDataTest
{
    @Mock
    private lateinit var timeSeries: TimeSeries;

    @Test fun dataProcessed()
    {
        var processData=ProcessData();
        val testcase:Boolean= processData.addCurrencyValues("2021-09-21","USDZAR",timeSeries) != null

        return assert(testcase)

    }
}
