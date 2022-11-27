package co.com.nisum.jpa.user.phone;

import co.com.nisum.jpa.user.UserData;
import co.com.nisum.model.user.phone.Phone;
import org.springframework.stereotype.Component;

@Component
public class PhoneMapper {

    public Phone toEntity(PhoneData phoneData) {
        return Phone.builder()
                .id(phoneData.getId())
                .number(phoneData.getNumber())
                .cityCode(phoneData.getCityCode())
                .countryCode(phoneData.getCountryCode())
                .build();
    }

    public PhoneData toData(UserData userData, Phone phone) {
        PhoneData phoneData = new PhoneData();
        phoneData.setId(phone.getId());
        phoneData.setNumber(phone.getNumber());
        phoneData.setCityCode(phone.getCityCode());
        phoneData.setCountryCode(phone.getCountryCode());
        phoneData.setUser(userData);
        return phoneData;
    }
}
