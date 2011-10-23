package pl.mjedynak.idea.plugins.notation.converter.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pl.mjedynak.idea.plugins.notation.converter.NotationConverter;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

@RunWith(value = Parameterized.class)
public class NotationConverterImplTest {

    private NotationConverter notationConverter = new NotationConverterImpl();
    private String camelToConstantInput;
    private String camelToConstantOutput;
    private String constantToCamelInput;
    private String constantToCamelOutput;

    public NotationConverterImplTest(String input, String output) {
        camelToConstantInput = input;
        camelToConstantOutput = output;
        constantToCamelInput = output;
        constantToCamelOutput = input;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][]{{"short", "SHORT"}, {"mediumString", "MEDIUM_STRING"}, {"veryLongString", "VERY_LONG_STRING"},
                {"short1", "SHORT1"}, {"1short", "1SHORT"}, {"short1string", "SHORT1STRING"}, {"longOBigString", "LONG_O_BIG_STRING"}};
        return Arrays.asList(data);
    }

    @Test
    public void shouldChangeNameToUnderscoreUpperCase() {
        // when
        String result = notationConverter.convertToUnderscoreUpperCase(camelToConstantInput);

        // then
        assertThat(result, is(camelToConstantOutput));
    }

    @Test
    public void shouldChangeNameToCamelCase() {
        // when
        String result = notationConverter.convertToCamelCase(constantToCamelInput);

        // then
        assertThat(result, is(constantToCamelOutput));
    }
}
