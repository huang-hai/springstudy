package fun.huanghai.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class String2DateConverter implements Converter<String,Date> {

    @Nullable
    @Override
    public Date convert(String s) {
        try {
            return new SimpleDateFormat().parse(s);
        } catch (Exception e) {
            return null;
        }
    }
}
