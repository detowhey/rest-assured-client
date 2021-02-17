package br.dev.henriquealmeida.util

import com.github.javafaker.Faker
import java.text.SimpleDateFormat
import java.util.*
import kotlin.random.Random

class FakeData (locale: Locale) {

    companion object {
        private val faker = Faker(Locale("pt-br"))

        @JvmStatic
        fun userName(pointOn: Boolean = false): String {
            val stringFormat = faker.name().username()
                .replace("[âãàáä]".toRegex(), "a").replace("[êèéë]".toRegex(), "e")
                .replace("[îíìï]".toRegex(), "i").replace("[ôõòóö]".toRegex(), "o")
                .replace("[ûúùü]".toRegex(), "u")

            return if (pointOn) stringFormat else stringFormat.replace(".", "_")
        }

        @JvmStatic
        fun email(valid: Boolean = true): String {
            return if (valid) "${this.userName(true)}@email.com" else "${this.userName(true)}.com"
        }

        @JvmStatic
        fun firstName(): String = faker.name().firstName()

        @JvmStatic
        fun lastName(): String = faker.name().lastName()

        @JvmStatic
        fun fullName(): String = faker.name().fullName()

        @JvmStatic
        fun invalidCpf(symbolsOn: Boolean = false): String {
            return if (symbolsOn) faker.numerify("###.###.###-##") else faker.numerify("###########")
        }

        @JvmStatic
        fun cellNumber(symbolsOn: Boolean = false): String {
            return if (symbolsOn)
                faker.phoneNumber().cellPhone()
            else
                faker.phoneNumber().cellPhone().replace("[^0-9]+".toRegex(), "")
        }

        @JvmStatic
        fun fullAdress(): String = faker.address().fullAddress()

        @JvmStatic
        fun streetName(): String = faker.address().streetName()

        @JvmStatic
        fun numberAdress(): String = faker.address().streetAddressNumber()

        @JvmStatic
        fun stateName(): String = faker.address().state()

        @JvmStatic
        fun stateInitials(): String = faker.address().stateAbbr()

        @JvmStatic
        fun cityName(): String = faker.address().cityName()

        @JvmStatic
        fun zipCode(): String = faker.address().zipCode()

        @JvmStatic
        fun randomIntNumber(minValue: Int = 0, maxValue: Int): Int = Random.nextInt(minValue, maxValue)

        @JvmStatic
        fun randomDoubleNumber(minValue: Double = 0.0, maxValue: Double): Double = Random.nextDouble(minValue, maxValue)

        @JvmStatic
        fun randomStringMoneyValue(minValue: Double = 0.0, maxValue: Double, pointOn: Boolean = false): String {
            var valueMoneyString = "%.2f".format(randomDoubleNumber(minValue, maxValue))

            if (!pointOn)
                valueMoneyString = valueMoneyString.replace(".", ",")
            return valueMoneyString
        }

        @JvmStatic
        fun validCpf(symbolsOn: Boolean): String {
            val n1 = Random.nextInt(10)
            val n2 = Random.nextInt(10)
            val n3 = Random.nextInt(10)
            val n4 = Random.nextInt(10)
            val n5 = Random.nextInt(10)
            val n6 = Random.nextInt(10)
            val n7 = Random.nextInt(10)
            val n8 = Random.nextInt(10)
            val n9 = Random.nextInt(10)
            val sum = n9 * 2 + n8 * 3 + n7 * 4 + n6 * 5 + n5 * 6 + n4 * 7 + n3 * 8 + n2 * 9 + n1 * 10
            val value = (sum / 11) * 11

            var d1 = sum - value
            var rest = d1 % 11

            d1 = if (d1 < 2) 0 else 11 - rest

            val sum2 = d1 * 2 + n9 * 3 + n8 * 4 + n7 * 5 + n6 * 6 + n5 * 7 + n4 * 8 + n3 * 9 + n2 * 10 + n1 * 11
            val value2 = (sum2 / 11) * 11

            var d2 = sum2 - value2
            rest = d2 % 11

            d2 = if (d2 < 2) 0 else 11 - rest

            val concatNumber = "${n1}${n2}${n3}.${n4}${n5}${n6}.${n7}${n8}${n9}-"
            val nDigResult = "${d1}${d2}"

            return if (symbolsOn)
                "${concatNumber}${nDigResult}"
            else
                "${concatNumber}${nDigResult}".replace("[.-]".toRegex(), "")
        }

        @JvmStatic
        fun birthDayString(minAge: Int, maxAge: Int): String {
            val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
            val date = birthDayDate(minAge, maxAge)
            return simpleDateFormat.format(date)
        }

        @JvmStatic
        fun birthDayDate(minAge: Int, maxAge: Int): Date = faker.date().birthday(minAge, maxAge)

        @JvmStatic
        fun charactersLorenIpsulum(
            minLength: Int = 1,
            maxLength: Int,
            upperCaseOn: Boolean = false,
            includeDigits: Boolean = false
        ): String = faker.lorem().characters(minLength, maxLength, upperCaseOn, includeDigits)

        fun wordsLorenIpsulum(numberOfWords: Int = 1): MutableList<String> = faker.lorem().words(numberOfWords)

        fun sentenceLorenIpsulum(numberOfWords: Int = 1): String = faker.lorem().sentence(numberOfWords)

        fun fixedString(numberOfLetters: Int): String = faker.lorem().fixedString(numberOfLetters)

        @JvmStatic
        fun randomTime(): String {
            val simpleDateFormat = SimpleDateFormat("HH:mm")
            val date = faker.date()
            return simpleDateFormat.format(date)
        }
    }
}
