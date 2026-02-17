package ru.netology

import org.junit.Assert.*
import org.junit.Test

class DiffCommissionTest {
    @Test
    fun diffCommissionMaster() {
        //arrange
        val payMethod = "Mastercard" //тип перевода
        val monthAmount = 25_000 //сумма переводов указанного типа за календарный месяц
        val amount = 25_000 //сумма перевода в рублях

        //act
        val result = diffCommissionMaster(
            typeOfPay = payMethod,
            monthAmount = monthAmount,
            amount = amount
        )

        //assert
        val delta = 0.0
        assertEquals(1.0, result, delta)

    }

    @Test
    fun diffCommissionMasterOverLimit() {
        val payMethod = "Mastercard"
        val monthAmount = 25_000
        val amount = 75_000

        val result = diffCommissionMaster(
            typeOfPay = payMethod,
            monthAmount = monthAmount,
            amount = amount
        )

        val delta = 0.0
        assertEquals(170.0, result, delta)
    }

    @Test
    fun diffCommissionMir() {
        val amount = 75_000

        val result = diffCommissionMaster(
            amount = amount
        )

        val delta = 0.0
        assertEquals(0.0, result, delta)
    }

    @Test
    fun diffCommissionVisa() {
        val payMethod = "Visa"
        val monthAmount = 25_000
        val amount = 75_000

        val result = diffCommissionMaster(
            typeOfPay = payMethod,
            monthAmount = monthAmount,
            amount = amount
        )

        val delta = 0.0
        assertEquals(562.5, result, delta)
    }

    @Test
    fun transferMessageOverLimited() {
        val dayAmount = 140_000 //сумма переводов за сутки
        val monthAmount = 550_000 //сумма переводов за месяц
        val amount = 75_0000 //сумма перевода
        val commission = 170.0 //комиссия за операцию

        val result = transferMessage(dayAmount, monthAmount, amount, commission)

        assertEquals("Превышение лимитов - операция заблокирована.", result)
    }

    @Test
    fun transferMessageCommission() {
        val dayAmount = 0
        val monthAmount = 25_000
        val amount = 75_000
        val commission = 170.0

        val result = transferMessage(dayAmount, monthAmount, amount, commission)

        assertEquals("Комиссия за перевод составит: $commission руб.", result)
    }

}