package pl.mjedynak.idea.plugins.notation.converter;

public interface NotationConverter {

    String convertToUnderscoreUpperCase(String input);

    String convertToCamelCase(String input);
}
