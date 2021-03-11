package unit

import br.qa.henriquealmeida.util.FakeData
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.Assertions.assertAll
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.function.Executable

@DisplayName("Unit tests of data provider class FakeData")
class FakeDataTest {

    @Test
    fun `Returns a random username without a dot`() = assertTrue(FakeData.userName().matches("[a-z_]+".toRegex()))

    @Test
    fun `Returns a random username with a dot`() = assertTrue(FakeData.userName(true).matches("[a-z.]+".toRegex()))

    @Test
    fun `Must contain a valid email`() = assertTrue(FakeData.email().contains("@email.com"))

    @Test
    fun `Must contain a invalid email`() = assertFalse(FakeData.email(false).contains("@email.com"))

    @Test
    fun `Returns a random firstname`() = assertTrue(FakeData.firstName().matches(lettersAccentRegex()))

    @Test
    fun `Returns a random lastname`() = assertTrue(FakeData.lastName().matches(lettersAccentRegex()))

    @Test
    fun `Returns a random full name`() = assertTrue(FakeData.fullName().matches(lettersAccentRegex()))

    @Test
    fun `Returns a invalid CPF with special characters`() {
        val invalidCPF = FakeData.invalidCpf(true)

        assertTrue {
            invalidCPF.contains("[.-]".toRegex())
                .and(invalidCPF.contains(numbersOnlyRegex()).and(invalidCPF.length == 14))
        }
    }

    @Test
    fun `Returns a invalid CPF without special characters`() {
        val invalidCPF = FakeData.invalidCpf()
        assertTrue(invalidCPF.matches(numbersOnlyRegex()).and(invalidCPF.length == 11))
    }

    @Test
    fun `Returns a String random cellphone number with special characters`() {
        val randomCellPhoneNumber = FakeData.cellNumber(true)
        assertTrue(randomCellPhoneNumber.matches("[0-9() .-]+".toRegex()).and(randomCellPhoneNumber.length == 15))
    }

    @Test
    fun `Returns a String random cellphone number without special characters`() {
        val randomCellPhoneNumber = FakeData.cellNumber()
        assertTrue(randomCellPhoneNumber.matches(numbersOnlyRegex()).and(randomCellPhoneNumber.length == 11))
    }

    @Test
    fun `Returns random address String`() {
        assertTrue(FakeData.fullAdress().isNotEmpty())
    }

    @Test
    fun `Returns random street name String`() = assertTrue(FakeData.streetName().matches(lettersAccentRegex()))

    @Test
    fun `Returns random number address String`() = assertTrue(FakeData.numberAdressString().matches(numbersOnlyRegex()))

    @Test
    fun `Returns a full state name`() {
        val stateName = FakeData.stateFullName()
        assertTrue(stateName.matches(lettersAccentRegex()).and(stateName.length > 2))
    }

    @Test
    fun `Returns the initials of a state`() {
        val stateInitials = FakeData.stateInitials()
        assertTrue(stateInitials.matches("[A-Z]+".toRegex()).and(stateInitials.length == 2))
    }

    @Test
    fun `Returns a random city name String`() = assertTrue(FakeData.cityName().matches(lettersAccentRegex()))

    @Test
    fun `Returns a random zipcode String with special characters`() {
        val zipCode = FakeData.zipCode(true)
        assertTrue(zipCode.matches("[0-9-]+".toRegex()).and(zipCode.length == 9))
    }

    @Test
    fun `Returns a random zipcode String without special characters`() {
        val zipCode = FakeData.zipCode()
        assertTrue(FakeData.zipCode().matches(numbersOnlyRegex()).and(zipCode.length == 8))
    }

    @Test
    fun `Returns a random Integer number with a max value`() =
        assertTrue(IntRange(0, 10).contains(FakeData.randomIntNumber(maxValue = 10)))

    @Test
    fun `Returns a random Integer number with specific range`() =
        assertTrue(IntRange(5, 50).contains(FakeData.randomIntNumber(5, 50)))

    @Test
    fun `Returns random Double number with a max value`() =
        assertTrue((0.0..50.05).contains(FakeData.randomDoubleNumber(maxValue = 50.05)))

    @Test
    fun `Returns a random Double number with specific range`() =
        assertTrue((5.5..50.05).contains(FakeData.randomDoubleNumber(5.5, 50.05)))

    @Test
    fun `Returns a valid CPF String without special characters`() =
        assertTrue(FakeData.validCpf().matches(numbersOnlyRegex()))


    @Test
    fun `Returns a valid CPF String with special characters`() =
        assertTrue(FakeData.validCpf(true).matches("[0-9.-]+".toRegex()))

    @Test
    fun `Returns random characters with range, without digits and capital letters`() {
        val characters = FakeData.charactersLorenIpsulum(10, 20)
        assertTrue(IntRange(10, 20).contains(characters.length).and(characters.matches(lowercaseOnlyRegex())))
    }

    @Test
    fun `Returns random characters with range, digits and without capital letters`() {
        val characters = FakeData.charactersLorenIpsulum(10, 50, includeDigits = true)
        assertTrue(IntRange(10, 50).contains(characters.length).and(characters.matches(lowercaseAndNumbersRegex())))
    }

    @Test
    fun `Returns random characters with range, digits and capital letters`() {
        val characters = FakeData.charactersLorenIpsulum(10, 30, upperCaseOn = true, includeDigits = true)
        assertTrue(IntRange(10, 30).contains(characters.length).and(characters.matches(lettersAndNumbersRegex())))
    }

    @Test
    fun `Returns a random String with fixed number of characters, without digits and capital letters`() {
        val randomString = FakeData.charactersLorenIpsulumFixedNumber(10)
        assertTrue(randomString.matches(lowercaseOnlyRegex()).and(randomString.length == 10))
    }

    @Test
    fun `Returns a random String with fixed number of characters, digits and without capital letters`() {
        val randomString = FakeData.charactersLorenIpsulumFixedNumber(20, includeDigits = true)
        assertTrue(randomString.matches(lowercaseAndNumbersRegex()).and(randomString.length == 20))
    }

    @Test
    fun `Returns a random String with fixed number of characters, digits and capital letters`() {
        val randomString = FakeData.charactersLorenIpsulumFixedNumber(15, upperCaseOn = true, includeDigits = true)
        assertTrue(randomString.matches(lettersAndNumbersRegex()).and(randomString.length == 15))
    }

    @Test
    fun `Return a random String with number of letters fixed`() = assertTrue(FakeData.fixedString(20).length == 20)

    @Test
    fun `Expects an exception, with the message, The number of characters cannot be less than 1`() {
        assertAll(
            Executable { assertThrows(IllegalArgumentException::class.java) { FakeData.fixedString(-1) } },
            Executable { assertThrows(IllegalArgumentException::class.java) { FakeData.charactersLorenIpsulumFixedNumber(-1) } }
        )
    }

    private fun numbersOnlyRegex(): Regex = "[0-9]+".toRegex()

    private fun lowercaseOnlyRegex(): Regex = "[a-z]+".toRegex()

    private fun lowercaseAndNumbersRegex(): Regex = "[a-z0-9]+".toRegex()

    private fun lettersAccentRegex(): Regex = "[A-Za-záàâãéêíóôõöúçñÁÀÂÃÉÍÇ ]+".toRegex()

    private fun lettersAndNumbersRegex(): Regex = "[A-Za-z0-9]+".toRegex()
}
