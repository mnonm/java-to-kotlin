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

    fun add(that: Money): Money { // <5>
        require(this.currency == that.currency) { "cannot add Money values of different currencies" }

        return Money(amount.add(that.amount), this.currency)
    }

    companion object {
        fun of(amount: BigDecimal, currency: Currency): Money { // <1>
            return Money(
                amount.setScale(currency.defaultFractionDigits),
                currency
            )
        }


        fun of(amountStr: String, currency: Currency): Money { // <2>
            return of(BigDecimal(amountStr), currency)
        }

        fun of(amount: Int, currency: Currency): Money {
            return of(BigDecimal(amount), currency)
        }

        fun zero(userCurrency: Currency): Money {
            return of(BigDecimal.ZERO, userCurrency)
        }
    }
}