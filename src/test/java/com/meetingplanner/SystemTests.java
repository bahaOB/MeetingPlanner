package com.meetingplanner;

import com.meetingplanner.requests.ReservationRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.Arrays;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SystemTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void reserver(){
        String url = "http://localhost:"+port+"/meetingPlanner";
        ReservationRequest rr1 = new ReservationRequest("Reunion 1",
                "10h-11h",
                8,
                "VC");
        ReservationRequest rr2 = new ReservationRequest("Reunion 2",
                "10h-11h",
                12,
                "VC");
        List<ReservationRequest> rrs = Arrays.asList(rr1,rr2);

        Assertions.assertThat(restTemplate.postForEntity(url, rrs, String.class).getStatusCodeValue()).isEqualTo(200);
    }
}
