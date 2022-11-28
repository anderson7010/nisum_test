package co.com.nisum.api.user.request;

import co.com.nisum.model.user.phone.Phone;
import lombok.Data;

import javax.validation.constraints.Pattern;

@Data
public class PhoneRequest {
    @Pattern(regexp="^[0-9]{1,12}$", message = "It must be a valid number with max length of 12 characters.")
    private String number;
    @Pattern(regexp="^[0-9]{1,3}$", message = "It must be a valid number with max length of 3 characters.")
    private String cityCode;
    @Pattern(regexp="^[0-9]{1,3}$", message = "It must be a valid number with max length of 3 characters.")
    private String countryCode;

    public Phone toModel() {
        return Phone.builder()
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build();
    }
}
