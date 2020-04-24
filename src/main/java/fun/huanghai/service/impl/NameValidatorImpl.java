package fun.huanghai.service.impl;

import fun.huanghai.service.NameValidator;
import org.springframework.util.StringUtils;

public class NameValidatorImpl implements NameValidator {

    @Override
    public boolean validate(String name) {
        return StringUtils.isEmpty(name);
    }
}
