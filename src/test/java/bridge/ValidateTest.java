package bridge;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static bridge.message.ExceptionMessage.OUT_OF_RANGE_EXCEPTION;
import static bridge.message.ExceptionMessage.TYPE_EXCEPTION;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ValidateTest {

    @DisplayName("숫자 이외의 문자가 입력되면 예외가 발생")
    @ParameterizedTest
    @ValueSource(strings = {"10a", "20.", " 5", "a"})
    void digitTypeValidate(String input) {
        assertThatThrownBy(() ->
                Validate.digitTypeValidate(input)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(TYPE_EXCEPTION);
    }

    @DisplayName("입력받은 숫자가 3 이상 20 이하의 숫자가 아니라면 예외가 발생")
    @ParameterizedTest
    @ValueSource(strings = {"0", "-1", "100", "2"})
    void numberRangeValidate(int number) {
        assertThatThrownBy(() ->
                Validate.numberRangeValidate(number)
        ).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(OUT_OF_RANGE_EXCEPTION);
    }

}