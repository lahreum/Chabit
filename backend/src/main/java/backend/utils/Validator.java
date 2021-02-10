package backend.utils;

import org.springframework.stereotype.Component;

@Component
public class Validator {
    public String phoneValidator(String phone) {
        return phone.replaceAll("-", "").replaceAll("\\(","").replaceAll("\\)", "");
    }
}
