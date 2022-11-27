package co.com.nisum.model.user.phone;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Phone {
    private Long id;
    private String number;
    private String cityCode;
    private String countryCode;
}
