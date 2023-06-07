package com.meetingplanner.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.meetingplanner.util.Constants;
import com.meetingplanner.util.TestUtil;
import com.meetingplanner.requests.ReservationRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@WebMvcTest(MeetingPlannerController.class)
public class MeetingPlannerControllerTests {

    @MockBean
    MeetingPlannerController meetingPlannerController;

    @Autowired
    MockMvc mockMvc;

    @Test
    public void reserver() throws Exception {
        ReservationRequest rr1 = new ReservationRequest("Reunion 1",
                "10h-11h",
                8,
                "VC");
        ReservationRequest rr2 = new ReservationRequest("Reunion 2",
                "10h-11h",
                12,
                "VC");

        List<ReservationRequest> rrs = Arrays.asList(rr1,rr2);

        Mockito.when(meetingPlannerController.reserver(rrs)).thenReturn(
                new ResponseEntity<>(Constants.SUCCESS, HttpStatus.OK));

        mockMvc.perform(post("/meetingPlanner/")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .accept(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(rrs)))
                        .andExpect(status().isOk())
                        .andReturn();

    }
}
