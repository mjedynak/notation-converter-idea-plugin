package pl.mjedynak.idea.plugins.notation.converter.impl;

import pl.mjedynak.idea.plugins.notation.converter.NotationConverter;

public class NotationConverterImpl implements NotationConverter {

    private static final String UNDERSCORE = "_";

    public String convertToUnderscoreUpperCase(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (currentCharacterIsLowerCase(character)) {
                result.append(Character.toUpperCase(character));
                if (currentCharacterIsNotLast(input, i) && nextCharacterIsUpperCase(input, i)) {
                    result.append(UNDERSCORE);
                }
            } else {
                result.append(character);
                if (currentCharacterIsNotLast(input, i) && nextCharacterIsUpperCase(input, i)) {
                    result.append(UNDERSCORE);
                }
            }
        }
        return result.toString();
    }

    private boolean currentCharacterIsNotLast(String input, int i) {
        return i < input.length() - 1;
    }

    private boolean nextCharacterIsUpperCase(String input, int i) {
        return Character.isUpperCase(input.charAt(i + 1));
    }

    private boolean currentCharacterIsLowerCase(char character) {
        return Character.isLowerCase(character);
    }


    public String convertToCamelCase(String input) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char character = input.charAt(i);
            if (currentCharacterIsNotFirst(i) && previousCharacterIsUnderscore(input, i)) {
                result.append(input.charAt(i));
            } else if (currentCharacterIsNotUnderscore(character)) {
                result.append(Character.toLowerCase(character));
            }
        }
        return result.toString();
    }

    private boolean currentCharacterIsNotUnderscore(char character) {
        return character != UNDERSCORE.charAt(0);
    }

    private boolean currentCharacterIsNotFirst(int i) {
        return i > 0;
    }

    private boolean previousCharacterIsUnderscore(String inputName, int i) {
        return UNDERSCORE.charAt(0) == inputName.charAt(i - 1);
    }

}