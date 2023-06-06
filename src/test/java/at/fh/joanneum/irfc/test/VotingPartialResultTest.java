package at.fh.joanneum.irfc.test;
import at.fh.joanneum.irfc.model.event.VotingPartialResultDTO;
import at.fh.joanneum.irfc.service.VotingPartialResultService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import io.quarkus.test.junit.QuarkusTest;

import javax.inject.Inject;
import static org.junit.jupiter.api.Assertions.assertSame;

@QuarkusTest

public class VotingPartialResultTest {


    @Inject
    VotingPartialResultService votingPartialResultService;


    @BeforeEach
    public void setup() {
        votingPartialResultService = new VotingPartialResultService();
    }


    @Test
    // Test post functionality of service
    public void TestPost() {
        VotingPartialResultDTO dto = new VotingPartialResultDTO();
        dto.setPercentage(50.0);
        dto.setEventName("MyExample VotPartlResult");

        VotingPartialResultDTO resultDto = votingPartialResultService.create(dto);

        assertSame(dto, resultDto);


//        Assert.assertTrue(EqualsBuilder.reflectionEquals(dto,votingPartialResultService.create(dto)));

    }











}
