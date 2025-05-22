package com.codedecode.restaurantlisting;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;

@ExtendWith(MockitoExtension.class)
class RestaurantlistingApplicationTests {

    @Test
    void mainTest() {
        try (MockedStatic<SpringApplication> mockedStatic =
                     Mockito.mockStatic(SpringApplication.class)) {

            mockedStatic
                    .when(() -> SpringApplication.run(RestaurantlistingApplication.class, eq(Mockito.any())))
                    .thenReturn(null);

            RestaurantlistingApplication.main(new String[] {});
            RestaurantlistingApplication application = new RestaurantlistingApplication();
            assertNotNull(application);
        }
    }

}
