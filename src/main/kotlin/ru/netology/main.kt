package ru.netology

import kotlin.math.roundToInt

fun main() {
    val payMethod = "Mastercard" //тип перевода
    val monthAmount = 25_000 //сумма переводов указанного типа за календарный месяц
    val dayAmount = 0 //сумма переводов указанного типа за сутки
    val amount = 75_000 //сумма перевода в рублях

    println(
        transferMessage(
            dayAmount, monthAmount, amount,
            diffCommissionMaster(payMethod, monthAmount, amount)
        )
    )
}

/*   Функция дифференцированного расчета комиссии за перевод
Комиссия:
* За переводы с карты Mastercard комиссия не взимается, пока не превышен месячный лимит в 75 000 руб.
Если лимит превышен, комиссия составит 0,6% + 20 руб.
* За переводы с карты Visa комиссия составит 0,75%, минимальная сумма комиссии 35 руб.
* За переводы с карты Мир комиссия не взимается.
Комиссия в лимитах не учитывается.
 */
fun diffCommissionMaster(
    typeOfPay: String = "Мир",
    monthAmount: Int = 0,
    amount: Int
): Double {
    val monthFreeLim = 75_000
    val sumOfTrans = monthAmount + amount

    return when {
        typeOfPay == "Mastercard" && sumOfTrans > monthFreeLim
            -> (((if (monthAmount < monthFreeLim) sumOfTrans - monthFreeLim else amount)
                * 100 * 0.006).roundToInt() + 20 * 100).toDouble() / 100

        typeOfPay == "Мир" || (typeOfPay == "Mastercard" && sumOfTrans <= monthFreeLim) -> 0.0

        else -> maxOf((amount * 100 * 0.0075).roundToInt(), 35 * 100).toDouble() / 100
        //При расчете выполняется перевод суммы в копейки, вычисление комиссии в копейках, перевод результата в рубли
    }
}

fun transferMessage(
    dayAmount: Int,
    monthAmount: Int,
    amount: Int,
    commission: Double
): String {
// Лимиты по операциям
    val dayLimit = 150_000
    val monthLimit = 600_000

    return when {
        (amount + dayAmount) > dayLimit ||
                (amount + monthAmount) > monthLimit -> "Превышение лимитов - операция заблокирована."

        else -> "Комиссия за перевод составит: $commission руб."
    }
}