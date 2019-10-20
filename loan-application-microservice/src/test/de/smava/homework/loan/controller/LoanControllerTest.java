package de.salil.homework.loan.controller;

import de.salil.homework.loan.service.LoanService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.nio.charset.Charset;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Sample test class with single test get method added to show use of mocks.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(LoanController.class)
public class LoanControllerTest {

    private MediaType defaultContentType = new MediaType(
            APPLICATION_JSON.getType(),
            APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    private MockMvc mockMvc;

    @MockBean
    private LoanService loanService;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new LoanController(loanService)).build();
    }

    @Test
    public void whenGettingLoansByUserId_UserIdNotGiven() throws Exception {
        this.mockMvc.perform(get("/api/loanapplications").accept(defaultContentType).contentType(defaultContentType))
                .andExpect(status().isBadRequest());
    }
}
