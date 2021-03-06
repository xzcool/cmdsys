package com.github.idkp.cmdsys.syntax.parameter;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public enum NumberParameterParser implements ParameterParser {
    INSTANCE;

    private static NumberFormat BACKING_PARSER = NumberFormat.getInstance(Locale.ENGLISH);

    @Override
    public Object parse(String parameter, Class<?> type) throws ParameterParsingException {
        Number result;

        try {
            result = BACKING_PARSER.parse(parameter);
        } catch (ParseException e) {
            throw new ParameterParsingException("Unable to parse \"" + parameter + "\" as a number:", e);
        }

        if (type == Integer.TYPE || type == Integer.class) {
            return result.intValue();
        } else if (type == Byte.TYPE || type == Byte.class) {
            return result.byteValue();
        } else if (type == Short.TYPE || type == Short.class) {
            return result.shortValue();
        } else if (type == Double.TYPE || type == Double.class) {
            return result.doubleValue();
        } else if (type == Float.TYPE || type == Float.class) {
            return result.floatValue();
        } else if (type == Long.TYPE || type == Long.class) {
            return result.longValue();
        }

        return result;
    }

    @Override
    public boolean canParse(Class<?> type) {
        return type == Integer.TYPE || type == Integer.class ||
                type == Double.TYPE || type == Double.class ||
                type == Float.TYPE || type == Float.class ||
                type == Long.TYPE || type == Long.class ||
                type == Byte.TYPE || type == Byte.class ||
                type == Short.TYPE || type == Short.class;
    }
}
