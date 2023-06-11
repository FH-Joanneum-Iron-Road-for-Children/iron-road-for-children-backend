package at.fh.joanneum.irfc.test;
import at.fh.joanneum.irfc.model.event.VotingPartialResultDTO;
import at.fh.joanneum.irfc.service.VotingPartialResultService;
import org.junit.jupiter.api.Test;
import io.quarkus.test.junit.QuarkusTest;
import javax.inject.Inject;
import java.lang.reflect.Field;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author https://github.com/GoldNova
 **/
@QuarkusTest
public class VotingPartialResultTest {


    @Inject
    VotingPartialResultService votingPartialResultService;

// Not needed because somehow an instance already gets created. for some reason making own instance bricks the test
//    @BeforeEach
//    public void setup() {
//        votingPartialResultService = new VotingPartialResultService();
//    }



    @Test
    // Test post functionality of service
    public void TestPost () throws IllegalAccessException {

        VotingPartialResultDTO dto = new VotingPartialResultDTO();
        dto.setPercentage(50.0);
        dto.setEventName("MyExample VotPartlResult");

        VotingPartialResultDTO resultDto = votingPartialResultService.create(dto);

//        Assert.assertTrue(EqualsBuilder.reflectionEquals(dto,resultDto));
        Field[] fields = VotingPartialResultDTO.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            assertEquals(field.get(dto), field.get(resultDto));
        }

    }

}
