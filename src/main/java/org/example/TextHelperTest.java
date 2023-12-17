package org.example;

import org.example.controller.TextHelper;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TextHelperTest {
    @Test
    public void isRussianCharsPresent_NoRussianChars_ReturnFalse() {
        String text = "йцукенгшщзхфівапролджєячсмитьбюї";

        boolean actual = TextHelper.isRussianCharsPresent(text);

        assertFalse(actual);
    }

    @Test(expected = IllegalArgumentException.class)
    public void isRussianCharsPresent_NullInputText_ThrowIllegalArgumentException() {
        TextHelper.isRussianCharsPresent(null);
    }

    @Test
    public void isRussianCharsPresent_HaveRussianChars_ReturnTrue() {
        String text = "йцукенгшщзхъфыівапролджэєячсмитьбюї";

        boolean actual = TextHelper.isRussianCharsPresent(text);

        assertTrue(actual);
    }
}
