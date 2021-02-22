package unit

import br.dev.henriquealmeida.util.FakeData
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class FakerDataTest {

    @Test
    fun `Username without dot`() = assertFalse(FakeData.userName().contains("."))

    @Test
    fun userNameWithPoint() = assertTrue(FakeData.userName(true).contains("."))

    @Test
    fun emailValid() = assertTrue(FakeData.email().contains("@email.com"))

    @Test
    fun emailInvalid() = assertFalse(FakeData.email(false).contains("@email.com"))

}