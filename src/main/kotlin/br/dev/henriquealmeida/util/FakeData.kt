package br.dev.henriquealmeida.util

import com.github.javafaker.Faker
import java.util.*

class FakeData {

    companion object {
        private val faker = Faker(Locale("pt-br"))

        @JvmStatic
        fun userName(pointOn: Boolean = false): String {
            val stringFormat = faker.name().username()
                .replace("[âãàáä]".toRegex(), "a").replace("[êèéë]".toRegex(), "e")
                .replace("[îíìï]".toRegex(), "i").replace("[ôõòóö]".toRegex(), "o")
                .replace("[ûúùü]".toRegex(), "u")

            return if (pointOn) stringFormat else stringFormat.replace("[.]".toRegex(), "_")
        }

        @JvmStatic
        fun email(): String = "${this.userName(true)}@email.com"

        @JvmStatic
        fun firstName(): String = faker.name().firstName()

        @JvmStatic
        fun lastName(): String = faker.name().lastName()

        @JvmStatic
        fun fullName(): String = faker.name().fullName()

        @JvmStatic
        fun fakeCpf(pointOn: Boolean = true): String {
            return if (pointOn) faker.numerify("###.###.###-##") else faker.numerify("###########")
        }


    }
}