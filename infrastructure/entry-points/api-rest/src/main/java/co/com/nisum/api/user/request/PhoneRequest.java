package co.com.nisum.api.user.request;

import co.com.nisum.model.user.phone.Phone;
import lombok.Data;

@Data
public class PhoneRequest {
    private String number;
    private String cityCode;
    private String countryCode;

    public Phone toModel() {
        return Phone.builder()
                .number(number)
                .cityCode(cityCode)
                .countryCode(countryCode)
                .build();
    }
}
