package co.com.nisum.model.validation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.regex.Pattern;

@Data
@AllArgsConstructor(staticName = "builder")
public class ValidationResult {
    private boolean isValid;
    private String message;

    public static boolean patternMatches(String text, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(text)
                .matches();
    }
}
