package com.trungdq.graphql.datasource.fake;

import com.trungdq.graphql.generated.types.Address;
import com.trungdq.graphql.generated.types.Author;
import com.trungdq.graphql.generated.types.MobileApp;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Configuration
public class FakeMobileAppDataSource {

    public static final List<MobileApp> MOBILE_APP_LIST = new ArrayList<>();

    @Autowired
    private Faker faker;

    @PostConstruct
    private void postConstruct() throws MalformedURLException {
        for (int i = 0; i < 20; i++) {
            var addresses = new ArrayList<Address>();
            var author = Author.newBuilder().addresses(addresses)
                    .name(faker.app().author())
                    .originCountry(faker.country().name())
                    .build();
            var mobileApp = MobileApp.newBuilder()
                    .name(faker.app().name())
                    .author(author).version(faker.app().version())
                    .platform(randomMobileAppPlatform())
//                    .appId(UUID.randomUUID().toString())
//                    .releaseDate(LocalDate.now().minusDays(faker.random().nextInt(365)))
//                    .downloaded(faker.number().numberBetween(1, 1_500_000))
//                    .homepage(new URL("https://" + faker.internet().url()))
//                    .category(MobileAppCategory.values()[
//                            faker.random().nextInt(MobileAppCategory.values().length)]
//                    )
                    .build();

            for (int j = 0; j < ThreadLocalRandom.current().nextInt(1, 3); j++) {
                var address = Address.newBuilder()
                        .country(faker.address().country())
                        .city(faker.address().cityName())
                        .country(faker.address().country())
                        .street(faker.address().streetAddress())
                        .zipCode(faker.address().zipCode())
                        .build();

                addresses.add(address);
            }

            MOBILE_APP_LIST.add(mobileApp);
        }
    }

    private List<String> randomMobileAppPlatform() {
        switch (ThreadLocalRandom.current().nextInt(10) % 3) {
            case 0:
                return List.of("android");
            case 1:
                return List.of("ios");
            default:
                return List.of("ios", "android");
        }
    }

}
