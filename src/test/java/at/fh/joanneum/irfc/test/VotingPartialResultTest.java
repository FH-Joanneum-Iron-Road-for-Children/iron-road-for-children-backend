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














}
