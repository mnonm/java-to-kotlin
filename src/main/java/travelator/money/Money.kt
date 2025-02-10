package travelator.money

import java.math.BigDecimal
import java.util.*

class Money // <1>
private constructor(// <2>
    val amount: BigDecimal, // <3>
    val currency: Currency
) {
    override fun equals(o: Any?): Boolean { // <3>
        if (this === o) return true
        if (o == null || javaClass != o.javaClass) return false
        val money = o as Money
        return amount == money.amount &&
                currency == money.currency
    }

    override fun hashCode(): Int { // <3>
        return Objects.hash(amount, currency)
    }

    override fun toString(): String { // <4>
        return amount.toString() + " " + currency.currencyCode
    }

    fun add(that: Money): Money = this + that

    operator fun plus(that: Money): Money { // <5>
        require(this.currency == that.currency) {
            "cannot add Money values of different currencies"
        }

        return Money(this.amount + that.amount, this.currency)
    }

    companion object {
        @JvmStatic
        fun of(amount: BigDecimal, currency: Currency): Money =
            this(amount, currency)

        operator fun invoke(amount: BigDecimal, currency: Currency): Money =
            Money(
                amount.setScale(currency.defaultFractionDigits),
                currency
            )

        @JvmStatic
        fun of(amountStr: String, currency: Currency): Money { // <2>
            return of(BigDecimal(amountStr), currency)
        }

        @JvmStatic
        fun of(amount: Int, currency: Currency): Money {
            return of(BigDecimal(amount), currency)
        }

        @JvmStatic
        fun zero(userCurrency: Currency): Money {
            return of(BigDecimal.ZERO, userCurrency)
        }
    }
}

fun Iterable<Money>.sumOrNull() = reduceOrNull(Money::add)